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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileViewModel {

    private String login = "";

    @NotEmpty(message = "Email field should be filled.")
    @Email(message = "Email string not match with email's format.")
    private String email = "";

    @Length(min = 4, max = 16, message = "Password should be has length from 4 to 16.")
    @Pattern(regexp = User.REGEX_PASSWORD, message = "Password should contains only a-z, A-Z and 0-9 characters")
    private String password = "";

    public static ProfileViewModel ToView(User user)
    {
        var view = new ProfileViewModel();

        view.setLogin(user.getLogin());
        view.setEmail(user.getEmail());

        return view;
    }
}
