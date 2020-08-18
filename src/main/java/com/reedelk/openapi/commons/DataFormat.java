package com.reedelk.openapi.commons;

import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
            } catch (Exception exception) {
                return false;
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public String dump(Object object) {
            // If the input object is a linked has map we must create a new HashMap,
            // otherwise the JSON object does not serialize correctly.
            if (object instanceof LinkedHashMap) {
                Map<Object, Object> map = new HashMap<>();
                ((Map<Object, Object>) object).forEach(map::put);
                return new JSONObject(map).toString(2);
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
