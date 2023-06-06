package com.company.storage;

import com.company.abstractions.IRepository;
import com.company.abstractions.IUserRepository;
import com.company.models.account.Role;
import com.company.storage.jpa.IRoleJpaRepository;
import com.company.storage.jpa.IUserJpaRepository;
import com.company.storage.models.StorageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepository implements IUserRepository {
    @Autowired
    private IUserJpaRepository userRepository;
    @Autowired
    private IRoleJpaRepository roleRepository;

    @Override
    public Iterable<StorageUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<StorageUser> getById(Long id) {
        return userRepository.findById(id);
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

    @Override
    public Optional<StorageUser> getByLogin(String login) {
        return Optional.ofNullable(userRepository.findByUserLogin(login));
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
