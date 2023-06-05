package com.company.logic;

import com.company.abstractions.IBonusRepository;
import com.company.abstractions.IBonusService;
import com.company.models.account.User;
import com.company.storage.models.StorageUser;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import com.company.storage.models.bonus.StorageUserBonusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BonusService implements IBonusService {

    @Autowired
    private IBonusRepository repository;

    @Override
    public void syncBonuses(User user) {
        var userBonuses = getBonusesForUser(user);



        for (var userBonus : userBonuses) {

            var offBonus = false;

            var bonus = getById(userBonus.getBonusId()).stream().filter(x -> x.getId().equals(userBonus.getBonusId())).findFirst();

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
    public List<StorageBonus> getBonuses() {

        var bonuses = repository.getAll();

        var list = new ArrayList<StorageBonus>();

        bonuses.forEach(list::add);

        return list;
    }

    @Override
    public Optional<StorageBonus> getById(Long id) {
        return repository.getById(id);
    }

    //todo транзакции мб
    @Override
    public void createBonus(StorageBonus bonus) {
        repository.add(bonus);
    }

    @Override
    public void updateBonus(StorageBonus bonus) {
        repository.update(bonus);
    }

    @Override
    public List<StorageUserBonus> getBonusesForUser(User user) {

        return repository
                .getUsersBonuses(user.getId())
                .stream()
                .toList();
    }

    @Override
    public void addBonusToUser(StorageBonus bonus, User user) {
        repository.addBonusToUser(bonus, user);
    }

    @Override
    public void changeBonusOfUser(StorageUserBonus userBonus) {
        repository.updateUserBonusConfig(userBonus);
    }
}
