package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class TagObjectSerializer extends AbstractSerializer<TagObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, TagObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, TagObject.Properties.NAME.value(), input.getName());
        set(map, TagObject.Properties.DESCRIPTION.value(), input.getDescription());

        if (input.getExternalDocs() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.EXTERNAL_DOCS);
            Map<String, Object> serializedExternalDocs = context.serialize(currentNavigationPath, input.getExternalDocs());
            map.put(TagObject.Properties.EXTERNAL_DOCS.value(), serializedExternalDocs);
        }
        return map;
    }
}
