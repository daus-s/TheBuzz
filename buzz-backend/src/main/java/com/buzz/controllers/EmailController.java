package com.buzz.controllers;

import com.buzz.model.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController
{
    private static final Logger logger = LogManager.getLogger(EmailController.class);

    @GetMapping("/email")
    public String verificationForm(Model m)
    {
        m.addAttribute("email", new Email("", "")); //1 directs to the verify email message in resources/emails
        return "email";
    }

    @PostMapping("/email")
    public String verifySubmit(@ModelAttribute Email e)
    {
        return "result";
    }
}
