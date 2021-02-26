package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.ExternalDocumentationObject;
import de.codecentric.reedelk.openapi.v3.model.TagObject;
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
