package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.InputType;
import com.buzz.util.DynamoDBUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AccessibleObject;

@Controller
public class VerifyController
{


    @GetMapping("/verify")
    public String getVerify(Model model)
    {
        model.addAttribute("verify", new InputType(""));
        return "verify";
    }


    @PostMapping("/verify")
    public String postVerify(@ModelAttribute InputType verify, HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies  = request.getCookies();
        Cookie profile = new Cookie("default", "default");
        for (Cookie c: cookies)
        {
            if (c.getName().equals("emailName"))
                profile = c;
        }
        Account account = new Account("","", profile.getValue());
        DynamoDBUtility.get(account);
        System.out.println(account);
        System.out.println("parsed verify code: " + verify);
        if (verify.toString().equals(account.getVerifyCode()))
        {
            account.setVerified(true);
            account.setTtl(Integer.MAX_VALUE); //TODO: change ttl to long
            DynamoDBUtility.put(account);
            Cookie cookie = new Cookie("loginStatus", "logged-in");
            cookie.setMaxAge(604800);
            response.addCookie(cookie);
            return "verify_confirmation";
        }

        return "bad_request";
    }
}
