package com.innowisegroup.messenger.view;

import com.innowisegroup.messenger.model.Message;

import java.util.List;

public interface Bot {
    void print(String stringToPrint);

    void print(List<Message> messageList);

    void print(Message message);

    String read();
}
