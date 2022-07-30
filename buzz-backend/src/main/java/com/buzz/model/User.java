package com.buzz.model;


import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

public class User implements RowDDB
{
    /**
     * email, used as key in the account database. MUST be unique and is
     * required in the constructor as well. the domain is used to check
     * student status
     *
     * The email must be unique(it is the only existence of the email in the database,
     * across all tables, both account and group profiles) and is used as the key in
     * the User dynamoDB table, "user-table".
     *
     */
    protected String email;

    /**
     * stored using B-crypt and is passed in through the /register page
     * non unique
     *
     * requirements, 8+ chars
     */
    protected String pwd;


    protected char type;

    public String getTableName()
    {
        return "user-table";
    }

    public void loadModel(Map<String, AttributeValue> map)
    {
        if (map != null)
        {
            this.type = map.get("type").getS().charAt(0);
            this.email = map.get("email").getS();
            this.pwd = map.get("pwd").getS();
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
        itemValues.put("email", new AttributeValue(this.email));
        itemValues.put("type", new AttributeValue(this.type + ""));
        itemValues.put("pwd", new AttributeValue(this.pwd));
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

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    public String getPwd()
    {
        return this.pwd;
    }

    public void setType(char type)
    {
        this.type = type;
    }
    public char getType()
    {
        return this.type;
    }


    public User createUserData()
    {
        User abs = new User();
        abs.setEmail(this.email);
        abs.setPwd(this.pwd);
        abs.setType(this.type);
        return abs;
    }
}
