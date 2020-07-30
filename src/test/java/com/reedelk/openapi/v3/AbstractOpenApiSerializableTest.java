package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.OpenApi;
import com.reedelk.openapi.OpenApiModel;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractOpenApiSerializableTest {

    protected void assertSerializeJSON(OpenApiModel serializable, Fixture.Provider expected) {
        String actualJson = OpenApi.toJson(serializable);
        assertSerializeJSON(actualJson, expected);
    }

    protected void assertSerializeYAML(OpenApiModel serializable, Fixture.Provider expected) {
        String actualYaml = OpenApi.toYaml(serializable);
        String expectedYaml = expected.string();
        assertThat(actualYaml).isEqualToNormalizingNewlines(expectedYaml);
    }

    protected void assertSerializeJSON(String actual, Fixture.Provider expected) {
        String expectedJson = expected.string();
        JSONAssert.assertEquals(expectedJson, actual, JSONCompareMode.STRICT);
    }
}
