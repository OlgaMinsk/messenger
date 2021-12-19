package com.innowisegroup.messenger.logger;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class Logger {
    private final static String FILE_NAME = "./src/main/resources/log.txt";
    private static FileWriter writer;

    public void log(String st) {
        try {
            writer = new FileWriter(FILE_NAME, true);
            writer.append(st);
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    private void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
