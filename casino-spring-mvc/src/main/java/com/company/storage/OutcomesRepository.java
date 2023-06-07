package com.company.storage;

import com.company.abstractions.storage.IRepository;
import com.company.storage.jpa.IOutcomesJpaRepository;
import com.company.storage.models.StorageOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomesRepository implements IRepository<StorageOutcome, Long> {
    @Autowired
    public IOutcomesJpaRepository outcomesRepository;

    @Override
    public List<StorageOutcome> getAll() {
        return outcomesRepository.findAll();
    }

    @Override
    public Optional<StorageOutcome> getById(Long id) {
        return outcomesRepository.findById(id);
    }

    @Override
    public void add(StorageOutcome storageOutcome) {
        outcomesRepository.saveAndFlush(storageOutcome);
    }

    @Override
    public void remove(StorageOutcome storageOutcome) {
        outcomesRepository.deleteById(storageOutcome.getId());
    }

    @Override
    public void update(StorageOutcome storageOutcome) {
        outcomesRepository.saveAndFlush(storageOutcome);
    }
}
