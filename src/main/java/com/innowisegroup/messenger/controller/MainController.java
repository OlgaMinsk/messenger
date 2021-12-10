package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controller")
public class MainController {
    private final MainService service;
    boolean isWorking = true;

    @Autowired
    public MainController(MainService service) {
        this.service = service;
    }

    public void controller(CommandEnum commandEnum) {
        switch (commandEnum) {
            case DISPLAY_ALL_MESSAGES -> service.displayAllMessages();
            case CREATE_NEW_MESSAGE -> service.createNewMessage();
            case UPDATE_MESSAGE -> service.updateMessage();
            case DELETE_MESSAGE -> service.deleteMessage();
            case SELECT_LANGUAGE -> service.changeLanguage();
            case CLOSE -> isWorking = false;
            default -> service.defaultMethod();
        }
    }

    public void controllerNum(Integer commandNum) {
        switch (commandNum) {
            case 1 -> service.displayAllMessages();
            case 2 -> service.createNewMessage();
            case 3 -> service.updateMessage();
            case 4 -> service.deleteMessage();
            case 5 -> service.changeLanguage();
            case 6 -> isWorking = false;
            default -> service.defaultMethod();
        }
    }

    public boolean isWorking() {
        return isWorking;
    }
}
