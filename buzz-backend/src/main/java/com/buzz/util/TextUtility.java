package com.buzz.util;

import com.buzz.model.Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextUtility
{
    public static String readFileAsString(String filePath) throws IOException
    {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[1024];
        int numRead=reader.read(buf);

        while(numRead != -1)
        {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            numRead=reader.read(buf);
        }
        reader.close();
        return fileData.toString();
    }


    public static String removeSpaces(String given)
    {
        String replace = "";

        for (int i = 0; i < given.length(); i++)
        {
            if (given.charAt(i)==' ')
                given = given.substring(0,i) + "-" + given.substring(i+1);
        }
        return given;
    }
}
