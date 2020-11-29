package com.buzz.controllers;

import com.buzz.model.*;
import com.buzz.util.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class CreateGroupController
{
    private static final Logger logger = LogManager.getLogger(CreateGroupController.class);


    @RequestMapping("/creategroup")
    public String registerForm()
    {
        return "group_factory_register";
    }

}
