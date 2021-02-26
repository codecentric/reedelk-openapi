package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.ContactObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactObjectTest extends AbstractOpenApiSerializableTest {

    private ContactObject contact;

    @BeforeEach
    void setUp() {
        contact = new ContactObject();
        contact.setName("API Support");
        contact.setUrl("http://www.example.com/support");
        contact.setEmail("support@example.com");
    }

    @Test
    void shouldCorrectlySerializeContactWithAllProperties() {
        // Given
        ContactObject theContact = contact;

        // Expect
        assertSerializeJSON(theContact, Fixture.ContactObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeContactWithAllProperties() {
        // Given
        ContactObject theContact = contact;

        // Expect
        assertDeserializeJSON(theContact, Fixture.ContactObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeContactWithRequiredValues() {
        // Given
        ContactObject contact = new ContactObject();

        // Expect (expect empty, because there are no required properties for contact)
        assertSerializeJSON(contact, Fixture.ContactObject.WithDefaultProperties);
    }

    @Test
    void shouldCorrectlyDeserializeContactWithRequiredValues() {
        // Given
        ContactObject contact = new ContactObject();

        // Expect (expect empty, because there are no required properties for contact)
        assertDeserializeJSON(contact, Fixture.ContactObject.WithDefaultProperties);
    }
}
