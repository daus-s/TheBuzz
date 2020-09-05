package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

/**
 * all objects that implement
 */
public class GroupFactory implements RowDDB
{

    /**
     *
     */
    protected char type;

    protected String id;

    public char getType()
    {
        return type;
    }
    public void setType(char type)
    {
        this.type = type;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getTableName()
    {
        String tableName;
        if (this.type == 'u')
            return "uni-table";
        if (this.type == 'b')
            return "business-table";
        if (this.type == 'c')
            return "club-table";
        else
            return "error";
    }

    @Override
    public void loadModel(Map<String, AttributeValue> map)
    {
        this.type =  map.get("type").getS().charAt(0);
        this.id = map.get("id").getS();
    }

    @Override
    public Map<String, AttributeValue> getModelAttributes()
    {
        Map<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();
        itemValues.put("id", new AttributeValue(this.id));
        itemValues.put("type", new AttributeValue(this.type + ""));

        return itemValues;
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
