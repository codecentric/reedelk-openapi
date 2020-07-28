package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;
import com.reedelk.openapi.Precondition;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class InfoObject extends OpenApiSerializableAbstract {

    private String title;
    private String description;
    private String termsOfService;
    private String version;
    private ContactObject contact;
    private LicenseObject license;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public ContactObject getContact() {
        return contact;
    }

    public void setContact(ContactObject contact) {
        this.contact = contact;
    }

    public LicenseObject getLicense() {
        return license;
    }

    public void setLicense(LicenseObject license) {
        this.license = license;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public Map<String, Object> serialize() {
        Precondition.checkNotNull("title", title);
        Precondition.checkNotNull("version", version);

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "title", title);
        set(map, "description", description);
        set(map, "termsOfService", termsOfService);
        set(map, "contact", contact);
        set(map, "license", license);
        set(map, "version", version);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        title = getString(serialized, "title");
        description = getString(serialized, "description");
        termsOfService = getString(serialized, "termsOfService");
        version = getString(serialized, "version");
        if (serialized.containsKey("contact")) {
            contact = new ContactObject();
            contact.deserialize(getMap(serialized, "contact"));
        }
        if (serialized.containsKey("license")) {
            license = new LicenseObject();
            license.deserialize(getMap(serialized, "license"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoObject that = (InfoObject) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(termsOfService, that.termsOfService) &&
                Objects.equals(version, that.version) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(license, that.license);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, termsOfService, version, contact, license);
    }

    @Override
    public String toString() {
        return "InfoObject{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfService='" + termsOfService + '\'' +
                ", version='" + version + '\'' +
                ", contact=" + contact +
                ", license=" + license +
                '}';
    }
}
