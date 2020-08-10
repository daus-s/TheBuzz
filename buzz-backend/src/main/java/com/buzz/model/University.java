package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.buzz.util.DynamoDBUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class University extends Group implements RowDDB
{

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

    public University(String displayName, String email, String domain)
    {
        super(displayName, email);
        this.domain = domain;
    }


    /**
     * @param r
     * @return whether the un-follow was successful
     * This overrides the method applicable to all groups as students
     * cannot un-follow their own college.
     */
    @Override
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
        super.loadModel(map);
        this.domain = map.get("domain").getS();

        ArrayList<Club> temp = new ArrayList<>();
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

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues = super.getModelAttributes();
        itemValues.put("domain", new AttributeValue(this.domain));
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
}
