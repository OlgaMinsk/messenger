package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.mapper.MessageMapper;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.MessageRepository;
import com.innowisegroup.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;


    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository,
                              MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public List<MessageResponse> getAllReceivedMessagesOfUser(Long receiverId)
            throws NotFoundException {
        checkingUserExistence(receiverId);
        User receiver = userRepository.getById(receiverId);
        return messageRepository.getMessageByReceiver(receiver)
                .stream()
                .map(messageMapper::toMessageResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<MessageResponse> getAllSentMessagesOfUser(Long senderId)
            throws NotFoundException {
        checkingUserExistence(senderId);
        User sender = userRepository.getById(senderId);
        return messageRepository.getMessageBySender(sender)
                .stream()
                .map(messageMapper::toMessageResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse createMessage(Long senderId, MessageCreateRequest messageCreateRequest) throws NotFoundException {
        checkingUserExistence(senderId);
        Long receiverId = messageCreateRequest.getReceiverId();
        checkingUserExistence(receiverId);
        User sender = userRepository.getById(senderId);
        User receiver = userRepository.getById(receiverId);
        Message message = messageMapper.toMessage(messageCreateRequest);
        message.setReceiver(receiver);
        message.setSender(sender);
        messageRepository.save(message);
        return messageMapper.toMessageResponse(message);
    }

    @Override
    public MessageResponse getMessageById(Long messageId, Long userId) throws NotFoundException {
        checkingUserExistence(userId);
        User user = userRepository.getById(userId);
        Message message = messageRepository.getById(messageId);
        if (user != message.getSender() && user != message.getReceiver()) {
            throw new NotFoundException("You don't have a message with id = " + messageId);
        }
        return messageMapper.toMessageResponse(message);
    }

    @Override
    public MessageResponse updateMessageById(Long messageId, Long senderId, MessageUpdateRequest messageUpdateRequest) throws NotFoundException {
        checkingToBeAbleToChangeOrDeleteMessages(messageId, senderId);
        Message message = messageRepository.getById(messageId);
        messageMapper.updateMessage(messageUpdateRequest, message);
        return messageMapper.toMessageResponse(messageRepository.save(message));
    }

    @Override
    public void deleteMessageById(Long messageId, Long senderId) throws NotFoundException {
        checkingToBeAbleToChangeOrDeleteMessages(messageId, senderId);
        messageRepository.deleteById(messageId);
    }

    private void checkingToBeAbleToChangeOrDeleteMessages(Long messageId, Long senderId) throws NotFoundException {
        checkingUserExistence(senderId);
        User sender = userRepository.getById(senderId);
        if (!messageRepository.existsById(messageId)) {
            throw new NotFoundException("No message with id" + messageId);
        }
        Message message = messageRepository.getById(messageId);
        if (sender != message.getSender()) {
            throw new NotFoundException("You don't have a message with id = " + messageId);
        }
    }

    private void checkingUserExistence(Long userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id " + userId + " does not exist");
        }
    }
}
