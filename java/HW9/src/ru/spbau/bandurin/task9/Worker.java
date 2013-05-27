package ru.spbau.bandurin.task9;

/**
 * See {@link Worker#run()} method for details
 * @author Dmitriy Bandurin
 *         Date: 27.05.13
 */
public class Worker implements Runnable {

    /**
     * Get Task from DistributedIncrementer and increase value by one.
     * Then release task to DistributedIncrementer
     * @see Thread#run()
     */
    public void run() {
        final DistributedIncrementer instance = DistributedIncrementer.getInstance();
        while(!Thread.interrupted()){
            try {
                final Task nextWork = instance.getNextWork();
                nextWork.setResult(nextWork.getInputValue() + 1);
                instance.releaseTask(nextWork);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupt in worker thread");
            }
        }
    }
}
