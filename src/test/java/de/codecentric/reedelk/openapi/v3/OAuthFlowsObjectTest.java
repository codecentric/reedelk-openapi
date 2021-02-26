package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowObject;
import de.codecentric.reedelk.openapi.v3.model.OAuthFlowsObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OAuthFlowsObjectTest extends AbstractOpenApiSerializableTest {

    private OAuthFlowsObject authFlowsObject;

    @BeforeEach
    void setUp() {
        authFlowsObject = new OAuthFlowsObject();
        authFlowsObject.setAuthorizationCode(createOAuthFlow("authorization"));
        authFlowsObject.setClientCredentials(createOAuthFlow("credentials"));
        authFlowsObject.setPassword(createOAuthFlow("password"));
        authFlowsObject.setImplicit(createOAuthFlow("implicit"));
    }

    @Test
    void shouldCorrectlySerializeObjectWithAllProperties() {
        // Given
        OAuthFlowsObject theAuthFlowsObject = authFlowsObject;

        // Expect
        assertSerializeJSON(theAuthFlowsObject, Fixture.OAuthFlowsObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeObjectWithAllProperties() {
        // Given
        OAuthFlowsObject theAuthFlowsObject = authFlowsObject;

        // Expect
        assertDeserializeJSON(theAuthFlowsObject, Fixture.OAuthFlowsObject.WithAllProperties);
    }

    private OAuthFlowObject createOAuthFlow(String url) {
        OAuthFlowObject object = new OAuthFlowObject();
        object.setTokenUrl(url);
        object.setRefreshUrl("http://my.refresh.url/refresh");
        object.setAuthorizationUrl("http://my.auth.url/auth");
        return object;
    }
}
