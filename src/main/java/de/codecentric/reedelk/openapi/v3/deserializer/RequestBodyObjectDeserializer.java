package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;
import de.codecentric.reedelk.openapi.v3.model.RequestBodyObject;

import java.util.Map;

public class RequestBodyObjectDeserializer extends AbstractDeserializer<RequestBodyObject> {

    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public RequestBodyObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        RequestBodyObject requestBodyObject = new RequestBodyObject();

        if (serialized.containsKey(RequestBodyObject.Properties.$REF.value())) {
            requestBodyObject.set$ref(getString(serialized, RequestBodyObject.Properties.$REF.value()));

        } else {
            requestBodyObject.setRequired(getBoolean(serialized, RequestBodyObject.Properties.REQUIRED.value()));
            requestBodyObject.setDescription(getString(serialized, RequestBodyObject.Properties.DESCRIPTION.value()));

            mapKeyApiModel(RequestBodyObject.Properties.CONTENT.value(), serialized,
                    (key, source) -> context.deserialize(MediaTypeObject.class, source))
                    .ifPresent(requestBodyObject::setContent);
        }

        return requestBodyObject;
    }
}
