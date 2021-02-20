package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Club extends User implements Group, RowDDB
{
    protected String displayName;
    protected ArrayList<String> followers = new ArrayList<String>();
    protected ArrayList<String> posts = new ArrayList<String>();
    protected String website;
    protected String logoIML = "default.png";

    protected boolean uniApproved;

    protected String affiliation;

    /**
     * The email must be affiliated with the university
     * */
    public Club(String displayName, String email)
    {
        this.displayName = displayName;
        this.email = email;
    }

    public boolean createPost(Post p, boolean privacy) {

        p.setPrivacy(privacy);

        if (uniApproved)
        {
            return posts.add(p.getId());
        }
        else return false;
    }


    public String getDisplayName()
    {
        return this.displayName;
    }
    public void setDisplayName(String d)
    {
        this.displayName = d;
    }

    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String e)
    {
        this.email = e;
    }

    public ArrayList<String> getFollowers()
    {
        return this.followers;
    }
    public void setFollowers(ArrayList<String> l)
    {
        this.followers = l;
    }

    public ArrayList<String> getPosts()
    {
        return this.posts;
    }
    public void setPosts(ArrayList<String> p)
    {
        this.posts = p;
    }

    public String getWebsite()
    {
        return this.website;
    }
    public void setWebsite(String w)
    {
        this.website = w;
    }

    public String getLogoIML()
    {
        return null;
    }
    public void setLogoIML(String i)
    {
        this.logoIML = i;
    }

    public boolean addFollower(Account a)
    {
        return followers.add(a.getEmail());
    }
    public boolean removeFollower(Account a)
    {
        return followers.remove(a.getEmail());
    }

    @Override
    public boolean createPost(Post p)
    {
        return this.posts.add(p.getId());
    }

    @Override
    public boolean deletePost(Post p)
    {
        return this.posts.remove(p.getId());
    }

    public boolean isUniApproved()
    {
        return uniApproved;
    }
    public void setUniApproved(boolean approved)
    {
        this.uniApproved = approved;
    }


    @Override
    public String getTableName()
    {
        return "club-table";
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        if (map != null)
        {
            super.loadModel(map);
            Set<String> keys = map.keySet();
            this.setEmail(map.get("email").getS());
            this.setUniApproved(map.get("uniApproved").getS().contains("true"));
            this.setPwd(map.get("pwd").getS());
            this.setAffiliation(map.get("affiliation").getS());
            this.setLogoIML(map.get("logoIML").getS());
            this.setWebsite(map.get("website").getS());
            this.setDisplayName(map.get("displayName").getS());
        }
        else
        {
            System.out.format("No item found with the key %s!\n", email);
        }
    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues =  super.getModelAttributes();
        itemValues.put("displayName", new AttributeValue(this.displayName));
        if (this.followers.size()>0)
            itemValues.put("followers", new AttributeValue(this.followers));
        if (this.posts.size()>0)
            itemValues.put("posts", new AttributeValue(this.posts.toString()));
        itemValues.put("website", new AttributeValue(this.website));
        itemValues.put("logoIML", new AttributeValue(this.logoIML));
        itemValues.put("affiliation", new AttributeValue(this.affiliation));
        itemValues.put("uniApproved", new AttributeValue(this.uniApproved + ""));
        return itemValues;
    }

    @Override
    public String getKey()
    {
        return "email";
    }
    @Override
    public String getKeyValue()
    {
        return this.email;
    }


    public String getAffiliation()
    {
        return this.affiliation;
    }

    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }
}
