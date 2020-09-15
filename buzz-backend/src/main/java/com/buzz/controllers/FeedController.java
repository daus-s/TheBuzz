package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.model.Feed;
import com.buzz.util.BuzzCookieManager;
import com.buzz.util.DynamoDBUtility;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class FeedController
{
    @GetMapping("/feed")
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
