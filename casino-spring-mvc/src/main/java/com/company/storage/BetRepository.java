package com.company.storage;

import com.company.abstractions.storage.IBetRepository;
import com.company.storage.jpa.IBetJpaRepository;
import com.company.storage.models.StorageBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BetRepository implements IBetRepository {
    @Autowired
    private IBetJpaRepository betRepository;

    @Override
    public List<StorageBet> getAll() {
        return betRepository.findAll();
    }

    @Override
    public Optional<StorageBet> getById(Long id) {
        return betRepository.findById(id);
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
