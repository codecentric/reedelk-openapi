package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.*;

public class OperationObject implements OpenApiModel {

    private Boolean deprecated;
    private String summary;
    private String description;
    private String operationId;
    private RequestBodyObject requestBody;
    private Map<String, ResponseObject> responses = new LinkedHashMap<>();
    private List<ParameterObject> parameters = new ArrayList<>();
    private List<Map<String,SecurityRequirementObject>> security = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
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

    public List<ParameterObject> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterObject> parameters) {
        this.parameters = parameters;
    }

    public List<Map<String, SecurityRequirementObject>> getSecurity() {
        return security;
    }

    public void setSecurity(List<Map<String, SecurityRequirementObject>> security) {
        this.security = security;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OperationObject that = (OperationObject) object;
        return Objects.equals(deprecated, that.deprecated) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(description, that.description) &&
                Objects.equals(operationId, that.operationId) &&
                Objects.equals(requestBody, that.requestBody) &&
                Objects.equals(responses, that.responses) &&
                Objects.equals(parameters, that.parameters) &&
                Objects.equals(security, that.security) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deprecated, summary, description, operationId, requestBody, responses, parameters, security, tags);
    }

    @Override
    public String toString() {
        return "OperationObject{" +
                "deprecated=" + deprecated +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", operationId='" + operationId + '\'' +
                ", requestBody=" + requestBody +
                ", responses=" + responses +
                ", parameters=" + parameters +
                ", security=" + security +
                ", tags=" + tags +
                '}';
    }

    public enum Properties {

        DEPRECATED("deprecated"),
        SUMMARY("summary"),
        DESCRIPTION("description"),
        OPERATION_ID("operationId"),
        REQUEST_BODY("requestBody"),
        RESPONSES("responses"),
        PARAMETERS("parameters"),
        SECURITY("security"),
        TAGS("tags");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
