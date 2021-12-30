package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.UserRepository;
import com.innowisegroup.messenger.view.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MainServiceCSVImpl implements MainService {

    private final MessageService messageService;
    private final Bot bot;
    private final UserRepository userRepository;

    @Autowired
    public MainServiceCSVImpl(MessageService messageService,
                              Bot bot,
                              UserRepository userRepository) {
        this.messageService = messageService;
        this.bot = bot;
        this.userRepository = userRepository;
    }

    @Override
    public void displayAllMessages() {
        bot.print(messageService.getAll());
    }

    @Override
    public void createNewMessage() {
        bot.print(CommandEnum.ENTER_NAME);
        String name = bot.read();
        bot.print(CommandEnum.ENTER_TEXT);
        String text = bot.read();
        Message message = new Message(name, text);
        messageService.saveMessage(message);
        bot.print(CommandEnum.DONE);
    }

    @Override
    public void updateMessage() {
        try {
            bot.print(CommandEnum.ENTER_ID);
            Long id = Long.parseLong(bot.read());
            bot.print(CommandEnum.ENTER_NEW_NAME);
            String name = bot.read();
            bot.print(CommandEnum.ENTER_NEW_TEXT);
            String text = bot.read();
            Message message = new Message(name, text);
            messageService.updateMessage(id, message);
            bot.print(CommandEnum.DONE);
        } catch (NotFoundException e) {
            bot.print(CommandEnum.CAN_NOT_UPDATE);
        }
    }

    @Override
    public void deleteMessage() {
        try {
            bot.print(CommandEnum.ENTER_ID);
            Long id = Long.parseLong(bot.read());
            messageService.deleteMessage(id);
            bot.print(CommandEnum.DONE);
        } catch (NotFoundException e) {
            bot.print(CommandEnum.CAN_NOT_DELETE);
        }
    }

    @Override
    public void defaultMethod() {
        bot.print(CommandEnum.WRONG_COMMAND);
    }

    @Override
    public void changeLanguage() {
        bot.print(CommandEnum.SELECT_LANGUAGE);
        String language = bot.read();
        int intLanguage = Integer.parseInt(language);
        //по умолчанию английский
        Locale locale = new Locale("en");
        switch (intLanguage) {
            case 1 -> locale = new Locale("en");
            case 2 -> locale = new Locale("ru");
            default -> {
                bot.print(CommandEnum.WRONG_LANGUAGE);
                changeLanguage();
            }
        }
        bot.setLocale(locale);
    }

    @Override
    public void createUser() {
        bot.print(CommandEnum.ENTER_NAME);
        String  name = bot.read();
        User user = new User(name);
        userRepository.addUser(user);

    }

    @Override
    public void getById() {
        bot.print(CommandEnum.ENTER_ID);
        String idString = bot.read();
        Long id = Long.parseLong(idString);
        try {
            Message message = messageService.getById(id);
            bot.print(message);
        } catch (NotFoundException e) {
            bot.print(CommandEnum.CAN_NOT_FIND_MESSAGE_BY_ID);
        }
    }
}
