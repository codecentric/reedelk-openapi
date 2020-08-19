package com.reedelk.openapi.commons;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

public enum DataFormat {

    YAML("yaml") {

        @Override
        public boolean is(String dataAsString) {
            try {
                new Yaml().load(dataAsString);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

        @Override
        public String dump(Object object) {
            return new Yaml().dump(object);
        }
    },

    JSON("json") {

        @Override
        public boolean is(String dataAsString) {
            try {
                new JSONObject(dataAsString);
                return true;
            } catch (Exception ignore) {
                // not a JSON object
            }
            try {
                new JSONArray(dataAsString);
                return true;
            } catch (Exception ignore) {
                // not a JSON array
            }
            return false;
        }

        @Override
        public String dump(Object object) {
            if (object instanceof List) {
                return new JSONArray((List<?>) object).toString(2);
            } else if (object instanceof Map) {
                return new JSONObject((Map<?,?>) object).toString(2);
            } else {
                return new JSONObject(object).toString(2);
            }
        }
    };

    final String extension;

    DataFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public abstract boolean is(String dataAsString);

    public abstract String dump(Object object);

}
