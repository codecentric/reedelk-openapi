package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OperationObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class OperationObjectSerializer extends AbstractSerializer<OperationObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, OperationObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "deprecated", input.getDeprecated());
        set(map, "summary", input.getSummary());
        set(map, "description", input.getDescription());
        set(map, "operationId", input.getOperationId());

        if (input.getRequestBody() != null) {
            Map<String, Object> serializedRequestBody = context.serialize(input.getRequestBody());
            set(map, "requestBody", serializedRequestBody);
        }

        Map<String, Map<String,Object>> serializedResponses = new HashMap<>();

        Map<String, ResponseObject> responses = input.getResponses();
        responses.forEach((statusCode, responseObject) -> {
            Map<String, Object> serializedResponse = context.serialize(responseObject);
            serializedResponses.put(statusCode, serializedResponse);
        });
        map.put("responses", serializedResponses);


        List<Map<String,Object>> serializedParameters = input
                .getParameters()
                .stream()
                .map(context::serialize)
                .collect(toList());
        map.put("parameters", serializedParameters);

        map.put("tags", input.getTags());
        return map;
    }
}
