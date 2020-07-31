package com.reedelk.openapi;

import com.reedelk.openapi.commons.Utils;
import com.reedelk.openapi.v3.model.OpenApiObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.Map;

class OpenApiDeserializer {

    OpenApiObject from(String jsonOrYaml) {
        Yaml yaml = new Yaml();
        Map<String,Object> openApiMap = yaml.load(jsonOrYaml);

        String openApiVersion = (String) openApiMap.get("openapi");
        if (Utils.isBlank(openApiVersion)) {
            throw new IllegalArgumentException("Missing 'openapi' version property in the JSON or YAML structure");
        }
        OpenApiVersion VERSION = Arrays.stream(OpenApiVersion.values())
                .filter(version -> version.isSupported(openApiVersion))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Open API version " + openApiVersion + ", not supported"));

        return VERSION.deserialize(openApiMap);
    }
}
