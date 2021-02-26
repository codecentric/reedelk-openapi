package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.SecuritySchemeObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.codecentric.reedelk.openapi.v3.model.SecuritySchemeObject.Properties;

public class SecuritySchemeObjectSerializer extends AbstractSerializer<SecuritySchemeObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, SecuritySchemeObject input) {
        Map<String, Object> serialized = new LinkedHashMap<>();
        set(serialized, Properties.TYPE.value(), input.getType().name());
        set(serialized, Properties.DESCRIPTION.value(), input.getDescription());
        set(serialized, Properties.NAME.value(), input.getName());
        if (input.getIn() != null) set(serialized, Properties.IN.value(), input.getIn().name());
        set(serialized, Properties.SCHEME.value(), input.getScheme());
        set(serialized, Properties.BEARER_FORMAT.value(), input.getBearerFormat());
        if (input.getFlows() != null) {
            Map<String, Object> serializedFlows = context.serialize(navigationPath, input.getFlows());
            set(serialized, Properties.FLOWS.value(), serializedFlows);
        }
        set(serialized, Properties.OPEN_ID_CONNECT_URL.value(), input.getOpenIdConnectUrl());
        return serialized;
    }
}
