package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.commons.Properties;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;

import java.util.Map;

public class RequestBodyObjectDeserializer extends AbstractDeserializer<RequestBodyObject> {

    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public RequestBodyObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        RequestBodyObject requestBodyObject = new RequestBodyObject();

        if (serialized.containsKey(Properties.$REF)) {
            requestBodyObject.set$ref(getString(serialized, Properties.$REF));

        } else {
            requestBodyObject.setRequired(getBoolean(serialized, "required"));
            requestBodyObject.setDescription(getString(serialized, "description"));

            mapKeyApiModel("content", serialized,
                    (key, source) -> context.deserialize(MediaTypeObject.class, source))
                    .ifPresent(requestBodyObject::setContent);
        }

        return requestBodyObject;
    }
}
