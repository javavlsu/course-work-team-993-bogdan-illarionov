package com.company;

import com.company.abstractions.IRepository;
import com.company.storage.models.StorageLot;
import com.company.viewModels.BetViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping
public class LotsController {
    @Autowired
    private IRepository<StorageLot,Long> lotsRepo;

    @GetMapping("/lots")
    public String getLots(Model model) {
        //model.addAttribute("lots", lotRepository.findAll();

        var lot = StreamSupport.stream(lotsRepo.getAll().spliterator(), false)
                .collect(Collectors.toSet())
                .stream().findFirst().get();

        var a = lot.getOutcomes();

        return "/lots";
    }

    @GetMapping("lots/{lotId}")
    public String getLot(@PathVariable long lotId, Model model) {
        //model.addAttribute("lot", _lotRepository.getById(lotId));

        return "/lots/a";
    }

    @PostMapping("lots/play")
    public String postLotPlay(
        @ModelAttribute BetViewModel betViewModel,
        BindingResult bindingResult,
        Model model)
    {
        if(bindingResult.hasErrors())
            return "/lots"; //Todo

        /*
        var bet = new Bet(
            betViewModel.userId,
            betViewModel.outcomeId,
            betViewModel.price);

        _betRepository.add(bet);

        var lot = _lotRepository.getById(betViewModel.lotId);
        var result = _lotsPlayer.playLot(lot, bet);

        model.addAttribute("lot", lot);
        model.addAttribute("result", result);

         */
        return "/lots/a";
    }


}
