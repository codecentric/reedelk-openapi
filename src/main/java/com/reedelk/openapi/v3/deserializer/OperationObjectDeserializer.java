package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.OperationObject;
import com.reedelk.openapi.v3.model.ParameterObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OperationObjectDeserializer extends AbstractDeserializer<OperationObject> {

    @SuppressWarnings("unchecked")
    @Override
    public OperationObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OperationObject operationObject = new OperationObject();

        operationObject.setDeprecated(getBoolean(serialized, "deprecated"));
        operationObject.setSummary(getString(serialized, "summary"));
        operationObject.setDescription(getString(serialized, "description"));
        operationObject.setOperationId(getString(serialized, "operationId"));

        // Request Body
        if (serialized.containsKey("requestBody")) {
            Map<String, Object> requestBodyObjectMap = getMap(serialized, "requestBody");
            RequestBodyObject requestBodyObject = context.deserialize(RequestBodyObject.class, requestBodyObjectMap);
            operationObject.setRequestBody(requestBodyObject);
        }

        // Responses
        if (serialized.containsKey("responses")) {
            Map<String, ResponseObject> statusResponseObjectMap = new LinkedHashMap<>();
            Map<String, Map<String,Object>> responsesMap = (Map<String, Map<String, Object>>) serialized.get("responses");
            responsesMap.forEach((responseStatusCode, responseObjectMap) -> {
                ResponseObject responseObject = context.deserialize(ResponseObject.class, responseObjectMap);
                statusResponseObjectMap.put(responseStatusCode, responseObject);
            });
            operationObject.setResponses(statusResponseObjectMap);
        }

        // Parameters
        if (serialized.containsKey("parameters")) {
            List<ParameterObject> parameterObjectList = new ArrayList<>();
            List<Map<String,Object>> parametersList = (List<Map<String, Object>>) serialized.get("parameters");
            parametersList.forEach(parameterMap -> {
                ParameterObject parameterObject = context.deserialize(ParameterObject.class, parameterMap);
                parameterObjectList.add(parameterObject);
            });
            operationObject.setParameters(parameterObjectList);
        }

        // Tags
        List<String> tags = (List<String>) serialized.get("tags");
        operationObject.setTags(tags);

        return operationObject;
    }
}
