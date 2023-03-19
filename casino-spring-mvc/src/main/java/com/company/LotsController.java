package com.company;

import com.company.storage.GameOutcomesRepo;
import com.company.storage.GameOutcomesRepoService;
import com.company.storage.ILotRepository;
import com.company.storage.LotRepositoryService;
import com.company.viewModels.BetViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/lots")
public class LotsController {
    //@Autowired
   // private LotRepositoryService lotRepositoryService;

    @Autowired
    @Qualifier
    GameOutcomesRepo gameOutcomesRepo;

    @GetMapping("/lots")
    public String getLots(Model model) {
        //var lots = lotRepository.findAll();

        //model.addAttribute("lots", lotRepository.findAll();

        return "/lots";
    }

    @GetMapping("lots/{lotId}")
    public String getLot(@PathVariable long lotId, Model model) {
        //model.addAttribute("lot", _lotRepository.getById(lotId));

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
