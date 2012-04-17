package ru.spbau.bandurin.task7;

import java.util.Random;

/**
 * Generate random int values in customized range
 * @author Luckyman
 */
public class InRangeGenerator {
    private int start;
    private int end;
    private int intervalLength;
    private Random random;

    /**
     * Create Range Generator
     * @param start range start
     * @param end range end
     * @throws IncorrectRangeException if start more than end
     */
    public InRangeGenerator(int start, int end) throws IncorrectRangeException {
        if(start > end){
            throw new IncorrectRangeException("Range start more than range end");
        }
        this.start = start;
        this.end = end;
        this.intervalLength = Math.abs(end - start);
        if(!isZeroLengthInterval()){
            random = new Random();
        }
    }

    /**
     *
     * @return generate next random int in selected range/
     */
    public int next(){
        if(!isZeroLengthInterval()){
            int rnd = random.nextInt(this.intervalLength);
            rnd += start;
            return rnd;
        }
        return start;
    }

    private boolean isZeroLengthInterval() {
        return intervalLength == 0;
    }
}
