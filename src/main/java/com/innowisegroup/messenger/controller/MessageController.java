package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.request.MessageCreateRequest;
import com.innowisegroup.messenger.dto.request.MessageUpdateRequest;
import com.innowisegroup.messenger.dto.response.MessageResponse;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId.equals(authentication.principal.id)" )
    public List<MessageResponse> getAllReceivedMessagesOfUser(@PathVariable("idUser") Long userId)
            throws NotFoundException {
        List<MessageResponse> received = messageService.getAllReceivedMessagesOfUser(userId);
        received.addAll(messageService.getAllSentMessagesOfUser(userId));
        return received;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or #senderId.equals(authentication.principal.id)" )
    public MessageResponse createMessage(@PathVariable(value = "idUser") Long senderId,
                                         @RequestBody MessageCreateRequest messageCreateRequest)
            throws NotFoundException {
        return messageService.createMessage(senderId, messageCreateRequest);

    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId.equals(authentication.principal.id)" )
    public MessageResponse getMessageById(@PathVariable Long messageId, @PathVariable("idUser") Long userId)
            throws NotFoundException {
        return messageService.getMessageById(messageId, userId);
    }

    @PutMapping("/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #senderId.equals(authentication.principal.id)" )
    public MessageResponse updateMessageById(@PathVariable Long messageId,
                                             @PathVariable("idUser") Long senderId,
                                             @RequestBody MessageUpdateRequest messageUpdateRequest)
            throws NotFoundException {
        return messageService.updateMessageById(messageId, senderId, messageUpdateRequest);
    }


    @DeleteMapping("/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #senderId.equals(authentication.principal.id)" )
    public void deleteMessageById(@PathVariable Long messageId,
                                             @PathVariable("idUser") Long senderId)
            throws NotFoundException {
        messageService.deleteMessageById(messageId, senderId);
    }

}
