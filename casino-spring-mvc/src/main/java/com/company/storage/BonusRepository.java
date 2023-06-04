package com.company.storage;

import com.company.abstractions.IBonusRepository;
import com.company.storage.jpa.IBonusJpaRepository;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BonusRepository implements IBonusRepository {

    @Autowired
    private IBonusJpaRepository jpaRepository;

    @Override
    public Iterable<StorageBonus> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public StorageBonus getById(Long id) {
        return null;
    }

    @Override
    public void add(StorageBonus storageBonus) {
        jpaRepository.saveAndFlush(storageBonus);
    }

    @Override
    public void remove(StorageBonus storageBonus) {
        jpaRepository.delete(storageBonus);
    }

    @Override
    public void update(StorageBonus storageBonus) {
        jpaRepository.saveAndFlush(storageBonus);
    }

    @Override
    public Set<StorageUserBonus> getUsersBonuses(Long userId) {
        return jpaRepository.getUsersBonuses(userId);
    }
}
