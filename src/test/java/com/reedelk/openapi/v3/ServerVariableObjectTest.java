package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.ServerVariableObject;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class ServerVariableObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeServerVariableWithAllProperties() {
        // Given
        ServerVariableObject serverVariable = new ServerVariableObject();
        serverVariable.setEnumValues(asList("dev", "uat", "prod"));
        serverVariable.setDescription("Environment variable");
        serverVariable.setDefaultValue("dev");

        // Expect
        assertSerializeJSON(serverVariable, Fixture.ServerVariableObject.WithAllPropertiesJson);
        assertSerializeYAML(serverVariable, Fixture.ServerVariableObject.WithAllPropertiesYaml);
    }

    @Test
    void shouldCorrectlySerializeServerWithRequiredValues() {
        // Given
        ServerVariableObject serverVariable = new ServerVariableObject();
        serverVariable.setDefaultValue("localhost");

        // When
        assertSerializeJSON(serverVariable, Fixture.ServerVariableObject.WithDefaultPropertiesJson);
        assertSerializeYAML(serverVariable, Fixture.ServerVariableObject.WithDefaultPropertiesYaml);
    }
}
