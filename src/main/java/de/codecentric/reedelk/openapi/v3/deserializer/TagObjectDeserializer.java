package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ExternalDocumentationObject;
import de.codecentric.reedelk.openapi.v3.model.TagObject;

import java.util.Map;

public class TagObjectDeserializer extends AbstractDeserializer<TagObject> {

    @Override
    public TagObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        TagObject tagObject = new TagObject();
        tagObject.setName(getString(serialized, TagObject.Properties.NAME.value()));
        tagObject.setDescription(getString(serialized, TagObject.Properties.DESCRIPTION.value()));

        // External Docs
        mapApiModel(serialized, TagObject.Properties.EXTERNAL_DOCS.value(), ExternalDocumentationObject.class, context)
                .ifPresent(tagObject::setExternalDocs);
        return tagObject;
    }
}
