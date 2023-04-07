package com.company.models.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterViewModel {
    private String login = "";

    private String password = "";

    private String repeatPassword = "";

    private String email = "";

    public RegisterViewModel() {

    }
}
