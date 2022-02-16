package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.exception.NotFoundException;

import java.util.List;

public interface MessageService {
    List<MessageResponse> getAllReceivedMessagesOfUser(Long receiverId)
            throws NotFoundException;

    List<MessageResponse> getAllSentMessagesOfUser(Long senderId)
            throws NotFoundException;

    MessageResponse createMessage(Long senderId,
                                  MessageCreateRequest messageCreateRequest)
            throws NotFoundException;

    MessageResponse getMessageById(Long messageId, Long userId)
            throws NotFoundException;

    MessageResponse updateMessageById(Long messageId,
                                      Long senderId,
                                      MessageUpdateRequest messageUpdateRequest)
            throws NotFoundException;

    void deleteMessageById(Long messageId, Long senderId) throws NotFoundException;
}
