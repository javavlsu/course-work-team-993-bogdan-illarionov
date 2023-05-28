package com.company.models.view;

import com.company.models.account.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRolesViewModel {
    private String login;

    private List<Role> roles;

    public boolean checkRole(String name) {
        return roles.stream().anyMatch(x -> x.getName().equals(name));
    }
}
