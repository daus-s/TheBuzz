package com.buzz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController
{
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @GetMapping("/")
    public String home(@RequestParam(name="name",required=false,defaultValue = "world") String name, Model model)
    {
        model.addAttribute("name", name);
        return "home";
    }
}
