package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;

import java.util.List;

public interface MessageRepository {
    List<Message> getAll();

    Message addMessage(Message message);

    void addAllMessages(List<Message> messages);

    Message getById(Long id) throws NotFoundException;

    boolean deleteMessage(Long id) throws NotFoundException;

    void updateMessage(Long id, Message message) throws NotFoundException;

    boolean existById(Long id);

}
