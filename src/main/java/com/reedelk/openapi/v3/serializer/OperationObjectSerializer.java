package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OperationObject;
import com.reedelk.openapi.v3.model.ParameterObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.*;

import static com.reedelk.openapi.v3.model.OperationObject.Properties;
import static java.util.stream.Collectors.toList;

public class OperationObjectSerializer extends AbstractSerializer<OperationObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OperationObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.DEPRECATED.value(), input.getDeprecated());
        set(map, Properties.SUMMARY.value(), input.getSummary());
        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        set(map, Properties.OPERATION_ID.value(), input.getOperationId());

        // Request Body
        if (input.getRequestBody() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.REQUEST_BODY);
            Map<String, Object> serializedRequestBody = context.serialize(currentNavigationPath, input.getRequestBody());
            set(map, Properties.REQUEST_BODY.value(), serializedRequestBody);
        }

        // Responses
        Map<String, ResponseObject> responses = input.getResponses() == null ? new HashMap<>() : input.getResponses(); // MANDATORY
        Map<String, Map<String, Object>> serializedResponses = new LinkedHashMap<>();
        responses.forEach((statusCode, responseObject) -> {
            NavigationPath currentNavigationPath = navigationPath
                    .with(NavigationPath.SegmentKey.RESPONSES)
                    .with(NavigationPath.SegmentKey.STATUS_CODE, statusCode);
            Map<String, Object> serializedResponse = context.serialize(currentNavigationPath, responseObject);
            serializedResponses.put(statusCode, serializedResponse);
        });
        map.put(Properties.RESPONSES.value(), serializedResponses);


        // Parameters
        List<ParameterObject> parameters = input.getParameters();
        if (parameters != null && !parameters.isEmpty()) {
            List<Map<String, Object>> serializedParameters = parameters
                    .stream()
                    .map(parameterObject -> {
                        NavigationPath currentNavigationPath = navigationPath
                                .with(NavigationPath.SegmentKey.PARAMETERS)
                                .with(NavigationPath.SegmentKey.PARAMETER_NAME, parameterObject.getName());
                        return context.serialize(currentNavigationPath, parameterObject);
                    })
                    .collect(toList());
            map.put(Properties.PARAMETERS.value(), serializedParameters);
        }

        // Tags
        if (input.getTags() != null && !input.getTags().isEmpty()) {
            map.put(Properties.TAGS.value(), input.getTags());
        }


        // Security Requirements
        if (input.getSecurity() != null && !input.getSecurity().isEmpty()) {
            List<Map<String, Object>> serializedSecurity = new ArrayList<>();
            map.put(Properties.SECURITY.value(), serializedSecurity);
            input.getSecurity().forEach(securityNameAndRequirement -> {
                Map<String, Object> security = new HashMap<>();
                securityNameAndRequirement.forEach((requirementName, securityRequirementObject) -> {
                    // Optional OAuth2 security as would be defined in an OpenAPI Object or an Operation Object:
                    // {
                    //  "security": [
                    //    {},
                    //    {
                    //      "petstore_auth": [
                    //        "write:pets",
                    //        "read:pets"
                    //      ]
                    //    }
                    //  ]
                    //}
                    if (requirementName != null) {
                        List<String> scopes = securityRequirementObject.getScopes();
                        security.put(requirementName, scopes == null ? new ArrayList<>() : scopes);
                    }
                });
                serializedSecurity.add(security);
            });
        }

        return map;
    }
}
