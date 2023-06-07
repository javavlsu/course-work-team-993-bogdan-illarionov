package com.company.storage;

import com.company.abstractions.storage.IBonusRepository;
import com.company.storage.jpa.bonus.IBonusConfigJpaRepository;
import com.company.storage.jpa.bonus.IBonusJpaRepository;
import com.company.storage.jpa.bonus.IUserBonusJpaRepository;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BonusRepository implements IBonusRepository {

    @Autowired
    private IBonusJpaRepository bonusRepository;

    @Autowired
    private IUserBonusJpaRepository userBonusRepository;

    @Autowired
    private IBonusConfigJpaRepository bonusConfigRepository;

    @Override
    public List<StorageBonus> getAll() {
        return bonusRepository.findAll();
    }

    @Override
    public Optional<StorageBonus> getById(Long id) {
        return bonusRepository.findById(id);
    }

    @Override
    public void add(StorageBonus storageBonus) {
        var config = storageBonus.getConfig();
        storageBonus.setConfig(null);

        storageBonus = bonusRepository.saveAndFlush(storageBonus);

        storageBonus.setConfig(config);

        updateBonusConfig(storageBonus);
    }

    @Override
    public void remove(StorageBonus storageBonus) {
        bonusRepository.delete(storageBonus);
    }

    @Override
    public void update(StorageBonus storageBonus) {
        bonusRepository.saveAndFlush(storageBonus);
        bonusConfigRepository.saveAndFlush(storageBonus.getConfig());
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

    @Override
    public void addUserBonus(StorageUserBonus userBonus) {
        userBonusRepository.saveAndFlush(userBonus);
    }

    @Override
    public void updateBonusConfig(StorageBonus bonus) {
        var newConfig = bonus.getConfig();

        var optional = bonusConfigRepository.findById(bonus.getId());

        if (optional.isEmpty()) {
            return;
        }

        var oldConfig = optional.get();

        switch (bonus.getEnumTriggerAction()) {
            case BonusAdd -> oldConfig.setBonusKoef(newConfig.getBonusKoef());
            case LotWin -> {
                oldConfig.setBonusKoef(newConfig.getBonusKoef());
                oldConfig.setLotsId(newConfig.getLotsId());
            }
            case LotPlay -> oldConfig.setLotsId(newConfig.getLotsId());
        }

        switch (bonus.getEnumExpireType()) {
            case Count -> oldConfig.setTriggerCount(newConfig.getTriggerCount());
            case Term -> oldConfig.setToTerm(newConfig.getToTerm());
            case Unlimited -> {
            }
        }

        bonusConfigRepository.saveAndFlush(oldConfig);

    }
}
