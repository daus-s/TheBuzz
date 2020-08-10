package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.DynamoDBUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Group, rarely instantiated, but extended by University/College, Business(Restaurants,...) and RSOs/clubs
 * The relationship between an Account, and a Group is "has-some"  i.e. the Group has some Accounts following
 * Groups can create posts that go to the followers
 */
public class Group implements RowDDB
{
    /**
     * The display name is the name that appears with the post and is linked to the
     * account. This is allowed to be non-unique, as the email must be unique.
     */
    protected String displayName = "group"; //defaults
    /**
     * The email must be unique(it is the only existence of the email in the database,
     * across all tables, both account and group profiles) and is used as the key in
     * the dynamoDB for Groups.
     */
    protected String email = "a@b.com"; //defaults

    /**
     * The follower Arraylist(AL) contains all accounts that follow the account.
     * The number of followers is accessible through the arraylist, but is not important
     * as of v0.1. This is the list that will receive Posts from the group. When
     * following or un-following, the code must ensure the action is completed on both sides
     * with preference going to the Group if there is a discrepancy.
     */
    protected ArrayList<String> followers = new ArrayList<String>();
    /**
     * List of all post that have not been deleted or expired based on time-to-live(later version)
     * Represented on the Groups page and displayed there. The caption and image are the only important
     * parts when being represented on the groups page.
     * */
    protected ArrayList<Post> posts = new ArrayList<Post>();

    /**
     * Website that is displayed on the profile of the group. Non-unique requirement, however, it often
     * should be.
     */
    protected String website = "thebuzz.com";

    /**
     * This is the address of the logo in the S3 that is used as the universities "profile picture."
     * See mock rendering of app design to see image
     */
    protected String logoIML = "default.png";

    /**
     *
     * */
    protected int followerCount;

    //follower & post accessors
    public ArrayList<String> getFollowers()
    {
        return followers;
    }
    public ArrayList<Post> getPosts()
    {
        return posts;
    }


    //email accessors and mutators
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    //username accessors and mutators
    public String getDisplayName()
    {
        return displayName;
    }
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    //website accessors and mutators
    public String getWebsite()
    {
        return website;
    }
    public void setWebsite(String website)
    {
        this.website = website;
    }


    public String getLogoIML() {
        return logoIML;
    }
    public void setLogoIML(String logoIML) {
        this.logoIML = logoIML;
    }

    public int getFollowerCount()
    {
        return followerCount;
    }
    public void setFollowerCount(int followerCount)
    {
        this.followerCount = followerCount;
    }
    //utility methods

    /**
     * @param email
     * @return returns whether the operation was successful or not(true-success, false-fail)
     * The method checks to see if the account is already following and then adds to the list.
     * Once an account follows, it will add to the accounts following.
     */
    public boolean addFollower(String email)
    {
        if (!followers.contains(email))
        {
            followers.add(email);
            return true;
        }
        return false;
    }

    /**
     * @param r
     * @return whether the follower was able to be removed
     * The method removes the account from the followers AL and then returns if this was successful.
     */
    public boolean removeFollower(Account r)
    {
        return followers.remove(r);
    }

    /**
     * @param p
     * @return whether the post was found and removed
     * The post is removed from the account and the data of the post should be
     * deleted from the S3
     */
    public boolean deletePost(Post p)
    {
        //TODO: delete post data from s3
        return posts.remove(p);
    }

    /**
     * @param p
     * @return whether the post was able to be created
     * This method publishes a post and sends out to followers. The method allows
     * for duplicate posting of the same post, this may change as it is possible to
     * take advantage of this.
     */
    public boolean createPost(Post p)
    {
        //TODO: add data to s3
        return posts.add(p);
    }


    public Group(String displayName, String email, String website)
    {
        this.displayName = displayName;
        this.email = email;
        this.website = website;
    }


    public Group(String displayName, String email)
    {
        this.displayName = displayName;
        this.email = email;
    }


    @Override
    public String getTableName()
    {
        return null;
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        this.email = map.get("email").getS();
        this.displayName = map.get("displayName").getS();
        this.followers = (ArrayList<String>) map.get("followers").getSS();
        ArrayList<String> names = (ArrayList<String>) map.get("posts").getSS();
        if (names!=null)
        {
            for (String s: names)
            {
                Post p = new Post(s,this);
                DynamoDBUtility.get(p);
                this.posts.add(p);
            }
        }
    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();

        itemValues.put("email", new AttributeValue(this.email));
        itemValues.put("displayName", new AttributeValue(this.displayName));
        itemValues.put("followers", new AttributeValue(this.followers));
        itemValues.put("posts", new AttributeValue());
        itemValues.put("website", new AttributeValue(this.website));
        itemValues.put("logoIML", new AttributeValue(this.logoIML));
        itemValues.put("followerCount", new AttributeValue(this.followerCount+""));

        return itemValues;
    }

    @Override
    public String getKey()
    {
        return "displayName";
    }

    @Override
    public String getKeyValue()
    {
        return this.displayName;
    }
}
