package com.reedelk.openapi;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class OpenApiSerializerTest {

    @Test
    void shouldCorrectlySerializeAsJSON() {
        // When
        String actual = OpenApiSerializer.toJson(Fixture.expectedOpenApi);

        // Then
        assertEquals(Fixture.EndToEnd.SAMPLE_JSON.string(), actual, JSONCompareMode.STRICT);
    }

    @Test
    void shouldCorrectlySerializeAsYAML() {
        // When
        String actual = OpenApiSerializer.toYaml(Fixture.expectedOpenApi);

        // Then
        String expected = Fixture.EndToEnd.SAMPLE_YAML.string();
        assertThat(actual).isEqualToNormalizingWhitespace(expected);
    }
}
