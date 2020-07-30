package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializable;

import java.util.Map;

public interface Schema extends OpenApiSerializable {

    Map<String, Object> getSchemaData();

    void setSchemaData(Map<String, Object> schemaData);

    String getSchemaId();

}
