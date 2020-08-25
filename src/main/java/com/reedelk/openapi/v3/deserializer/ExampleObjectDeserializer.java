package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ExampleObject;

import java.util.Map;

import static com.reedelk.openapi.v3.model.ExampleObject.Properties;

public class ExampleObjectDeserializer extends AbstractDeserializer<ExampleObject> {

    @Override
    public ExampleObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ExampleObject exampleObject = new ExampleObject();
        exampleObject.setExternalValue(getString(serialized, Properties.EXTERNAL_VALUE.value()));
        exampleObject.setDescription(getString (serialized, Properties.DESCRIPTION.value()));
        exampleObject.setSummary(getString(serialized, Properties.SUMMARY.value()));
        exampleObject.setExampleRef(getString(serialized, Properties.$REF.value()));

        if (serialized.containsKey(Properties.VALUE.value())) {
            Object exampleData = serialized.get(Properties.VALUE.value());
            String exampleDataAsString = deserializeExampleDataAsString(exampleData);
            exampleObject.setValue(exampleDataAsString);
        }

        return exampleObject;
    }
}
