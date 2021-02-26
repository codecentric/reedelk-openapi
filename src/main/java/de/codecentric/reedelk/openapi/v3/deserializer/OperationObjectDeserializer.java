package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OperationObjectDeserializer extends AbstractDeserializer<OperationObject> {

    @SuppressWarnings("unchecked")
    @Override
    public OperationObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OperationObject operationObject = new OperationObject();

        operationObject.setDeprecated(getBoolean(serialized, OperationObject.Properties.DEPRECATED.value()));
        operationObject.setSummary(getString(serialized, OperationObject.Properties.SUMMARY.value()));
        operationObject.setDescription(getString(serialized, OperationObject.Properties.DESCRIPTION.value()));
        operationObject.setOperationId(getString(serialized, OperationObject.Properties.OPERATION_ID.value()));

        // Request Body
        if (serialized.containsKey(OperationObject.Properties.REQUEST_BODY.value())) {
            Map<String, Object> requestBodyObjectMap = getMap(serialized, OperationObject.Properties.REQUEST_BODY.value());
            RequestBodyObject requestBodyObject = context.deserialize(RequestBodyObject.class, requestBodyObjectMap);
            operationObject.setRequestBody(requestBodyObject);
        }

        // Responses
        mapKeyApiModel(OperationObject.Properties.RESPONSES.value(), serialized,
                (key, source) -> context.deserialize(ResponseObject.class, source))
                .ifPresent(operationObject::setResponses);

        // Parameters
        if (serialized.containsKey(OperationObject.Properties.PARAMETERS.value())) {
            List<ParameterObject> parameterObjectList = new ArrayList<>();
            List<Map<String,Object>> parametersList =
                    (List<Map<String, Object>>) serialized.get(OperationObject.Properties.PARAMETERS.value());
            parametersList.forEach(parameterMap -> {
                ParameterObject parameterObject = context.deserialize(ParameterObject.class, parameterMap);
                parameterObjectList.add(parameterObject);
            });
            operationObject.setParameters(parameterObjectList);
        }

        // Tags
        List<String> tags = (List<String>) serialized.get(OperationObject.Properties.TAGS.value());
        operationObject.setTags(tags);

        // Security
        mapListApiModel(OperationObject.Properties.SECURITY.value(), serialized, source -> {
            Map<String, SecurityRequirementObject> mapped = new LinkedHashMap<>();
            source.forEach((securityRequirementId, object) -> {
                List<String> scopes = (List<String>) source.get(securityRequirementId);
                SecurityRequirementObject securityRequirementObject = new SecurityRequirementObject();
                securityRequirementObject.setScopes(scopes);
                mapped.put(securityRequirementId, securityRequirementObject);
            });
            return mapped;
        }).ifPresent(operationObject::setSecurity);

        return operationObject;
    }
}
