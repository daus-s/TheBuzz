package com.buzz.util;

import com.buzz.model.Account;
import com.buzz.model.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender
{
    @Value("${EMAIL_SERVER_HOST}")
    String emailServerHost;

    @Value("${EMAIL_USERNAME}")
    String username;

    @Value("${EMAIL_PASSWORD}")
    String pwd;


    /*Email:
     * send an email to the to address routed through the company SES called->(ese)
     * from the single "thebuzzemail" account with content determined on what the
     * message is, current messages include, email verification, forgot your password
     * */
    public void send(Email email, Account a) {
        String file_s = "";
        //TODO: change to html file

        file_s = new EmailUtility().generateContent(a, email.getContentName());


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
        props.put("mail.smtp.host", emailServerHost); // the ese when it is setup
        props.put("mail.smtp.port", "587");
        props.put("mail.user", username);
        System.out.println(pwd);
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
            Transport.send(msg, username, pwd);
        }
        catch (MessagingException mex)
        {
            System.out.println("send failed, exception: " + mex);
            mex.printStackTrace();
        }
    }
}
