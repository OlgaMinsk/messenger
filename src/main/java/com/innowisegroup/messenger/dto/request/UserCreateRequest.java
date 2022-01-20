package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class UserCreateRequest {
    String name;

    public UserCreateRequest() {
    }

    public UserCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateRequest that = (UserCreateRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
