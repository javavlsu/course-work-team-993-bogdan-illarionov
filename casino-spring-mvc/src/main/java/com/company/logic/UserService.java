package com.company.logic;

import com.company.abstractions.IUserRepository;
import com.company.abstractions.IUserService;
import com.company.models.account.User;
import com.company.storage.jpa.IRoleJpaRepository;
import com.company.storage.jpa.IUserJpaRepository;
import com.company.models.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.company.storage.models.StorageUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final String IS_ENABLE_ROLE_NAME = "Enabled";
    private static final String PLAYER_ROLE_NAME = "Player";

    @Autowired
    public IUserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {

        var user = userRepository.getByLogin(login);

        return Optional.ofNullable(StorageUser.toModel(user.orElse(null)));
    }

    @Override
    public List<Role> getRoles() {
        return userRepository.getRoles();
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();

        userRepository
                .getAll()
                .forEach(x -> list.add(StorageUser.toModel(x)));

        return list;
    }

    @Override
    public void RegisterUser(User user) {
        var roles = getRoles();

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

        userRepository.add(storage);
    }

    @Override
    public void UpdateUser(User user) {
        var storage = userRepository.getByLogin(user.getLogin());

        if (storage.isEmpty()) {
            return;
        }

        var storageUser = storage.get();

        storageUser.setEmail(user.getEmail());
        storageUser.setPassword(user.getPassword());
        storageUser.setRoles(user.getRoles());

        userRepository.update(storageUser);
    }

    @Override
    public void ChangeUserBalance(String userLogin, BigDecimal balanceDelta) {
        var storage = userRepository.getByLogin(userLogin);

        if (storage.isEmpty()) {
            return;
        }

        var storageUser = storage.get();

        storageUser.setBalance(storageUser.getBalance().add(balanceDelta));

        userRepository.update(storageUser);
    }

    @Override
    public void UpdateAuthorizeUserData(User user) {

        //Authentication newAuthentication = new UsernamePasswordAuthenticationToken(newUser, newUser.getPassword(), newUser.getAuthorities());

        ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setBalance(user.getBalance());

    }
}