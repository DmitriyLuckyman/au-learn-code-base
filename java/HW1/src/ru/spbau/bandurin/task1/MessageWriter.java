package ru.spbau.bandurin.task1;

import java.io.IOException;

/**
 * Common interface for Message Writers
 * @author Dmitriy Bandurin
 */
public interface MessageWriter {

    /**
     * Write message for client-recipient
     * @param message message to write
     * @throws java.io.IOException IOException if any exception occurred while writing content
     */
    public void writeMessage(final Message message) throws IOException;

    /**
     * Free used resources
     * @throws java.io.IOException IOException if any exception occurred while free used resources
     */
    public void close() throws IOException;
}
