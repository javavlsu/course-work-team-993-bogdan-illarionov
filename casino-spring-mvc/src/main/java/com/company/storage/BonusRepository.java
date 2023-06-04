package com.company.storage;

import com.company.abstractions.IBonusRepository;
import com.company.storage.jpa.bonus.IBonusConfigJpaRepository;
import com.company.storage.jpa.bonus.IBonusJpaRepository;
import com.company.storage.jpa.bonus.IUserBonusJpaRepository;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BonusRepository implements IBonusRepository {

    @Autowired
    private IBonusJpaRepository bonusRepository;

    @Autowired
    private IUserBonusJpaRepository userBonusRepository;

    @Autowired
    private IBonusConfigJpaRepository bonusConfigRepository;

    @Override
    public Iterable<StorageBonus> getAll() {
        return bonusRepository.findAll();
    }

    @Override
    public StorageBonus getById(Long id) {
        return null;
    }

    @Override
    public void add(StorageBonus storageBonus) {
        bonusRepository.saveAndFlush(storageBonus);
    }

    @Override
    public void remove(StorageBonus storageBonus) {
        bonusRepository.delete(storageBonus);
    }

    @Override
    public void update(StorageBonus storageBonus) {
        bonusRepository.saveAndFlush(storageBonus);
    }

    @Override
    public Set<StorageUserBonus> getUsersBonuses(Long userId) {
        return userBonusRepository.getUsersBonuses(userId);
    }

    @Override
    @Transactional
    public void updateUserBonusConfig(StorageUserBonus storageUserBonus) {
        for (var param : storageUserBonus.getConfig()) {
            userBonusRepository.updateConfig(param.getMapId(), param.getName(), param.getValue());
        }
    }
}
