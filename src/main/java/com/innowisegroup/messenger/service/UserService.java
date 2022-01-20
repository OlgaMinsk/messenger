package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse createNewUser(UserCreateRequest userCreateRequest);
}
