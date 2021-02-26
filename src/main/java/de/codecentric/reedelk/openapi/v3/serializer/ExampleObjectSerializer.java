package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.ExampleObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.codecentric.reedelk.openapi.v3.model.ExampleObject.Properties;

public class ExampleObjectSerializer extends AbstractSerializer<ExampleObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ExampleObject input) {
        Map<String,Object> serialized = new LinkedHashMap<>();
        if (isExampleReference(input)) {
            set(serialized, Properties.$REF.value(), input.getExampleRef());
        } else {
            set(serialized, Properties.VALUE.value(), input.getValue());
            set(serialized, Properties.SUMMARY.value(), input.getSummary());
            set(serialized, Properties.DESCRIPTION.value(), input.getDescription());
            set(serialized, Properties.EXTERNAL_VALUE.value(), input.getExternalValue());
        }
        return serialized;
    }

    private boolean isExampleReference(ExampleObject input) {
        return input.getExampleRef() != null && input.getExampleRef().length() > 0;
    }
}
