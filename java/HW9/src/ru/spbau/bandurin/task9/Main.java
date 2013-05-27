package ru.spbau.bandurin.task9;

/**
 * Main class create 5 thread for distributed child and 5 thread for worker.
 */
public class Main {
    /**
     * Main method start test of distributed incrementer.
     */
    public static void main(String[] args){
        Worker worker = new Worker();
        for(int i = 0; i < 5; i++){
            Thread workerThread = new Thread(worker);
            Thread clientThread = new Thread(new StupidChild(i,10000,1,1000));
            clientThread.start();
            workerThread.setDaemon(true);
            workerThread.start();
        }
    }
}
