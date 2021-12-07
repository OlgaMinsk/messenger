package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAll();

    void deleteMessage(Long id) throws NotFoundException;

    void updateMessage(Long id, Message newMessage) throws NotFoundException;

    void saveMessage(Message message);

    void saveAllMessages(List<Message> messageList);

}
