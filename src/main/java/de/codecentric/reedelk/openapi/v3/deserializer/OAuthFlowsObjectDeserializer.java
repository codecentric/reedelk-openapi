package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowObject;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowsObject;

import java.util.Map;

public class OAuthFlowsObjectDeserializer extends AbstractDeserializer<OAuthFlowsObject> {

    @Override
    public OAuthFlowsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OAuthFlowsObject flowsObject = new OAuthFlowsObject();
        mapApiModel(serialized, OAuthFlowsObject.Properties.IMPLICIT.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setImplicit);
        mapApiModel(serialized, OAuthFlowsObject.Properties.PASSWORD.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setPassword);
        mapApiModel(serialized, OAuthFlowsObject.Properties.CLIENT_CREDENTIALS.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setClientCredentials);
        mapApiModel(serialized, OAuthFlowsObject.Properties.AUTHORIZATION_CODE.value(), OAuthFlowObject.class, context)
                .ifPresent(flowsObject::setAuthorizationCode);
        return flowsObject;
    }
}
