package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    User getUserById(Long id) throws NotFoundException;

    User addUser(User user);

    User updateUser(Long id, User user) throws NotFoundException;

    boolean deleteUser(Long id) throws NotFoundException;

    boolean existUserById(Long id);

}
