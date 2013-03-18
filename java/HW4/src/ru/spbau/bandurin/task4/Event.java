package ru.spbau.bandurin.task4;


import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for Event. Implements control for action Listeners.
 * @author Dmitriy Bandurin
 *         Date: 10.03.13
 */
public abstract class Event {
    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    /**
     * Show event status.
     * @return true if event should be fired, false otherwise
     */
    public abstract boolean ready();

    /**
     * Perform Event
     */
    public void fireEvent(){
        for (ActionListener listener : listeners) {
            listener.performAction(this);
        }
    }

    /**
     * Add specified action listener. If actionListener is null then it's ignored.
     * @param actionListener action listener for event
     */
    public void addListener(ActionListener actionListener){
        if(actionListener != null){
            listeners.add(actionListener);
        }
    }
}
