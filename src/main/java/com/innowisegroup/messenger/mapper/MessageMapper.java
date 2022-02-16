package com.innowisegroup.messenger.mapper;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
        //, uses = UserMapper.class)
public interface MessageMapper {

    @Mapping(target = "senderId", source = "message.sender.id")
    @Mapping(target = "receiverId", source = "message.receiver.id")
    MessageResponse toMessageResponse(Message message);

    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    Message toMessage(MessageCreateRequest messageCreateRequest);

    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateMessage(MessageUpdateRequest messageUpdateRequest, @MappingTarget Message message);
}
