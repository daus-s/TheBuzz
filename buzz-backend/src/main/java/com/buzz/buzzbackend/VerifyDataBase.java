package com.buzz.buzzbackend;

import com.buzz.model.Account;
import com.buzz.util.DynamoDBUtility;

public class VerifyDataBase
{
    public static void main(String[] args)
    {
        Account a = new Account("","","carmichael@chapman.edu");
        DynamoDBUtility.get(a);

        System.out.print(a);
    }
}
