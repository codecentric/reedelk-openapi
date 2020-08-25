package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OAuthFlowObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.OAuthFlowObject.Properties;

public class OAuthFlowObjectSerializer extends AbstractSerializer<OAuthFlowObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OAuthFlowObject input) {
        Map<String, Object> serialized = new LinkedHashMap<>();
        set(serialized, Properties.AUTHORIZATION_URL.value(), input.getAuthorizationUrl());
        set(serialized, Properties.TOKEN_URL.value(), input.getTokenUrl());
        set(serialized, Properties.REFRESH_URL.value(), input.getRefreshUrl());
        if (input.getScopes() != null && !input.getScopes().isEmpty()) {
            serialized.put(Properties.SCOPES.value(), input.getScopes());
        }
        return serialized;
    }
}
