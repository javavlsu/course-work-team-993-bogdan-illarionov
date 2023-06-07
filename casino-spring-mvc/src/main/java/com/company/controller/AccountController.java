package com.company.controller;

import com.company.abstractions.storage.IBonusRepository;
import com.company.abstractions.IBonusService;
import com.company.abstractions.storage.IUserRepository;
import com.company.abstractions.IUserService;
import com.company.models.view.*;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import com.company.storage.models.bonus.StorageUserBonusConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBonusRepository bonusRepository;

    @Autowired
    private IBonusService bonusService;

    @GetMapping("/login")
    public String getLogin(Model model) {

            var viewModel = new LoginViewModel();

            model.addAttribute("loginModel", viewModel);

            return "/account/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        var viewModel = new RegisterViewModel();

        model.addAttribute("viewModel", viewModel);

        return "/account/register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("viewModel") RegisterViewModel viewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/account/register";
        }

        if (userRepository.getByLogin(viewModel.getLogin()).isPresent()) {
            var error = new FieldError("viewModel","login", "Given login already exists.");

            bindingResult.addError(error);

            return "/account/register";
        }

        userService.RegisterUser(RegisterViewModel.ToUser(viewModel));

        return "redirect:/account/login";

    }

    @GetMapping("/manage")
    public String getUsers(Model model) {

        var users = userRepository.getAll();

        var roles = userRepository.getRoles();

        var viewModel = new ManageUsersViewModel();

        viewModel.setRoles(roles);

        for (var user : users) {
            viewModel.addUser(user);
        }

        model.addAttribute("viewModel", viewModel);

        return "/account/manage";
    }

    @GetMapping("/manage/add")
    public String getAddRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = userRepository.getByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/manage";
        }

        var roleToAdd = userRepository.getRoles().stream().filter(x -> Objects.equals(x.getId(), role)).findFirst();

        if (roleToAdd.isEmpty()) {
            return "redirect:/account/manage";
        }

        var domain = user.get();

        domain.getRoles().add(roleToAdd.get());

        userService.UpdateUser(domain);

        return "redirect:/account/manage";
    }

    @GetMapping("/manage/remove")
    public String getRemoveRole(@RequestParam String name, @RequestParam Short role, Model model) {
        var user = userRepository.getByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/manage";
        }

        var roleToRemove = userRepository.getRoles().stream().filter(x -> Objects.equals(x.getId(), role)).findFirst();

        if (roleToRemove.isEmpty()) {
            return "redirect:/account/manage";
        }

        var domain = user.get();

        domain.getRoles().remove(roleToRemove.get());

        userService.UpdateUser(domain);

        return "redirect:/account/manage";
    }

    @GetMapping("/profile/index")
    public String getProfile(Authentication authentication, Model model) {
        var name = authentication.getName();

        var user = userRepository.getByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/account/register";
        }

        var viewModel = ProfileViewModel.ToView(user.get());

        model.addAttribute("viewModel", viewModel);

        return "/account/profile";
    }

    @PostMapping("/profile/index")
    public String postProfile(@Valid @ModelAttribute("viewModel") ProfileViewModel viewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/account/profile";
        }

        var user = userRepository.getByLogin(viewModel.getLogin());

        if (user.isEmpty()) {
            return "redirect:/account/register";
        }

        if (!user.get().getPassword().equals(viewModel.getPassword())) {
            var error = new FieldError("viewModel","password", "Password not match with current password.");

            bindingResult.addError(error);

            return "/account/profile";
        }

        user.get().setEmail(viewModel.getEmail());

        userService.UpdateUser(user.get());

        return "redirect:/account/profile/index";

    }

    @GetMapping("/balance")
    public String getBalance(Authentication authentication, Model model) {

        var name = authentication.getName();

        var user = userRepository.getByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/index";
        }

        var viewModel = new AddBalanceViewModel();

        model.addAttribute("viewModel", viewModel);

        return "/account/balance";
    }

    @PostMapping("/balance")
    public String getBalance(Authentication authentication, @Valid @ModelAttribute("viewModel") AddBalanceViewModel viewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/account/balance";
        }

        var name = authentication.getName();

        var user = userRepository.getByLogin(name);

        if (user.isEmpty()) {
            return "redirect:/index";
        }

        bonusService.syncBonuses(user.get());

        var bonuses = bonusRepository.getAll();

        var bonusesUser = bonusService.getBonusesForUser(user.get());

        var bonusKoef = BigDecimal.ONE;

        var userBonuses = new ArrayList<StorageUserBonus>();

        for (var userBonus : bonusesUser) {

            if (userBonus.getConfig()
                    .stream()
                    .filter(x -> x.getName().equals(StorageUserBonusConfig.IS_ENABLED_PARAM_NAME))
                    .findAny().get().getValue().equals("false")) {
                continue;
            }

            var bonus = bonuses.stream().filter(x -> x.getId().equals(userBonus.getBonusId())).findFirst();

            if (bonus.isEmpty() || !bonus.get().getIsEnabled()) {
                continue;
            }

            if (bonus.get().getTriggerActionId().equals(StorageBonus.BALANCE_ADD_ACTION_ID)) {
                bonusKoef = bonusKoef.multiply(bonus.get().getConfig().getBonusKoef());

                userBonuses.add(userBonus);
            }

        }

        userService.ChangeUserBalance(user.get().getLogin(), viewModel.getPositiveBalanceDelta().multiply(bonusKoef));

        for (var userBonus : userBonuses) {
            var countParam = userBonus.getConfig()
                    .stream()
                    .filter(x -> x.getName().equals(StorageUserBonusConfig.COUNT_PARAM_NAME))
                    .findAny();

            if (countParam.isEmpty()) {
                continue;
            }

            countParam.get().setValue(Integer.toString(Integer.parseInt(countParam.get().getValue()) - 1));

            bonusService.changeBonusOfUser(userBonus);
        }

        userService.UpdateAuthorizeUserData(userRepository.getByLogin(name).get());

        return "redirect:/index";
    }
}
