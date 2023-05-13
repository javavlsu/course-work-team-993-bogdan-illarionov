package com.company.controller;

import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.models.view.LoginViewModel;
import com.company.models.view.ProfileViewModel;
import com.company.models.view.RegisterViewModel;
import com.company.logic.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin(Model model) {

            var viewModel = new LoginViewModel();

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
    public String postRegister(@Valid @ModelAttribute("viewModel") RegisterViewModel viewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/account/register";
        }

        if (userService.findByLogin(viewModel.getLogin()).isPresent()) {
            var error = new FieldError("viewModel","login", "Given login already exists.");

            bindingResult.addError(error);

            return "/account/register";
        }

        userService.RegisterUser(RegisterViewModel.ToUser(viewModel));

        return "redirect:/account/login";

    }

    @GetMapping("/manage")
    public String getUsers(Model model) {

        var users = userService.getUsers();

        model.addAttribute("users", users);

        return "/account/manage";
    }

    @GetMapping("/manage/user")
    public String getManageUser(@RequestParam String name,Model model) {

        //var roles = usersService.getRoles();

        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roles = new HashSet<Role>();

        for (var role : userService.getRoles()) {

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
        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roleToAdd = userService.getRoles().stream().filter(x -> x.getId() == role).findFirst();

        if (roleToAdd.isEmpty()) {
            return "/index";
        }

        var domain = user.get();

        domain.getRoles().add(roleToAdd.get());

        userService.UpdateUser(domain);

        return "/index";
    }

    @GetMapping("/manage/user/remove")
    public String getRemoveRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "/index";
        }

        var roleToRemove = userService.getRoles().stream().filter(x -> x.getId() == role).findFirst();

        if (roleToRemove.isEmpty()) {
            return "/index";
        }

        var domain = user.get();

        domain.getRoles().remove(roleToRemove.get());

        userService.UpdateUser(domain);

        return "/index";
    }

    @GetMapping("/profile/index")
    public String getProfile(Authentication authentication, Model model) {
        var name = authentication.getName();

        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/register";
        }

        var viewModel = ProfileViewModel.ToView(user.get());

        model.addAttribute("viewModel", viewModel);

        return "/account/profile";
    }

    @PostMapping("/profile/index")
    public String postProfile(@Valid @ModelAttribute("viewModel") ProfileViewModel viewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/profile/index";
        }

        var user = userService.findByLogin(viewModel.getLogin());

        if (user.isEmpty()) {
            return "redirect:/account/register";
        }

        if (!user.get().getPassword().equals(viewModel.getPassword())) {
            var error = new FieldError("viewModel","password", "Password not match with current password.");

            bindingResult.addError(error);

            return "/profile/index";
        }

        user.get().setEmail(viewModel.getEmail());

        userService.UpdateUser(user.get());

        return "redirect:/profile/index";

    }

    @GetMapping("/balance")
    public String getBalance(Model model) {

        System.out.println(345);

        return "/account/balance";
    }
}
