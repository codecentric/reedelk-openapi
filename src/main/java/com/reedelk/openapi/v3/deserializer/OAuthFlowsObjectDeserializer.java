package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.OAuthFlowObject;
import com.reedelk.openapi.v3.model.OAuthFlowsObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.OAuthFlowsObject.Properties;

public class OAuthFlowsObjectDeserializer extends AbstractDeserializer<OAuthFlowsObject> {

    @Override
    public OAuthFlowsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OAuthFlowsObject flowsObject = new OAuthFlowsObject();
        mapApiModel(serialized, Properties.IMPLICIT.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setImplicit);
        mapApiModel(serialized, Properties.PASSWORD.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setPassword);
        mapApiModel(serialized, Properties.CLIENT_CREDENTIALS.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setClientCredentials);
        mapApiModel(serialized, Properties.AUTHORIZATION_CODE.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setAuthorizationCode);
        return flowsObject;
    }
}
