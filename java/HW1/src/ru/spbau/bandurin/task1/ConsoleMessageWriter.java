package ru.spbau.bandurin.task1;

import java.io.*;

/**
 * Write messages to console
 * @author Dmitriy Bandurin
 *
 */
public class ConsoleMessageWriter extends AbstractMessageWriter {
    private int messageCounter;

    /**
     * Create ConsoleMessageWriter and set output to System.out
     */
    public ConsoleMessageWriter() {
        initWriter(new PrintWriter(System.out));
    }

    /**
     * Write message with message number and line number in message per message line
     * @param message message to write
     * @throws IOException if any exception occurred while writing content
     */
    public void writeMessage(final Message message) throws IOException {
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
