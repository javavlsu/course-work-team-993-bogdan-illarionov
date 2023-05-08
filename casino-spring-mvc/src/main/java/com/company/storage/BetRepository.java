package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.jpa.IBetJpaRepository;
import com.company.storage.models.StorageBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetRepository implements IRepository<StorageBet, Long> {
    @Autowired
    private IBetJpaRepository betRepository;

    @Override
    public Iterable<StorageBet> getAll() {
        return betRepository.findAll();
    }

    @Override
    public StorageBet getById(Long id) {
        return betRepository.findById(id).get();
    }

    @Override
    public void add(StorageBet storageBet) {
        betRepository.saveAndFlush(storageBet);
    }

    @Override
    public void remove(StorageBet storageBet) {
        betRepository.delete(storageBet);
    }

    @Override
    public void update(StorageBet storageBet) {
        betRepository.saveAndFlush(storageBet);
    }
}
