package com.buzz.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExploreController
{
    @RequestMapping(value="/explore")
    public String college()
    {
        return "there are so many colleges to look at! Let buzz narrow it down for you.";
    }
}
