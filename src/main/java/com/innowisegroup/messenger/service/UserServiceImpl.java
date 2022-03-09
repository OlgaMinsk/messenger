package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.DuplicateUniqueValueException;
import com.innowisegroup.messenger.exception.NotCreate;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.mapper.UserMapper;
import com.innowisegroup.messenger.model.Role;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.RoleRepository;
import com.innowisegroup.messenger.repository.UserRepository;
import com.innowisegroup.messenger.security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final MyPasswordEncoder passwordEncoder;
    private final String roleUser = "ROLE_USER";


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RoleRepository roleRepository, MyPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
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
        if (!roleRepository.existsByRoleName(roleUser)) {
            throw new NotCreate("Can not find role user! Chek role table");
        }
        Role role = roleRepository.findByRoleName(roleUser);

        return Optional.of(userCreateRequest)
                .map(userMapper::toUser)
                .map(it -> setRole(it, role))
                .map(it -> setPassword(it))
                .map(userRepository::save)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new NotCreate("Can not create user"));
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
        userUpdateRequest.setPassword(passwordEncoder.setPassword(userUpdateRequest.getPassword()));
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

    @Override
    public User getByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new NotFoundException("User with name " + userName + " does not exist");
        }
        return user;
    }

    private void checkUserExistence(Long userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id " + userId + " does not exist");
        }
    }

    private User setRole(User user, Role role) {
        user.setRole(role);
        return user;
    }

    private User setPassword(User user) {
        user.setPassword(passwordEncoder.setPassword(user.getPassword()));
        return user;
    }
}
