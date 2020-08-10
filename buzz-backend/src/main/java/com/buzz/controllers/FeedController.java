package com.buzz.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class FeedController
{
    @GetMapping("/feed")
    public String getVerify()
    {
        return "feed";
    }

}
