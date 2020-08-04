package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OperationObject;
import com.reedelk.openapi.v3.model.ParameterObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class OperationObjectSerializer extends AbstractSerializer<OperationObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OperationObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "deprecated", input.getDeprecated());
        set(map, "summary", input.getSummary());
        set(map, "description", input.getDescription());
        set(map, "operationId", input.getOperationId());

        if (input.getRequestBody() != null) {
            NavigationPath currentNavigationPath = navigationPath.with("requestBody");
            Map<String, Object> serializedRequestBody = context.serialize(currentNavigationPath, input.getRequestBody());
            set(map, "requestBody", serializedRequestBody);
        }

        Map<String, ResponseObject> responses = input.getResponses() == null ? new HashMap<>() : input.getResponses(); // MANDATORY
        Map<String, Map<String, Object>> serializedResponses = new LinkedHashMap<>();
        responses.forEach((statusCode, responseObject) -> {
            NavigationPath currentNavigationPath = navigationPath
                    .with("responses")
                    .with("statusCode", statusCode);
            Map<String, Object> serializedResponse = context.serialize(currentNavigationPath, responseObject);
            serializedResponses.put(statusCode, serializedResponse);
        });
        map.put("responses", serializedResponses);


        List<ParameterObject> parameters = input.getParameters();
        if (parameters != null && !parameters.isEmpty()) {
            List<Map<String, Object>> serializedParameters = parameters
                    .stream()
                    .map(parameterObject -> {
                        NavigationPath currentNavigationPath = navigationPath
                                .with("parameters")
                                .with("parameterName", parameterObject.getName());
                        return context.serialize(currentNavigationPath, parameterObject);
                    })
                    .collect(toList());
            map.put("parameters", serializedParameters);
        }

        if (input.getTags() != null && !input.getTags().isEmpty()) {
            map.put("tags", input.getTags());
        }
        return map;
    }
}
