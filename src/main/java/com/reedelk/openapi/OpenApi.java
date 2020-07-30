package com.reedelk.openapi;

import com.reedelk.openapi.v3.model.OpenApiObject;

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
}
