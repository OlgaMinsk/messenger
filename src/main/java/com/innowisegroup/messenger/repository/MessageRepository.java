package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;

public interface MessageRepository {

    boolean addMessageForUser(Long userId, String messageText) throws NotFoundException;

    boolean updateMessageFromUser(Long messageId, String messageText) throws NotFoundException;

    boolean deleteMessageFromUser(Long messageId) throws NotFoundException;

}
