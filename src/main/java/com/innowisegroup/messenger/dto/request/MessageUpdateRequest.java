package com.innowisegroup.messenger.dto.request;

import java.util.Objects;

public class MessageUpdateRequest {
    String message;

    public MessageUpdateRequest() {
    }

    public MessageUpdateRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageUpdateRequest that = (MessageUpdateRequest) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "MessageUpdateRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
