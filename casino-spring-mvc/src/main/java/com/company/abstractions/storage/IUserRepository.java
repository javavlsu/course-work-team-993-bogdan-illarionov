package com.company.abstractions.storage;

import com.company.abstractions.storage.IRepository;
import com.company.models.account.Role;
import com.company.models.account.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IRepository<User, Long> {

    Optional<User> getByLogin(String login);

    List<Role> getRoles();

}
