package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.view.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceCSVImpl implements MainService {
    private final MessageService messageService;
    private final Bot bot;

    @Autowired
    public MainServiceCSVImpl(MessageService messageService, Bot bot) {
        this.messageService = messageService;
        this.bot = bot;
    }

    @Override
    public void displayAllMessages() {
        bot.print(messageService.getAll());
    }

    @Override
    public void createNewMessage() {
        bot.print(CommandEnum.ENTER_NAME.getMessage());
        String name = bot.read();
        bot.print(CommandEnum.ENTER_TEXT.getMessage());
        String text = bot.read();
        Message message = new Message(name, text);
        messageService.saveMessage(message);
        bot.print("Done");
    }

    @Override
    public void updateMessage() {
        try {
            bot.print(CommandEnum.ENTER_ID.getMessage());
            Long id = Long.parseLong(bot.read());
            bot.print(CommandEnum.ENTER_NEW_NAME.getMessage());
            String name = bot.read();
            bot.print(CommandEnum.ENTER_NEW_TEXT.getMessage());
            String text = bot.read();
            Message message = new Message(name, text);
            messageService.updateMessage(id, message);
            bot.print("Done");
        } catch (NotFoundException e) {
            bot.print("Can't delete the message (can't find such a message)");
        }
    }

    @Override
    public void deleteMessage() {
        try {
            bot.print(CommandEnum.ENTER_ID.getMessage());
            Long id = Long.parseLong(bot.read());
            messageService.deleteMessage(id);
            bot.print("Done");
        } catch (NotFoundException e) {
            bot.print("Can't delete the message (can't find such a message)");
        }
    }

    @Override
    public void defaultMethod() {
       bot.print("The command is entered incorrectly. Enter command from the list");
    }
}
