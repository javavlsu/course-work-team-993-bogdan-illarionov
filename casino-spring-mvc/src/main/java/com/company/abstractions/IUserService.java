package com.company.abstractions;

import com.company.models.account.Role;
import com.company.models.account.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByLogin(String login);

    List<Role> getRoles();

    List<User> getUsers();

    void RegisterUser(User user);

    void UpdateUser(User user);
}
