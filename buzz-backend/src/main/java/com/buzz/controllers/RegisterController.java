package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Email;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.EmailSender;
import com.buzz.util.EmailUtility;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController
{
    @Autowired
    EmailSender sender;

    @GetMapping("/register")
    public String registerForm(Model model)
    {
        Account a = new Account();
        a.setFirstName("");
        a.setLastName("");
        a.setEmail("");
        model.addAttribute("account", a);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute Account account)
    {

        account.setHashedPassword(BCrypt.hashpw(account.getHashedPassword(), BCrypt.gensalt()));
        System.out.println(account);
        DynamoDBUtility.put(account);
        //send email
        Email email = new Email(account.getEmail(), "src/main/resources/emails/verification.txt"); //this should direct to the email file in resources for registering an account
        sender.send(email, account);

        //create account

        return "register_confirmation";
    }

}
