package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.TagObject.Properties;

public class TagObjectSerializer extends AbstractSerializer<TagObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, TagObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.NAME.value(), input.getName());
        set(map, Properties.DESCRIPTION.value(), input.getDescription());

        if (input.getExternalDocs() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.EXTERNAL_DOCS);
            Map<String, Object> serializedExternalDocs = context.serialize(currentNavigationPath, input.getExternalDocs());
            map.put(Properties.EXTERNAL_DOCS.value(), serializedExternalDocs);
        }
        return map;
    }
}
