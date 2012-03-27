package ru.spbau.bandurin.task1;

import java.io.*;

/**
 * Write messages to console
 * @author Dmitriy Bandurin
 *
 */
public class ConsoleMessageWriter extends AbstractMessageWriter {
    private int messageCounter;

    public ConsoleMessageWriter() {
        initWriter(new PrintWriter(System.out));
    }

    public void writeMessage(Message message) throws IOException {
        if(message != null){
            messageCounter++;
            writeLine("Message " + messageCounter);
            int linesCounter = 0;
            for (String line : message.getLines()) {
                linesCounter++;
                writeLine(messageCounter + "." + linesCounter + ". " + line);
            }
        }
    }
}
