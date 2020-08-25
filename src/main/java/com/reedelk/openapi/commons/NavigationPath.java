package com.reedelk.openapi.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NavigationPath {

    private List<PathSegment> pathList;

    private NavigationPath() {
        pathList = new ArrayList<>();
    }

    private NavigationPath(List<PathSegment> pathList, SegmentKey key, String segmentValue) {
        this.pathList = new ArrayList<>(pathList);
        this.pathList.add(new PathSegment(key, segmentValue));
    }

    public NavigationPath with(SegmentKey tokenKey) {
        // Segment Key == Segment Value
        return new NavigationPath(pathList, tokenKey, tokenKey.key);
    }

    public NavigationPath with(SegmentKey segmentKey, String segmentValue) {
        return new NavigationPath(pathList, segmentKey, segmentValue);
    }

    public static NavigationPath create() {
        return new NavigationPath();
    }

    public List<PathSegment> getPathList() {
        return Collections.unmodifiableList(pathList);
    }

    @Override
    public String toString() {
        return pathList + "";
    }

    public static class PathSegment {

        private final SegmentKey segmentKey;
        private final String segmentValue;

        PathSegment(SegmentKey segmentKey, String segmentValue) {
            this.segmentKey = segmentKey;
            this.segmentValue = segmentValue;
        }

        public SegmentKey getSegmentKey() {
            return segmentKey;
        }

        public String getSegmentValue() {
            return segmentValue;
        }

        @Override
        public String toString() {
            if (Objects.equals(segmentKey.key, segmentValue)) {
                return segmentKey.key;
            } else {
                return segmentValue + " (" + segmentKey.key + ")";
            }
        }
    }

    public enum SegmentKey {

        EXTERNAL_DOCS("externalDocs"),
        HEADER("header"),
        HEADERS("headers"),
        HEADER_NAME("headerName"),
        CONTENT("content"),
        CONTENT_TYPE("contentType"),
        INFO("info"),
        SERVERS("servers"),
        SERVER_URL("serverUrl"),
        TAGS("tags"),
        TAG_NAME("tagName"),
        COMPONENTS("components"),
        PATH("path"),
        PATHS("paths"),
        METHOD("method"),
        OPERATION_ID("operationId"),
        LICENSE("license"),
        CONTACT("contact"),
        REQUEST_BODY_ID("requestBodyId"),
        REQUEST_BODIES("requestBodies"),
        REQUEST_BODY("requestBody"),
        RESPONSE("response"),
        RESPONSES("responses"),
        STATUS_CODE("statusCode"),
        VARIABLES("variables"),
        VARIABLE_NAME("variableName"),
        PARAMETER("parameter"),
        PARAMETERS("parameters"),
        PARAMETER_NAME("parameterName"),
        SCHEMA("schema"),
        SCHEMAS("schemas"),
        SCHEMA_ID("schemaId"),
        EXAMPLE("example"),
        EXAMPLES("examples"),
        EXAMPLE_ID("exampleId");

        String key;

        SegmentKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
