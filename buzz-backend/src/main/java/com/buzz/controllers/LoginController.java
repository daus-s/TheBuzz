package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Email;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class LoginController
{
    @GetMapping("/login")
    public String loginForm(Model model)
    {
        return "login";
    }

    @PostMapping("/register")
    public String loginSubmit(@ModelAttribute Account account)
    {

        //check login
        String hash = account.getHashedPassword();
        DynamoDBUtility.get(account);
        if (hash.equals(account.getHashedPassword()))
        {
            //put session information
            return "home";
        }
        else return "pwd_error";
    }
}
