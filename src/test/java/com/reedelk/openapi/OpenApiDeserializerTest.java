package com.reedelk.openapi;

import com.reedelk.openapi.v3.OpenApiObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String json = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject model = OpenApiDeserializer.from(json);

        // Then
        assertThat(model).isNotNull();
    }

    @Test
    void shouldDeserializeFromYAML() {
        // Given
        String yaml = Fixture.EndToEnd.SAMPLE_YAML.string();

        // When
        OpenApiObject model = OpenApiDeserializer.from(yaml);

        // Then
        assertThat(model).isNotNull();
    }
}
