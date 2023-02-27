package com.company.storage;

import com.company.models.account.User;

public interface IUserRepository extends IRepository<User> {

    public boolean isCanLogIn(User user);

    public void changeCredentials(User user, String login, String password, String email);

}
