package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Post implements RowDDB
{
    /**
     * Image Location is required. The html will display the image as a part of a
     * type in the framework. Ideas for representing see Garmin watch app/old instagram.
     * */
    private String imageLocation = "default.png";

    /**
     * Caption is not required, but is displayed along with the image on the feed.
     * This string is sent to the html template that then displays these two along
     * with the GROUP display name
     * */
    private String caption;

    /**
     * The group publishing also has this posts data stored in its ArrayList of posts
     * and can delete the post anytime.
     * */
    private String publisher;

    /**
     * date represents when the Post was created and cannot be changed inside a single post,
     * in that way it is final upon creation, however, for a post to be part of the feed, date
     * is not the only parameter, so promoted posts do not need to be posted as often
     *
     * additionally if a posts data is copied it can be deleted and reposted changing the date-time
     * value
     * */
    private final Date date;

    /**
     * This field allows certain people to see the post, whether or not they are in the group
     * Being in the group means you can see it.
     *
     * the values assigned to this are
     *     TRUE=private -> only group members can see
     *     FALSE=public -> anyone viewing can see
     *
     *     default:true, this is to make university events not have to specify whether it is or
     *     is not private and so all students automatically see and public posts are reserved,
     *     this prevents feed cluttering
     * */
    private boolean privacy = true;

    /**
     * id of the post generated and used to ensure that in a single feed posts are not repeated
     * generated form a UUID and assigned to the post at creation, also enforces that posts are
     * reconstructed when posted again
     */
    private String id;



    public String getImageLocation()
    {
        return imageLocation;
    }
    public void setImageLocation(String imageLocation)
    {
        this.imageLocation = imageLocation;
    }

    public String getCaption()
    {
        return caption;
    }
    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public String getPublisher()
    {
        return publisher;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public boolean isPrivacy()
    {
        return privacy;
    }
    public void setPrivacy(boolean privacy)
    {
        this.privacy = privacy;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public Post(String iml)
    {
        this.imageLocation = iml;
        this.date = new Date();
    }

    public Post(String imageLocation, Group publisher)
    {
        this.date = new Date();
        this.imageLocation = imageLocation;
        this.publisher = publisher.getDisplayName();
        this.caption = "";
        this.id = UUID.randomUUID().toString();
    }

    public Post(String imageLocation, String caption, Group publisher)
    {
        this.date = new Date();
        this.imageLocation = imageLocation;
        this.publisher = publisher.getDisplayName();
        this.caption = caption;
        this.id = UUID.randomUUID().toString();
    }


    public Date getDate() {
        return date;
    }



    @Override
    public String getTableName()
    {
        return "post-table";
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        this.publisher = map.get("publisher").getS();
        this.imageLocation = map.get("imageLocation").getS();
        this.caption = map.get("caption").getS();
        this.privacy = map.get("privacy").getBOOL();
    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        return null;
    }

    @Override
    public String getKey()
    {
        return "imageLocation";
    }

    @Override
    public String getKeyValue()
    {
        return this.imageLocation;
    }

    public String toString()
    {
        return "{publisher:" + this.publisher + "\nimageLocation:" + this.imageLocation + "\ncaption:" + this.caption + "\ndate:" + this.date.toString() +"}";
    }
}
