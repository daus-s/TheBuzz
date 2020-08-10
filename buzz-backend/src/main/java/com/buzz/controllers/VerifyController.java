package com.buzz.controllers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.buzz.model.Account;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.ModelUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VerifyController
{


    @GetMapping("/verify")
    public String getVerify()
    {
        return "verify";
    }


    @PostMapping("/verify")
    public String postVerify()
    {
        return "bad_request";
    }
}
