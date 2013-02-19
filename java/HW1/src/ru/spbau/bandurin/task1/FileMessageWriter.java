package ru.spbau.bandurin.task1;

import java.io.*;
import java.util.List;

/**
 * Writer for messages to file.
 * @author Dmitriy Bandurin
 */
public class FileMessageWriter extends AbstractMessageWriter {
    /**
     * Create new FileMessageWriter which write to given file
     * @param filePath path of file to write
     * @throws IOException if any occurred while working with given file.
     */
    public FileMessageWriter(final String filePath) throws IOException {
        initWriter(new FileWriter(new File(filePath)));
    }

    /**
     * Write message to File
     * @param message message to write
     * @throws IOException if any exception occurred while writing content
     */
    public void writeMessage(final Message message) throws IOException {
        if(message != null){
            final List<String> lines = message.getLines();
            writeLine(String.valueOf(lines.size()));
            for (String line : lines) {
                writeLine(line);
            }
        }
    }
}
