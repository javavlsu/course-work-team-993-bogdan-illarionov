package com.company.controller;

import com.company.abstractions.IBonusRepository;
import com.company.abstractions.IBonusService;
import com.company.abstractions.IUserService;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageBonusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Duration;

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