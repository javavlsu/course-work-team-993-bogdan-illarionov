package com.company;

import com.company.models.account.User;
import com.company.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model)
    {
        User user  = userRepository.getById(1);;

        String login = user.getLogin();

        return "/account/login";
    }

    @GetMapping("/account/login")
    public String getLogin(Model model) {

            System.out.println(345);

            return "/account/login";
    }

    @GetMapping("/account/register")
    public String getRegister(Model model) {

        System.out.println(345);

        return "/account/register";
    }

    @GetMapping("/account/profile")
    public String getProfile(Model model) {

        System.out.println(345);

        return "/account/profile";
    }

    @GetMapping("/account/balance")
    public String getBalance(Model model) {

        System.out.println(345);

        return "/account/balance";
    }
}
