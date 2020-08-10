package com.buzz.model;

public class Club extends Group
{
    private boolean uniApproved;

    /**
     * This field is the Univeristy to which the group is affiliated. The University must approveof the
     * group in order for the group to create posts .
     */
    private University affiliation;

    public Club(String displayName, String email)
    {
        super(displayName, email);
    }

    public boolean createPost(Post p, boolean privacy) {

        p.setPrivacy(privacy);

        if (uniApproved)
        {
            return posts.add(p);
        }
        else return false;
    }

    public University getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(University affiliation) {
        this.affiliation = affiliation;
    }
}
