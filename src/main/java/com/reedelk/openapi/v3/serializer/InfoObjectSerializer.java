package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.InfoObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfoObjectSerializer extends AbstractSerializer<InfoObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, InfoObject input) {
        Precondition.checkNotNull("title", input.getTitle());
        Precondition.checkNotNull("version", input.getVersion());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "title", input.getTitle());
        set(map, "description", input.getDescription());
        set(map, "termsOfService", input.getTermsOfService());

        if (input.getContact() != null) {
            Map<String, Object> serializedContact = context.serialize(input.getContact());
            set(map, "contact", serializedContact);
        }

        if (input.getLicense() != null) {
            Map<String, Object> serializedLicense = context.serialize(input.getLicense());
            set(map, "license", serializedLicense);
        }

        set(map, "version", input.getVersion());
        return map;
    }
}
