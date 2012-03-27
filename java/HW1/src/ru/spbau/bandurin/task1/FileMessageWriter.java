package ru.spbau.bandurin.task1;

import java.io.*;
import java.util.List;

/**
 * Writer for messages to file.
 * @author Dmitriy Bandurin
 */
public class FileMessageWriter extends AbstractMessageWriter {
    public FileMessageWriter(String filePath) {
        try {
            initWriter(new FileWriter(new File(filePath)));
        } catch (IOException e) {
            System.err.println(new StringBuilder().append("Error while open file ")
                    .append(filePath).append(" : ").append(e.getMessage()).toString());
            e.printStackTrace(); 
        }
    }

    /**
     * write message to File
     * @param message message to write
     * @throws IOException if any exception occurred while writing content
     */
    public void writeMessage(Message message) throws IOException {
        if(message != null){
            final List<String> lines = message.getLines();
            writeLine(String.valueOf(lines.size()));
            for (String line : lines) {
                writeLine(line);
            }
        }
    }
}
