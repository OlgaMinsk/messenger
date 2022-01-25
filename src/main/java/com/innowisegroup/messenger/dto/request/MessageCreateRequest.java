package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class MessageCreateRequest {
    private Long receiverId;
    private String message;

    public MessageCreateRequest() {
    }

    public MessageCreateRequest(Long receiverId, String message) {
        this.message = message;
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageCreateRequest that = (MessageCreateRequest) o;
        return Objects.equals(message, that.message) && Objects.equals(receiverId, that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, receiverId);
    }

    @Override
    public String toString() {
        return "MessageCreateRequest{" +
                "message='" + message + '\'' +
                ", receiverId=" + receiverId +
                '}';
    }
}
