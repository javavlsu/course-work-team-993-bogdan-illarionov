package com.company.controller;

import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.models.view.LoginViewModel;
import com.company.models.view.ProfileViewModel;
import com.company.models.view.RegisterViewModel;
import com.company.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public String getLogin(Model model) {

            var viewModel = new LoginViewModel();

            viewModel.setUsername("default");
            viewModel.setPassword("password");

            model.addAttribute("loginModel", viewModel);

            return "/account/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        var viewModel = new RegisterViewModel();

        model.addAttribute("viewModel", viewModel);

        return "/account/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute RegisterViewModel viewModel, Model model) {

        if (!usersService.findByLogin(viewModel.getLogin()).isEmpty()) {
            return "/account/register";
        }

        var user = new User(
                viewModel.getLogin(),
                viewModel.getPassword(),
                viewModel.getEmail(),
                new HashSet<Role>());

        usersService.RegisterUser(user);

        return "/index";

    }

    @GetMapping("/manage")
    public String getUsers(Model model) {

        var users = usersService.getUsers();

        model.addAttribute("users", users);

        return "/account/manage";
    }

    @GetMapping("/manage/user")
    public String getManageUser(@RequestParam String name,Model model) {

        //var roles = usersService.getRoles();

        var user = usersService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roles = new HashSet<Role>();

        for (var role : usersService.getRoles()) {

            if (user.get().getRoles().stream().anyMatch(x -> x.getName().equals(role.getName()))) {
                continue;
            }

            roles.add(role);

        }

        model.addAttribute("roles", roles);
        model.addAttribute("login", name);
        model.addAttribute("users_roles", user.get().getRoles().stream().toList());

        return "/account/manage_user";
    }

    @GetMapping("/manage/user/add")
    public String getAddRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = usersService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roleToAdd = usersService.getRoles().stream().filter(x -> x.getId() == role).findFirst();

        if (roleToAdd.isEmpty()) {
            return "/index";
        }

        var domain = user.get();

        domain.getRoles().add(roleToAdd.get());

        usersService.UpdateUser(domain);

        return "/index";
    }

    @GetMapping("/manage/user/remove")
    public String getRemoveRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = usersService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roleToRemove = usersService.getRoles().stream().filter(x -> x.getId() == role).findFirst();

        if (roleToRemove.isEmpty()) {
            return "/index";
        }

        var domain = user.get();

        domain.getRoles().remove(roleToRemove.get());

        usersService.UpdateUser(domain);

        return "/index";
    }

    @GetMapping("/profile/index")
    public String getProfile(Authentication authentication, Model model) {
        var name = authentication.getName();

        var user = usersService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var viewModel = new ProfileViewModel(
                user.get().getLogin(),
                user.get().getEmail(),
                user.get().getPassword());

        model.addAttribute("viewModel", viewModel);

        return "/account/profile";
    }

    @PostMapping("/profile/index")
    public String postProfile(@ModelAttribute ProfileViewModel viewModel, Model model) {
        if (usersService.findByLogin(viewModel.getLogin()).isEmpty()) {
            return "/index";
        }

        var user = new User(
                viewModel.getLogin(),
                viewModel.getPassword(),
                viewModel.getEmail(),
                new HashSet<Role>());

        usersService.UpdateUser(user);

        return "/index";

    }

    @GetMapping("/balance")
    public String getBalance(Model model) {

        System.out.println(345);

        return "/account/balance";
    }
}
