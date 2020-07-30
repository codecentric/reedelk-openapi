package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ContactObject;

import java.util.Map;

public class ContactObjectDeserializer extends AbstractDeserializer<ContactObject> {

    @Override
    public ContactObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ContactObject contactObject = new ContactObject();
        contactObject.setName(getString(serialized, "name"));
        contactObject.setUrl(getString (serialized, "url"));
        contactObject.setEmail(getString(serialized, "email"));
        return contactObject;
    }
}
