package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ContactObject;
import com.reedelk.openapi.v3.model.InfoObject;
import com.reedelk.openapi.v3.model.LicenseObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.InfoObject.Properties;

public class InfoObjectDeserializer extends AbstractDeserializer<InfoObject> {

    @Override
    public InfoObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        InfoObject infoObject = new InfoObject();
        infoObject.setTitle(getString(serialized, Properties.TITLE.value()));
        infoObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));
        infoObject.setTermsOfService(getString(serialized, Properties.TERMS_OF_SERVICE.value()));
        infoObject.setVersion(getString(serialized, Properties.VERSION.value()));

        // Contact
        mapApiModel(serialized, Properties.CONTACT.value(), ContactObject.class, context)
                .ifPresent(infoObject::setContact);

        // License
        mapApiModel(serialized, Properties.LICENSE.value(), LicenseObject.class, context)
                .ifPresent(infoObject::setLicense);

        return infoObject;
    }
}
