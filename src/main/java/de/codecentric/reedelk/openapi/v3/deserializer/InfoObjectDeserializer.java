package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ContactObject;
import de.codecentric.reedelk.openapi.v3.model.InfoObject;
import de.codecentric.reedelk.openapi.v3.model.LicenseObject;

import java.util.Map;

public class InfoObjectDeserializer extends AbstractDeserializer<InfoObject> {

    @Override
    public InfoObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        InfoObject infoObject = new InfoObject();
        infoObject.setTitle(getString(serialized, InfoObject.Properties.TITLE.value()));
        infoObject.setDescription(getString(serialized, InfoObject.Properties.DESCRIPTION.value()));
        infoObject.setTermsOfService(getString(serialized, InfoObject.Properties.TERMS_OF_SERVICE.value()));
        infoObject.setVersion(getString(serialized, InfoObject.Properties.VERSION.value()));

        // Contact
        mapApiModel(serialized, InfoObject.Properties.CONTACT.value(), ContactObject.class, context)
                .ifPresent(infoObject::setContact);

        // License
        mapApiModel(serialized, InfoObject.Properties.LICENSE.value(), LicenseObject.class, context)
                .ifPresent(infoObject::setLicense);

        return infoObject;
    }
}
