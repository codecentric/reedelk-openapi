package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.ResponseObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.ResponseObject.Properties;

public class ResponseObjectDeserializer extends AbstractDeserializer<ResponseObject> {

    @Override
    public ResponseObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ResponseObject responseObject = new ResponseObject();

        responseObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));

        // Content
        mapKeyApiModel(Properties.CONTENT.value(), serialized,
                (key, source) -> context.deserialize(MediaTypeObject.class, source))
                .ifPresent(responseObject::setContent);

        // Headers
        mapKeyApiModel(Properties.HEADERS.value(), serialized,
                (key, source) -> context.deserialize(HeaderObject.class, source))
                .ifPresent(responseObject::setHeaders);

        return responseObject;
    }
}
