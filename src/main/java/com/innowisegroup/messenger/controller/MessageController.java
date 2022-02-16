package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "messengerAPI/v01/users/{idUser}/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageResponse> getAllReceivedMessagesOfUser(@PathVariable("idUser") Long userId)
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

    @GetMapping("/{messageId}")
    public MessageResponse getMessageById(@PathVariable Long messageId, @PathVariable("idUser") Long userId)
            throws NotFoundException {
        return messageService.getMessageById(messageId, userId);
    }

    @PutMapping("/{messageId}")
    public MessageResponse updateMessageById(@PathVariable Long messageId,
                                             @PathVariable("idUser") Long senderId,
                                             @RequestBody MessageUpdateRequest messageUpdateRequest)
            throws NotFoundException {
        return messageService.updateMessageById(messageId, senderId, messageUpdateRequest);
    }


    @DeleteMapping("/{messageId}")
    public void deleteMessageById(@PathVariable Long messageId,
                                             @PathVariable("idUser") Long senderId)
            throws NotFoundException {
        messageService.deleteMessageById(messageId, senderId);
    }

}
