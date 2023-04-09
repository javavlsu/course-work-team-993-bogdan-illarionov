package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.storage.jpa.IUserRepository;
import com.company.storage.models.StorageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepository implements IRepository<StorageUser, Long> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<StorageUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public StorageUser getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void add(StorageUser storageUser) {
        userRepository.saveAndFlush(storageUser);
    }

    @Override
    public void remove(StorageUser storageUser) {
        userRepository.delete(storageUser);
    }

    @Override
    public void update(StorageUser storageUser) {
        userRepository.saveAndFlush(storageUser);
    }
}
