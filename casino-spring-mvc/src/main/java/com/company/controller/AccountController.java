package com.company.controller;

import com.company.models.account.Password;
import com.company.models.account.User;
import com.company.storage.IUserRepository;
import com.company.storage.LotRepositoryService;
import com.company.storage.UserRepositoryService;
import com.company.viewModels.LoginViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepositoryService userRep;

    /*@Autowired
    private IUserRepository userRepository;*/

    @GetMapping("/test")
    public String getUsers(Model model)
    {
        /*User user  = userRepository.getById(1);;

       var canLoginUser = new User(-1,user.getLogin(),user.getPassword(), user.getEmail());
       var cannotLogin = new User(-1,user.getLogin()+"none",user.getPassword(), user.getEmail());

       var res1 = userRepository.isCanLogIn(canLoginUser);
       var res2 = userRepository.isCanLogIn(cannotLogin);

        userRepository.changeCredentials(user, user.getLogin(), user.getPassword().getValue(), "!"+LocalDateTime.now().toString());

        String login = user.getLogin();*/

        return "/account/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

            var viewModel = new LoginViewModel();

            viewModel.setUsername("default");
            viewModel.setPassword("password");

            model.addAttribute("loginModel", viewModel);

            return "/account/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute LoginViewModel viewModel, Model model) {

        if (viewModel == null)
        {
            throw new IllegalArgumentException("Null not allowed");
        }

        var user = userRep.LoginUser(viewModel);

        int b = 2 + 3;

        return "/index";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        System.out.println(345);

        return "/account/register";
    }

    @GetMapping("/profile/index")
    public String getProfile(Model model) {

        System.out.println(345);

        return "/account/profile";
    }

    @GetMapping("/balance")
    public String getBalance(Model model) {

        System.out.println(345);

        return "/account/balance";
    }
}
