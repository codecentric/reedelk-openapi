package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.PathsObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class PathsObjectSerializer extends AbstractSerializer<PathsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, PathsObject input) {
        Map<String, Object> pathsObject = new LinkedHashMap<>();

        input.getPaths().forEach((path, pathItemObject) -> {
            Map<String, Object> operationsByPathJsonObject = new LinkedHashMap<>();
            pathItemObject.forEach((restMethod, operationObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.PATH, path)
                        .with(NavigationPath.SegmentKey.METHOD, restMethod.name())
                        .with(NavigationPath.SegmentKey.OPERATION_ID, operationObject.getOperationId());
                Map<String, Object> serializedOperation = context.serialize(currentNavigationPath, operationObject);
                operationsByPathJsonObject.put(restMethod.name().toLowerCase(), serializedOperation);
            });
            pathsObject.put(path, operationsByPathJsonObject);
        });

        return pathsObject;
    }
}
