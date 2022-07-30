package com.buzz.model;

public class AccountRegister extends Account
{
    private String confirmHashedPwd;


    public String getConfirmHashedPwd()
    {
        return confirmHashedPwd;
    }

    public void setConfirmHashedPwd(String confirmHashedPwd)
    {
        this.confirmHashedPwd = confirmHashedPwd;
    }
}
