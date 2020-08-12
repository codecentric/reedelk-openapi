package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class PathsObject implements OpenApiModel {

    private Map<String, Map<RestMethod, OperationObject>> paths = new TreeMap<>();

    public Map<String, Map<RestMethod, OperationObject>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<RestMethod, OperationObject>> paths) {
        this.paths = paths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathsObject that = (PathsObject) o;
        return Objects.equals(paths, that.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paths);
    }

    @Override
    public String toString() {
        return "PathsObject{" +
                "paths=" + paths +
                '}';
    }

    public enum Properties {

        PATHS("paths");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
