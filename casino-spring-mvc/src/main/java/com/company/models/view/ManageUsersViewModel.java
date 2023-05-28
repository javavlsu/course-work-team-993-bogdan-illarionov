package com.company.models.view;

import com.company.models.account.Role;
import com.company.models.account.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ManageUsersViewModel {
    private List<Role> roles;

    private List<ShowRolesViewModel> usersRoles;

    public ManageUsersViewModel() {
        usersRoles = new ArrayList<>();
    }

    public void addUser(User user) {

        usersRoles.add(
                new ShowRolesViewModel(
                        user.getLogin(),
                        user.getRoles().stream().toList()
                )
        );
    }
}
