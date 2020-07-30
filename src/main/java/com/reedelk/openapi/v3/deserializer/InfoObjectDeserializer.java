package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ContactObject;
import com.reedelk.openapi.v3.model.InfoObject;
import com.reedelk.openapi.v3.model.LicenseObject;

import java.util.Map;

public class InfoObjectDeserializer extends AbstractDeserializer<InfoObject> {

    @Override
    public InfoObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        InfoObject infoObject = new InfoObject();
        infoObject.setTitle(getString(serialized, "title"));
        infoObject.setDescription(getString(serialized, "description"));
        infoObject.setTermsOfService(getString(serialized, "termsOfService"));
        infoObject.setVersion(getString(serialized, "version"));

        if (serialized.containsKey("contact")) {
            Map<String, Object> contactObjectMap = getMap(serialized, "contact");
            ContactObject contactObject = context.deserialize(ContactObject.class, contactObjectMap);
            infoObject.setContact(contactObject);
        }

        if (serialized.containsKey("license")) {
            Map<String, Object> licenseObjectMap = getMap(serialized, "license");
            LicenseObject licenseObject = context.deserialize(LicenseObject.class, licenseObjectMap);
            infoObject.setLicense(licenseObject);
        }

        return infoObject;
    }
}
