package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;
import org.junit.jupiter.api.Test;

public class ExternalDocumentationObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeWithAllProperties() {
        // Given
        ExternalDocumentationObject externalDocumentationObject = new ExternalDocumentationObject();
        externalDocumentationObject.setUrl("http://external.doc/url");
        externalDocumentationObject.setDescription("External documentation URL");

        // Expect
        assertSerializeJSON(externalDocumentationObject, Fixture.ExternalDocumentationObject.WithAllProperties);
    }
}
