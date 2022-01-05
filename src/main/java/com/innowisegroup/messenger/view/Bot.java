package com.innowisegroup.messenger.view;

import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;

import java.util.List;
import java.util.Locale;

public interface Bot {
    void setLocale(Locale locale);

    void print(CommandEnum commandEnum);

    void print(List list);

    void print(Message message);

    void print(User user);

    String returnDescriptionOfException(CommandEnum commandEnum);

    String read();
}
