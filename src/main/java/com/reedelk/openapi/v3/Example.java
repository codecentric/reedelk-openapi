package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.Map;

public class Example extends OpenApiSerializableAbstract {

    private String data;

    public Example() {
    }

    public Example(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

    @Override
    public Map<String, Object> serialize() {
        // TODO: Here
        return null;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        // TODO: Here
    }
}
