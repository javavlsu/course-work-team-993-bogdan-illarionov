package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.models.StorageGameOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameOutcomesRepository implements IRepository<StorageGameOutcome, Long> {
    @Autowired
    public IGameOutcomesRepository gameOutcomesRepository;

    @Override
    public Iterable<StorageGameOutcome> getAll() {
        return gameOutcomesRepository.findAll();
    }

    @Override
    public StorageGameOutcome getById(Long id) {
        return gameOutcomesRepository.findById(id).get();
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
