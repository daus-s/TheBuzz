package com.buzz.controllers;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.buzz.model.University;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UniversityRegisterController
{
    @GetMapping("/creategroup/university")
    public String getRegister(Model m)
    {
        University u  = new University("","","");
        m.addAttribute("university", u);
        return "register_university";
    }

    @PostMapping
    public String postRegister(University u)
    {

        return "register_confirmation";
    }
}
