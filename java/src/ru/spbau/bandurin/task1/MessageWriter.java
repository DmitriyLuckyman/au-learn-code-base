package ru.spbau.bandurin.task1;

import java.io.IOException;

/**
 * @author Dmitriy Bandurin
 */
public interface MessageWriter {

    /**
     * write message for client-recipient
     * @param message message to write
     * @throws java.io.IOException IOException if any exception occurred while writing content
     */
    public void writeMessage(Message message) throws IOException;

    /**
     * Free used resources
     */
    public void close();
}
