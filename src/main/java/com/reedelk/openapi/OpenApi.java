package com.reedelk.openapi;

import com.reedelk.openapi.commons.MapToJsonObject;
import com.reedelk.openapi.commons.Utils;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OpenApiObject;
import com.reedelk.openapi.v3.serializer.Serializers;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OpenApi {

    public static String toJson(OpenApiModel openApiModel) {
        return new OpenApiSerializer().toJson(openApiModel, new HashMap<>());
    }

    public static String toJson(OpenApiModel openApiModel, Map<Class<?>, Serializer<?>> overridden) {
        return new OpenApiSerializer().toJson(openApiModel, overridden);
    }

    public static String toYaml(OpenApiModel openApiModel, Map<Class<?>, Serializer<?>> overridden) {
        return new OpenApiSerializer().toYaml(openApiModel, overridden);
    }

    public static String toYaml(OpenApiModel openApiModel) {
        return new OpenApiSerializer().toYaml(openApiModel, new HashMap<>());
    }

    public static OpenApiObject from(String jsonOrYaml) {
        return new OpenApiDeserializer().from(jsonOrYaml, new HashMap<>());
    }

    public static OpenApiObject from(String jsonOrYaml, Map<Class<?>, Deserializer<?>> overridden) {
        return new OpenApiDeserializer().from(jsonOrYaml, overridden);
    }

    static class OpenApiDeserializer {

        OpenApiObject from(String jsonOrYaml, Map<Class<?>, Deserializer<?>> overridden) {
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

            return VERSION.deserialize(openApiMap, overridden);
        }
    }

    static class OpenApiSerializer {

        private static final int JSON_INDENT_FACTOR = 2;

        String toJson(OpenApiModel serializable, Map<Class<?>, Serializer<?>> overridden) {
            Map<String, Object> serialized = serializeAsMap(serializable, overridden);
            // We use the custom object factory to preserve position
            // of serialized properties in the map.
            JSONObject jsonObject = (JSONObject) MapToJsonObject.convert(serialized);
            return jsonObject.toString(JSON_INDENT_FACTOR);
        }

        String toYaml(OpenApiModel serializable, Map<Class<?>, Serializer<?>> overridden) {
            Map<String, Object> serialized = serializeAsMap(serializable, overridden);
            Yaml yaml = new Yaml();
            return yaml.dump(serialized);
        }

        private Map<String, Object> serializeAsMap(OpenApiModel serializable, Map<Class<?>, Serializer<?>> overridden) {
            Serializers serializers = new Serializers(overridden);
            SerializerContext context = new SerializerContext(serializers);
            return context.serialize(serializable);
        }
    }
}
