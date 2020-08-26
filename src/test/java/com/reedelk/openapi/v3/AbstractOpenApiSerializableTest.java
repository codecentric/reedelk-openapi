package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.OpenApi;
import com.reedelk.openapi.OpenApiModel;
import com.reedelk.openapi.commons.DataFormat;
import com.reedelk.openapi.v3.deserializer.Deserializers;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractOpenApiSerializableTest {

    protected void assertDeserializeYAML(OpenApiModel expectedObject, Fixture.Provider expected) {
        String jsonOrYaml = expected.string();
        Yaml yaml = new Yaml();
        Map<String,Object> openApiMap = yaml.load(jsonOrYaml);

        DeserializerContext context =  new DeserializerContext(new Deserializers(), DataFormat.YAML);
        OpenApiModel actualObject = context.deserialize(expectedObject.getClass(), openApiMap);

        assertThat(actualObject).isEqualTo(expectedObject);
    }

    protected void assertDeserializeJSON(OpenApiModel expectedObject, Fixture.Provider expected) {
        String jsonOrYaml = expected.string();
        Yaml yaml = new Yaml();
        Map<String,Object> openApiMap = yaml.load(jsonOrYaml);

        DeserializerContext context =  new DeserializerContext(new Deserializers(), DataFormat.JSON);
        OpenApiModel actualObject = context.deserialize(expectedObject.getClass(), openApiMap);

        assertThat(actualObject).isEqualTo(expectedObject);
    }

    protected void assertSerializeJSON(OpenApiModel serializable, Fixture.Provider expected) {
        String actualJson = OpenApi.toJson(serializable);
        assertSerializeJSON(actualJson, expected);
    }

    protected void assertSerializeYAML(OpenApiModel serializable, Fixture.Provider expected) {
        String actualYaml = OpenApi.toYaml(serializable);
        String expectedYaml = expected.string();
        Yaml yaml = new Yaml();
        Map<String,Object> actualMap = yaml.load(actualYaml);
        Map<String,Object> expectedMap = yaml.load(expectedYaml);
        assertThat(actualMap).isEqualTo(expectedMap);
    }

    protected void assertSerializeJSON(String actual, Fixture.Provider expected) {
        String expectedJson = expected.string();
        JSONAssert.assertEquals(expectedJson, actual, JSONCompareMode.STRICT);
    }
}
