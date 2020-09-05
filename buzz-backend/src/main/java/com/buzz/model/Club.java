package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.TextUtility;
import org.attoparser.util.TextUtil;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Map;

public class Club extends GroupFactory implements Group, RowDDB
{
    private String displayName;
    private String email;
    private ArrayList<String> followers;
    private ArrayList<Post> posts;
    private String id;
    private String website;
    private String logoIML;

    private boolean uniApproved;

    /**
     * This field is the University to which the group is affiliated. The University must approve of the
     * group in order for the group to create posts .
     */
    private University affiliation;


    /**
     * The email must be affiliated with the university
     * */
    public Club(String displayName, String email)
    {
        this.displayName = displayName;
        this.email = email;
        this.id = TextUtility.removeSpaces(displayName) + email.substring(email.indexOf("@"));
    }

    public boolean createPost(Post p, boolean privacy) {

        p.setPrivacy(privacy);

        if (uniApproved)
        {
            return posts.add(p);
        }
        else return false;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
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

    public ArrayList<Post> getPosts()
    {
        return this.posts;
    }
    public void setPosts(ArrayList<Post> p)
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

    public University getAffiliation()
    {
        return affiliation;
    }
    public void setAffiliation(University affiliation)
    {
        this.affiliation = affiliation;
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

    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        return null;
    }

    @Override
    public String getKey()
    {
        return "id";
    }
    @Override
    public String getKeyValue()
    {
        return this.id;
    }


}
