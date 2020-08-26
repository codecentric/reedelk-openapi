package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.OAuthFlowObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OAuthFlowObjectTest extends AbstractOpenApiSerializableTest {

    private OAuthFlowObject authFlowObject;

    @BeforeEach
    void setUp() {
        authFlowObject = new OAuthFlowObject();
        authFlowObject.setTokenUrl("http://my.token.url/token");
        authFlowObject.setRefreshUrl("http://my.refresh.url/refresh");
        authFlowObject.setAuthorizationUrl("http://my.auth.url/auth");

        Map<String,String> scopes = new HashMap<>();
        scopes.put("key1", "value1");
        scopes.put("key2", "value2");
        authFlowObject.setScopes(scopes);
    }

    @Test
    void shouldCorrectlySerializeObjectWithAllProperties() {
        // Given
        OAuthFlowObject theAuthFlowObject = authFlowObject;

        // Expect
        assertSerializeJSON(theAuthFlowObject, Fixture.OAuthFlowObject.WithAllProperties);
    }
}
