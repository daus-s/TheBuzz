package com.buzz.buzzbackend;

import org.mindrot.jbcrypt.BCrypt;

public class SaltUpdate
{
    public static void main(String[] args)
    {
        System.out.println(BCrypt.gensalt());
    }
}
