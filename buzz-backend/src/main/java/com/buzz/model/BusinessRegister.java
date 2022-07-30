package com.buzz.model;

public class BusinessRegister extends Business
{
    private String confirmHashedPwd;

    public BusinessRegister(String email)
    {
        super(email);
    }

    public String getConfirmHashedPwd()
    {
        return confirmHashedPwd;
    }

    public void setConfirmHashedPwd(String confirmHashedPwd)
    {
        this.confirmHashedPwd = confirmHashedPwd;
    }
}
