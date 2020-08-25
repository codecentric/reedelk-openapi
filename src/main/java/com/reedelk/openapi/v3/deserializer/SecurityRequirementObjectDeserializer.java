package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.SecurityRequirementObject;

import java.util.List;
import java.util.Map;

import static com.reedelk.openapi.v3.model.SecurityRequirementObject.Properties;

public class SecurityRequirementObjectDeserializer extends AbstractDeserializer<SecurityRequirementObject> {

    @SuppressWarnings("unchecked")
    @Override
    public SecurityRequirementObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        SecurityRequirementObject requirementObject = new SecurityRequirementObject();
        if (serialized.containsKey(Properties.SCOPES.value())) {
            List<String> scopes = (List<String>) serialized.get(Properties.SCOPES.value());
            requirementObject.setScopes(scopes);
        }
        return requirementObject;
    }
}
