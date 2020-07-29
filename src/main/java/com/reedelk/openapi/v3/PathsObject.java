package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.*;

public class PathsObject extends OpenApiSerializableAbstract {

    private Map<String, Map<RestMethod, OperationObject>> paths = new TreeMap<>();

    public Map<String, Map<RestMethod, OperationObject>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<RestMethod, OperationObject>> paths) {
        this.paths = paths;
    }

    @Override
    public Map<String,Object> serialize() {
        Map<String, Object> pathsObject = new LinkedHashMap<>();
        paths.forEach((path, pathItemObject) -> {
            Map<String, Object> operationsByPathJsonObject = new LinkedHashMap<>();
            pathItemObject.forEach((restMethod, operationObject) ->
                    operationsByPathJsonObject.put(restMethod.name().toLowerCase(),
                            operationObject.serialize()));
            pathsObject.put(path, operationsByPathJsonObject);
        });
        return pathsObject;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        serialized.forEach((pathEntry, pathDefinition) -> {
            Map<String,Object> pathDefinitionMap = (Map<String, Object>) pathDefinition;
            Map<RestMethod, OperationObject> methodAndOperationMap = new HashMap<>();
            pathDefinitionMap.forEach((method, operationObjectMap) -> {
                RestMethod restMethod = RestMethod.valueOf(method.toUpperCase());
                OperationObject operationObject = new OperationObject();
                operationObject.deserialize((Map<String,Object>) operationObjectMap);
                methodAndOperationMap.put(restMethod, operationObject);
                paths.put(pathEntry, methodAndOperationMap);
            });
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathsObject that = (PathsObject) o;
        return Objects.equals(paths, that.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paths);
    }

    @Override
    public String toString() {
        return "PathsObject{" +
                "paths=" + paths +
                '}';
    }
}
