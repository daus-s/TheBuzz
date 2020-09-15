package com.buzz.controllers;

import com.buzz.model.*;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.EmailSender;
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
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreateGroupController
{
    @Value("${BCRYPT_SALT}")
    String salt;

    @Autowired
    EmailSender sender;

    @GetMapping("/group-register")
    public String registerForm(Model model)
    {
        GroupFactory factory = new GroupFactory();
        return "group-register";
    }

    @PostMapping("/group-register")
    public String registerSubmit(@ModelAttribute GroupFactory factory, HttpServletResponse response)
    {

        return "";
    }
}
