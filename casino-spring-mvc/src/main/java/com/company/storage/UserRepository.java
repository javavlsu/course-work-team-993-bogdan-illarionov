package com.company.storage;

import com.company.dao.DAOClass;
import com.company.models.account.Password;
import com.company.models.account.User;

import java.util.stream.Collectors;

public class UserRepository implements IUserRepository{

    private DAOClass<com.company.bean.User> dao;

    public void setDao(DAOClass<com.company.bean.User> dao) {
        this.dao = dao;
    }

    @Override
    public Iterable<User> getAll() {
        return dao.getAll().stream().map((x) -> ConvertTo(x)).collect(Collectors.toList());
    }

    @Override
    public User getById(long id) {
        return ConvertTo(dao.getById(id));
    }

    @Override
    public void add(User user) {
        var storageUser = ConvertBack(user);

        dao.create(storageUser);
    }

    public User ConvertTo(com.company.bean.User user)
    {
        if (user == null)
            return null;
        return new User(user.getId(), user.getLogin(), new Password(user.getPassword()), user.getEmail());
    }

    public com.company.bean.User ConvertBack(User user)
    {
        var newUser = new com.company.bean.User();
        newUser.setId(user.getKey());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword().getValue());
        newUser.setEmail(user.getEmail());

        return newUser;
    }
}
