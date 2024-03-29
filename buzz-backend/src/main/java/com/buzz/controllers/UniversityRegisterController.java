package com.buzz.controllers;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.buzz.model.University;
import com.buzz.model.UniversityRegister;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.TextUtility;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class UniversityRegisterController
{
    @Value("${BCRYPT_SALT}")
    String salt;

    @GetMapping("/creategroup/university")
    public String getRegister(Model m)
    {
        University u  = new University("","","");
        m.addAttribute("university", u);
        return "register_university";
    }

    @PostMapping
    public String postRegister(UniversityRegister ur, HttpServletResponse response)
    {
        String kvp = ",{\"name\":\"" + ur.getDisplayName() + ",\"domain\":\"" + ur.getDomain() + "\"}\n";
        File file = new File("../resources/static/university-domain.js");
        try
        {
            String fileString = TextUtility.readFileAsString("../resources/static/university-domain.js");
            FileWriter writer = new FileWriter("../resources/static/university-domain.js", false);
            if (fileString.contains(ur.getDomain()) || fileString.contains(ur.getDisplayName()))
            {
                System.out.println("already exists! please ensure you are not recreating an account.");
                return "account-already-exists";
            }
            else
            {
                fileString = fileString.substring(0,fileString.length()-3);
                fileString += kvp;
                writer.write(fileString);
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
            System.out.println("could not access \"university-domain.js\" in \"resources/static/\"");
        }

        Cookie c1 = new Cookie("emailName", ur.getEmail());
        Cookie c2 = new Cookie("firstName", TextUtility.removeSpaces(ur.getDisplayName())); //TODO: change to .getPrefferedName()/.getNickname();
        response.addCookie(c1);
        response.addCookie(c2);

        String p1 = ur.getPwd();
        String p2 = ur.getConfirmHashedPwd();

        p1 = BCrypt.hashpw(p1, salt);
        p2 = BCrypt.hashpw(p2, salt);

        ur.setPwd(p1);
        ur.setConfirmHashedPwd(p2);

        University u = ur;
        DynamoDBUtility.put(u);

        return "confirm-phone-number";
    }
}
