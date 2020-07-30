package com.reedelk.openapi;

import java.util.List;
import java.util.Map;

public abstract class AbstractSerializer<T> implements Serializer<T> {


    protected void setList(Map<String,Object> object, String propertyName, List<String> items) {
        if (items != null && !items.isEmpty()) object.put(propertyName, items);
    }

    protected void set(Map<String,Object> object, String propertyName, Boolean aBoolean) {
        if (aBoolean != null) object.put(propertyName, aBoolean);
    }

    protected void set(Map<String,Object> object, String propertyName, Map<String,Object> value) {
        if (value != null) object.put(propertyName, value);
    }

    protected void set(Map<String,Object> object, String propertyName, String value) {
        if (value != null) object.put(propertyName, value);
    }
}
