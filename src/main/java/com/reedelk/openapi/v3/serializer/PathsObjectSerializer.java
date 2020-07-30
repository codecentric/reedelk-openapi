package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.PathsObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class PathsObjectSerializer extends AbstractSerializer<PathsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, PathsObject input) {
        Map<String, Object> pathsObject = new LinkedHashMap<>();

        input.getPaths().forEach((path, pathItemObject) -> {
            Map<String, Object> operationsByPathJsonObject = new LinkedHashMap<>();
            pathItemObject.forEach((restMethod, operationObject) -> {
                Map<String, Object> serializedOperation = context.serialize(operationObject);
                operationsByPathJsonObject.put(restMethod.name().toLowerCase(), serializedOperation);
            });
            pathsObject.put(path, operationsByPathJsonObject);
        });

        return pathsObject;
    }
}
