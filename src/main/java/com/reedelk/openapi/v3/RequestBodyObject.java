package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class RequestBodyObject extends OpenApiSerializableAbstract {

    private String $ref;
    private Boolean required;
    private String description;
    private Map<String, MediaTypeObject> content = new HashMap<>();

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, MediaTypeObject> getContent() {
        return content;
    }

    public void setContent(Map<String, MediaTypeObject> content) {
        this.content = content;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    @Override
    public Map<String,Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        if ($ref != null && $ref.length() > 0) {
            // It is a reference.
            set(map, "$ref", $ref);
        } else {
            set(map, "description", description);
            // By specification, the content object must be present, even if it is empty.
            // Therefore we add an empty object instead.
            if (content.isEmpty()) {
                map.put("content", new LinkedHashMap<>());
            }
            setMapSerializable(map, "content", content);
            set(map, "required", required);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        boolean isReference = serialized.containsKey("$ref");
        if (isReference) {
            $ref = getString(serialized, "$ref");
        } else {
            required = getBoolean(serialized, "required");
            description = getString(serialized, "description");
            Map<String, Map<String,Object>> contentMap = (Map<String, Map<String, Object>>) serialized.get("content");
            contentMap.forEach((contentType, mediaTypeMap) -> {
                MediaTypeObject mediaTypeObject = new MediaTypeObject();
                mediaTypeObject.deserialize(mediaTypeMap);
                content.put(contentType, mediaTypeObject);
            });
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBodyObject that = (RequestBodyObject) o;
        return Objects.equals($ref, that.$ref) &&
                Objects.equals(required, that.required) &&
                Objects.equals(description, that.description) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash($ref, required, description, content);
    }

    @Override
    public String toString() {
        return "RequestBodyObject{" +
                "$ref='" + $ref + '\'' +
                ", required=" + required +
                ", description='" + description + '\'' +
                ", content=" + content +
                '}';
    }
}
