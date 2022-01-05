package com.innowisegroup.messenger.model;

public enum CommandEnum {
    SELECT_LANGUAGE("selectLanguage"),
    CHANGE_LANGUAGE("changeLanguage"),
    ENTER_NEW_COMMAND("enterNewCommand"),
    DISPLAY_ALL_MESSAGES("displayAllMessages"),
    CREATE_NEW_MESSAGE("createNewMessage"),
    UPDATE_MESSAGE("updateMessage"),
    DELETE_MESSAGE("deleteMessage"),
    GET_MESSAGE_BY_ID("getMessageById"),
    CREATE_USER("createUser"),
    DISPLAY_ALL_USERS("displayAllUsers"),
    UPDATE_USER("updateUser"),
    DELETE_USER("deleteUser"),
    GET_USER_BY_ID("getUserById"),
    CLOSE("close"),
    ENTER_ID("enterId"),
    ENTER_USER_ID("enterUserId"),
    ENTER_MESSAGE_ID("enterMessageId"),
    ENTER_NAME("enterName"),
    ENTER_TEXT("enterText"),
    ENTER_NEW_NAME("enterNewName"),
    ENTER_NEW_TEXT("enterNewText"),
    WRONG_COMMAND("wrongCommand"),
    WRONG_MESSAGE("wrongMessage"),
    CAN_NOT_FIND_MESSAGE_BY_ID("canNotFindMessageWithId"),
    CAN_NOT_FIND_USER_BY_ID("canNotFindUserWithId"),
    CHECK_ID("checkId"),
    DONE("done"),
    CAN_NOT_UPDATE("canNotUpdate"),
    CAN_NOT_DELETE("canNotDelete"),
    WRONG_LANGUAGE("wrongLanguage");


    private String messageForLocale;

    CommandEnum(String messageForLocale) {
        this.messageForLocale = messageForLocale;
    }

    public String getMessageForLocale() {
        return messageForLocale;
    }

    public void setMessageForLocale(String messageForLocale) {
        this.messageForLocale = messageForLocale;
    }
}
