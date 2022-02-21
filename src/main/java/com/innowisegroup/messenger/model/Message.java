package com.innowisegroup.messenger.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name = "messages")
@Table(name = "messages", schema = "messenger")
public class Message implements Serializable {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String message;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id.equals(message1.id) && message.equals(message1.message) && sender.equals(message1.sender) && receiver.equals(message1.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, sender, receiver);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
