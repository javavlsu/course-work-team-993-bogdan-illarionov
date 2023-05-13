package com.company.models.view;

import com.company.annotations.EqualFields;
import com.company.models.account.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualFields(field = "password", matchField = "repeatPassword", message = "Passwords should be equals.")
public class RegisterViewModel {

    @Length(min = 5, max = 12, message = "Login should be has length from 5 to 12.")
    @Pattern(regexp = User.REGEX_LOGIN, message = "Login should contains only a-z, A-Z and 0-9 characters")
    private String login = "";

    @Length(min = 4, max = 16, message = "Password should be has length from 4 to 16.")
    @Pattern(regexp = User.REGEX_PASSWORD, message = "Password should contains only a-z, A-Z and 0-9 characters")
    private String password = "";

    private String repeatPassword = "";

    @NotEmpty(message = "Email field should be filled.")
    @Email(message = "Email string not match with email's format.")
    private String email = "";

    public static User ToUser(RegisterViewModel viewModel)
    {
        return new User(
                viewModel.getLogin(),
                viewModel.getPassword(),
                viewModel.getEmail(),
                new HashSet<>());
    }
}
