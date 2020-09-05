package com.buzz.buzzbackend;

import com.buzz.model.Account;
import com.buzz.util.DynamoDBUtility;

public class WriteToDDB
{
    public static void main(String[] args)
    {
        DynamoDBUtility.put(new Account("daus", "c", "carmichael@chapman.edu"));
    }
}
