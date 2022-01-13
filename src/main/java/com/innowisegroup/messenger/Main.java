package com.innowisegroup.messenger;

import com.innowisegroup.messenger.config.AppConfiguration;
import com.innowisegroup.messenger.controller.MainController;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.view.Bot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    private static MainController controller;
    private static Bot bot;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        controller = context.getBean("controller", MainController.class);
        bot = context.getBean("bot", Bot.class);
        controller.controller(CommandEnum.SELECT_LANGUAGE);
        while (controller.isWorking()) {
            start();
        }

    }


    public static void start() {
        bot.print(CommandEnum.ENTER_NEW_COMMAND);
        bot.print(CommandEnum.SHOW_ALL_NAMES);
//        bot.print(CommandEnum.DISPLAY_ALL_MESSAGES);
//        bot.print(CommandEnum.GET_MESSAGE_BY_ID);
        bot.print(CommandEnum.CREATE_NEW_MESSAGE);
        bot.print(CommandEnum.UPDATE_MESSAGE);
        bot.print(CommandEnum.DELETE_MESSAGE);
        bot.print(CommandEnum.DISPLAY_ALL_USERS);
        bot.print(CommandEnum.GET_USER_BY_ID);
        bot.print(CommandEnum.CREATE_USER);
        bot.print(CommandEnum.UPDATE_USER);
        bot.print(CommandEnum.DELETE_USER);
        bot.print(CommandEnum.CHANGE_LANGUAGE);
        bot.print(CommandEnum.CLOSE);
        String commandFromUser = bot.read();
        try {
            controller.controllerNum(Integer.parseInt(commandFromUser));
        } catch (NumberFormatException e) {
            bot.print(CommandEnum.WRONG_COMMAND);
        }
    }
}
