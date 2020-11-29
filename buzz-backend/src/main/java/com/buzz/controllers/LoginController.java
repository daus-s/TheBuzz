package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Credential;
import com.buzz.model.Email;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController
{
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Value("${BCRYPT_SALT}")
    String salt;

    @GetMapping("/login")
    public String loginForm(Model model)
    {
        Credential c = new Credential("", "");
        model.addAttribute("cred", c);
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Credential c, HttpServletResponse response)
    {

        //check login
        Account account = new Account("", "", c.getUsername());
        DynamoDBUtility.get(account);

        String hash = account.getHashedPassword();

        String inputHashed = BCrypt.hashpw(c.getPassword(), salt);

        if (hash.equals(inputHashed))
        {
            //put session information
            Cookie cookie = new Cookie("loginStatus", "logged-in");
            //cookie.setSecure(true);
            cookie.setMaxAge(604800);
            response.addCookie(cookie);
            return "home";
        }
        else return "login";
    }
}
