package com.reedelk.openapi;

import com.reedelk.openapi.v3.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String json = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(json);

        // Then
        assertEquals(expectedOpenApi, actualOpenApi);
    }

    @Test
    void shouldDeserializeFromYAML() {
        // Given
        String yaml = Fixture.EndToEnd.SAMPLE_YAML.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(yaml);

        // Then
        assertEquals(expectedOpenApi, actualOpenApi);
    }

    private void assertEquals(OpenApiObject expected, OpenApiObject actual) {
        InfoObject expectedInfo = expected.getInfo();
        InfoObject actualInfo = actual.getInfo();
        assertThat(expectedInfo).isEqualTo(actualInfo);

        List<ServerObject> expectedServers = expected.getServers();
        List<ServerObject> actualServers = actual.getServers();
        assertThat(expectedServers).isEqualTo(actualServers);
    }

    private static final OpenApiObject expectedOpenApi = new OpenApiObject();
    static {
        // Contact Object
        ContactObject expectedContact = new ContactObject();
        expectedContact.setEmail("apiteam@swagger.io");

        // License Object
        LicenseObject expectedLicense = new LicenseObject();
        expectedLicense.setName("Apache 2.0");
        expectedLicense.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");

        // Info Object
        InfoObject expectedInfo = new InfoObject();
        expectedInfo.setDescription("This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.");
        expectedInfo.setVersion("1.0.2");
        expectedInfo.setTitle("Swagger Petstore");
        expectedInfo.setTermsOfService("http://swagger.io/terms/");
        expectedInfo.setContact(expectedContact);
        expectedInfo.setLicense(expectedLicense);

        // Server
        ServerObject expectedServer = new ServerObject();
        expectedServer.setUrl("https://petstore.swagger.io/v2");

        expectedOpenApi.setOpenapi("3.0.0");
        expectedOpenApi.setBasePath("/");
        expectedOpenApi.setInfo(expectedInfo);
        expectedOpenApi.setServers(Collections.singletonList(expectedServer));
    }
}
