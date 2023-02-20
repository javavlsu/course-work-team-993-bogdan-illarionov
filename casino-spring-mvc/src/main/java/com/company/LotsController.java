package com.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LotsController {

    @GetMapping("lots/a")
    public String getLotA(Model model)
    {
        return "/lots/a";
    }
}
