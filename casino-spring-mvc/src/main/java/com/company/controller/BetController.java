package com.company.controller;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IBetService;
import com.company.abstractions.IRepository;
import com.company.models.casino.Bet;
import com.company.models.view.BetViewModel;
import com.company.models.view.EditOutcomeModel;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageLot;
import com.company.storage.models.StorageOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
