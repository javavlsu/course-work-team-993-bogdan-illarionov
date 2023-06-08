package com.company.models.view;

import com.company.models.account.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class LoginViewModel {

    @Length(min = 5, max = 12, message = "Login should be has length from 5 to 12.")
    @Pattern(regexp = User.REGEX_LOGIN, message = "Login should contains only a-z, A-Z and 0-9 characters")
    private String username = "";

    @Length(min = 4, max = 16, message = "Password should be has length from 4 to 16.")
    @Pattern(regexp = User.REGEX_PASSWORD, message = "Password should contains only a-z, A-Z and 0-9 characters")
    private String password = "";
}
