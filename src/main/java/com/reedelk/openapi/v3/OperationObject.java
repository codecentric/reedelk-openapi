package com.reedelk.openapi.v3;


import com.reedelk.openapi.OpenApiSerializableAbstract;
import com.reedelk.openapi.OpenApiSerializableContext;

import java.util.*;

public class OperationObject extends OpenApiSerializableAbstract {

    private Boolean exclude;
    private Boolean deprecated;
    private String summary;
    private String description;
    private String operationId;
    private RequestBodyObject requestBody;
    private Map<String, ResponseObject> responses = new HashMap<>();
    private List<ParameterObject> parameters = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    public Boolean getExclude() {
        return exclude;
    }

    public void setExclude(Boolean exclude) {
        this.exclude = exclude;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public RequestBodyObject getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBodyObject requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, ResponseObject> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, ResponseObject> responses) {
        this.responses = responses;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public List<ParameterObject> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterObject> parameters) {
        this.parameters = parameters;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public Map<String,Object> serialize(OpenApiSerializableContext context) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "exclude", exclude);
        set(map, "deprecated", deprecated);
        set(map, "summary", summary);
        set(map, "description", description);
        set(map, "operationId", operationId);
        if (requestBody != null) set(map, "requestBody", requestBody, context);
        if (responses.isEmpty()) {
            // make sure at least one default response is present if there are
            // no user defined responses.
            responses.put("default", new ResponseObject());
        }
        set(map, "responses", responses, context);
        set(map, "parameters", parameters, context);
        setList(map, "tags", tags);
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        exclude = getBoolean(serialized, "exclude");
        deprecated = getBoolean(serialized, "deprecated");
        summary = getString(serialized, "summary");
        description = getString(serialized, "description");
        operationId = getString(serialized, "operationId");
        if (serialized.containsKey("requestBody")) {
            RequestBodyObject requestBodyObject = new RequestBodyObject();
            requestBodyObject.deserialize(getMap(serialized, "requestBody"));
            requestBody = requestBodyObject;
        }
        if (serialized.containsKey("responses")) {
            Map<String,Map<String,Object>> responsesMap = (Map<String, Map<String, Object>>) serialized.get("responses");
            responsesMap.forEach((responseStatusCode, responseObjectMap) -> {
                ResponseObject responseObject = new ResponseObject();
                responseObject.deserialize(responseObjectMap);
                responses.put(responseStatusCode, responseObject);
            });
        }
        if (serialized.containsKey("parameters")) {
            List<Map<String,Object>> parametersList = (List<Map<String, Object>>) serialized.get("parameters");
            parametersList.forEach(parameterMap -> {
                ParameterObject parameterObject = new ParameterObject();
                parameterObject.deserialize(parameterMap);
                parameters.add(parameterObject);
            });
        }

        tags = (List<String>) serialized.get("tags");
    }
}
