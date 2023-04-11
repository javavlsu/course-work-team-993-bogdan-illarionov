package com.company.controller;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IRepository;
import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.models.casino.Bet;
import com.company.models.view.RegisterViewModel;
import com.company.storage.models.StorageLot;
import com.company.models.view.BetViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping
public class LotsController {
    @Autowired
    private IRepository<StorageLot,Long> lotsRepo;
    @Autowired
    private IBetApplicator betApplicator;

    @GetMapping("/lots")
    public String getLots(Model model) {

        model.addAttribute(
                "lots",
                StreamSupport
                        .stream(lotsRepo.getAll().spliterator(), false)
                        .collect(Collectors.toSet()));

        return "/gaming/lots";
    }

    @GetMapping("lots/{lotId}")
    public String getLot(@PathVariable Long lotId, Model model) {

        var lot = lotsRepo.getById(lotId);
        Map<Long, String> outcomesMap = new HashMap<Long, String>();
        for (var outcome:
             lot.getOutcomes()) {
            outcomesMap.put(outcome.getId(), outcome.getValue());
        }

        model.addAttribute("lot", lot);
        model.addAttribute("outcomesMap", outcomesMap);
        model.addAttribute("viewModel", new BetViewModel());

        return "/gaming/lot";
    }

    @PostMapping("lots/{lotId}")
    public String postLot(@ModelAttribute BetViewModel viewModel, Model model) {



        return "/index";

    }

    @PostMapping("lots/play")
    public String postLotPlay(
        @ModelAttribute BetViewModel betViewModel,
        BindingResult bindingResult,
        Model model)
    {
        if(bindingResult.hasErrors())
            return "/lots"; //Todo

        /*var bet = new Bet(
            betViewModel.userId,
            betViewModel.outcomeId,
            betViewModel.price);

        _betRepository.add(bet);

        var lot = _lotRepository.getById(betViewModel.lotId);
        var result = _lotsPlayer.playLot(lot, bet);

        model.addAttribute("lot", lot);
        model.addAttribute("result", result);*/


        return "/lots/a";
    }


}
