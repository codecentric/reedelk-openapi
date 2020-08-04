package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Properties;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestBodyObjectSerializer extends AbstractSerializer<RequestBodyObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, RequestBodyObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (input.get$ref() != null && input.get$ref().length() > 0) {
            // It is a reference.
            set(map, Properties.$REF, input.get$ref());

        } else {
            set(map, "description", input.getDescription());
            // By specification, the content object must be present, even if it is empty.
            // Therefore we add an empty object instead.
            if (input.getContent().isEmpty()) {
                map.put("content", new LinkedHashMap<>());
            }

            Map<String, Map<String,Object>> contentTypeMediaTypeMap = new LinkedHashMap<>();
            Map<String, MediaTypeObject> content = input.getContent();
            content.forEach((contentType, mediaTypeObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with("content")
                        .with("contentType", contentType);
                Map<String, Object> serialized = context.serialize(currentNavigationPath, mediaTypeObject);
                contentTypeMediaTypeMap.put(contentType, serialized);
            });
            map.put("content", contentTypeMediaTypeMap);
            set(map, "required", input.getRequired());
        }
        return map;
    }
}
