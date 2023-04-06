package com.company.models.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileViewModel {

    private String login;
    private String email;
    private String password;

    public ProfileViewModel() {

    }

}
