package com.reedelk.openapi;

import com.reedelk.openapi.commons.JsonObjectFactory;
import com.reedelk.openapi.commons.Utils;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OpenApiObject;
import com.reedelk.openapi.v3.serializer.Serializers;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.Map;

public class OpenApi {

    public static String toJson(OpenApiModel openApiModel) {
        return new OpenApiSerializer().toJson(openApiModel);
    }

    public static String toYaml(OpenApiModel openApiModel) {
        return new OpenApiSerializer().toYaml(openApiModel);
    }

    public static OpenApiObject from(String jsonOrYaml) {
        return new OpenApiDeserializer().from(jsonOrYaml);
    }

    static class OpenApiDeserializer {

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

    static class OpenApiSerializer {

        private static final int JSON_INDENT_FACTOR = 2;

        private final Serializers serializers = new Serializers();

        String toJson(OpenApiModel serializable) {
            SerializerContext context =  new SerializerContext(serializers);
            Map<String, Object> serialized = context.serialize(serializable);
            // We use the custom object factory to preserve position
            // of serialized properties in the map.
            JSONObject jsonObject = (JSONObject) process(serialized);
            return jsonObject.toString(JSON_INDENT_FACTOR);
        }

        String toYaml(OpenApiModel serializable) {
            SerializerContext context =  new SerializerContext(serializers);
            Map<String, Object> serialized = context.serialize(serializable);
            Yaml yaml = new Yaml();
            return yaml.dump(serialized);
        }

        @SuppressWarnings("unchecked")
        private Object process(Object value) {
            if (value instanceof Map) {
                JSONObject nested = JsonObjectFactory.newJSONObject();
                Map<String,Object> nestedMap = (Map<String, Object>) value;
                nestedMap.forEach((key, theValue) -> nested.put(key, process(theValue)));
                return nested;
            } else {
                return value;
            }
        }
    }
}
