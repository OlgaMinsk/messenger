package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageRepositoryHibernateImpl implements MessageRepository {
    private static final String CAN_NOT_FIND_MESSAGE_BY_ID ="Can't find the message with id ";
    private final SessionFactory sessionFactory;
    private final UserRepository userRepository;

    @Autowired
    public MessageRepositoryHibernateImpl(SessionFactory sessionFactory,
                                          UserRepository userRepository) {
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }


    @Override
    public boolean addMessageForUser(Long userId, String messageText) throws NotFoundException {
        User user = userRepository.getUserById(userId);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Message message = new Message(messageText);
        message.setUser(user);
        session.save(message);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateMessageFromUser(Long messageId, String messageText) throws NotFoundException {
        Session session = sessionFactory.openSession();
        Message message = session.get(Message.class, messageId);
        session.close();
        if (message == null) {
            throw new NotFoundException(CAN_NOT_FIND_MESSAGE_BY_ID + messageId);
        }
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        message.setMessage(messageText);
        session.update(message);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteMessageFromUser(Long messageId) throws NotFoundException {
        Session session = sessionFactory.openSession();
        Message message = session.get(Message.class, messageId);
        if (message == null) {
            session.close();
            throw new NotFoundException(CAN_NOT_FIND_MESSAGE_BY_ID + messageId);
        }
        Transaction transaction = session.beginTransaction();
        User user = message.getUser();
        user.getMessageList().remove(message);
        session.delete(message);
        transaction.commit();
        session.close();
        return true;
    }

}
