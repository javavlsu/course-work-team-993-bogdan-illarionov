package com.company.models.account;

public class User {
    public long key;
    private String login;
    private String email;
    private Password password;

    public User(long key, String login, Password password,String email)
    {
        this.key = key;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public long getKey() {
        return key;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
