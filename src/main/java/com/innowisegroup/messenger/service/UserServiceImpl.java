package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new LinkedList<UserResponse>();
    }

    @Override
    public UserResponse createNewUser(UserCreateRequest userCreateRequest) {
        return null;
    }
}
