package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.jpa.ILotRepository;
import com.company.storage.models.StorageLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotRepository implements IRepository<StorageLot, Long> {

    @Autowired
    private ILotRepository lotRepository;

    @Override
    public Iterable<StorageLot> getAll() {
        return lotRepository.findAll();
    }

    @Override
    public StorageLot getById(Long id) {
        return lotRepository.findById(id).get();
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
