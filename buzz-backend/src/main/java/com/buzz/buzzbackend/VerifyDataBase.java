package com.buzz.buzzbackend;

import com.buzz.model.Account;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.TextUtility;

public class VerifyDataBase
{
    public static void main(String[] args)
    {
        Account a = new Account("","","carmichael@chapman.edu");
        DynamoDBUtility.get(a);

        System.out.println(a);

        String s = "\" this has spaces \"";
        System.out.println(s);
        s = TextUtility.removeSpaces(s);
        System.out.println(s);

    }
}
