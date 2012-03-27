package ru.spbau.bandurin.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitriy Bandurin
 */
public class Message {
    private List<String> lines = new ArrayList<String>();

    /**
     * Append another message to current message
     * @param message message to append
     */
    public void append(Message message){
        if(message != null){
            lines.addAll(message.getLines());
        }
    }

    /**
     * Add line to message
     * @param line line to add
     */
    public void append(String line){
        lines.add(line);
    }

    /**
     *
     * @return list of message lines
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
