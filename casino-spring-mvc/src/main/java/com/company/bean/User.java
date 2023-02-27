package com.company.bean;

import jakarta.validation.constraints.Email;

public class User {

    // Class data members

    // Map to studentsdetails.id
    private long id;
    // Map to studentsdetails.name
    private String login;
    // Map to studentsdetails.caste
    private String password;

    // Map to studentsdetails.gender
    private String email;

    // Getter and setter methods

    // Getter

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}