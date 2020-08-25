package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.OAuthFlowObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.OAuthFlowObject.Properties;

public class OAuthFlowObjectDeserializer extends AbstractDeserializer<OAuthFlowObject> {

    @SuppressWarnings("unchecked")
    @Override
    public OAuthFlowObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OAuthFlowObject flowObject = new OAuthFlowObject();
        flowObject.setAuthorizationUrl(getString(serialized, Properties.AUTHORIZATION_URL.value()));
        flowObject.setTokenUrl(getString(serialized, Properties.TOKEN_URL.value()));
        flowObject.setRefreshUrl(getString(serialized, Properties.REFRESH_URL.value()));
        flowObject.setScopes((Map<String,String>) serialized.get(Properties.SCOPES.value()));
        return flowObject;
    }
}
