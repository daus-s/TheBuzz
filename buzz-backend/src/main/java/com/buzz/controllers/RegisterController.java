package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.AccountRegister;
import com.buzz.model.Email;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.EmailSender;
import com.buzz.util.EmailUtility;
import com.buzz.util.TextUtility;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class RegisterController
{
    @Value("${BCRYPT_SALT}")
    String salt;

    @Autowired
    EmailSender sender;

    @GetMapping("/register")
    public String registerForm(Model model)
    {
        AccountRegister a = new AccountRegister();
        a.setFirstName("");
        a.setLastName("");
        a.setEmail("");
        model.addAttribute("account", a);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute AccountRegister account, HttpServletResponse response)
    {
        String pwd = BCrypt.hashpw(account.getHashedPassword(), salt);
        Account written = new Account(account.getFirstName(), account.getLastName(), account.getEmail());

        written.setHashedPassword(pwd);
        University u = new University("","", written.getCurrentSchoolID());
        DynamoDBUtility.get(u);
        written.follow(u);


        Cookie c1 = new Cookie("emailName", account.getEmail());
        Cookie c2 = new Cookie("firstName", TextUtility.removeSpaces(account.getFirstName())); //TODO: change to .getPrefferedName()/.getNickname();
        response.addCookie(c1);
        response.addCookie(c2);

        //System.out.println(account);
        DynamoDBUtility.put(written);
        //send email
        Email email = new Email(account.getEmail(), "src/main/resources/emails/verification.txt"); //this should direct to the email file in resources for registering an account
        sender.send(email, written);

        //create account

        return "register_confirmation";
    }

}
