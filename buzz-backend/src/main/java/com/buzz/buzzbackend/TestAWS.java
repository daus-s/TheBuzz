package com.buzz.buzzbackend;

import com.buzz.model.Account;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.TextUtility;

import java.io.IOException;

public class TestAWS
{
    public static void main(String[] args)
    {
        University u = new University("University of Washington", "dilt@uw.edu", "uw.edu");
        DynamoDBUtility.put(u); //error--------
        Account account = new Account("f","l", "test@uw.edu");
        DynamoDBUtility.put(account);

        Account a = new Account("--","--","test@uw.edu");
        DynamoDBUtility.get(a);
        System.out.println(a);

//        for (int i = 0; i < 100000; i++)
//        {
//            System.out.println(createVerifyCode());
//        }
    }


    public static String generateContent(Account account, String pathname)
    {
        try
        {
            String content = TextUtility.readFileAsString(pathname);
            content = content.replaceAll("--fName--", account.getFirstName());
            content = content.replace("--link--", "asd##sad123");
            return content;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Error in reading " +  pathname +  ". Something went wrong while converting to String data OR could not find the file name.");
            //TODO: error reporting method
            return "--ERROR--";
        }
    }

    public static String createVerifyCode()
    {
        int i = (int)(Math.random()*1000000 + 1);
        String s = i + "";

        while (s.length()<6)
        {
            s = "0" + s;
        }

        return s;
    }
}
