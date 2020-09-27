package com.buzz.controllers;

import com.buzz.model.*;
import com.buzz.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class CreateGroupController
{

    @RequestMapping("/creategroup")
    public String registerForm()
    {
        return "group_factory_register";
    }

}
