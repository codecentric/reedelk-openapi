package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestBodyObjectDeserializer extends AbstractDeserializer<RequestBodyObject> {

    @SuppressWarnings({"DuplicatedCode", "unchecked"})
    @Override
    public RequestBodyObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        RequestBodyObject requestBodyObject = new RequestBodyObject();

        if (serialized.containsKey("$ref")) {
            requestBodyObject.set$ref(getString(serialized, "$ref"));

        } else {
            Map<String, MediaTypeObject> contentMediaTypeMap = new LinkedHashMap<>();
            Map<String, Map<String,Object>> contentMap = (Map<String, Map<String, Object>>) serialized.get("content");
            contentMap.forEach((contentType, mediaTypeMap) -> {
                MediaTypeObject mediaTypeObject = context.deserialize(MediaTypeObject.class, mediaTypeMap);
                contentMediaTypeMap.put(contentType, mediaTypeObject);
            });

            requestBodyObject.setRequired(getBoolean(serialized, "required"));
            requestBodyObject.setDescription(getString(serialized, "description"));
            requestBodyObject.setContent(contentMediaTypeMap);
        }

        return requestBodyObject;
    }
}
