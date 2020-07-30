package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.LicenseObject;

import java.util.Map;

public class LicenseObjectDeserializer extends AbstractDeserializer<LicenseObject> {

    @Override
    public LicenseObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        LicenseObject licenseObject = new LicenseObject();
        licenseObject.setName(getString(serialized, "name"));
        licenseObject.setUrl(getString(serialized, "url"));
        return licenseObject;
    }
}
