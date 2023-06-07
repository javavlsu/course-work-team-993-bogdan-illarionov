package com.company.abstractions;

import com.company.models.account.User;

import java.math.BigDecimal;

public interface IUserService {
    void registerUser(User user);

    void updateUser(User user);

    void changeUserBalance(String userLogin, BigDecimal balanceDelta);

    void updateAuthorizeUserData(User user);


}
