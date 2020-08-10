package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.Map;

public interface RowDDB
{
    String getTableName();
    void loadModel(Map<String, AttributeValue> map);
    Map<String, AttributeValue> getModelAttributes();
    String getKey();
    String getKeyValue();
}
