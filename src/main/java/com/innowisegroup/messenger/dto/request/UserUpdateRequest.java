package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class UserUpdateRequest {
    private String userName;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateRequest that = (UserUpdateRequest) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "name='" + userName + '\'' +
                '}';
    }
}
