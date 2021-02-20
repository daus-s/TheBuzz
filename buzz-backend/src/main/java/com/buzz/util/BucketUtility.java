package com.buzz.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Component
public class BucketUtility
{
    @Value("${STORAGE_PATH}")
    String storageLocation;

    public String genFilename(String type)
    {
        return storageLocation + UUID.randomUUID().toString() + TextUtility.extFromType(type);
    }

    public boolean saveFile(String filename, MultipartFile file)
    {
        try
        {
            File data = new File(filename); //check not exist?
            FileWriter writer = new FileWriter(filename);

            try (FileOutputStream fos = new FileOutputStream(filename))
            {
                fos.write(file.getBytes());
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
            //log 4 j
            return false;
        }
        return true;
    }
}
