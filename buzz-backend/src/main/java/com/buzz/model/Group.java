package com.buzz.model;

import java.util.ArrayList;

public interface Group
{
    public String getDisplayName();
    public void setDisplayName(String d);

    public String getEmail();
    public void setEmail(String e);

    public ArrayList<String> getFollowers();
    public void setFollowers(ArrayList<String> l);

    public ArrayList<Post> getPosts();
    public void setPosts(ArrayList<Post> p);

    public String getWebsite();
    public void setWebsite(String w);

    public String getLogoIML();
    public void setLogoIML(String i);


}
