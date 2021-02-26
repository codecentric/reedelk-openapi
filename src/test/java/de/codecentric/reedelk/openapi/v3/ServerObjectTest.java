package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.ServerObject;
import de.codecentric.reedelk.openapi.v3.model.ServerVariableObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ServerObjectTest extends AbstractOpenApiSerializableTest {

    private ServerObject server;

    @BeforeEach
    void setUp() {
        ServerVariableObject environmentVariable = new ServerVariableObject();
        environmentVariable.setEnumValues(Arrays.asList("dev", "uat", "prod"));
        environmentVariable.setDescription("Environment variable");
        environmentVariable.setDefaultValue("dev");

        ServerVariableObject domainVariable = new ServerVariableObject();
        domainVariable.setEnumValues(Arrays.asList("localhost", "mydomain1", "mydomain2"));
        domainVariable.setDescription("Domain variable");
        domainVariable.setDefaultValue("localhost");

        Map<String, ServerVariableObject> variables = new HashMap<>();
        variables.put("environment", environmentVariable);
        variables.put("domain", domainVariable);

        server = new ServerObject();
        server.setUrl("https://{environment}.{domain}.com/v1");
        server.setDescription("Development server");
        server.setVariables(variables);
    }

    @Test
    void shouldCorrectlySerializeServerWithAllProperties() {
        // Given
        ServerObject theServer = server;

        // Expect
        assertSerializeJSON(theServer, Fixture.ServerObject.WithAllPropertiesJson);
        assertSerializeYAML(theServer, Fixture.ServerObject.WithAllPropertiesYaml);
    }

    @Test
    void shouldCorrectlyDeserializeWithAllProperties() {
        // Given
        ServerObject theServer = server;

        // Expect
        assertDeserializeJSON(theServer, Fixture.ServerObject.WithAllPropertiesJson);
        assertDeserializeYAML(theServer, Fixture.ServerObject.WithAllPropertiesYaml);
    }

    @Test
    void shouldCorrectlySerializeServerWithRequiredValues() {
        // Given
        ServerObject server = new ServerObject();
        server.setUrl("/");

        // Expect
        assertSerializeJSON(server, Fixture.ServerObject.WithDefaultPropertiesJson);
        assertSerializeYAML(server, Fixture.ServerObject.WithDefaultPropertiesYaml);
    }

    @Test
    void shouldCorrectlyDeserializeServerWithRequiredValues() {
        // Given
        ServerObject server = new ServerObject();
        server.setUrl("/");

        // Expect
        assertDeserializeJSON(server, Fixture.ServerObject.WithDefaultPropertiesJson);
        assertDeserializeYAML(server, Fixture.ServerObject.WithDefaultPropertiesYaml);
    }
}
