package ru.spbau.bandurin.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Message container
 * @author Dmitriy Bandurin
 */
public class Message {
    private final List<String> lines = new ArrayList<String>();

    /**
     * Append another message to current message
     * @param message message to append
     */
    public void append(final Message message){
        if(message != null){
            lines.addAll(message.getLines());
        }
    }

    /**
     * Add line to message
     * @param line line to add
     */
    public void append(final String line){
        lines.add(line);
    }

    /**
     *
     * @return unmodifiable list of message lines
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
