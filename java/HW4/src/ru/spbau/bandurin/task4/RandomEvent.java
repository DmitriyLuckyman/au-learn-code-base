package ru.spbau.bandurin.task4;

import java.util.Random;

/**
 * RandomEvent use random generator for determine ready status of event.
 * {@link RandomEvent#ready}
 * @author Dmitriy Bandurin
 *         Date: 10.03.13
 */
public class RandomEvent extends Event {
    private Random random;

    /**
     * Create new Instance of RandomEvent
     */
    public RandomEvent() {
        super();
        random = new Random();
    }

    /**
     *
     * @return true if next random long even otherwise false
     */
    @Override
    public boolean ready() {
        return random.nextLong() % 2 == 0;
    }
}
