package ru.spbau.bandurin.task4;

/**
 * TimeEvent perform event with fixed time between each event.
 * @author Dmitriy Bandurin
 *         Date: 10.03.13
 */
public class TimeEvent extends Event {
    private long nextStamp;
    private long timeout;

    /**
     * Default timeout: ten seconds
     */
    public static final long TEN_SECONDS = 10 * 1000;


    /**
     * Create new instance of TimeEvent with ten seconds timeout between events ready.
     */
    public TimeEvent() {
        timeout = TEN_SECONDS;
        calculateNextStamp();
    }

    /**
     * Recalculate internal state and call parent method
     */
    @Override
    public void fireEvent() {
        this.calculateNextStamp();
        super.fireEvent();
    }

    /**
     *
     * @return true if from previous fireEvent call was ten seconds
     */
    @Override
    public boolean ready() {
        return nextStamp <= System.currentTimeMillis();
    }

    /**
     * Calculate next timestamp when event should be ready.
     */
    private void calculateNextStamp() {
        nextStamp = System.currentTimeMillis() + timeout;
    }
}