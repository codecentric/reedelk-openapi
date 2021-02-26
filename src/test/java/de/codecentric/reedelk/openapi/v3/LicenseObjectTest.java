package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.LicenseObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LicenseObjectTest extends AbstractOpenApiSerializableTest {

    private LicenseObject license;

    @BeforeEach
    void setUp() {
        license = new LicenseObject();
        license.setName("Apache 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
    }

    @Test
    void shouldCorrectlySerializeLicenseWithAllProperties() {
        // Given
        LicenseObject theLicense = license;

        // Expect
        assertSerializeJSON(theLicense, Fixture.LicenseObject.WithAllPropertiesJson);
        assertSerializeYAML(theLicense, Fixture.LicenseObject.WithAllPropertiesYaml);
    }

    @Test
    void shouldCorrectlyDeserializeLicenseWithAllProperties() {
        // Given
        LicenseObject theLicense = license;

        // Expect
        assertDeserializeJSON(theLicense, Fixture.LicenseObject.WithAllPropertiesJson);
        assertDeserializeYAML(theLicense, Fixture.LicenseObject.WithAllPropertiesYaml);
    }

    @Test
    void shouldCorrectlySerializeLicenseWithRequiredValues() {
        // Given
        LicenseObject license = new LicenseObject();
        license.setName("API License");

        // Expect
        assertSerializeJSON(license, Fixture.LicenseObject.WithDefaultPropertiesJson);
        assertSerializeYAML(license, Fixture.LicenseObject.WithDefaultPropertiesYaml);
    }
}
