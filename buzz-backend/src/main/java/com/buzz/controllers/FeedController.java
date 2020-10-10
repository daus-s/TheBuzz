package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Feed;
import com.buzz.util.BuzzCookieManager;
import com.buzz.util.DynamoDBUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FeedController
{
    @RequestMapping("/feed")
    public String getVerify(Model model, HttpServletRequest request)
    {
        String s = BuzzCookieManager.value(request.getCookies(), "emailName");
        if (!s.equals("default"))
        {
            Account a = new Account();
            a.setEmail(s);
            DynamoDBUtility.get(a);

            Feed feed = new Feed(a);
            model.addAttribute("feed", feed);
        }

        return "feed";
    }

}
