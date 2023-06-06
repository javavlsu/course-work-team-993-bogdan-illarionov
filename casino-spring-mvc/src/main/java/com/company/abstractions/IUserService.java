package com.company.abstractions;

import com.company.models.account.Role;
import com.company.models.account.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByLogin(String login);

    Optional<User> getById(Long id);

    List<Role> getRoles();

    List<User> getUsers();

    void RegisterUser(User user);

    void UpdateUser(User user);

    void ChangeUserBalance(String userLogin, BigDecimal balanceDelta);

    void UpdateAuthorizeUserData(User user);
}
