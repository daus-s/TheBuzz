package com.buzz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExploreController
{
    private static final Logger logger = LogManager.getLogger(ExploreController.class);

    @RequestMapping(value="/explore")
    public String college()
    {
        return "there are so many colleges to look at! Let buzz narrow it down for you.";
    }
}
