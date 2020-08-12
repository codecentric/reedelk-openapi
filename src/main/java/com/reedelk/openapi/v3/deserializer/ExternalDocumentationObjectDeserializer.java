package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.ExternalDocumentationObject.Properties;

public class ExternalDocumentationObjectDeserializer extends AbstractDeserializer<ExternalDocumentationObject> {

    @Override
    public ExternalDocumentationObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ExternalDocumentationObject externalDocumentationObject = new ExternalDocumentationObject();
        externalDocumentationObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));
        externalDocumentationObject.setUrl(getString(serialized, Properties.URL.value()));
        return externalDocumentationObject;
    }
}
