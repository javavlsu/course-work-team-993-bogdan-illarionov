package com.company.storage;

import com.company.abstractions.storage.IUserRepository;
import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.storage.jpa.IRoleJpaRepository;
import com.company.storage.jpa.IUserJpaRepository;
import com.company.storage.models.StorageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository implements IUserRepository {
    @Autowired
    private IUserJpaRepository userRepository;
    @Autowired
    private IRoleJpaRepository roleRepository;

    private Optional<User> optionalStorageToOptionalModel(StorageUser optionalStorage) {
        User user = null;

        if (optionalStorage != null) {
            user = StorageUser.toModel(optionalStorage);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {

        var list = new ArrayList<User>();

        userRepository.findAll().stream().forEach(x -> {
            var user = StorageUser.toModel(x);

            if (user == null) {
                return;
            }

            list.add(user);
        });

        return list;
    }

    @Override
    public Optional<User> getById(Long id) {
        var optional = userRepository.findById(id);

        StorageUser findUser = null;

        if (optional.isPresent()) {
            findUser = optional.get();
        }

        return optionalStorageToOptionalModel(findUser);
    }

    @Override
    public void add(User user) {

        if (user == null) {
            return;
        }
        userRepository.saveAndFlush(User.ToStorage(user));
    }

    @Override
    public void remove(User user) {
        userRepository.delete(User.ToStorage(user));
    }

    @Override
    public void update(User user) {
        userRepository.saveAndFlush(User.ToStorage(user));
    }

    @Override
    public Optional<User> getByLogin(String login) {
        var findUser = userRepository.findByUserLogin(login);

        return optionalStorageToOptionalModel(findUser);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


}
