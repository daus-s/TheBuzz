package com.buzz.controllers;

import com.amazonaws.services.dynamodbv2.xspec.B;
import com.buzz.model.Account;
import com.buzz.model.Business;
import com.buzz.model.Email;
import com.buzz.model.University;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusinessRegisterController
{
    private static final Logger logger = LogManager.getLogger(BusinessRegisterController.class);

    @Value("${BCRYPT_SALT}")
    String salt;

    @Autowired
    EmailSender sender;

    @GetMapping("/creategroup/business")
    public String businessRegisterForm(Model model)
    {
        Business b = new Business("","");
        model.addAttribute("business", b);
        return "business-register";
    }

    @PostMapping("/creategroup/business")
    public String businessRegisterSubmit(@ModelAttribute Business business)
    {
        String pwd1 = BCrypt.hashpw(business.getHashedPwd1(),salt);
        String pwd2 = BCrypt.hashpw(business.getHashedPwd2(), salt);

        business.setHashedPwd1(pwd1);
        business.setHashedPwd2(pwd2);

        DynamoDBUtility.put(business);
        Email email = new Email(business.getEmail(), "src/main/resources/emails/verification.txt");
        sender.send(email, new Account(business.getDisplayName(),"", business.getEmail()));

        return "confirm-phone-number";
    }

}
