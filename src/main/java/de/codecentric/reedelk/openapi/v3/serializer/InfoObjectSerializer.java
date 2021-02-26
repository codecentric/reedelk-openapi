package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.commons.Precondition;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.InfoObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class InfoObjectSerializer extends AbstractSerializer<InfoObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, InfoObject input) {
        Precondition.checkNotNull(InfoObject.Properties.TITLE.value(), input.getTitle());
        Precondition.checkNotNull(InfoObject.Properties.VERSION.value(), input.getVersion());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, InfoObject.Properties.TITLE.value(), input.getTitle());
        set(map, InfoObject.Properties.DESCRIPTION.value(), input.getDescription());
        set(map, InfoObject.Properties.TERMS_OF_SERVICE.value(), input.getTermsOfService());

        if (input.getContact() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.CONTACT);
            Map<String, Object> serializedContact = context.serialize(currentNavigationPath, input.getContact());
            set(map, InfoObject.Properties.CONTACT.value(), serializedContact);
        }

        if (input.getLicense() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.LICENSE);
            Map<String, Object> serializedLicense = context.serialize(currentNavigationPath, input.getLicense());
            set(map, InfoObject.Properties.LICENSE.value(), serializedLicense);
        }

        set(map, InfoObject.Properties.VERSION.value(), input.getVersion());
        return map;
    }
}
