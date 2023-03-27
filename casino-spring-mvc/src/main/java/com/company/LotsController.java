/*
package com.company;

import com.company.storage.GameOutcomesRepo;
import com.company.storage.GameOutcomesRepoService;
import com.company.storage.ILotRepository;
import com.company.storage.LotRepositoryService;
import com.company.viewModels.BetViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class LotsController {
    //@Autowired
   // private LotRepositoryService lotRepositoryService;

    @Autowired
    private LotRepositoryService gameOutcomesRepo;

    @GetMapping("/lots")
    public String getLots(Model model) {
        //var lots = lotRepository.findAll();

        //model.addAttribute("lots", lotRepository.findAll();

        var outcomes = gameOutcomesRepo.lotRepository.findAll();

        var a = outcomes.stream().findFirst().get();

        var b = a.getGameOutcomes();

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
*/
