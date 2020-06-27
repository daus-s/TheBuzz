package com.buzz.controllers;

import com.buzz.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController
{
    @GetMapping("/register")
    public String registerForm(Model model)
    {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute Account account)
    {
        return "register_confirmation";
    }

}
