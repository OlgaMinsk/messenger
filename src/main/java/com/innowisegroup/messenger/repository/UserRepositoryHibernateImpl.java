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

import java.util.List;

@Component
public class UserRepositoryHibernateImpl implements UserRepository {

    SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List userList = session.createQuery("FROM User", User.class).list();
        session.close();
        return userList;
    }

    @Override
    public User getUserById(Long id) throws NotFoundException {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        if (user == null) {
            throw new NotFoundException(CommandEnum.CAN_NOT_FIND_USER_BY_ID.getMessageForLocale());
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User updateUser(Long id, User user) throws NotFoundException {
        if (!existUserById(id)) {
            throw new NotFoundException(CommandEnum.CAN_NOT_FIND_USER_BY_ID.getMessageForLocale());
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        user.setId(id);
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean deleteUser(Long id) throws NotFoundException {
        User user = getUserById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean existUserById(Long id) {
        try {
            getUserById(id);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }

}
