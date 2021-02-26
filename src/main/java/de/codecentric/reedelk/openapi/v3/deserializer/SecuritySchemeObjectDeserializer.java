package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowsObject;
import de.codecentric.reedelk.openapi.v3.model.SecurityKeyLocation;
import de.codecentric.reedelk.openapi.v3.model.SecuritySchemeObject;
import de.codecentric.reedelk.openapi.v3.model.SecurityType;

import java.util.Map;

public class SecuritySchemeObjectDeserializer extends AbstractDeserializer<SecuritySchemeObject> {

    @Override
    public SecuritySchemeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        SecuritySchemeObject security = new SecuritySchemeObject();

        String typeValue = getString(serialized, SecuritySchemeObject.Properties.TYPE.value());
        if (typeValue != null) security.setType(SecurityType.valueOf(typeValue));

        security.setDescription(getString(serialized, SecuritySchemeObject.Properties.DESCRIPTION.value()));
        security.setName(getString(serialized, SecuritySchemeObject.Properties.NAME.value()));

        String keyLocation = getString(serialized, SecuritySchemeObject.Properties.IN.value());
        if (keyLocation != null) security.setIn(SecurityKeyLocation.valueOf(keyLocation));

        security.setScheme(getString(serialized, SecuritySchemeObject.Properties.SCHEME.value()));
        security.setBearerFormat(getString(serialized, SecuritySchemeObject.Properties.BEARER_FORMAT.value()));

        mapApiModel(serialized, SecuritySchemeObject.Properties.FLOWS.value(), OAuthFlowsObject.class, context)
                .ifPresent(security::setFlows);

        security.setOpenIdConnectUrl(getString(serialized, SecuritySchemeObject.Properties.OPEN_ID_CONNECT_URL.value()));
        return security;
    }
}
