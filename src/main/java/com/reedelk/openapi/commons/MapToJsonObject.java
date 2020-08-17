package com.reedelk.openapi.commons;

import org.json.JSONObject;

import java.util.Map;

public class MapToJsonObject {

    private MapToJsonObject() {
    }

    @SuppressWarnings("unchecked")
    public static Object convert(Object value) {
        if (value instanceof Map) {
            JSONObject nested = JsonObjectFactory.newJSONObject();
            Map<String,Object> nestedMap = (Map<String, Object>) value;
            nestedMap.forEach((key, theValue) -> nested.put(key, convert(theValue)));
            return nested;
        } else {
            return value;
        }
    }
}
