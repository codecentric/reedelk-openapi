package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowObject;

import java.util.Map;

public class OAuthFlowObjectDeserializer extends AbstractDeserializer<OAuthFlowObject> {

    @SuppressWarnings("unchecked")
    @Override
    public OAuthFlowObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OAuthFlowObject flowObject = new OAuthFlowObject();
        flowObject.setAuthorizationUrl(getString(serialized, OAuthFlowObject.Properties.AUTHORIZATION_URL.value()));
        flowObject.setTokenUrl(getString(serialized, OAuthFlowObject.Properties.TOKEN_URL.value()));
        flowObject.setRefreshUrl(getString(serialized, OAuthFlowObject.Properties.REFRESH_URL.value()));
        flowObject.setScopes((Map<String,String>) serialized.get(OAuthFlowObject.Properties.SCOPES.value()));
        return flowObject;
    }
}
