package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ExternalDocumentationObject.Properties;

public class ExternalDocumentationObjectSerializer extends AbstractSerializer<ExternalDocumentationObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ExternalDocumentationObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        set(map, Properties.URL.value(), input.getUrl());
        return map;
    }
}
