package com.reedelk.openapi.v3;

import java.util.Objects;

public class Example {

    private String data;

    public Example() {
    }

    public Example(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(data, example.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Example{" +
                "data='" + data + '\'' +
                '}';
    }
}
