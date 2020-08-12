package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.TagObject.Properties;

public class TagObjectDeserializer extends AbstractDeserializer<TagObject> {

    @Override
    public TagObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        TagObject tagObject = new TagObject();
        tagObject.setName(getString(serialized, Properties.NAME.value()));
        tagObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));

        // External Docs
        mapApiModel(serialized, Properties.EXTERNAL_DOCS.value(), ExternalDocumentationObject.class, context)
                .ifPresent(tagObject::setExternalDocs);
        return tagObject;
    }
}
