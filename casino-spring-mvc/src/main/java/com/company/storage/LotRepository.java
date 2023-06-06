package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.jpa.ILotJpaRepository;
import com.company.storage.models.StorageLot;
import com.company.storage.models.bonus.StorageBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LotRepository implements IRepository<StorageLot, Long> {

    @Autowired
    private ILotJpaRepository lotRepository;

    @Override
    public Iterable<StorageLot> getAll() {
        return lotRepository.findAll();
    }

    @Override
    public Optional<StorageLot> getById(Long id) {
        return lotRepository.findById(id);
    }

    @Override
    public void add(StorageLot lot) {
        lotRepository.saveAndFlush(lot);
    }

    @Override
    public void remove(StorageLot lot) {
        lotRepository.deleteById(lot.getId());
    }

    @Override
    public void update(StorageLot lot) {
        lotRepository.saveAndFlush(lot);
    }
}
