package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.RequestBodyObject.Properties;

public class RequestBodyObjectDeserializer extends AbstractDeserializer<RequestBodyObject> {

    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public RequestBodyObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        RequestBodyObject requestBodyObject = new RequestBodyObject();

        if (serialized.containsKey(Properties.$REF.value())) {
            requestBodyObject.set$ref(getString(serialized, Properties.$REF.value()));

        } else {
            requestBodyObject.setRequired(getBoolean(serialized, Properties.REQUIRED.value()));
            requestBodyObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));

            mapKeyApiModel(Properties.CONTENT.value(), serialized,
                    (key, source) -> context.deserialize(MediaTypeObject.class, source))
                    .ifPresent(requestBodyObject::setContent);
        }

        return requestBodyObject;
    }
}
