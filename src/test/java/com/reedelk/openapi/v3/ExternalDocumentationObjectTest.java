package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.ExternalDocumentationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExternalDocumentationObjectTest extends AbstractOpenApiSerializableTest {

    private ExternalDocumentationObject externalDocumentationObject;

    @BeforeEach
    void setUp() {
        externalDocumentationObject = new ExternalDocumentationObject();
        externalDocumentationObject.setUrl("http://external.doc/url");
        externalDocumentationObject.setDescription("External documentation URL");
    }

    @Test
    void shouldCorrectlySerializeWithAllProperties() {
        // Given
        ExternalDocumentationObject theExternalDocumentationObject = externalDocumentationObject;

        // Expect
        assertSerializeJSON(theExternalDocumentationObject, Fixture.ExternalDocumentationObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeWithAllProperties() {
        // Given
        ExternalDocumentationObject theExternalDocumentationObject = externalDocumentationObject;

        // Expect
        assertDeserializeJSON(theExternalDocumentationObject, Fixture.ExternalDocumentationObject.WithAllProperties);
    }
}
