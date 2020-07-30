package com.reedelk.openapi;

import com.reedelk.openapi.v3.model.OpenApiObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String json = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject actualOpenApi = OpenApi.from(json);

        // Then
        OpenApiObject expectedOpenApi = Fixture.expectedOpenApi;
        assertThat(expectedOpenApi).isEqualTo(actualOpenApi);
    }

    @Test
    void shouldDeserializeFromYAML() {
        // Given
        String yaml = Fixture.EndToEnd.SAMPLE_YAML.string();

        // When
        OpenApiObject actualOpenApi = OpenApi.from(yaml);

        // Then
        OpenApiObject expectedOpenApi = Fixture.expectedOpenApi;
        assertThat(expectedOpenApi).isEqualTo(actualOpenApi);
    }
}
