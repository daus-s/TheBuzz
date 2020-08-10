package com.buzz.model;

import java.util.ArrayList;

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
    }


    public ArrayList<Post> generateFeed()
    {
        //university
        if (university!=null)
        {
            ArrayList<Club> clubs = university.getClubs();
            for (int i = 0; i < clubs.size(); i++)
            {
                ArrayList<Post> clubsPost = clubs.get(i).getPosts();
                for (int j = 0; j < clubsPost.size(); j++)
                {
                    feed.add(clubsPost.get(j));
                }
            }
        }
        //following

        ArrayList<String> following = account.getFollowingIDs();
//        for (int i = 0; i < following.size(); i++)
//        {
//            if (!following.get(i).equals(this.university))
//            {
//                ArrayList<Post> groupPosts = following.get(i).getPosts();
//                for (int j = 0; j < groupPosts.size(); j++)
//                {
//                    feed.add(groupPosts.get(j));
//                }
//
//            }
//        }

        //promoted

        return feed;
    }
}
