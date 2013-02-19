package ru.spbau.bandurin.task1;

import java.io.IOException;

/**
 * Wrapper for another message writer.
 * Compress two message in one.
 * If the number of message is odd, then the last message write as is.
 *  @author Dmitriy Bandurin
 */
public class CompressingMessageWriter implements MessageWriter {
    private Message bufferedMessage;
    private final MessageWriter messageWriter;

    /**
     * Create new CompressingMessageWriter with specified destination MessageWriter
     * @param writer destination message writer
     */
    public CompressingMessageWriter(final MessageWriter writer) {
        messageWriter = writer;
    }

    /**
     * Write messages with compress two message into one
     * @param message message to write
     * @throws IOException  if any exception occurred while writing content
     */
    public void writeMessage(final Message message) throws IOException {
        if(message != null){
            if(bufferedMessage == null){
                bufferedMessage = message;
            } else {
                bufferedMessage.append(message);
                messageWriter.writeMessage(bufferedMessage);
                bufferedMessage = null;
            }
        }
    }

    /**
     * If message buffer is not empty write message to MessageWriter.
     * and then close used resources.
     * @throws IOException if any exception occurred while write content or close resource
     */
    public void close() throws IOException {
        try {
            if(bufferedMessage != null) {
                messageWriter.writeMessage(bufferedMessage);
                bufferedMessage = null;
            }
        } finally {
            //try to close write
            try{
                messageWriter.close();
            } catch (IOException e){
                if(bufferedMessage != null){
                    throw e;
                } else {
                    //ignore because we override exception from write message action
                    System.err.println("Strange exception : " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
