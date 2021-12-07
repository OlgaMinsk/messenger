package com.innowisegroup.messenger;

import com.innowisegroup.messenger.config.AppConfiguration;
import com.innowisegroup.messenger.controller.MainController;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.view.Bot;
import com.innowisegroup.messenger.view.BotConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    private static MainController controller;



    public static void main(String[] args) {
         ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
   //     ApplicationContext context = new AnnotationConfigApplicationContext("com.innowisegroup.messenger");

 controller = context.getBean("mainController", MainController.class);

        while (true) {
            start();
        }
    }


    public static void start() {
        Bot bot = new BotConsoleImpl();
        bot.print("\n" + "\n" + CommandEnum.ENTER_NEW_COMMAND.getMessage()
                + ": " + "\n" + CommandEnum.DISPLAY_ALL_MESSAGES.getMessage()
                + ", " + "\n" + CommandEnum.CREATE_NEW_MESSAGE.getMessage()
                + ", " + "\n" + CommandEnum.UPDATE_MESSAGE.getMessage()
                + ", " + "\n" + CommandEnum.DELETE_MESSAGE.getMessage()
        );
        String commandFromUser = bot.read();
        try {
            controller.controller(CommandEnum.getCommandByDescription(commandFromUser));
        } catch (NotFoundException e) {
            bot.print("The command is entered incorrectly. Enter command from the list");
        }
    }
}
