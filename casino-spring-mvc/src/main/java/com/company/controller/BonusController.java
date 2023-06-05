package com.company.controller;

import com.company.abstractions.IBonusService;
import com.company.abstractions.IUserService;
import com.company.models.account.User;
import com.company.models.view.BetViewModel;
import com.company.models.view.bonus.CreateBonusViewModel;
import com.company.models.view.bonus.EditBonusViewModel;
import com.company.models.view.bonus.SetupUserBonusViewModel;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageBonusConfig;
import com.company.storage.models.bonus.StorageUserBonus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private IBonusService bonusService;

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    public String getIndex(Model model) {

        var bonuses = bonusService.getBonuses();

        model.addAttribute("list", bonuses);

        return "/bonus/list";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {

        var viewModel = new CreateBonusViewModel();

        model.addAttribute("viewModel", viewModel);

        prepareCreateBonusModels(model);

        return "/bonus/create";
    }

    @PostMapping("/create")
    public String postCreate(
            @Valid @ModelAttribute("viewModel") CreateBonusViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            prepareCreateBonusModels(model);

            return "/bonus/create";
        }

        var errors = false;

        if (viewModel.getExpireTypeId() == StorageBonus.COUNT_EXPIRE_TYPE_ID) {
            if (viewModel.getTriggerCount() == null) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","triggerCount", "Should be filled."));
            }
        }
        else if (viewModel.getExpireTypeId() == StorageBonus.TERM_EXPIRE_TYPE_ID) {
            if (viewModel.getToTerm() == null || viewModel.getToTerm().equals("")) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","toTerm", "Should be filled."));
            }
        }

        if (viewModel.getTriggerActionTypeId() == StorageBonus.BALANCE_ADD_ACTION_ID) {
            if (viewModel.getBonusKoef() == null) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","bonusKoef", "Should be filled."));
            }
        }
        else if (viewModel.getTriggerActionTypeId() == StorageBonus.LOT_WIN_ACTION_ID) {
            if (viewModel.getBonusKoef() == null) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","bonusKoef", "Should be filled."));
            }
            if (viewModel.getLotsIds() == null || viewModel.getLotsIds().equals("")) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","lotsIds", "Should be filled."));
            }
        }
        else if (viewModel.getTriggerActionTypeId() == StorageBonus.LOT_PLAY_ACTION_ID) {
            if (viewModel.getLotsIds() == null || viewModel.getLotsIds().equals("")) {
                errors = true;
                bindingResult.addError(new FieldError("viewModel","lotsIds", "Should be filled."));
            }
        }

        if (errors) {
            prepareCreateBonusModels(model);

            return "/bonus/create";
        }

        var bonus = CreateBonusViewModel.ToModel(viewModel);

        bonusService.createBonus(bonus);

        return "redirect:/bonus/index";
    }

    @GetMapping("/edit/{bonusId}")
    public String getEdit(@PathVariable Long bonusId, Model model) {

        var optionalBonus = bonusService.getById(bonusId);

        if (optionalBonus.isEmpty()) {
            return "redirect:/bonus/index";
        }

        var viewModel = EditBonusViewModel.FromModel(optionalBonus.get());

        model.addAttribute("viewModel", viewModel);

        return "/bonus/edit";
    }

    @PostMapping("/edit/{bonusId}")
    public String postEdit(
            @PathVariable Long bonusId,
            @Valid @ModelAttribute("viewModel") EditBonusViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            prepareCreateBonusModels(model);

            return "/bonus//edit";
        }

        var optionalOldBonus = bonusService.getById(bonusId);

        if (optionalOldBonus.isEmpty()) {
            return "redirect:/bonus/index";
        }

        viewModel.setId(optionalOldBonus.get().getId());
        viewModel.setTriggerActionTypeId(optionalOldBonus.get().getTriggerActionId());
        viewModel.setExpireTypeId(optionalOldBonus.get().getExpireTypeId());

        var bonus = EditBonusViewModel.ToModel(viewModel);

        bonusService.updateBonus(bonus);

        return "redirect:/bonus/index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        var viewModel = new SetupUserBonusViewModel();

        prepareSetupUserBonusModels(model);

        model.addAttribute("viewModel", viewModel);

        return "/bonus/users";
    }

    @PostMapping("/users")
    public String postSetup(
            @ModelAttribute("viewModel") @Valid SetupUserBonusViewModel viewModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            prepareSetupUserBonusModels(model);

            return "/bonus/users";
        }

        var userBonus = new StorageUserBonus();
        userBonus.setBonusId(viewModel.getBonusId());
        userBonus.setUserId(viewModel.getUserId());

        var optionalUser = userService.getById(viewModel.getUserId());
        var optionalBonus = bonusService.getById(viewModel.getBonusId());

        if (optionalUser.isEmpty() || optionalBonus.isEmpty()) {
            return "redirect:/bonus/users";
        }

        bonusService.addBonusToUser(optionalBonus.get(), optionalUser.get());

        return "redirect:/bonus/users";
    }

    @GetMapping("/users/{userId}/enable/{bonusId}")
    public String getUserEnable(@PathVariable Long userId, @PathVariable Long bonusId, Model model) {

        var optionalUser = userService.getById(userId);
        var optionalBonus = bonusService.getById(bonusId);

        if (optionalUser.isEmpty() || optionalBonus.isEmpty()) {
            return "redirect:/bonus/users";
        }

        var bonus = bonusService.getBonusesForUser(optionalUser.get()).stream().filter(x -> x.getBonusId().equals(bonusId)).findFirst();

        if (bonus.isEmpty()) {
            return "redirect:/bonus/users";
        }

        var param = bonus.get().getConfig().stream().filter(x -> x.getName().equals("is_enabled")).findFirst();

        if (param.isEmpty()) {
            return "redirect:/bonus/users";
        }

        param.get().setValue(param.get().getValue().equals("true") ? "false" : "true");

        bonusService.changeBonusOfUser(bonus.get());

        return "redirect:/bonus/users";
    }

    private void prepareCreateBonusModels(Model model) {
        Map<Short, String> triggerMap = new HashMap<Short, String>(){{
            put(StorageBonus.BALANCE_ADD_ACTION_ID, "Balance add");
            put(StorageBonus.LOT_WIN_ACTION_ID, "Lot win");
            put(StorageBonus.LOT_PLAY_ACTION_ID, "Lot play");
        }};

        Map<Short, String> expireMap = new HashMap<Short, String>(){{
            put(StorageBonus.COUNT_EXPIRE_TYPE_ID, "Count");
            put(StorageBonus.TERM_EXPIRE_TYPE_ID, "Term");
            put(StorageBonus.UNLIMITED_EXPIRE_TYPE_ID, "Unlimited");
        }};

        model.addAttribute("expireMap", expireMap);
        model.addAttribute("triggerMap", triggerMap);
    }

    private void prepareSetupUserBonusModels(Model model) {

        var users = userService.getUsers();

        var bonuses = bonusService.getBonuses();

        Map<Long, String> usersMap = users.stream().collect(Collectors.toMap(User::getId, User::getLogin));
        Map<Long, String> bonusMap = bonuses.stream().collect(Collectors.toMap(StorageBonus::getId, StorageBonus::getName));

        Map<String, List<StorageUserBonus>> usersBonusMap = new HashMap<>();

        var arr = new ArrayList<String>();

        for (var user : users) {
            var bonues = bonusService.getBonusesForUser(user);

            if (bonues.size() == 0) {
                continue;
            }

            usersBonusMap.put(user.getLogin(), bonues);
        }

        model.addAttribute("usersMap", usersMap);
        model.addAttribute("bonusMap", bonusMap);
        model.addAttribute("usersBonusMap", usersBonusMap);
    }
}
