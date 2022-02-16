package com.innowisegroup.messenger;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.exception.DuplicateUniqueValueException;
import com.innowisegroup.messenger.mapper.UserMapper;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.UserRepository;
import com.innowisegroup.messenger.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    void createNewUserPositive() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUserName("J");
        User user = new User();
        UserResponse userResponseFromMapper = new UserResponse();

        Mockito.when(userRepository.existsByUserName(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userMapper.toUser(userCreateRequest)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.toUserResponse(user)).thenReturn(userResponseFromMapper);

        UserResponse userResponse = userService.createNewUser(userCreateRequest);

        Mockito.verify(userMapper)
                .toUser(ArgumentMatchers.any(UserCreateRequest.class));
        Mockito.verify(userRepository)
                .save(ArgumentMatchers.any(User.class));
        Mockito.verify(userMapper)
                .toUserResponse(ArgumentMatchers.any(User.class));

        Assert.notNull(userResponse, "returned message can't be null");
    }

    @Test
    void createNewUserNegative() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUserName("J");

        Mockito.when(userRepository.existsByUserName(ArgumentMatchers.anyString())).thenReturn(true);

        Assertions.assertThrows(DuplicateUniqueValueException.class, () ->
                userService.createNewUser(userCreateRequest));

        Mockito.verify(userMapper, Mockito.times(0))
                .toUser(ArgumentMatchers.any(UserCreateRequest.class));
        Mockito.verify(userRepository, Mockito.times(0))
                .save(ArgumentMatchers.any(User.class));
        Mockito.verify(userMapper, Mockito.times(0))
                .toUserResponse(ArgumentMatchers.any(User.class));
    }
}