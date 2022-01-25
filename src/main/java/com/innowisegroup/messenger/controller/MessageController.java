package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.service.MessageService;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{idUser}/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageResponse> getAllReceivedMessagesOfUser(@PathVariable(value = "idUser") Long userId)
            throws NotFoundException {
        List<MessageResponse> received = messageService.getAllReceivedMessagesOfUser(userId);
        received.addAll(messageService.getAllSentMessagesOfUser(userId));
        return received;
    }

    @PostMapping
    public MessageResponse createMessage(@PathVariable(value = "idUser") Long senderId,
                                         @RequestBody MessageCreateRequest messageCreateRequest)
            throws NotFoundException {
        return messageService.createMessage(senderId, messageCreateRequest);

    }

    @GetMapping(value = "/{messageId}")
    public MessageResponse getMessageById(@PathVariable Long messageId, @PathVariable(value = "idUser") Long userId) throws NotFoundException {
        return messageService.getMessageById(messageId, userId);
    }

    @PutMapping(value = "/{messageId}")
    public MessageResponse updateMessageById(@PathVariable Long messageId,
                                             @PathVariable(value = "idUser") Long senderId,
                                             @RequestBody MessageUpdateRequest messageUpdateRequest)
            throws NotFoundException {
        return messageService.updateMessageById(messageId, senderId, messageUpdateRequest);
    }


    @DeleteMapping(value = "/{messageId}")
    public void deleteMessageById(@PathVariable Long messageId,
                                             @PathVariable(value = "idUser") Long senderId)
            throws NotFoundException {
        messageService.deleteMessageById(messageId, senderId);
    }

}
