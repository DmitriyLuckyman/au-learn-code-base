package ru.spbau.bandurin.task9;

import java.util.Random;

/**
 * @author Dmitriy Bandurin
 *         Date: 27.05.13
 */
public class StupidChild implements Runnable {
    private final int id;
    private final int count;
    private final int minBoundary;
    private final Random r;
    private final int delta;

    /**
     *
     * @param id unique identifier of current child
     * @param count number of generated random numbers
     * @param minBoundary min value of random include
     * @param maxBoundary max value of random exclude
     * @throws IllegalArgumentException if maxBoundary < minBoundary or maxBoundary == minBoundary
     */
    public StupidChild(int id, int count, int minBoundary, int maxBoundary) {
        this.id = id;
        this.count = count;
        if(maxBoundary < minBoundary || maxBoundary == minBoundary){
            throw new IllegalArgumentException("maxBoundary is more or equals then minBoundary");
        }
        this.minBoundary = minBoundary;
        delta = maxBoundary - this.minBoundary;
        this.r = new Random();
    }

    /**
     * Generates random number tell DistributedIncrementer to increment it and return result.
     * @see Thread#run()
     */
    public void run() {
        int currentGenerated = 0;
        while(!Thread.interrupted() && currentGenerated < count){
            try {
                final int random = minBoundary + r.nextInt(delta);
                final int incrementValue = DistributedIncrementer.getInstance().increment(random);
                System.out.printf("%d : %d %d%n", id, random, incrementValue);
                currentGenerated++;
            } catch (InterruptedException e) {
                System.err.println("Exception in StupidChild" + e.getMessage());
            }
        }
    }
}
