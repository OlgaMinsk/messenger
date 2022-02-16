package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.DuplicateUniqueValueException;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.mapper.UserMapper;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse createNewUser(UserCreateRequest userCreateRequest) throws DuplicateUniqueValueException {
        if (userRepository.existsByUserName(userCreateRequest.getUserName())) {
            throw new DuplicateUniqueValueException("User with name " + userCreateRequest.getUserName() + " already exists");
        }

        return Optional.of(userCreateRequest)
                .map(userMapper::toUser)
                .map(userRepository::save)
                .map(userMapper::toUserResponse)
                .get();
        //.orElseThrow(() -> );
    }

    @Override
    public UserResponse getUserById(Long userId) throws NotFoundException {
        checkUserExistence(userId);
        return userMapper.toUserResponse(userRepository.getById(userId));
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest)
            throws NotFoundException, DuplicateUniqueValueException {
        checkUserExistence(userId);
        if (userRepository.existsByUserName(userUpdateRequest.getUserName())) {
            throw new DuplicateUniqueValueException("Can't update name: user with name " + userUpdateRequest.getUserName() + " already exists.");
        }
        User user = userRepository.getById(userId);
        userMapper.updateUser(userUpdateRequest, user);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Long userId) throws NotFoundException {
        checkUserExistence(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void setAvatarId(Long userId, String avatarId) {
        checkUserExistence(userId);
        User user = userRepository.getById(userId);
        user.setAvatarId(avatarId);
        userRepository.save(user);
    }

    @Override
    public String getAvatarId(Long userId) {
        checkUserExistence(userId);
        User user = userRepository.getById(userId);
        String avatarId = user.getAvatarId();
        if (avatarId == null) {
            throw new NotFoundException("The user " + userId + " does not have an avatar");
        }
        return avatarId;
    }

    private void checkUserExistence(Long userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id " + userId + " does not exist");
        }
    }

}
