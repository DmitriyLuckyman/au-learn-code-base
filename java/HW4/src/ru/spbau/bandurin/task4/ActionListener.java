package ru.spbau.bandurin.task4;

/**
 * Main interface for event listeners
 * @author Dmitriy Bandurin
 *         Date: 10.03.13
 */
public interface ActionListener {

    /**
     * Perform some work when event occurs
     * @param event fired event object
     */
    public void performAction(Event event);
}
