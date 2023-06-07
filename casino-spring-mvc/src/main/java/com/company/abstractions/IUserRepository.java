package com.company.abstractions;

import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.storage.models.StorageUser;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IRepository<User, Long> {

    Optional<User> getByLogin(String login);

    List<Role> getRoles();

}
