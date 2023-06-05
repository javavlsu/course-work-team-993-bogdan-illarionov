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
import java.util.Optional;

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
