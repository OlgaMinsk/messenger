package com.innowisegroup.messenger.model;

public enum CommandEnum {
    SELECT_LANGUAGE("selectLanguage"),
    CHANGE_LANGUAGE("changeLanguage"),
    ENTER_NEW_COMMAND("enterNewCommand"),
    DISPLAY_ALL_MESSAGES("displayAll_Messages"),
    CREATE_NEW_MESSAGE("createNewMessage"),
    UPDATE_MESSAGE("updateMessage"),
    DELETE_MESSAGE("deleteMessage"),
    CLOSE("close"),
    ENTER_ID("enterId"),
    ENTER_NAME("enterName"),
    ENTER_TEXT("enterText"),
    ENTER_NEW_NAME("enterNewName"),
    ENTER_NEW_TEXT("enterNewText"),
    WRONG_COMMAND("wrongCommand"),
    CAN_NOT_FIND_MESSAGE_BY_ID("canNotFindMessageWithId"),
    CHECK_ID("checkId"),
    DONE("done"),
    CAN_NOT_UPDATE("canNotUpdate"),
    CAN_NOT_DELETE("canNotDelete"),
    WRONG_LANGUAGE("wrongLanguage"),
    GET_BY_ID("getById");

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
