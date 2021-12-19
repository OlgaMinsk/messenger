package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.CommandEnum;
import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.view.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MessageRepositoryCsvImpl implements MessageRepository {
    private final static String FILE_NAME = "./src/main/resources/messages.csv";

    Bot bot;

    @Autowired
    public MessageRepositoryCsvImpl(Bot bot) {
        this.bot = bot;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messageList = toList(getAllMessages());
        return toList(getAllMessages());
    }

    @Override
    public Message addMessage(Message message) {
        message.setId(getNextId());
        List<Message> messageList = getAll();
        messageList.add(message);
        saveAllMessages(messageList);
        return message;
    }

    @Override
    public void addAllMessages(List<Message> messages) {
        List<Message> messageList = getAll();
        Long idNext = getNextId();
        for (Message message : messages) {
            message.setId(idNext++);
            messageList.add(message);
        }
        saveAllMessages(messageList);
    }

    @Override
    public Message getById(Long id) throws NotFoundException {
        if (existById(id)) {
            return getAllMessages().get(id);
        }
        throw new NotFoundException(bot.returnDescriptionOfException(CommandEnum.CAN_NOT_FIND_MESSAGE_BY_ID) + id);
    }

    @Override
    public boolean deleteMessage(Long id) throws NotFoundException {
        if (existById(id)) {
            Map<Long, Message> messageMap = getAllMessages();
            messageMap.remove(id);
            saveAllMessages(toList(messageMap));
            return true;
        } else {
            throw new NotFoundException(bot.returnDescriptionOfException(CommandEnum.CAN_NOT_FIND_MESSAGE_BY_ID) + id);
            //or return false; you should check the flag - if return is true - ok (message has been deleted).
            //if the flag is false - the message has not been deleted.
        }
    }

    @Override
    public void updateMessage(Long id, Message message) throws NotFoundException {
        if (existById(id)) {
            Map<Long, Message> messageMap = getAllMessages();
            message.setId(id);
            messageMap.put(id, message);
            saveAllMessages(toList(messageMap));
        } else {
            throw new NotFoundException(bot.returnDescriptionOfException(CommandEnum.CAN_NOT_FIND_MESSAGE_BY_ID) + id);
        }
    }


    @Override
    public boolean existById(Long id) {
        Map<Long, Message> messageMap = getAllMessages();
        return messageMap.containsKey(id);
    }

    private void saveAllMessages(List<Message> messages) {
        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter(FILE_NAME), CsvPreference.STANDARD_PREFERENCE);) {
            final String[] header = new String[]{"Id", "Name", "Text"};
            final CellProcessor[] processors = getProcessors();
            // write the header
            beanWriter.writeHeader(header);
            // write the beans data
            for (Message message : messages
            ) {
                beanWriter.write(message, header, processors);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Long, Message> getAllMessages() {
        Map<Long, Message> messageMap = new HashMap<>();

        try (ICsvListReader listReader = new CsvListReader(new FileReader(FILE_NAME), CsvPreference.STANDARD_PREFERENCE);) {
            //First Column is header names- though we don't need it in runtime
            @SuppressWarnings("unused") final String[] headers = listReader.getHeader(true);
            while (listReader.read() != null) {
                Message message = parseRow(listReader);
                messageMap.put(message.getId(), message);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (NumberFormatException exception) {

            throw new RuntimeException(bot.returnDescriptionOfException(CommandEnum.CHECK_ID), exception);
        }
        return messageMap;
    }

    private Message parseRow(ICsvListReader listReader) {
        CellProcessor[] processors = getProcessors();
        List<Object> fieldInRow = listReader.executeProcessors(processors);
        String idS = (String) fieldInRow.get(0);
        Long id = Long.parseLong(idS);
        String name = (String) fieldInRow.get(1);
        String text = (String) fieldInRow.get(2);
        return new Message(id, name, text);
    }

    private CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new Unique(), // id
                new NotNull(), // name
                new NotNull() // text
        };
    }

    private List<Message> toList(Map<Long, Message> messageMap) {
        List<Message> messageList = new LinkedList<>();
        Set<Long> keySet = messageMap.keySet();
        for (Long key : keySet) {
            messageList.add(messageMap.get(key));
        }
        return messageList;
    }

    private Long getNextId() {
        Set<Long> idSet = getAllMessages().keySet();
        Long idNext = 0L;
        for (Long id : idSet) {
            if (id > idNext) {
                idNext = id;
            }
        }
        idNext++;
        return idNext;
    }
}
