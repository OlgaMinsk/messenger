package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

public class MessageRepositoryJdbcImpl implements MessageRepository {


    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public Message addMessage(Message message) {
        return null;
    }

    @Override
    public void addAllMessages(List<Message> messages) {

    }

    @Override
    public Message getById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public boolean deleteMessage(Long id) throws NotFoundException {
        return false;
    }

    @Override
    public void updateMessage(Long id, Message message) throws NotFoundException {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
