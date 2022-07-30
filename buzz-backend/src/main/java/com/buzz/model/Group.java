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

    public ArrayList<String> getPosts();
    public void setPosts(ArrayList<String> id);

    public String getWebsite();
    public void setWebsite(String w);

    public String getLogoIML();
    public void setLogoIML(String i);

    public boolean addFollower(Account a);
    public boolean removeFollower(Account a);

    public boolean createPost(Post p);
    public boolean deletePost(Post p);
}
