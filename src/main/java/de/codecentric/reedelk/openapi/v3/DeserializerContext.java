package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Deserializer;
import de.codecentric.reedelk.openapi.commons.DataFormat;
import de.codecentric.reedelk.openapi.v3.deserializer.Deserializers;

import java.util.Map;

public class DeserializerContext {

    private final Deserializers deserializers;
    private final DataFormat dataFormat;

    public DeserializerContext(Deserializers deserializers, DataFormat dataFormat) {
        this.deserializers = deserializers;
        this.dataFormat = dataFormat;
    }

    public DataFormat getDataFormat() {
        return dataFormat;
    }

    public <T> T deserialize(Class<T> targetClazz, Map<String, Object> objectAsMap) {
        Deserializer<T> deserializer = deserializers.forType(targetClazz);
        return deserializer.deserialize(this, objectAsMap);
    }
}
