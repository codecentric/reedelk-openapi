package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenApiObject implements OpenApiModel {

    private static final String OPEN_API_VERSION = "3.0.3";
    
    // Info Object is required by spec
    private String openapi = OPEN_API_VERSION;
    private InfoObject info = new InfoObject();
    private ComponentsObject components = new ComponentsObject();
    private List<ServerObject> servers = new ArrayList<>();
    private PathsObject paths = new PathsObject();
    private List<TagObject> tags = new ArrayList<>();

    public String getOpenapi() {
        return openapi;
    }

    public void setOpenapi(String openapi) {
        this.openapi = openapi;
    }

    public InfoObject getInfo() {
        return info;
    }

    public void setInfo(InfoObject info) {
        this.info = info;
    }

    public List<ServerObject> getServers() {
        return servers;
    }

    public void setServers(List<ServerObject> servers) {
        this.servers = servers;
    }

    public void setPaths(PathsObject paths) {
        this.paths = paths;
    }

    public PathsObject getPaths() {
        return paths;
    }

    public ComponentsObject getComponents() {
        return components;
    }

    public void setComponents(ComponentsObject components) {
        this.components = components;
    }

    public List<TagObject> getTags() {
        return tags;
    }

    public void setTags(List<TagObject> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenApiObject that = (OpenApiObject) o;
        return Objects.equals(openapi, that.openapi) &&
                Objects.equals(info, that.info) &&
                Objects.equals(components, that.components) &&
                Objects.equals(servers, that.servers) &&
                Objects.equals(paths, that.paths) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openapi, info, components, servers, paths, tags);
    }

    @Override
    public String toString() {
        return "OpenApiObject{" +
                "openapi='" + openapi + '\'' +
                ", info=" + info +
                ", components=" + components +
                ", servers=" + servers +
                ", paths=" + paths +
                ", tags=" + tags +
                '}';
    }

    public enum Properties {

        OPEN_API("openapi"),
        INFO("info"),
        COMPONENTS("components"),
        SERVERS("servers"),
        PATHS("paths"),
        TAGS("tags");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
