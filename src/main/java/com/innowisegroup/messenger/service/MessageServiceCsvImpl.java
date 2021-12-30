package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.MessageRepository;
import com.innowisegroup.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceCsvImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private static int i;

    @Autowired
    public MessageServiceCsvImpl(MessageRepository messageRepository,
                                 UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    @Override
    public void deleteMessage(Long id) throws NotFoundException {
        messageRepository.deleteMessage(id);
    }

    @Override
    public void updateMessage(Long id, Message newMessage) throws NotFoundException {
        messageRepository.updateMessage(id, newMessage);
    }

    @Override
    public void saveMessage(Message message) {
        userRepository.addUser(new User("Test"+i));
        i++;
        //messageRepository.addMessage(message);
    }

    @Override
    public void saveAllMessages(List<Message> messageList) {
        messageRepository.addAllMessages(messageList);
    }

    @Override
    public Message getById(Long id) throws NotFoundException {
        return messageRepository.getById(id);
    }
}
