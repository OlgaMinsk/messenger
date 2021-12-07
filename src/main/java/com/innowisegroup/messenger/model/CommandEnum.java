package com.innowisegroup.messenger.model;

import com.innowisegroup.messenger.exception.NotFoundException;

public enum CommandEnum {
    ENTER_NEW_COMMAND("Enter new command"),
    DISPLAY_ALL_MESSAGES("Display a list of messages"),
    CREATE_NEW_MESSAGE("Create a new message"),
    UPDATE_MESSAGE("Update a message"),
    DELETE_MESSAGE("Delete a message"),
    ENTER_ID("Enter id"),
    ENTER_NAME("Enter name"),
    ENTER_TEXT("Enter text"),
    ENTER_NEW_ID("Enter new id"),
    ENTER_NEW_NAME("Enter new name"),
    ENTER_NEW_TEXT("Enter new text");

    private String message;

    CommandEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    public static CommandEnum getCommandByDescription(String message) throws NotFoundException {
        message = message.trim();
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (message.equalsIgnoreCase(commandEnum.message)) {
                return commandEnum;
            }
        }
        throw new NotFoundException("");
    }
}
