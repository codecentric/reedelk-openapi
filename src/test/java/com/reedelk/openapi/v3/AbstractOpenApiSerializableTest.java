package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.OpenApiSerializer;
import com.reedelk.openapi.Serializer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractOpenApiSerializableTest {

    protected void assertSerializeJSON(Serializer serializable, Fixture.Provider expected) {
        String actualJson = OpenApiSerializer.toJson(serializable);
        assertSerializeJSON(actualJson, expected);
    }

    protected void assertSerializeYAML(Serializer serializable, Fixture.Provider expected) {
        String actualYaml = OpenApiSerializer.toYaml(serializable);
        String expectedYaml = expected.string();
        assertThat(actualYaml).isEqualToNormalizingNewlines(expectedYaml);
    }

    protected void assertSerializeJSON(String actual, Fixture.Provider expected) {
        String expectedJson = expected.string();
        JSONAssert.assertEquals(expectedJson, actual, JSONCompareMode.STRICT);
    }
}
