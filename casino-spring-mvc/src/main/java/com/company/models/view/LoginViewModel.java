package com.company.models.view;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginViewModel {

    @NotEmpty(message = "Login can't be empty.")
    private String username;

    @NotEmpty(message = "Password can't be empty.")
    private String password;

    public LoginViewModel(){

    }

    public LoginViewModel(String login, String password){
        this.username = login;
        this.password = password;
    }
}
