package com.innowisegroup.messenger.view;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@Component("bot")
public class BotConsoleLocaleImpl implements Bot {

    private static Scanner scan;
    private static Locale locale = new Locale("en");
    private static ResourceBundle bundle = ResourceBundle.getBundle("command");

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle("command", locale);
    }

    @Override
    public void print(CommandEnum commandEnum) {
        System.out.println(bundle.getString(commandEnum.getMessageForLocale()));
    }

    @Override
    public void print(List list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    @Override
    public void print(Message message) {
        System.out.println(message.toString());
    }

    @Override
    public void print(User user) {
        System.out.println(user.toString());
    }

    @Override
    public String returnDescriptionOfException(CommandEnum commandEnum) {
        return bundle.getString(commandEnum.getMessageForLocale());
    }

    @Override
    public String read() {
        if (scan == null) {
            scan = new Scanner(System.in);
        }
        return scan.nextLine();
    }

    @PreDestroy
    private void scannerClose() {
        if (scan != null) {
            scan.close();
        }
    }
}
