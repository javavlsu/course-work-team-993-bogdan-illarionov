package com.company.storage;

import com.company.abstractions.storage.IRepository;
import com.company.storage.jpa.IGameOutcomesJpaRepository;
import com.company.storage.models.StorageGameOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameOutcomesRepository implements IRepository<StorageGameOutcome, Long> {
    @Autowired
    public IGameOutcomesJpaRepository gameOutcomesRepository;

    @Override
    public List<StorageGameOutcome> getAll() {
        return gameOutcomesRepository.findAll();
    }

    @Override
    public Optional<StorageGameOutcome> getById(Long id) {
        return gameOutcomesRepository.findById(id);
    }

    @Override
    public void add(StorageGameOutcome storageGameOutcome) {
        gameOutcomesRepository.saveAndFlush(storageGameOutcome);
    }

    @Override
    public void remove(StorageGameOutcome storageGameOutcome) {
        gameOutcomesRepository.deleteById(storageGameOutcome.getId());
    }

    @Override
    public void update(StorageGameOutcome storageGameOutcome) {
        gameOutcomesRepository.saveAndFlush(storageGameOutcome);
    }
}
