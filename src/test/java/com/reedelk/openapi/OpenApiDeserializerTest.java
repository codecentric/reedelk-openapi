package com.reedelk.openapi;

import com.reedelk.openapi.v3.OpenApiObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String input = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject actual = OpenApiDeserializer.from(input);

        // Then

        assertThat(actual).isNotNull();
    }
}
