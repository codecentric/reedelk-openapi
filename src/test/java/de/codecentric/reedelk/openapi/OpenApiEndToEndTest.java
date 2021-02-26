package de.codecentric.reedelk.openapi;

import de.codecentric.reedelk.openapi.v3.model.OpenApiObject;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

/*
 * These tests make sure that the Serialization/Deserialization/Serialization are working correctly.
 */
public class OpenApiEndToEndTest {

    private static final List<Fixture.Provider> JSON_EXAMPLES;
    static {
        List<Fixture.Provider> tmp = new ArrayList<>();
        tmp.add(Fixture.EndToEnd.SAMPLE_JSON);
        tmp.add(Fixture.EndToEnd.WITH_AUTH_JSON);
        tmp.add(Fixture.EndToEnd.BOOKINGS_API_JSON);
        JSON_EXAMPLES = tmp;
    }

    private static final List<Fixture.Provider> YAML_EXAMPLES;
    static {
        List<Fixture.Provider> tmp = new ArrayList<>();
        tmp.add(Fixture.EndToEnd.SAMPLE_YAML);
        tmp.add(Fixture.EndToEnd.WITH_AUTH_YAML);
        tmp.add(Fixture.EndToEnd.BOOKINGS_API_YAML);
        YAML_EXAMPLES = tmp;
    }

    @Test
    void shouldCorrectlySerializeDeserializeAndSerializeJSONExamples() {
        for (Fixture.Provider example : JSON_EXAMPLES) {
            String input = example.string();
            OpenApiObject deserialized = OpenApi.from(input);
            String serialized = OpenApi.toJson(deserialized);
            assertEquals(input, serialized, STRICT);
        }
    }

    @Test
    void shouldCorrectlySerializeDeserializeAndSerializeYAMLExamples() {
        for (Fixture.Provider example : YAML_EXAMPLES) {
            String input = example.string();
            OpenApiObject deserialized = OpenApi.from(input);
            String serialized = OpenApi.toYaml(deserialized);
            Map<String,Object> inputAsMap = new Yaml().load(input);
            Map<String,Object> actualAsMap = new Yaml().load(serialized);
            assertThat(inputAsMap).isEqualTo(actualAsMap);
        }
    }
}
