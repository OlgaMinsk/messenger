package com.innowisegroup.messenger.mapper;

import com.innowisegroup.messenger.dto.request.UserCreateRequest;
import com.innowisegroup.messenger.dto.request.UserUpdateRequest;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatarId", ignore = true)
    User toUser(UserCreateRequest userCreateRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatarId", ignore = true)
    void updateUser(UserUpdateRequest userUpdateRequest, @MappingTarget User user);
}
