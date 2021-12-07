package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    private final MainService service;

    @Autowired
    public MainController(MainService service) {
        this.service = service;
    }

    public void controller(CommandEnum commandEnum) {
        switch (commandEnum) {
            case DISPLAY_ALL_MESSAGES:
                service.displayAllMessages();
                break;
            case CREATE_NEW_MESSAGE:
                service.createNewMessage();
                break;
            case UPDATE_MESSAGE:
                service.updateMessage();
                break;
            case DELETE_MESSAGE:
                service.deleteMessage();
                break;
            default:
                service.defaultMethod();
        }
    }
}
