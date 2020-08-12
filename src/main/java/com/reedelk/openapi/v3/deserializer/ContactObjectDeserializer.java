package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ContactObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.ContactObject.Properties;

public class ContactObjectDeserializer extends AbstractDeserializer<ContactObject> {

    @Override
    public ContactObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ContactObject contactObject = new ContactObject();
        contactObject.setName(getString(serialized, Properties.NAME.value()));
        contactObject.setUrl(getString (serialized, Properties.URL.value()));
        contactObject.setEmail(getString(serialized, Properties.EMAIL.value()));
        return contactObject;
    }
}
