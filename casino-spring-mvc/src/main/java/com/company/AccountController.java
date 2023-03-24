package com.company;

import com.company.models.account.Password;
import com.company.models.account.User;
import com.company.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AccountController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/test")
    public String getUsers(Model model)
    {
        User user  = userRepository.getById(1);;

       var canLoginUser = new User(-1,user.getLogin(),user.getPassword(), user.getEmail());
       var cannotLogin = new User(-1,user.getLogin()+"none",user.getPassword(), user.getEmail());

       var res1 = userRepository.isCanLogIn(canLoginUser);
       var res2 = userRepository.isCanLogIn(cannotLogin);

        userRepository.changeCredentials(user, user.getLogin(), user.getPassword().getValue(), "!"+LocalDateTime.now().toString());

        String login = user.getLogin();

        return "/account/login";
    }

    @GetMapping("/user/login")
    public String getLogIn(@RequestParam(name = "login") String login, @RequestParam(name = "pwd") String password, Model model)
    {
        var user = new User(-1,login, new Password(password),"");

        if (userRepository.isCanLogIn(user))
        {
            return "/account/login";
        }
        else
        {
            return "/account/nologin";
        }
    }

    @GetMapping("/user/change")
    public String getChange(@RequestParam(name = "login") String login, @RequestParam(name = "pwd") String password, @RequestParam(name="new") String newPwd, Model model)
    {
        var user = new User(-1,login, new Password(password),"");

        if (!userRepository.isCanLogIn(user))
        {
            return "/account/nologin";
        }

        userRepository.changeCredentials(user, login, newPwd, user.getEmail());

        return "/account/profile";
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
