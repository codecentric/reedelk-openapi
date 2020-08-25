package com.reedelk.openapi.commons;

import com.reedelk.openapi.Deserializer;
import com.reedelk.openapi.v3.DeserializerContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
    protected <U> Optional<U> mapApiModel(Map<String, Object> serialized, String propertyName, Class<U> targetClazz, DeserializerContext context) {
        if (serialized.containsKey(propertyName)) {
            Map<String, Object> objectMap = (Map<String, Object>) serialized.get(propertyName);
            return Optional.of(context.deserialize(targetClazz, objectMap));
        } else {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    protected <U> Optional<Map<String,U>> mapKeyApiModel(String propertyName, Map<String, Object> parent, KeyValueMapper<U> mapper) {
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

    protected <U> Optional<List<U>> mapListApiModel(String propertyName, Map<String, Object> parent, ValueMapper<U> mapper) {
        if (parent.containsKey(propertyName)) {
            List<Map<String, Object>> serversList = getList(parent, propertyName);
            List<U> serverObjects = serversList
                    .stream()
                    .map(mapper::map)
                    .collect(toList());
            return Optional.of(serverObjects);
        }
        return Optional.empty();
    }

    protected interface KeyValueMapper<U> {
        U map(String key, Map<String, Object> source);
    }

    protected interface ValueMapper<U> {
        U map(Map<String, Object> source);
    }

    protected String deserializeExampleDataAsString(Object exampleData) {
        if (exampleData instanceof String) {
            return (String) exampleData;
        } else {
            // If it is an object must have the type of the
            // OpenAPI document: must be serialized as JSON.
            // If it is an XML example it must be a string, therefore this case
            // is covered in the above condition.
            return DataFormat.JSON.dump(exampleData);
        }
    }
}
