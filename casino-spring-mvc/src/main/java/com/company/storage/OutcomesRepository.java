package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.models.StorageGameOutcome;
import com.company.storage.models.StorageOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomesRepository implements IRepository<StorageOutcome, Long> {
    @Autowired
    public IOutcomesRepository outcomesRepository;

    @Override
    public Iterable<StorageOutcome> getAll() {
        return outcomesRepository.findAll();
    }

    @Override
    public StorageOutcome getById(Long id) {
        return outcomesRepository.findById(id).get();
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
