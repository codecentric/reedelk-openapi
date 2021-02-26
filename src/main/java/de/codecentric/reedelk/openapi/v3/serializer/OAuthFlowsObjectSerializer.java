package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowsObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class OAuthFlowsObjectSerializer extends AbstractSerializer<OAuthFlowsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OAuthFlowsObject input) {
        Map<String, Object> serialized = new LinkedHashMap<>();
        if (input.getImplicit() != null) {
            Map<String, Object> serializedImplicit = context.serialize(navigationPath, input.getImplicit());
            set(serialized, OAuthFlowsObject.Properties.IMPLICIT.value(), serializedImplicit);
        }
        if (input.getPassword() != null) {
            Map<String, Object> serializedPassword = context.serialize(navigationPath, input.getPassword());
            set(serialized, OAuthFlowsObject.Properties.PASSWORD.value(), serializedPassword);
        }
        if (input.getClientCredentials() != null) {
            Map<String, Object> serializedClientCredentials = context.serialize(navigationPath, input.getClientCredentials());
            set(serialized, OAuthFlowsObject.Properties.CLIENT_CREDENTIALS.value(), serializedClientCredentials);
        }
        if (input.getAuthorizationCode() != null) {
            Map<String, Object> serializedAuthorizationCode = context.serialize(navigationPath, input.getAuthorizationCode());
            set(serialized, OAuthFlowsObject.Properties.AUTHORIZATION_CODE.value(), serializedAuthorizationCode);
        }
        return serialized;
    }
}
