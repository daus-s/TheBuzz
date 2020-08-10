package com.buzz.model;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.DynamoDBUtility;
import com.buzz.util.TextUtility;

import java.io.IOException;
import java.util.*;

public class Account extends Item implements RowDDB
{
    /**
     * first name, preferred name, not necessary to be legal name as no
     * features require at the time. this field is required for the account creation
     * and does not need to be unique
     */
    private String firstName;                                           //required

    /**
     * last name, should be legal but no verification of this. also does not need to be unique.
     * required by the constructor to create account
     */
    private String lastName;                                            //required

    /**
     * email, used as key in the account database. MUST be unique and is
     * required in the constructor as well. the domain is used to check
     * student status
     */
    private String email;                                               //required

    /**
     * stored using B-crypt and is passed in through the /register page
     * non unique
     *
     * requirements, 8+ chars
     */
    private String hashedPassword;                                      //required

    /**
     * verfied; determines if the account can perform actions at all\
     * verfied is the value of whether the account has a valid email and
     * has responded to and clicked the link to confirm the email is correct
     */
    private boolean verified = false;                                   //non constructor

    /**
     * boolean variable whether the student belongs to a university or not
     * determined solely from the email extension, but checked by the verified boolean
     *
     * e.g. if a malicious user wanted to create an account ending in the university domain
     *      they could, however, they would be unable to perform any actions due to the
     *      verified boolean being false. which can only be set with access to the email
     */
    private boolean student = false;                                    //generate in constructor


    /**
     * value used to verfiy account in the verify webpage. used to check equality with user input
     * sent in email
     *
     * 6 digit code generated
     */
    private String verifyCode;

    /**
     *  all the groups that the
     */
    private ArrayList<String> followingIDs = new ArrayList<String>();   //non constructor


    /**
     * the domain(unique field) of the school the student(account currently attends)
     * used to generate the feed
     */
    private String currentSchoolID;                                //generate in constructor

    /**
     * determines the time for when the account should be deleted from the database
     * until verified this value is 1 day
     * after verification, this value is 5 years
     * value adds a year every login when the time remaining is less than a year
     */
    private int ttl;                                                    //constant-86400s -24 hrs

    //email
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    //last name
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    //first name
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    //verified
    public boolean isVerified()
    {
        return verified;
    }
    public void setVerified(boolean verified)
    {
        this.verified = verified;
    }

    //following arraylist
    public ArrayList<String> getFollowingIDs()
    {
        return followingIDs;
    }
    public void setFollowingIDs(ArrayList<String> followingIDs)
    {
        for (String key: followingIDs)
        {
            //this.follow(DynamoDBUtility.getGroup());
        }
        this.followingIDs = followingIDs;
    }

    //current school
    public String getCurrentSchoolID()
    {
        return currentSchoolID;
    }
    public void setCurrentSchoolID(String currentSchoolID)
    {
        this.currentSchoolID = currentSchoolID;
    }
    //Student status
    public boolean isStudent()
    {
        return student;
    }
    public void setStudent(boolean student)
    {
        this.student = student;
    }

    //not password
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    //ttl NUMBER value
    public int getTtl()
    {
        return ttl;
    }
    public void setTtl(int ttl)
    {
        this.ttl = ttl;
    }

    //verify code
    public String getVerifyCode()
    {
        return verifyCode;
    }
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }

    public Account(String firstName, String lastName, String email)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.hashedPassword = "defaultPassword1";
        this.currentSchoolID = email.substring(email.indexOf("@")+1);
        this.ttl = ((int) (System.currentTimeMillis()/1000))+86400;
        this.verifyCode = this.createVerifyCode();

        try
        {
            String domains = TextUtility.readFileAsString("src/main/resources/udomains.txt");
            if (domains.contains(this.currentSchoolID))
            {
                this.student = true;
                //TODO: get university from ddb to set values
                followingIDs.add(this.currentSchoolID);

                RowDDB temp = new University("", "", this.currentSchoolID);
                DynamoDBUtility.get(temp);
                DynamoDBUtility.put(temp);
            }
        }
        catch (IOException io)
        {
            io.printStackTrace();
            System.out.println("IOException in file \"resources/udomains.txt\"");
        }
    }

    public Account()
    {
        this.firstName = "fName";
        this.lastName  = "lName";
        this.email = "default@thebuzz.com";
        this.verifyCode = this.createVerifyCode();
    }

    //utility


    /**
     * @param r
     * @return
     */
    public boolean unfollow(Group r)
    {
        return followingIDs.remove(r.getEmail());
    }

    /**
     * @param f
     * @return
     */
    public boolean follow(Group f)
    {
        if (!followingIDs.contains(f.getEmail()))
        {
            followingIDs.add(f.getEmail());
            return true;
        }
        return false;
    }

    /**
     * @return object as a string with all fields
     */
    public String toString()
    {
        return "firstName:" + this.firstName + "\nlastName:" + this.lastName + "\nemail:" + this.email + "\nverified:" + this.verified + "\nstudent:" + this.student + "\nfollowingIDs:" + followingIDs.toString() + "\ncurrentSchoolID:" + this.currentSchoolID;
    }


    /**
     * @return string of an integer between 0 and 999999, with length set to 6
     */
    public String createVerifyCode()
    {
        int i = (int)(Math.random()*1000000 + 1);
        String s = i + "";

        while (s.length()<6)
        {
            s = "0" + s;
        }

        return s;
    }



    /**
     * interface methods
     *
     *
     * */


    @Override
    public String getTableName()
    {
        return "account-table";
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        if (map != null)
        {
            Set<String> keys = map.keySet();
            this.setEmail(map.get("email").getS());
            this.setFirstName(map.get("firstName").getS());
            this.setLastName(map.get("lastName").getS());
            this.setVerified(map.get("verified").getS().contains("true"));
            this.setStudent(map.get("student").getS().contains("true"));
            this.setHashedPassword(map.get("hashedPassword").getS());
            this.setCurrentSchoolID(map.get("currentSchoolID").getS());
        }
        else
        {
            System.out.format("No item found with the key %s!\n", email);
        }
    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();

        itemValues.put("email", new AttributeValue(this.getEmail()));
        itemValues.put("firstName", new AttributeValue(this.firstName));
        itemValues.put("lastName", new AttributeValue(this.lastName));
        itemValues.put("hashedPassword", new AttributeValue(this.hashedPassword));
        itemValues.put("verified", new AttributeValue(this.verified+""));
        itemValues.put("student", new AttributeValue(this.student+""));
        itemValues.put("followingIDs", new AttributeValue(this.followingIDs));
        itemValues.put("currentSchoolID", new AttributeValue(this.currentSchoolID));
        itemValues.put("ttl", new AttributeValue(this.ttl + ""));

        return itemValues;
    }

    @Override
    public String getKey()
    {
        return "email";
    }

    @Override
    public String getKeyValue()
    {
        return this.email;
    }


}
