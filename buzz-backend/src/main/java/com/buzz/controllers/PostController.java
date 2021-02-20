package com.buzz.controllers;

import com.buzz.model.*;
import com.buzz.util.BucketUtility;
import com.buzz.util.BuzzCookieManager;
import com.buzz.util.DynamoDBUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class PostController
{

    @Autowired
    BucketUtility bucketUtil;

    @GetMapping("/upload")
    public String getUpload(Model model)
    {
        PostWrapper wrapper = new PostWrapper();
        model.addAttribute("postwrapper", wrapper);
        return "post";
    }

    @PostMapping("/upload")
    public String postUpload(@RequestParam("file") MultipartFile file, @ModelAttribute PostWrapper post, HttpServletRequest request)
    {
        //get cookie
        String name = BuzzCookieManager.value(request.getCookies(), "buzzID");
        //file should be type checked

        //System.out.println("checking if file is null");
        if (file!=null)
        {
            //System.out.println("checking that file type is: .jpg or .png");
            if (!(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(), "image/png")))
            {
                return "error"; // TODO: specify the error, or ensure in the html on specific type is allowed.
            }
        }

        //add apropriate extension
        assert file != null;
        String filename = bucketUtil.genFilename(file.getContentType());
        //System.out.printf("saving file %s\n", filename);
        bucketUtil.saveFile(filename, file);



        //find what type of group is using the post
        GroupFactory superstruct = new GroupFactory();
        superstruct.setId(name);
        DynamoDBUtility.get(superstruct);

        //get group from data base then fill group, to pass it
        Group group;
        if (superstruct.getType()=='b')
        {
            group = new Business("", name);
        }
        else if (superstruct.getType()=='u')
        {
            group = new University("", name, "");
        }
        else if (superstruct.getType()=='c')
        {
            group = new Club("", name);
        }
        else
        {
            return "error";
        }

        //add post to groups post
        Post post1 = new Post(filename, post.getCaption(), group);
        group.createPost(post1);
        DynamoDBUtility.put(post1);


        if (superstruct.getType()=='b')
        {
            group = new Business("", name);
            DynamoDBUtility.put((Business)group);
        }
        else if (superstruct.getType()=='u')
        {
            group = new University("", name, "");
            DynamoDBUtility.put((University)group);

        }
        else if (superstruct.getType()=='c')
        {
            group = new Club("", name);
            DynamoDBUtility.put((Club)group);

        }
        else
        {
            return "error";
        }


        return "feed";
    }
}
