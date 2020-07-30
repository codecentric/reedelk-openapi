package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.OperationObject;
import com.reedelk.openapi.v3.model.PathsObject;
import com.reedelk.openapi.v3.model.RestMethod;

import java.util.HashMap;
import java.util.Map;

public class PathsObjectDeserializer extends AbstractDeserializer<PathsObject> {

    @SuppressWarnings("unchecked")
    @Override
    public PathsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        PathsObject pathsObject = new PathsObject();

        Map<String, Map<RestMethod, OperationObject>> pathsMap = new HashMap<>();

        serialized.forEach((pathEntry, pathDefinition) -> {
            Map<String,Object> pathDefinitionMap = (Map<String, Object>) pathDefinition;
            Map<RestMethod, OperationObject> methodAndOperationMap = new HashMap<>();
            pathDefinitionMap.forEach((method, operationObjectData) -> {
                RestMethod restMethod = RestMethod.valueOf(method.toUpperCase());
                Map<String,Object> operationObjectMap = (Map<String,Object>)operationObjectData;
                OperationObject operationObject = context.deserialize(OperationObject.class, operationObjectMap);
                methodAndOperationMap.put(restMethod, operationObject);
                pathsMap.put(pathEntry, methodAndOperationMap);
            });
        });

        pathsObject.setPaths(pathsMap);
        return pathsObject;
    }
}
