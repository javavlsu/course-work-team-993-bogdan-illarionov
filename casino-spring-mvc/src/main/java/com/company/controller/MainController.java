package com.company.controller;

import com.company.abstractions.IBonusService;
import com.company.abstractions.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private IBonusService service;

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public String getIndex(Model model) {

//        var bonuses = service.getBonuses();
//
//        bonuses.get(0).getConfig().setToTerm(Duration.parse("days 4 hours 1"));
//
//        service.updateBonus(bonuses.get(0));

        return "index";
    }
}