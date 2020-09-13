package com.buzz.model;

public class Credential
{


    private String username;
    private String password;

    public Credential(String a, String b)
    {
        this.username = a;
        this.password = b;
    }


    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}
