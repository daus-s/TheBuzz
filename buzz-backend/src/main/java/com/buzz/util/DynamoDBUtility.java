package com.buzz.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.buzz.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamoDBUtility
{
    private static AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build();

    //Table names
    public static final String ACCOUNT_TABLE = "account-table";


    public static AmazonDynamoDB getDynamoDB() {
        return dynamoDB;
    }


    public static boolean get(RowDDB row)
    {
        HashMap<String,AttributeValue> key_to_get = new HashMap<String,AttributeValue>();

        key_to_get.put(row.getKey(), new AttributeValue(row.getKeyValue()));
        GetItemRequest request  = new GetItemRequest().withKey(key_to_get).withTableName(row.getTableName());

        Map<String, AttributeValue> returned_item = dynamoDB.getItem(request).getItem();
        if (returned_item!=null)
        {
            row.loadModel(returned_item);
            return true;

        }
        return false;
    }

    public static boolean put(RowDDB row)
    {
        Map<String,AttributeValue> item_values = row.getModelAttributes();

        //item_values.put();
        //System.out.println(item_values);


        try
        {
            dynamoDB.putItem(row.getTableName(), item_values);
        }
        catch (ResourceNotFoundException e)
        {
            System.err.format("Error: The table \"%s\" can't be found.\n", row.getTableName());
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            e.printStackTrace();
            return false;
        }
        catch (AmazonServiceException e)
        {
            e.printStackTrace();
            System.err.printf("problem discovered when putting \"%s\" into ddb\n", row.getKeyValue());
            return false;
        }
        return true;
    }

}
