package com.buzz.model;

public class UniversityRegister extends University
{
    private String confirmHashedPwd;

    public UniversityRegister(String displayName, String email, String domain)
    {
        super(displayName, email, domain);
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
