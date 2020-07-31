package com.reedelk.openapi.commons;

import com.reedelk.openapi.Deserializer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDeserializer<T> implements Deserializer<T> {

    protected String getString(Map<String,Object> data, String propertyName) {
        return (String) data.get(propertyName);
    }

    protected Boolean getBoolean(Map<String,Object> data, String propertyName) {
        return (Boolean) data.get(propertyName);
    }

    @SuppressWarnings("unchecked")
    protected Map<String,Object> getMap(Map<String,Object> data, String propertyName) {
        return (Map<String,Object>) data.get(propertyName);
    }

    @SuppressWarnings("unchecked")
    protected List<Map<String,Object>> getList(Map<String,Object> data, String propertyName) {
        return (List<Map<String,Object>>) data.get(propertyName);
    }

    @SuppressWarnings("unchecked")
    protected <U> Optional<Map<String,U>> mapKeyApiModel(String propertyName, Map<String, Object> parent, Mapper<U> mapper) {
        if (parent.containsKey(propertyName)) {
            Map<String, U> targetMap = new LinkedHashMap<>();
            Map<String, Object> sourceMap = (Map<String, Object>) parent.get(propertyName);
            sourceMap.forEach((key, objectMap) -> {
                Map<String, Object> targetObjectMap = (Map<String, Object>) objectMap;
                U targetObject = mapper.map(key, targetObjectMap);
                targetMap.put(key, targetObject);
            });
            return Optional.of(targetMap);
        }
        return Optional.empty();
    }

    protected interface Mapper<U> {
        U map(String key, Map<String, Object> source);
    }
}
