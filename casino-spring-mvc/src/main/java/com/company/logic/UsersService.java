package com.company.logic;

import com.company.abstractions.IUserService;
import com.company.models.account.User;
import com.company.storage.jpa.IRoleRepository;
import com.company.storage.jpa.IUserRepository;
import com.company.models.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.storage.models.StorageUser;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements IUserService {

    private static final String IS_ENABLE_ROLE_NAME = "Enabled";
    private static final String PLAYER_ROLE_NAME = "Player";

    @Autowired
    public IUserRepository userRepository;

    @Autowired
    public IRoleRepository roleRepository;

    @Override
    public Optional<User> findByLogin(String login) {

        var user = userRepository.findByUserLogin(login);

        return Optional.ofNullable(StorageUser.toModel(user));
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll().stream().map(x -> StorageUser.toModel(x)).toList();
    }

    @Override
    public void RegisterUser(User user) {
        var roles = getRoles().stream().toList();

        var enableRole = roles.stream().filter(x -> x.getName().equals(IS_ENABLE_ROLE_NAME)).findFirst().get();
        var playerRole = roles.stream().filter(x -> x.getName().equals(PLAYER_ROLE_NAME)).findFirst().get();

        user.getRoles().add(enableRole);
        user.getRoles().add(playerRole);

        var storage = new StorageUser();

        storage.setId(Long.reverse(0));
        storage.setLogin(user.getLogin());
        storage.setPassword(user.getPassword());
        storage.setEmail(user.getEmail());
        storage.setRoles(user.getRoles());

        userRepository.saveAndFlush(storage);
    }

    @Override
    public void UpdateUser(User user) {
        var storage = userRepository.findByUserLogin(user.getLogin());

        storage.setEmail(user.getEmail());
        storage.setPassword(user.getPassword());
        storage.setRoles(user.getRoles());

        userRepository.saveAndFlush(storage);
    }
}