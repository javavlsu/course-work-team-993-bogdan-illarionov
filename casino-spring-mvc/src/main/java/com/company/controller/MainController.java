package com.company.controller;

import com.company.abstractions.IBonusService;
import com.company.abstractions.IUserService;
import com.company.abstractions.storage.IBetRepository;
import com.company.abstractions.storage.IRepository;
import com.company.storage.models.StorageLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private IBonusService service;

    @Autowired
    private IBetRepository betRepository;

    @Autowired
    private IRepository<StorageLot, Long> lotRepository;

    @GetMapping("/index")
    public String getIndex(Model model) {

        model.addAttribute(
                "lots",
                lotRepository.getAll()
                        .stream()
                        .limit(3)
                        .toList());

        model.addAttribute(
                "bets",
                betRepository.getPartOgBets(5, 0)
                        .stream()
                        .toList());

        return "index";
    }
}