package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.Map;

public class TagObjectDeserializer extends AbstractDeserializer<TagObject> {

    @Override
    public TagObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        TagObject tagObject = new TagObject();
        tagObject.setName(getString(serialized, "name"));
        tagObject.setDescription(getString(serialized, "description"));

        if (serialized.containsKey("externalDocs")) {
            Map<String, Object> externalDocsMap = getMap(serialized, "externalDocs");
            ExternalDocumentationObject externalDocumentationObject =
                    context.deserialize(ExternalDocumentationObject.class, externalDocsMap);
            tagObject.setExternalDocs(externalDocumentationObject);
        }
        return tagObject;
    }
}
