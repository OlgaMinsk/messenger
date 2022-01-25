package com.innowisegroup.messenger.mapper;

import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.dto.response.UserResponse;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MessageMapper {
    MessageResponse toUserResponse(Message message);
}
