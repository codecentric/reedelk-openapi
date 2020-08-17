package com.reedelk.openapi.commons;

import org.yaml.snakeyaml.Yaml;

public class YamlDumper {

    public static String from(Object data) {
        return new Yaml().dump(data);
    }
}
