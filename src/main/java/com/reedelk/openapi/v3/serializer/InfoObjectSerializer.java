package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.InfoObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.InfoObject.Properties;

public class InfoObjectSerializer extends AbstractSerializer<InfoObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, InfoObject input) {
        Precondition.checkNotNull(Properties.TITLE.value(), input.getTitle());
        Precondition.checkNotNull(Properties.VERSION.value(), input.getVersion());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.TITLE.value(), input.getTitle());
        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        set(map, Properties.TERMS_OF_SERVICE.value(), input.getTermsOfService());

        if (input.getContact() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.CONTACT);
            Map<String, Object> serializedContact = context.serialize(currentNavigationPath, input.getContact());
            set(map, Properties.CONTACT.value(), serializedContact);
        }

        if (input.getLicense() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.LICENSE);
            Map<String, Object> serializedLicense = context.serialize(currentNavigationPath, input.getLicense());
            set(map, Properties.LICENSE.value(), serializedLicense);
        }

        set(map, Properties.VERSION.value(), input.getVersion());
        return map;
    }
}
