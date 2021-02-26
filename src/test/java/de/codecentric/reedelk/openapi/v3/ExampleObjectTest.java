package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.ExampleObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleObjectTest extends AbstractOpenApiSerializableTest {

    private ExampleObject exampleObject;

    @BeforeEach
    void setUp() {
        exampleObject = new ExampleObject();
        exampleObject.setExternalValue("http://example.org/examples/address-example.xml");
        exampleObject.setDescription("My description");
        exampleObject.setValue("Hello World!");
        exampleObject.setSummary("My summary");
    }

    @Test
    void shouldCorrectlySerializeExampleWithAllProperties() {
        // Given
        ExampleObject theExample = exampleObject;

        // Expect
        assertSerializeJSON(theExample, Fixture.ExampleObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeExampleWithAllProperties() {
        // Given
        ExampleObject theExample = exampleObject;

        // Expect
        assertDeserializeJSON(theExample, Fixture.ExampleObject.WithAllProperties);
    }
}
