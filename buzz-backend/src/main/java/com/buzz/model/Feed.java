package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.DynamoDBUtility;

import java.util.ArrayList;
import java.util.Map;

public class Feed
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
                ArrayList<String> clubsPost = club.getPosts();
                for (String id: clubsPost)
                {
                    Post p = new Post(id);
                    DynamoDBUtility.get(p);
                    feed.add(p);
                }
            }
        }
        //following

        ArrayList<String> followingIDs = account.getFollowingIDs();
        ArrayList<Group> following = new ArrayList<>();


        for (String id: followingIDs)
        {

            User entrant = new User();
            entrant.setEmail(id);
            DynamoDBUtility.get(entrant);
            if (entrant.getType()=='u')
            {
                University u = new University("", entrant.email, "");
                DynamoDBUtility.get(u);
                following.add(u);
            }
            if (entrant.getType()=='c')
            {
                Club c = new Club("", entrant.email);
                DynamoDBUtility.get(c);
                following.add(c);
            }
            if (entrant.getType()=='b')
            {
                Business b = new Business("", entrant.email, "");
                DynamoDBUtility.get(b);
                following.add(b);
            }
        }


        for (Group group : following)
        {
            if (!group.equals(this.university))
            {
                ArrayList<String> groupPosts = group.getPosts();
                for (String groupPost : groupPosts)
                {
                    Post p = new Post(groupPost);
                    DynamoDBUtility.get(p);
                    feed.add(p);
                }

            }
        }

        //promoted

        for (Business b : university.getBusinesses())
        {
            for (String iml: b.getPosts())
            {
                Post p = new Post(iml);
                DynamoDBUtility.get(p);
                feed.add(p);
            }
        }

        return feed;
    }


}
