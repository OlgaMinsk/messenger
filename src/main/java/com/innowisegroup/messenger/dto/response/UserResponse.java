package com.innowisegroup.messenger.dto.response;

import java.util.Objects;

public class UserResponse {
    private Long id;
    private String userName;

    public UserResponse() {
    }

    public UserResponse(Long userId, String userName) {
        this.id = userId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        UserResponse that = (UserResponse) o;
        return id.equals(that.id) && userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + id +
                ", name='" + userName + '\'' +
                '}';
    }
}
