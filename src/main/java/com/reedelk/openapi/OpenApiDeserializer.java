package com.reedelk.openapi;

import com.reedelk.openapi.v3.model.OpenApiObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.Map;

class OpenApiDeserializer {

    OpenApiObject from(String jsonOrYaml) {
        Yaml yaml = new Yaml();
        Map<String,Object> openApiMap = yaml.load(jsonOrYaml);

        // TODO: Add check on open api property is mandatory
        String openapi = (String) openApiMap.get("openapi");
        OpenApiVersion VERSION = Arrays.stream(OpenApiVersion.values())
                .filter(openApiVersion -> openApiVersion.isSupported(openapi))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Open API version " + openapi + ", not supported"));

        return VERSION.deserialize(openApiMap);
    }
}
