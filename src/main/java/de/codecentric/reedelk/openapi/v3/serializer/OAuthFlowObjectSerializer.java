package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class OAuthFlowObjectSerializer extends AbstractSerializer<OAuthFlowObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OAuthFlowObject input) {
        Map<String, Object> serialized = new LinkedHashMap<>();
        set(serialized, OAuthFlowObject.Properties.AUTHORIZATION_URL.value(), input.getAuthorizationUrl());
        set(serialized, OAuthFlowObject.Properties.TOKEN_URL.value(), input.getTokenUrl());
        set(serialized, OAuthFlowObject.Properties.REFRESH_URL.value(), input.getRefreshUrl());
        if (input.getScopes() != null && !input.getScopes().isEmpty()) {
            serialized.put(OAuthFlowObject.Properties.SCOPES.value(), input.getScopes());
        }
        return serialized;
    }
}
