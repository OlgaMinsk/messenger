package com.innowisegroup.messenger.model;

import com.innowisegroup.messenger.exception.NotFoundException;

public enum CommandEnum {
    SELECT_LANGUAGE("Please enter 1 to select English.  2 ", "selectLanguage"),
    ENTER_NEW_COMMAND("Enter the command number", "enterNewCommand"),
    DISPLAY_ALL_MESSAGES("1 Display a list of messages", "displayAll_Messages"),
    CREATE_NEW_MESSAGE("2 Create a new message", "createNewMessage"),
    UPDATE_MESSAGE("3 Update a message", "updateMessage"),
    DELETE_MESSAGE("4 Delete a message", "deleteMessage"),
    ENTER_ID("Enter id", "enterId"),
    ENTER_NAME("Enter name", "enterName"),
    ENTER_TEXT("Enter text", "enterText"),
    ENTER_NEW_NAME("Enter new name", "enterNewName"),
    ENTER_NEW_TEXT("Enter new text", "enterNewText"),
    WRONG_COMMAND("The command is entered incorrectly. Enter command from the list (Only a number!)", "wrongCommand"),
    CAN_NOT_FIND_MESSAGE_BY_ID("Can't find message with id =", "canNotFindMessageWithId"),
    CHECK_ID("Please check the id in the file. It must be a number", "checkId"),
    DONE("Done", "done"),
    CAN_NOT_UPDATE("Can't update the message (can't find such a message)", "canNotUpdate"),
    CAN_NOT_DELETE("Can't delete the message (can't find such a message)", "canNotDelete"),
    WRONG_LANGUAGE("Please enter only number!", "wrongLanguage");

    private final String message;
    private final String messageForLocale;

    CommandEnum(String message, String messageForLocale) {
        this.message = message;
        this.messageForLocale = messageForLocale;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageForLocale() {
        return messageForLocale;
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
