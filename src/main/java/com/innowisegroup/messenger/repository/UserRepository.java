package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User addUser(User user);

    User getById(Long id) throws NotFoundException;

    boolean deleteUser(Long id) throws NotFoundException;

    void updateUser(Long id, User user) throws NotFoundException;

    boolean existById(Long id);

}
