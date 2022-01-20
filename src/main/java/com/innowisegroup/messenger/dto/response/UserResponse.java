package com.innowisegroup.messenger.dto.response;

import java.util.List;
import java.util.Objects;

public class UserResponse {
    Long userId;
    String name;
    List<MessageResponse> messages;

    public UserResponse() {
    }

    public UserResponse(Long userId, String name, List<MessageResponse> messages) {
        this.userId = userId;
        this.name = name;
        this.messages = messages;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return userId.equals(that.userId) && name.equals(that.name) && messages.equals(that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, messages);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", messages=" + messages +
                '}';
    }
}
