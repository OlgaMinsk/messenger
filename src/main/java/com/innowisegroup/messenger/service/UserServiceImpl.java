package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
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
    public UserResponse createNewUser(UserCreateRequest userCreateRequest) {
        return Optional.of(userCreateRequest)
                .map(userMapper::toUser)
                .map(userRepository::save)
                .map(userMapper::toUserResponse)
                .get();
    }

    @Override
    public UserResponse getUserById(Long userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("");
        }
        return userMapper.toUserResponse(userRepository.getById(userId));
    }

//    public UserResponse getUserById(Long userId) {
//        return jopa.findById(userId)
//                .map(userMapper::toUserResponse)
//                .orElseThrow(() ->
//                        {
//                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "getUserById(), User can not found");
//                        }
//                );
//    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userMapper.updateUser(userUpdateRequest, userRepository.getById(userId));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
