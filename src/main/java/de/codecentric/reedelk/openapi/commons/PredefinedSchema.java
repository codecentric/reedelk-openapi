package de.codecentric.reedelk.openapi.commons;

import org.json.JSONObject;

import java.util.Map;

public enum PredefinedSchema {

    STRING("{\n" +
            "    \"type\": \"string\"\n" +
            "  }"),
    INTEGER("{\n" +
            "    \"type\": \"integer\"\n" +
            "  }"),
    ARRAY_INTEGER("{\n" +
            "    \"type\": \"array\",\n" +
            "    \"items\": {\n" +
            "      \"type\": \"integer\"\n" +
            "      }" +
            "    }"),
    ARRAY_STRING("{\n" +
            "    \"type\": \"array\",\n" +
            "    \"items\": {\n" +
            "      \"type\": \"string\"\n" +
            "      }" +
            "    }");

    private String schema;

    PredefinedSchema(String schema) {
        this.schema = schema;
    }

    public Map<String,Object> schema() {
        return new JSONObject(schema).toMap();
    }
}
