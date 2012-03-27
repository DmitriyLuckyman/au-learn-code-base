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
    private MessageWriter messageWriter;

    public CompressingMessageWriter(MessageWriter writer) {
        messageWriter = writer;
    }

    public void writeMessage(Message message) throws IOException {
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

    public void close() {
        if(bufferedMessage != null){
            try {
                messageWriter.writeMessage(bufferedMessage);
            } catch (IOException e) {
                System.err.println("Can't write message : " + e.getMessage());
                e.printStackTrace();
            }
        }
        messageWriter.close();
    }
}
