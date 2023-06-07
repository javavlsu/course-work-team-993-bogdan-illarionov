package com.company.logic;

import com.company.abstractions.storage.IBonusRepository;
import com.company.abstractions.IBonusService;
import com.company.models.BonusResult;
import com.company.models.account.User;
import com.company.models.enums.BonusExpireType;
import com.company.models.enums.BonusTriggerAction;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import com.company.storage.models.bonus.StorageUserBonusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BonusService implements IBonusService {

    @Autowired
    private IBonusRepository bonusRepository;

    @Override
    public void syncBonuses(User user) {
        var userBonuses = getBonusesForUser(user);

        for (var userBonus : userBonuses) {

            var offBonus = false;

            var bonus = bonusRepository.getById(userBonus.getBonusId()).stream().filter(x -> x.getId().equals(userBonus.getBonusId())).findFirst();

            if (bonus.isEmpty() || !bonus.get().getIsEnabled()) {
                continue;
            }

            for (var params : userBonus.getConfig()) {
                if (params.getName().equals(StorageUserBonusConfig.IS_ENABLED_PARAM_NAME) && params.getValue().equals("false")) {
                    break;
                }

                if (params.getName().equals(StorageUserBonusConfig.COUNT_PARAM_NAME) && params.getValue().equals("0")) {
                    offBonus = true;
                }
                else if (params.getName().equals(StorageUserBonusConfig.TERM_PARAM_NAME)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSX");
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(params.getValue(), formatter);

                    if (zonedDateTime.compareTo(ZonedDateTime.now()) <= 0) {
                        offBonus = true;
                    }
                }
            }

            if (offBonus) {
                userBonus.getConfig().stream().filter(x -> x.getName().equals(StorageUserBonusConfig.IS_ENABLED_PARAM_NAME)).findFirst().get().setValue("false");

                changeBonusOfUser(userBonus);
            }
        }

    }

    @Override
    public List<StorageUserBonus> getBonusesForUser(User user) {

        return bonusRepository
                .getUsersBonuses(user.getId())
                .stream()
                .toList();
    }

    @Override
    public void addBonusToUser(StorageBonus bonus, User user) {
        var newBonus = new StorageUserBonus();
        newBonus.setBonusId(bonus.getId());
        newBonus.setUserId(user.getId());

        bonusRepository.addUserBonus(newBonus);
    }

    @Override
    public void changeBonusOfUser(StorageUserBonus userBonus) {
        bonusRepository.updateUserBonusConfig(userBonus);
    }

    @Override
    public Optional<BonusResult> triggerBonuses(User user, BonusTriggerAction action) {

        syncBonuses(user);

        var result = new BonusResult();

        for (var userBonus : getBonusesForUser(user)) {

            if (userBonus.getConfig()
                    .stream()
                    .filter(x -> x.getName().equals(StorageUserBonusConfig.IS_ENABLED_PARAM_NAME))
                    .findAny().get().getValue().equals("false")) {
                continue;
            }

            var optional = bonusRepository.getById(userBonus.getBonusId());

            if (optional.isEmpty() || !optional.get().getIsEnabled()) {
                continue;
            }

            var bonus = optional.get();

            if (!bonus.getTriggerActionId().equals(action.getValue())) {
                continue;
            }

            if (Objects.nonNull(bonus.getConfig().getBonusKoef())) {
                result.setBonusKoef(result.getBonusKoef().multiply(bonus.getConfig().getBonusKoef()));
            }

            if (Objects.nonNull(bonus.getConfig().getLotsId()) && !bonus.getConfig().getLotsId().equals("")) {

                var set = new HashSet<Long>();

                Arrays.stream(bonus.getConfig().getLotsId().split(",")).forEach(x -> {
                    var l = Long.valueOf(x);

                    set.add(l);
                });

                for (var index : set) {
                    result.getLotsList().add(index);
                }
            }
        }

        if (result.haveResult()) {
            return Optional.of(result);
        }

        return Optional.empty();
    }

    @Override
    public void expireBonus(User user, BonusTriggerAction action) {
        for (var userBonus : getBonusesForUser(user)) {

            if (userBonus.getConfig()
                    .stream()
                    .filter(x -> x.getName().equals(StorageUserBonusConfig.IS_ENABLED_PARAM_NAME))
                    .findAny().get().getValue().equals("false")) {
                continue;
            }

            var optional = bonusRepository.getById(userBonus.getBonusId());

            if (optional.isEmpty() || !optional.get().getIsEnabled()) {
                continue;
            }

            var bonus = optional.get();

            if (!bonus.getTriggerActionId().equals(action.getValue()) ||
                    !bonus.getExpireTypeId().equals(BonusExpireType.Count.getValue())) {
                continue;
            }

            var countParam = userBonus.getConfig()
                    .stream()
                    .filter(x -> x.getName().equals(StorageUserBonusConfig.COUNT_PARAM_NAME))
                    .findAny();

            if (countParam.isEmpty()) {
                continue;
            }

            countParam.get().setValue(Integer.toString(Integer.parseInt(countParam.get().getValue()) - 1));

            changeBonusOfUser(userBonus);

        }
    }
}
