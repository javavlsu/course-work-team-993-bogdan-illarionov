package com.company.controller;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IRepository;
import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.models.casino.Bet;
import com.company.models.view.EditOutcomeModel;
import com.company.models.view.RegisterViewModel;
import com.company.storage.models.StorageLot;
import com.company.models.view.BetViewModel;
import com.company.storage.models.StorageOutcome;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    private IRepository<StorageOutcome,Long> outcomesRepo;
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
        prepareModelLot(model, lotId, true);

        return "/gaming/lot";
    }

    @Transactional
    @PostMapping("lots/{lotId}")
    public String postLot(
            @PathVariable Long lotId,
            @Valid @ModelAttribute("viewModel") BetViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors()) {
            prepareModelLot(model, lotId, false);
            return "/gaming/lot";
        }

        var bet = new Bet(
                viewModel.getLogin(),
                viewModel.getOutcomeId(),
                viewModel.getBetSize());

        var result = betApplicator.applyBet(bet);

        prepareModelLot(model, lotId, true);
        model.addAttribute("gameWin", result.isWin());
        model.addAttribute("gameResult", result.getGameOutcomeView());

        return "/gaming/lot";
    }

    private void prepareModelLot(Model model, Long lotId, boolean newViewModel){
        var lot = lotsRepo.getById(lotId);
        Map<Long, String> outcomesMap = new HashMap<Long, String>();
        for (var outcome:
                lot.getOutcomes()) {
            outcomesMap.put(outcome.getId(), outcome.getValue());
        }

        model.addAttribute("lot", lot);
        model.addAttribute("outcomesMap", outcomesMap);

        if (newViewModel) {
            model.addAttribute("viewModel", new BetViewModel());
        }
    }
}
