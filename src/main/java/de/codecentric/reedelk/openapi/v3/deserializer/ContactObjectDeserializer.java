package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ContactObject;

import java.util.Map;

public class ContactObjectDeserializer extends AbstractDeserializer<ContactObject> {

    @Override
    public ContactObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ContactObject contactObject = new ContactObject();
        contactObject.setName(getString(serialized, ContactObject.Properties.NAME.value()));
        contactObject.setUrl(getString (serialized, ContactObject.Properties.URL.value()));
        contactObject.setEmail(getString(serialized, ContactObject.Properties.EMAIL.value()));
        return contactObject;
    }
}
