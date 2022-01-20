package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class UserUpdateRequest {
    String name;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name) {
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
        UserUpdateRequest that = (UserUpdateRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
