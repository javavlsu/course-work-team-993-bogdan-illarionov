package com.company.storage;

import com.company.dao.DAOClass;
import com.company.dao.UserDAO;
import com.company.models.account.Password;
import com.company.models.account.User;

import java.util.stream.Collectors;

public class UserRepository implements IUserRepository{

    public static User ConvertTo(com.company.bean.User user)
    {
        if (user == null)
            return null;
        return new User(user.getId(), user.getLogin(), new Password(user.getPassword()), user.getEmail());
    }

    public static com.company.bean.User ConvertBack(User user)
    {
        var newUser = new com.company.bean.User();
        newUser.setId(user.getKey());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword().getValue());
        newUser.setEmail(user.getEmail());

        return newUser;
    }

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

    @Override
    public boolean isCanLogIn(User user) {
        var storageUser = ((UserDAO)dao).getByLogin(user.getLogin());

        if (storageUser == null || !storageUser.getPassword().equals(user.getPassword().getValue()))
            return false;

        user = ConvertTo(storageUser);

        return true;
    }

    @Override
    public void changeCredentials(User user, String login, String password, String email) {
        if (!isCanLogIn(user))
        {

            return;
        }

        if (!user.getLogin().equals(login) && (((UserDAO)dao).getByLogin(login) != null))
        {
            return;
        }

        var storageUser = ((UserDAO)dao).getByLogin(user.getLogin());

        storageUser.setEmail(email);
        storageUser.setLogin(login);
        storageUser.setPassword(password);

        dao.update(storageUser);
    }
}
