package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.xspec.B;
import com.buzz.util.DynamoDBUtility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Feed implements RowDDB
{
    private ArrayList<Post> feed = new ArrayList<Post>();

    private Account account;
    private University university = null;


    public Account getAccount()
    {
        return account;
    }
    public void setAccount(Account account)
    {
        this.account = account;
    }

    public University getUniversity()
    {
        return university;
    }
    public void setUniversity(University university)
    {
        this.university = university;
    }

    public Feed(Account account)
    {
        this.account = account;
        University u = new University("", "", account.getCurrentSchoolID());
        DynamoDBUtility.get(u);
        this.university = u;
        this.feed = generateFeed();
    }


    public ArrayList<Post> generateFeed()
    {
        //university
        if (university!=null)
        {
            ArrayList<Club> clubs = university.getClubs();
            for (Club club : clubs)
            {
                ArrayList<Post> clubsPost = club.getPosts();
                feed.addAll(clubsPost);
            }
        }
        //following

        ArrayList<String> followingIDs = account.getFollowingIDs();
        ArrayList<Group> following = new ArrayList<>();


        for (String id: followingIDs)
        {

            GroupFactory instance = new GroupFactory();
            if (instance.getType()=='u')
            {
                University u = new University("", "", "");
                DynamoDBUtility.get(u);
                following.add(u);
            }
            if (instance.getType()=='c')
            {
                Club c = new Club("", "");
                DynamoDBUtility.get(c);
                following.add(c);
            }
            if (instance.getType()=='b')
            {
                Business b = new Business("", "", "");
                DynamoDBUtility.get(b);
                following.add(b);
            }
        }


        for (int i = 0; i < following.size(); i++)
        {
            if (!following.get(i).equals(this.university))
            {
                ArrayList<Post> groupPosts = following.get(i).getPosts();
                for (int j = 0; j < groupPosts.size(); j++)
                {
                    feed.add(groupPosts.get(j));
                }

            }
        }

        //promoted

        for (Business b : university.getBusinesses())
        {
            for (Post p: b.getPosts())
            {
                feed.add(p);
            }
        }

        return feed;
    }

    @Override
    public String getTableName()
    {
        return "feed-table";
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
        return "account.email";
    }

    @Override
    public String getKeyValue()
    {
        return this.account.getEmail();
    }
}
