package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class UserCreateRequest {
    private String userName;
    private String password;

    public UserCreateRequest() {
    }

    public UserCreateRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateRequest that = (UserCreateRequest) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "name='" + userName + '\'' +
                '}';
    }
}
