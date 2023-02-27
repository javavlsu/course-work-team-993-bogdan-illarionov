package com.company;

import com.company.bean.User;
import com.company.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class AccountController {

    @Autowired
    private UserDAO users;

    @GetMapping("/users")
    public String getUsers(Model model)
    {
        User user  = users.getById(1);;

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
