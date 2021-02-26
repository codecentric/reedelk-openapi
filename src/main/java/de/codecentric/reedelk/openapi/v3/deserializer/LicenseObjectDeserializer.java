package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.LicenseObject;

import java.util.Map;

public class LicenseObjectDeserializer extends AbstractDeserializer<LicenseObject> {

    @Override
    public LicenseObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        LicenseObject licenseObject = new LicenseObject();
        licenseObject.setName(getString(serialized, LicenseObject.Properties.NAME.value()));
        licenseObject.setUrl(getString(serialized, LicenseObject.Properties.URL.value()));
        return licenseObject;
    }
}
