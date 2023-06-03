package com.company.controller;

import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.models.view.*;
import com.company.logic.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;

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

        var roles = userService.getRoles();

        var viewModel = new ManageUsersViewModel();

        viewModel.setRoles(roles);

        for (var user : users) {
            viewModel.addUser(user);
        }

        model.addAttribute("viewModel", viewModel);

        return "/account/manage";
    }

    @GetMapping("/manage/add")
    public String getAddRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/manage";
        }

        var roleToAdd = userService.getRoles().stream().filter(x -> Objects.equals(x.getId(), role)).findFirst();

        if (roleToAdd.isEmpty()) {
            return "redirect:/account/manage";
        }

        var domain = user.get();

        domain.getRoles().add(roleToAdd.get());

        userService.UpdateUser(domain);

        return "redirect:/account/manage";
    }

    @GetMapping("/manage/remove")
    public String getRemoveRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/manage";
        }

        var roleToRemove = userService.getRoles().stream().filter(x -> Objects.equals(x.getId(), role)).findFirst();

        if (roleToRemove.isEmpty()) {
            return "redirect:/account/manage";
        }

        var domain = user.get();

        domain.getRoles().remove(roleToRemove.get());

        userService.UpdateUser(domain);

        return "redirect:/account/manage";
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
            return "/account/profile";
        }

        var user = userService.findByLogin(viewModel.getLogin());

        if (user.isEmpty()) {
            return "redirect:/account/register";
        }

        if (!user.get().getPassword().equals(viewModel.getPassword())) {
            var error = new FieldError("viewModel","password", "Password not match with current password.");

            bindingResult.addError(error);

            return "/account/profile";
        }

        user.get().setEmail(viewModel.getEmail());

        userService.UpdateUser(user.get());

        return "redirect:/account/profile/index";

    }

    @GetMapping("/balance")
    public String getBalance(Authentication authentication, Model model) {

        var name = authentication.getName();

        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/index";
        }

        var viewModel = new AddBalanceViewModel();

        model.addAttribute("viewModel", viewModel);

        return "/account/balance";
    }

    @PostMapping("/balance")
    public String getBalance(Authentication authentication, @Valid @ModelAttribute("viewModel") AddBalanceViewModel viewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/account/balance";
        }

        var name = authentication.getName();

        var user = userService.findByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/index";
        }

        userService.ChangeUserBalance(user.get().getLogin(), viewModel.getPositiveBalanceDelta());

        userService.UpdateAuthorizeUserData(userService.findByLogin(name).get());

        return "redirect:/index";
    }
}
