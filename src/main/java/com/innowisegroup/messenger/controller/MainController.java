package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controller")
public class MainController {

    private final MainService service;
    private boolean isWorking = true;

    @Autowired
    public MainController(MainService service) {
        this.service = service;
    }

    public void controller(CommandEnum commandEnum) {
        switch (commandEnum) {
            case CREATE_NEW_MESSAGE:
                service.createNewMessage();
                break;
            case UPDATE_MESSAGE:
                service.updateMessage();
                break;
            case DELETE_MESSAGE:
                service.deleteMessage();
                break;
            case DISPLAY_ALL_USERS:
                service.getAllUsers();
                break;
            case GET_USER_BY_ID:
                service.getUserById();
                break;
            case CREATE_USER:
                service.createUser();
                break;
            case UPDATE_USER:
                service.updateUser();
                break;
            case DELETE_USER:
                service.deleteUser();
                break;
            case SELECT_LANGUAGE:
                service.changeLanguage();
                break;
            case CLOSE:
                isWorking = false;
                break;
            default:
                service.defaultMethod();
        }
    }

    public void controllerNum(Integer commandNum) {
        switch (commandNum) {
            case 3:
                service.createNewMessage();
                break;
            case 4:
                service.updateMessage();
                break;
            case 5:
                service.deleteMessage();
                break;
            case 6:
                service.getAllUsers();
                break;
            case 7:
                service.getUserById();
                break;
            case 8:
                service.createUser();
                break;
            case 9:
                service.updateUser();
                break;
            case 10:
                service.deleteUser();
                break;
            case 11:
                service.changeLanguage();
                break;
            case 12:
                isWorking = false;
                break;
            default:
                service.defaultMethod();
        }
    }

    public boolean isWorking() {
        return isWorking;
    }
}
