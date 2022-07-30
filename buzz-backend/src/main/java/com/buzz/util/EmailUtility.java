package com.buzz.util;

import com.buzz.model.Account;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class EmailUtility
{
    @Value("${WEBSITE_NAME}")
    String websiteLink;

    /*used to create link from username and a UUID
     *for verification email
    * */
    public String verifyLink(Account account)
    {
        String link = this.websiteLink + "/verify?";
        link += "registrationCode=";
        link += account.getEmail() + "&";
        link += "email=";
        link += account.getEmail();
        return link;
    }

    public String generateContent(Account account, String pathname)
    {
        try
        {
            String content = TextUtility.readFileAsString(pathname);
            content = content.replaceAll("--fName--", account.getFirstName());
            content = content.replace("--code--", account.getVerifyCode());
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
}

