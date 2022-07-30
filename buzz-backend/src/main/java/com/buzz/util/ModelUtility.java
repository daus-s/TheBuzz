package com.buzz.util;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.buzz.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 *
 * JSON and toMap methods:
 *  This class works with models that need to be stored in the DDB and/or
 *  need to be manipulated in the code. Any Model(Object) that is stored
 *  in the DDB will have a {model}ToMap(Model model) as well as a
 *  JSONtoModel(Item item), where Item is the stored object in the DDB.
 *
 *
 */
public class ModelUtility
{

    /**
     * Methods for converting a Account object to a JSON as well as
     * the reverse, creating a Account object given a JSON string
     * from the DynamoDB.
     **/
    public static Map<String, AttributeValue> accountToMap(Account a)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try
        {
            jsonStr = objectMapper.writeValueAsString(a);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        Item item = new Item().withJSON("document", jsonStr);
        Map<String,AttributeValue> attributes = ItemUtils.toAttributeValues(item);
        return attributes;
    }
    public static Account JSONtoAccount(GetItemResult item)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = new Account();
        try
        {
            account = objectMapper.readValue(item.getItem().toString(), Account.class);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            System.out.println(item.toString());
        }
        return account;
    }
    /**
     * END ACCOUNT UTILITY
     * */

    public String stringAttribute(String s)
    {
        return s.substring(s.indexOf(":"),s.length()-2);
    }



}
