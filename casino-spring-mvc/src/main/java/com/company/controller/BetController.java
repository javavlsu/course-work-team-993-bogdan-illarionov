package com.company.controller;

import com.company.abstractions.IBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class BetController {
    @Autowired
    private IBetService betService;

    @Transactional
    @GetMapping("/bets")
    public String getLots(Model model) {

        var bets = betService.GetBetsByLoginUser(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("bets", bets);

        return "/bets/bet-table";
    }
}
