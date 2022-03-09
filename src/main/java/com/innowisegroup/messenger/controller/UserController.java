package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.DuplicateUniqueValueException;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.security.jwt.JwtUser;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("messengerAPI/v01")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        if (userCreateRequest.getUserName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The name field cannot be empty. Please fill it out");
        }
        try {
            return userService.createNewUser(userCreateRequest);
        } catch (DuplicateUniqueValueException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId.equals(authentication.principal.id)")
    @GetMapping("/users/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) {
        JwtUser u = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            return userService.getUserById(userId);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId.equals(authentication.principal.id)")
    @PutMapping("/users/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest.getUserName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field cannot be empty. Please fill it out");
        }
        try {
            return userService.updateUser(userId, userUpdateRequest);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (DuplicateUniqueValueException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId.equals(authentication.principal.id)")
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }


}
