package ru.spbau.bandurin.task7;

/**
 * @author : Luckyman
 */
public class DistributedIncrementor  {

    public int increment(int i) throws InterruptedException{
        return i += 1;
    }
}
