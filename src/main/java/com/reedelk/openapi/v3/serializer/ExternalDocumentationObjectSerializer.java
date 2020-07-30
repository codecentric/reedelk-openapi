package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExternalDocumentationObjectSerializer extends AbstractSerializer<ExternalDocumentationObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, ExternalDocumentationObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "description", input.getDescription());
        set(map, "url", input.getUrl());
        return map;

    }
}
