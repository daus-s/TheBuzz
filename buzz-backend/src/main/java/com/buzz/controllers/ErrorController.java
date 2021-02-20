package com.buzz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController
{
    private static final Logger logger = LogManager.getLogger(ErrorController.class);

    @GetMapping("/error")
    public String error()
    {
        return "error";
    }
}
