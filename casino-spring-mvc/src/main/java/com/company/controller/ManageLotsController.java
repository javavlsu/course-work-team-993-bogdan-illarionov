package com.company.controller;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IRepository;
import com.company.models.casino.Bet;
import com.company.models.view.BetViewModel;
import com.company.models.view.EditOutcomeModel;
import com.company.storage.models.StorageLot;
import com.company.storage.models.StorageOutcome;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ManageLotsController {
    @Autowired
    private IRepository<StorageLot,Long> lotsRepo;
    @Autowired
    private IRepository<StorageOutcome,Long> outcomesRepo;

    private String getLots(Model model) {

        model.addAttribute(
                "lots",
                StreamSupport
                        .stream(lotsRepo.getAll().spliterator(), false)
                        .collect(Collectors.toSet()));

        return "/gaming/lots";
    }

    @GetMapping("lots/manage")
    public String getManage(Model model) {

        model.addAttribute(
                "lots",
                StreamSupport
                        .stream(lotsRepo.getAll().spliterator(), false)
                        .collect(Collectors.toSet()));

        return "/gaming/manage";
    }

    @GetMapping("lots/manage/outcomes/{lotId}")
    public String getManageLot(@PathVariable Long lotId, Model model) {
        model.addAttribute(
                "outcomes",
                lotsRepo.getById(lotId).getOutcomes()
                        .stream()
                        .sorted((a1,b1) -> Long.compare(a1.getId(),b1.getId()))
                        .toList());

        return "/gaming/manage-outcomes";
    }

    @GetMapping("lots/manage/outcomes/edit/{outcomeId}")
    public String getManageOutcome(
            @PathVariable Long outcomeId,
            Model model) {
        model.addAttribute("outcome", outcomesRepo.getById(outcomeId));
        model.addAttribute("viewModel", new EditOutcomeModel());

        return "/gaming/edit-outcome";
    }

    @PostMapping("lots/manage/outcomes/edit/{outcomeId}")
    public String postManageOutcome(
            @PathVariable Long outcomeId,
            @ModelAttribute EditOutcomeModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if(bindingResult.hasErrors())
            return getLots(model); //Todo

        var outcome = outcomesRepo.getById(outcomeId);
        outcome.setKoef(viewModel.getKoef());
        outcomesRepo.update(outcome);

        return getLots(model);
    }
}
