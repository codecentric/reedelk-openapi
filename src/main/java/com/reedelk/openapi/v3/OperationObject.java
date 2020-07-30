package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.*;

public class OperationObject extends OpenApiSerializableAbstract {

    private Boolean deprecated;
    private String summary;
    private String description;
    private String operationId;
    private RequestBodyObject requestBody;
    private Map<String, ResponseObject> responses = new HashMap<>();
    private List<ParameterObject> parameters = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

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
    public Map<String,Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "deprecated", deprecated);
        set(map, "summary", summary);
        set(map, "description", description);
        set(map, "operationId", operationId);
        if (requestBody != null) set(map, "requestBody", requestBody);
        if (responses.isEmpty()) {
            // make sure at least one default response is present if there are
            // no user defined responses.
            responses.put("default", new ResponseObject());
        }
        setMapSerializable(map, "responses", responses);
        set(map, "parameters", parameters);
        setList(map, "tags", tags);
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationObject that = (OperationObject) o;
        return Objects.equals(deprecated, that.deprecated) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(description, that.description) &&
                Objects.equals(operationId, that.operationId) &&
                Objects.equals(requestBody, that.requestBody) &&
                Objects.equals(responses, that.responses) &&
                Objects.equals(parameters, that.parameters) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deprecated, summary, description, operationId, requestBody, responses, parameters, tags);
    }

    @Override
    public String toString() {
        return "OperationObject{" +
                ", deprecated=" + deprecated +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", operationId='" + operationId + '\'' +
                ", requestBody=" + requestBody +
                ", responses=" + responses +
                ", parameters=" + parameters +
                ", tags=" + tags +
                '}';
    }
}
