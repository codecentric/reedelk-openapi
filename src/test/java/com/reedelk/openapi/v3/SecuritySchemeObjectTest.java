package com.reedelk.openapi.v3;

import com.reedelk.openapi.v3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.reedelk.openapi.Fixture.SecuritySchemeObject.WithAllProperties;

public class SecuritySchemeObjectTest extends AbstractOpenApiSerializableTest {

    private SecuritySchemeObject securitySchemeObject;

    @BeforeEach
    void setUp() {
        OAuthFlowObject flowObject = new OAuthFlowObject();
        flowObject.setRefreshUrl("http://refresh.url");
        OAuthFlowsObject flowsObject = new OAuthFlowsObject();
        flowsObject.setImplicit(flowObject);

        securitySchemeObject = new SecuritySchemeObject();
        securitySchemeObject.setType(SecurityType.apiKey);
        securitySchemeObject.setIn(SecurityKeyLocation.header);
        securitySchemeObject.setOpenIdConnectUrl("http://openid.connection/url");
        securitySchemeObject.setBearerFormat("JWT");
        securitySchemeObject.setName("my_auth");
        securitySchemeObject.setDescription("My description");
        securitySchemeObject.setScheme("basic");
        securitySchemeObject.setFlows(flowsObject);
    }

    @Test
    void shouldCorrectlySerializeWithAllProperties() {
        // Given
        SecuritySchemeObject theSecuritySchemeObject = securitySchemeObject;

        // Expect
        assertSerializeJSON(theSecuritySchemeObject, WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeWithAllProperties() {
        // Given
        SecuritySchemeObject theSecuritySchemeObject = securitySchemeObject;

        // Expect
        assertDeserializeJSON(theSecuritySchemeObject, WithAllProperties);
    }
}
