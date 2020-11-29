package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Business;
import com.buzz.model.Club;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController
{
    private static final Logger logger = LogManager.getLogger(BusinessRegisterController.class);

    @GetMapping("/profile/{identifier}")
    String display(@RequestParam(required = true)String identifier, @RequestParam Model model)
    {
        if (identifier.split("--", 2)[0].equals("business"))
        {
            Business b = new Business("",identifier);
            DynamoDBUtility.get(b);
            model.addAttribute("profile", b);
            return "business-profile";
        }
        if (identifier.split("--", 2)[0].equals("account"))
        {
            Account a = new Account("", "", identifier);
            DynamoDBUtility.get(a);
            model.addAttribute("profile", a);
            return "account-profile";

        }
        if (identifier.split("--", 2)[0].equals("university"))
        {
            University u = new University("","", identifier);
            DynamoDBUtility.get(u);
            model.addAttribute("profile", u);
            return "uni-profile";

        }
        if (identifier.split("--", 2)[0].equals("organization"))
        {
            Club c = new Club("",identifier);
            DynamoDBUtility.get(c);
            model.addAttribute("profile", c);
            return "club-profile";
        }
        return "bad-request";
    }
}
