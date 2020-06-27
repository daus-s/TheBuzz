package com.buzz.util;

import com.buzz.model.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class EmailSender
{
    /*Email:
     * send an email to the to address routed through the company SES called->(ese)
     * from the single "thebuzzemail" account with content determined on what the
     * message is, current messages include, email verification, forgot your password
     * */
    public static void send(Email email) {
        String file_s = "";
        //TODO: change to html file
        try
        {
            file_s = TextUtility.readFileAsString(email.getContentName());
        }
        catch (IOException io)
        {
            System.out.println(io);
        }

        Address address = new InternetAddress();
        try
        {
            address = new InternetAddress(email.getTo());

        }
        catch (javax.mail.internet.AddressException addy)
        {
            System.out.println(addy);
        }

        Properties props = new Properties();
        //TODO: make this for the SES(the ese)
        props.put("mail.smtp.host", "smtp.cgrmtn-carmichaels.com"); // the ese when it is setup
        props.put("mail.smtp.port", "465");
        props.put("mail.user", "toddalt1@cgrmtn-carmichaels.com");
        Session session = Session.getInstance(props, null);

        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("test.at.thebuzz@gmail.com"); //TODO: make this environment variable

            msg.setRecipient(Message.RecipientType.TO, address);

            msg.setSubject("The Buzz email services Test: Java");
            msg.setSentDate(new Date());
            msg.setText(file_s);

            //this will be the ese login
            //TODO: hide this when pushing code to github/servers
            Transport.send(msg, "", "");
        }
        catch (MessagingException mex)
        {
            System.out.println("send failed, exception: " + mex);
            mex.printStackTrace();
        }
    }
}
