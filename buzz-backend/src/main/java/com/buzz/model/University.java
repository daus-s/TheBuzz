package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.DynamoDBUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class University extends GroupFactory implements RowDDB, Group
{


    /**
     * The display name is the name that appears with the post and is linked to the
     * account. This does not need to be unique, however it often should, the key is not the email,
     * rather the id field generated on this field.
     */
    private String displayName = "group"; //defaults

    /**
     * The email must be unique(it is the only existence of the email in the database,
     * across all tables, both account and group profiles) and is used as the key in
     * the dynamoDB for Groups.
     */
    private String email = "a@b.com"; //defaults

    /**
     * The follower Arraylist(AL) contains all accounts that follow the account.
     * The number of followers is accessible through the arraylist, but is not important
     * as of v0.1. This is the list that will receive Posts from the group. When
     * following or un-following, the code must ensure the action is completed on both sides
     * with preference going to the Group if there is a discrepancy.
     */
    private ArrayList<String> followers = new ArrayList<String>();
    /**
     * List of all post that have not been deleted or expired based on time-to-live(later version)
     * Represented on the Groups page and displayed there. The caption and image are the only important
     * parts when being represented on the groups page.
     * */
    private ArrayList<Post> posts = new ArrayList<Post>();

    /**
     * Website that is displayed on the profile of the group. Non-unique requirement, however, it often
     * should be.
     */
    private String website = "thebuzz.com";

    /**
     * This is the address of the logo in the S3 that is used as the universities "profile picture."
     * See mock rendering of app design to see image
     */
    private String logoIML = "default.png";


    /**
     * while this field may seem similar to website, it is not the same in all cases.
     * This field provides the string that also is contained in a separate file in the database.
     * There will be redundancy and when generating these, it may be possible to use DB a to
     * populate DB for example.
     */
    private String domain;

    /**
     * list of clubs affiliated with the university, these will require approval
     * facilitated by the university.
     * */
    private ArrayList<Club> clubs = new ArrayList<Club>();


    /**
     *
     */
    private ArrayList<Business> businesses = new ArrayList<>();





    public University(String displayName, String email, String domain)
    {
        this.domain = domain;
    }


    /**
     * @param r
     * @return whether the un-follow was successful
     * This overrides the method applicable to all groups as students
     * cannot un-follow their own college.
     */
    public boolean removeFollower(Account r)
    {
        if (r.getEmail().contains(domain))
            return false;
        else
            return this.removeFollower(r);
    }

    public String getDomain()
    {
        return this.domain;
    }
    public void setDomain()
    {
        this.domain = domain;
    }

    public ArrayList<Club> getClubs()
    {
//        if (clubs.size()==0)
//        {
//            clubs.add(new Club("tempClubMUSTBEREMOVED","atfortheloveofgodnooneeverusethisemail"));
//        }
        return clubs;
    }

    @Override
    public String getTableName()
    {
        return "uni-table";
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        this.email = map.get("email").getS();
        this.displayName = map.get("displayName").getS();

        if (map.get("followers")!=null)
        {
            this.followers = (ArrayList<String>) map.get("followers").getSS();
        }
        else
        {
            this.followers = new ArrayList<String>();
        }

        if (map.get("posts")!=null)
        {
            ArrayList<String> postIDS = (ArrayList<String>) map.get("posts").getSS();
            if (postIDS != null)
            {
                for (String s : postIDS)
                {
                    Post p = new Post(s, this);
                    DynamoDBUtility.get(p);
                    this.posts.add(p);
                }
            }
            this.domain = map.get("domain").getS();
        }
        else
        {
            this.posts = new ArrayList<Post>();
        }


        ArrayList<Club> temp = new ArrayList<>();
        if (map.get("clubs")!=null)
        {
            ArrayList<String> S = (ArrayList<String>) map.get("clubs").getSS();
            if (S!=null)
            {
                for (String s : S)
                {
                    Club c = new Club("club", "s");
                    DynamoDBUtility.get(c);
                    temp.add(c);
                }
            }
            this.clubs = temp;
        }
        else
        {
            this.clubs = new ArrayList<Club>();
        }

    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("email", new AttributeValue(this.email));
        itemValues.put("displayName", new AttributeValue(this.displayName));
        if (this.followers.size()>0)
            itemValues.put("followers", new AttributeValue(this.followers));
        if (this.posts.size()>0)
            itemValues.put("posts", new AttributeValue(this.posts.toString()));
        itemValues.put("website", new AttributeValue(this.website));
        itemValues.put("logoIML", new AttributeValue(this.logoIML));
        itemValues.put("domain", new AttributeValue(this.domain));
        if (this.clubs.size()>0)
            itemValues.put("clubs", new AttributeValue(this.clubs.toString()));

        return itemValues;
    }

    @Override
    public String getKey()
    {
        return "domain";
    }

    @Override
    public String getKeyValue()
    {
        return this.domain;
    }

    @Override
    public String getDisplayName()
    {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String d)
    {
        this.displayName = d;
    }

    @Override
    public String getEmail()
    {
        return this.email;
    }

    @Override
    public void setEmail(String e)
    {
        this.email = e;
    }

    @Override
    public ArrayList<String> getFollowers()
    {
//        if (followers.size()==0)
//        {
//            followers.add(UUID.randomUUID().toString());
//        }
        return this.followers;
    }

    @Override
    public void setFollowers(ArrayList<String> l)
    {
        this.followers = l;
    }

    @Override
    public ArrayList<Post> getPosts()
    {
//        if (posts.size()==0)
//        {
//            posts.add(new Post("default.png", new Business("ongodIFANYONEOFYALLTRIESsome","sillyassSHIII@thiswholeclassgonefeelmy.wrath")));
//        }
        return this.posts;
    }

    @Override
    public void setPosts(ArrayList<Post> p)
    {
        this.posts = p;
    }

    @Override
    public String getWebsite()
    {
        return null;
    }

    @Override
    public void setWebsite(String w)
    {
        this.website = w;
    }

    @Override
    public String getLogoIML()
    {
        return this.logoIML;
    }

    @Override
    public void setLogoIML(String i)
    {
        this.logoIML = i;
    }


    public ArrayList<Business> getBusinesses()
    {
//        if (businesses.size()==0)
//        {
//            businesses.add(new Business("ongodIFANYONEOFYALLTRIESsome","sillyassSHIII@thiswholeclassgonefeelmy.wrath"));
//        }
        return businesses;
    }
    public void setBusinesses(ArrayList<Business> businesses)
    {
        this.businesses = businesses;
    }
}
