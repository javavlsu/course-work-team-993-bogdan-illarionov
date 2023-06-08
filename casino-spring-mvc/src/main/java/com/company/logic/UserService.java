package com.company.logic;

import com.company.abstractions.storage.IUserRepository;
import com.company.abstractions.IUserService;
import com.company.models.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService implements IUserService {

    public static final String IS_ENABLE_ROLE_NAME = "Enabled";
    public static final String PLAYER_ROLE_NAME = "Player";

    @Autowired
    public IUserRepository userRepository;

    @Override
    public void registerUser(User user) {
        var roles = userRepository.getRoles();

        var enableRole = roles.stream().filter(x -> x.getName().equals(IS_ENABLE_ROLE_NAME)).findFirst().get();
        var playerRole = roles.stream().filter(x -> x.getName().equals(PLAYER_ROLE_NAME)).findFirst().get();

        user.getRoles().add(enableRole);
        user.getRoles().add(playerRole);

        userRepository.add(user);
    }

    @Override
    public void updateUser(User user) {
        var optionalUser = userRepository.getByLogin(user.getLogin());

        if (optionalUser.isEmpty()) {
            return;
        }

        optionalUser.get().setEmail(user.getEmail());
        optionalUser.get().setPassword(user.getPassword());
        optionalUser.get().updateRoles(user.getRoles());

        userRepository.update(optionalUser.get());
    }

    @Override
    public void changeUserBalance(String userLogin, BigDecimal balanceDelta) {
        var storage = userRepository.getByLogin(userLogin);

        if (storage.isEmpty()) {
            return;
        }

        var storageUser = storage.get();

        storageUser.setBalance(storageUser.getBalance().add(balanceDelta));

        userRepository.update(storageUser);
    }

    @Override
    public void updateAuthorizeUserData(User user) {

        //Authentication newAuthentication = new UsernamePasswordAuthenticationToken(newUser, newUser.getPassword(), newUser.getAuthorities());

        ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setBalance(user.getBalance());

    }
}