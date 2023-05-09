package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.jpa.IBetJpaRepository;
import com.company.storage.models.StorageBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class BetRepository implements com.company.abstractions.IBetRepository {
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

    @Transactional
    @Override
    public Set<StorageBet> GetBetsByUserId(Long userId) {
        return betRepository.findAllByUserId(userId);
    }
}
