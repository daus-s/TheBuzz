package com.buzz.util;

import javax.servlet.http.Cookie;

public class BuzzCookieManager
{

    public static String value(Cookie[] cookies, String name)
    {
        for (Cookie c: cookies)
        {
            if (c.getName().equals(name))
            {
                return c.getValue();
            }
        }
        return "default";
    }
}
