package com.innowisegroup.messenger.model;

import java.util.Objects;

public class User {
    private Long user_id;
    private String user_name;

    public User(String user_name) {
        this.user_name = user_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id) && Objects.equals(user_name, user.user_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_name);
    }
}
