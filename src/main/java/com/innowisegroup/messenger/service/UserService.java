package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.DuplicateUniqueValueException;
import com.innowisegroup.messenger.exception.NotFoundException;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse createNewUser(UserCreateRequest userCreateRequest)
            throws DuplicateUniqueValueException;

    UserResponse getUserById(Long userId) throws NotFoundException;

    UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest)
            throws NotFoundException, DuplicateUniqueValueException;

    void deleteUser(Long userId) throws NotFoundException;
}
