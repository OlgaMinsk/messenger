package com.innowisegroup.messenger.view;

import com.innowisegroup.messenger.model.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Scanner;

@Component
public class BotConsoleImpl implements Bot {

    private static Scanner scan;

    @Override
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    @Override
    public void print(List<Message> messageList) {
        for (Message message : messageList) {
            System.out.println(message.toString());
        }
    }

    @Override
    public void print(Message message) {
        System.out.println(message.toString());
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
