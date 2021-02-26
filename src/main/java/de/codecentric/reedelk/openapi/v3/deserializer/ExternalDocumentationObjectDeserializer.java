package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ExternalDocumentationObject;

import java.util.Map;

public class ExternalDocumentationObjectDeserializer extends AbstractDeserializer<ExternalDocumentationObject> {

    @Override
    public ExternalDocumentationObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ExternalDocumentationObject externalDocumentationObject = new ExternalDocumentationObject();
        externalDocumentationObject.setDescription(getString(serialized, ExternalDocumentationObject.Properties.DESCRIPTION.value()));
        externalDocumentationObject.setUrl(getString(serialized, ExternalDocumentationObject.Properties.URL.value()));
        return externalDocumentationObject;
    }
}
