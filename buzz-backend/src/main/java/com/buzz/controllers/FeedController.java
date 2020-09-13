package com.buzz.controllers;

import com.buzz.model.Account;
import com.buzz.util.BuzzCookieManager;
import com.buzz.util.DynamoDBUtility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

public class FeedController
{
    @GetMapping("/feed")
    public String getVerify(HttpServletRequest request)
    {
        String s = BuzzCookieManager.value(request.getCookies(), "emailName");
        if (!s.equals("default"))
        {
            Account a = new Account();
            a.setEmail(s);
            DynamoDBUtility.get(a);

            //save off feed object here
        }

        return "feed";
    }

}
