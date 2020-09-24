package com.buzz.model;

public class LoginWrapper
{
    private String pwd1;
    private String pwd2;
    private String phoneNumber;

    public LoginWrapper(String pwd1, String pwd2, String phoneNumber)
    {
        this.pwd1 = pwd1;
        this.pwd2 = pwd2;
        this.phoneNumber = phoneNumber;
    }

    public String getPwd1()
    {
        return pwd1;
    }
    public void setPwd1(String pwd1)
    {
        this.pwd1 = pwd1;
    }

    public String getPwd2()
    {
        return pwd2;
    }
    public void setPwd2(String pwd2)
    {
        this.pwd2 = pwd2;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
