package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.User;
import com.innowisegroup.messenger.repository.MessageRepository;
import com.innowisegroup.messenger.repository.UserRepository;
import com.innowisegroup.messenger.view.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MainServiceImpl implements MainService {

    private final Bot bot;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MainServiceImpl(Bot bot,
                           UserRepository userRepository,
                           MessageRepository messageRepository) {
        this.bot = bot;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void createNewMessage() {
        bot.print(CommandEnum.ENTER_USER_ID);
        String idString = bot.read();
        Long userId = Long.parseLong(idString);
        bot.print(CommandEnum.ENTER_TEXT);
        String text = bot.read();
        try {
            messageRepository.addMessageForUser(userId, text);
        } catch (NotFoundException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CAN_NOT_FIND_USER_BY_ID);
        }
    }

    @Override
    public void updateMessage() {
        try {
            bot.print(CommandEnum.ENTER_MESSAGE_ID);
            Long messageId = Long.parseLong(bot.read());
            bot.print(CommandEnum.ENTER_NEW_TEXT);
            String text = bot.read();
            messageRepository.updateMessageFromUser(messageId, text);
            bot.print(CommandEnum.DONE);
        } catch (NotFoundException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CAN_NOT_UPDATE);
        }
    }

    @Override
    public void deleteMessage() {
        try {
            bot.print(CommandEnum.ENTER_MESSAGE_ID);
            Long messageId = Long.parseLong(bot.read());
            messageRepository.deleteMessageFromUser(messageId);
            bot.print(CommandEnum.DONE);
        } catch (NotFoundException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CAN_NOT_DELETE);
        }
    }

    @Override
    public void getAllUsers() {
        bot.print(userRepository.getAllUsers());
    }

    @Override
    public void getUserById() {
        bot.print(CommandEnum.ENTER_ID);
        try {
            Long id = Long.parseLong(bot.read());
            bot.print(userRepository.getUserById(id));
        } catch (NotFoundException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CAN_NOT_FIND_USER_BY_ID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CHECK_ID);
        } catch (Exception e) {
            e.printStackTrace();
            bot.print(CommandEnum.WRONG_MESSAGE);
        }
    }

    @Override
    public void createUser() {
        try {
            bot.print(CommandEnum.ENTER_NAME);
            String name = bot.read();
            User user = new User(name);
            userRepository.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            bot.print(CommandEnum.WRONG_MESSAGE);
        }
    }

    @Override
    public void updateUser() {
        try {
            bot.print(CommandEnum.ENTER_ID);
            Long id = Long.parseLong(bot.read());
            bot.print(CommandEnum.ENTER_NEW_NAME);
            String name = bot.read();
            User user = new User(name);
            userRepository.updateUser(id, user);
        } catch (NotFoundException e) {
            e.printStackTrace();
            bot.print(CommandEnum.CAN_NOT_UPDATE);
        } catch (Exception e) {
            e.printStackTrace();
            bot.print(CommandEnum.WRONG_MESSAGE);
        }
    }

    @Override
    public void deleteUser() {
        try {
            bot.print(CommandEnum.ENTER_ID);
            Long id = Long.parseLong(bot.read());
            userRepository.deleteUser(id);
        } catch (NotFoundException exception) {
            bot.print(CommandEnum.CAN_NOT_DELETE);
        } catch (Exception e) {
            bot.print(CommandEnum.WRONG_MESSAGE);
        }
    }


    @Override
    public void changeLanguage() {
        bot.print(CommandEnum.SELECT_LANGUAGE);
        String language = bot.read();
        int intLanguage = Integer.parseInt(language);
        //по умолчанию английский
        Locale locale = new Locale("en");
        switch (intLanguage) {
            case 1:
                locale = new Locale("en");
                break;
            case 2:
                locale = new Locale("ru");
                break;
            default:
                bot.print(CommandEnum.WRONG_LANGUAGE);
                changeLanguage();
        }
        bot.setLocale(locale);
    }

    @Override
    public void defaultMethod() {
        bot.print(CommandEnum.WRONG_COMMAND);
    }

    @Override
    public void showAllNames() {
        bot.print(userRepository.getAllNames());
    }
}
