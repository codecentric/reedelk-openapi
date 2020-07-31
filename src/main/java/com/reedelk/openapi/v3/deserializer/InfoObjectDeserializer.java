package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
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

        // Contact
        mapApiModel(serialized, "contact", ContactObject.class, context)
                .ifPresent(infoObject::setContact);

        // License
        mapApiModel(serialized, "license", LicenseObject.class, context)
                .ifPresent(infoObject::setLicense);

        return infoObject;
    }
}
