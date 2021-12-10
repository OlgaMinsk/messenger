package com.innowisegroup.messenger.view;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;

import java.util.List;
import java.util.Locale;

public interface Bot {
    void setLocale(Locale locale);

    void print(CommandEnum commandEnum);

    void print(List<Message> messageList);

    void print(Message message);

    String returnDescriptionOfException(CommandEnum commandEnum);

    String read();
}
