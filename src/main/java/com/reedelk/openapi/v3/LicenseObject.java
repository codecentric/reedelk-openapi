package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;
import com.reedelk.openapi.Precondition;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LicenseObject extends OpenApiSerializableAbstract {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Map<String, Object> serialize() {
        Precondition.checkNotNull("name", name);

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", name);
        set(map, "url", url);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        name = getString(serialized, "name");
        url = getString(serialized, "url");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseObject that = (LicenseObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "LicenseObject{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
