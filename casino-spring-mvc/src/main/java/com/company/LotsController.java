package com.company;

import com.company.models.Lot;
import com.company.storage.IRepository;
import com.company.viewModels.BetViewModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LotsController {
    private final IRepository<Lot> _lotRepository;
    private final ILotsPlayer _lotsPlayer;

    public LotsController(
        IRepository<Lot> lotRepository,
        ILotsPlayer lotsPlayer)
    {
        if(lotRepository == null)
            throw new IllegalArgumentException("Lot repository can't be null");
        _lotRepository = lotRepository;

        if(lotsPlayer == null)
            throw new IllegalArgumentException("Lots player can't be null");
        _lotsPlayer = lotsPlayer;
    }

    @GetMapping("/lots")
    public String getLots(Model model) {
        model.addAttribute("lots", _lotRepository.getAll());

        return "/lots";
    }

    @GetMapping("lots/{lotId}")
    public String getLot(@PathVariable long lotId, Model model) {
        model.addAttribute("lot", _lotRepository.getById(lotId));

        return "/lots/a";
    }

    @PostMapping("lots/play")
    public String postLotPlay(
        @Valid @ModelAttribute BetViewModel betViewModel,
        BindingResult bindingResult,
        Model model)
    {
        if(bindingResult.hasErrors())
            return "/lots"; //Todo

        // Add bet to bet repository

        var lot = _lotRepository.getById(betViewModel.lotId);
        var result = _lotsPlayer.PlayLot(lot);

        model.addAttribute("lot", lot);
        model.addAttribute("result", result);

        return "/lots/a";
    }


}
