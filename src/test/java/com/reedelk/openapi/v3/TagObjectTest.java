package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.junit.jupiter.api.Test;

public class TagObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeWithAllProperties() {
        // Given
        ExternalDocumentationObject externalDocumentationObject = new ExternalDocumentationObject();
        externalDocumentationObject.setUrl("http://external.doc/url");
        TagObject tagObject = new TagObject();
        tagObject.setName("pet");
        tagObject.setDescription("All about pets");
        tagObject.setExternalDocs(externalDocumentationObject);

        // Expect
        assertSerializeJSON(tagObject, Fixture.TagObject.WithAllProperties);
    }
}
