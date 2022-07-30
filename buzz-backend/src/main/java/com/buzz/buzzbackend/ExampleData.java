package com.buzz.buzzbackend;

import com.buzz.model.*;
import com.buzz.util.DynamoDBUtility;
import org.mindrot.jbcrypt.BCrypt;

public class ExampleData
{
    private static String salt = "$2a$10$FviacUpiy4tu.Jxf6/oK..9=Dc5g=:pg@M}X9J&}-I@Rg`,M0Y7nH6";

    public static void main(String[] args)
    {
        account();
        business();
        university();
        club();


    }

    public static void account()
    {
        Account account = new Account();
        account.setVerified(true);
        account.setEmail("user1@daus-imaginary-company.com");
        String pwd = BCrypt.hashpw("poggersACC1", salt);
        account.setPwd(pwd);
        account.setStudent(true);
        account.setTtl(Integer.MAX_VALUE);
        account.setFirstName("hasan");
        account.setLastName("abi");
        account.setVerifyCode("123456");
        account.setCurrentSchoolID("school@daus-imaginary-company.edu");
        account.setType('a');

        boolean a = DynamoDBUtility.put(account.createUserData());
        boolean b = DynamoDBUtility.put(account);

        System.out.printf("put meta data success: %b\nput account info success: %b\n", a, b);
    }

    public static void business()
    {
        Business business = new Business("busy@daus-imaginary-company.com");
        String pwd = BCrypt.hashpw("basedChamp10", salt);
        business.setPwd(pwd);
        business.setAddress("69420 W Chapman Ave.");
        business.setPhoneNumber("(206)427-6419");
        business.setDisplayName("Bobby's got burgers");
        business.setWebsite("www.pornhub.com");
        business.setType('b');

        boolean a = DynamoDBUtility.put(business.createUserData());
        boolean b = DynamoDBUtility.put(business);

        System.out.printf("put meta data success: %b\nput account info success: %b\n", a, b);

    }

    public static void university()
    {
        University university = new University("University of Baste", "school@daus-imaginary-company.edu", "knowledge.edu");
        String pwd = BCrypt.hashpw("secureServer2", salt);
        university.setPwd(pwd);
        university.setWebsite("knowledge.edu");
        university.setType('u');
        university.setDomain("knowledge.edu");

        boolean a = DynamoDBUtility.put(university.createUserData());
        boolean b = DynamoDBUtility.put(university);

        System.out.printf("put meta data success: %b\nput account info success: %b\n", a, b);
    }

    public static void club()
    {
        Club club = new Club("virgins club at knowledge university", "virgins@daus-imaginary-company.edu");
        String pwd = BCrypt.hashpw("iveNeverHadSex", salt);
        club.setPwd(pwd);
        club.setWebsite("virgins.ins");
        club.setType('c');
        club.setAffiliation("school@knowledge.edu");

        boolean a = DynamoDBUtility.put(club.createUserData());
        boolean b = DynamoDBUtility.put(club);

        System.out.printf("put meta data success: %b\nput account info success: %b\n", a, b);
    }


}
