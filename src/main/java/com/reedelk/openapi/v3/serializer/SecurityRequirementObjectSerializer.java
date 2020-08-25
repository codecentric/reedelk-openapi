package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.SecurityRequirementObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class SecurityRequirementObjectSerializer extends AbstractSerializer<SecurityRequirementObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, SecurityRequirementObject input) {
        Map<String, Object> responseObject = new LinkedHashMap<>();
        responseObject.put(SecurityRequirementObject.Properties.SCOPES.value(), input.getScopes());
        return responseObject;
    }

}
