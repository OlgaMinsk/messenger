package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createNewUser(userCreateRequest);
    }

    @GetMapping(value = "/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) {
//        try {
//            return userService.getUserById(userId);
//        } catch (EntityNotFoundException exception) {
//            String reason = "User with id " + userId + " does not exist";
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, exception);
//        }

//        return userService.getUserById(userId);

        try {
            return userService.getUserById(userId);
        } catch (NotFoundException exception) {
            String reason = "User with id " + userId + " does not exist";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, exception);
        }

    }

    @PutMapping(value = "/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            return userService.updateUser(userId, userUpdateRequest);
        } catch (EntityNotFoundException exception) {
            String reason = "User with id " + userId + " does not exist";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, exception);
        }
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (EmptyResultDataAccessException exception) {
            String reason = "User with id " + userId + " does not exist";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, exception);
        }
    }
}
