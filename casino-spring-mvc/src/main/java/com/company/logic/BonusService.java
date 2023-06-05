package com.company.logic;

import com.company.abstractions.IBonusRepository;
import com.company.abstractions.IBonusService;
import com.company.models.account.User;
import com.company.storage.models.StorageUser;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BonusService implements IBonusService {

    @Autowired
    private IBonusRepository repository;

    @Override
    public List<StorageBonus> getBonuses() {

        var bonuses = repository.getAll();

        var list = new ArrayList<StorageBonus>();

        bonuses.forEach(list::add);

        return list;
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
    public List<StorageBonus> getBonusesForUser(User user) {

        var bonuses = repository.getUsersBonuses(user.getId());

        var list = new ArrayList<StorageBonus>();

        bonuses.forEach(x -> {
            var optionalBonus = repository.getById(x.getBonusId());

            optionalBonus.ifPresent(list::add);
        });

        return list;
    }

    @Override
    public List<StorageUserBonus> getDetailBonusesForUser(User user) {
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
