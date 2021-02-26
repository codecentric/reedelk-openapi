package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ExampleObject;

import java.util.Map;

public class ExampleObjectDeserializer extends AbstractDeserializer<ExampleObject> {

    @Override
    public ExampleObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ExampleObject exampleObject = new ExampleObject();
        exampleObject.setExternalValue(getString(serialized, ExampleObject.Properties.EXTERNAL_VALUE.value()));
        exampleObject.setDescription(getString (serialized, ExampleObject.Properties.DESCRIPTION.value()));
        exampleObject.setSummary(getString(serialized, ExampleObject.Properties.SUMMARY.value()));
        exampleObject.setExampleRef(getString(serialized, ExampleObject.Properties.$REF.value()));

        if (serialized.containsKey(ExampleObject.Properties.VALUE.value())) {
            Object exampleData = serialized.get(ExampleObject.Properties.VALUE.value());
            String exampleDataAsString = deserializeExampleDataAsString(exampleData);
            exampleObject.setValue(exampleDataAsString);
        }

        return exampleObject;
    }
}
