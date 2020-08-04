package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseObjectSerializer extends AbstractSerializer<ResponseObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ResponseObject input) {
        Map<String, Object> responseObject = new LinkedHashMap<>();
        set(responseObject, "description", input.getDescription());

        // Content
        Map<String, MediaTypeObject> content = input.getContent();
        if (content != null && !content.isEmpty()) {
            Map<String, Map<String, Object>> serializedContent = new LinkedHashMap<>();
            content.forEach((contentType, mediaTypeObject) -> {

                NavigationPath currentNavigationPath = navigationPath
                        .with("content")
                        .with("contentType", contentType);

                Map<String, Object> serializedMediaType = context.serialize(currentNavigationPath, mediaTypeObject);
                serializedContent.put(contentType, serializedMediaType);
            });
            responseObject.put("content", serializedContent);
        }

        // Headers
        Map<String, HeaderObject> headerObjectMap = input.getHeaders();
        if (headerObjectMap != null && !headerObjectMap.isEmpty()) {
            Map<String, Object> serializedHeaders = new LinkedHashMap<>();
            headerObjectMap.forEach((headerName, headerObject) -> {

                NavigationPath currentNavigationPath = navigationPath
                        .with("headers")
                        .with("headerName", headerName);

                Map<String, Object> serializedHeaderObject = context.serialize(currentNavigationPath, headerObject);
                serializedHeaders.put(headerName, serializedHeaderObject);
            });
            responseObject.put("headers", serializedHeaders);
        }

        return responseObject;
    }
}
