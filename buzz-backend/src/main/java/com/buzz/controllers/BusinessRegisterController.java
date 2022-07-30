package com.buzz.controllers;

import com.amazonaws.services.dynamodbv2.xspec.B;
import com.buzz.model.*;
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
    public String businessRegisterSubmit(@ModelAttribute BusinessRegister br)
    {
        String pwd1 = BCrypt.hashpw(br.getPwd(),salt);
        String pwd2 = BCrypt.hashpw(br.getConfirmHashedPwd(),salt);

        br.setPwd(pwd1);
        br.setConfirmHashedPwd(pwd2);

        DynamoDBUtility.put(br);
        Email email = new Email(br.getEmail(), "src/main/resources/emails/verification.txt");
        sender.send(email, new Account(br.getDisplayName(),"", br.getEmail()));

        return "confirm-phone-number";
    }

}
