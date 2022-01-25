package com.innowisegroup.messenger.dto.response;

import java.util.Objects;

public class MessageResponse {
    private Long id;
    private Long receiverId;
    private Long senderId;
    private String message;

    public MessageResponse() {
    }

    public MessageResponse(Long messageId, Long receiverId, Long senderId, String message) {
        this.id = messageId;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
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
        MessageResponse that = (MessageResponse) o;
        return id.equals(that.id) && receiverId.equals(that.receiverId) && senderId.equals(that.senderId) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receiverId, senderId, message);
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "messageId=" + id +
                ", receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", message='" + message + '\'' +
                '}';
    }
}

