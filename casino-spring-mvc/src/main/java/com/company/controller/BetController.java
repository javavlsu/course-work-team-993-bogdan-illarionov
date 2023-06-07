package com.company.controller;

import com.company.abstractions.IBetService;
import com.company.abstractions.IUserService;
import com.company.abstractions.storage.IBetRepository;
import com.company.abstractions.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class BetController {

    public static final int BETS_PAGE_COUNT = 5;

    @Autowired
    private IBetRepository betRepository;

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @GetMapping("/bets")
    public String getLots(@RequestParam(required = false) Integer num, Model model) {

        var user = userRepository.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        var count = betRepository.getCountOfUser(user.get().getId());

        var allNumber = (count + BETS_PAGE_COUNT - 1) / BETS_PAGE_COUNT;

        if (allNumber == 0) {
            model.addAttribute("isShow",false);
            return "/bets/bet-table";
        }

        if (num == null || num  < 0 || num > allNumber) {
            return "redirect:/bets?num=1";
        }

        var bets = betRepository.getPartBetsOfUser(user.get().getId(), BETS_PAGE_COUNT, (num - 1)*BETS_PAGE_COUNT);

        model.addAttribute("bets", bets);
        model.addAttribute("isShow",true);
        model.addAttribute("next", num+1);
        model.addAttribute("prev", num-1);
        model.addAttribute("max",allNumber+1);
        model.addAttribute("num", num);

        return "/bets/bet-table";
    }
}
