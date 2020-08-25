package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.OAuthFlowsObject;
import com.reedelk.openapi.v3.model.SecurityKeyLocation;
import com.reedelk.openapi.v3.model.SecuritySchemeObject;
import com.reedelk.openapi.v3.model.SecurityType;

import java.util.Map;

import static com.reedelk.openapi.v3.model.SecuritySchemeObject.Properties;

public class SecuritySchemeObjectDeserializer extends AbstractDeserializer<SecuritySchemeObject> {

    @Override
    public SecuritySchemeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        SecuritySchemeObject security = new SecuritySchemeObject();

        String typeValue = getString(serialized, Properties.TYPE.value());
        if (typeValue != null) security.setType(SecurityType.valueOf(typeValue));

        security.setDescription(getString(serialized, Properties.DESCRIPTION.value()));
        security.setName(getString(serialized, Properties.NAME.value()));

        String keyLocation = getString(serialized, Properties.IN.value());
        if (keyLocation != null) security.setIn(SecurityKeyLocation.valueOf(keyLocation));

        security.setScheme(getString(serialized, Properties.SCHEME.value()));
        security.setBearerFormat(getString(serialized, Properties.BEARER_FORMAT.value()));

        mapApiModel(serialized, Properties.FLOWS.value(), OAuthFlowsObject.class, context)
                .ifPresent(security::setFlows);

        security.setOpenIdConnectUrl(getString(serialized, Properties.OPEN_ID_CONNECT_URL.value()));
        return security;
    }
}
