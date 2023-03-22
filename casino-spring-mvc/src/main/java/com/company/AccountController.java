package com.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

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

    @GetMapping("/account/tmplogin")
    public String getTmpLogin(Model model) {

        System.out.println(345);

        model.addAttribute("isLogin", true);

        return "/index";
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
