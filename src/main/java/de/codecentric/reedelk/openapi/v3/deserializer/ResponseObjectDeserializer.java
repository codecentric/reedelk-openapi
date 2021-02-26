package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.HeaderObject;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;
import de.codecentric.reedelk.openapi.v3.model.ResponseObject;

import java.util.Map;

public class ResponseObjectDeserializer extends AbstractDeserializer<ResponseObject> {

    @Override
    public ResponseObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ResponseObject responseObject = new ResponseObject();

        responseObject.setDescription(getString(serialized, ResponseObject.Properties.DESCRIPTION.value()));

        // Content
        mapKeyApiModel(ResponseObject.Properties.CONTENT.value(), serialized,
                (key, source) -> context.deserialize(MediaTypeObject.class, source))
                .ifPresent(responseObject::setContent);

        // Headers
        mapKeyApiModel(ResponseObject.Properties.HEADERS.value(), serialized,
                (key, source) -> context.deserialize(HeaderObject.class, source))
                .ifPresent(responseObject::setHeaders);

        return responseObject;
    }
}
