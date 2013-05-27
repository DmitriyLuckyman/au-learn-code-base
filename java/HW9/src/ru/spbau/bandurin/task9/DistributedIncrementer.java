package ru.spbau.bandurin.task9;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Dmitriy Bandurin
 *         Date: 27.05.13
 */
public class DistributedIncrementer {
    private final Queue<Task> tasks = new LinkedList<Task>();
    private final static DistributedIncrementer instance = new DistributedIncrementer();

    private DistributedIncrementer() {
    }

    public static DistributedIncrementer getInstance() {
        return instance;
    }

    public int increment(int i) throws InterruptedException{
        final Task e = new Task(i);
        synchronized (tasks){
            tasks.add(e);
            tasks.notify();
        }
        synchronized (e){
            while(e.getResult() == null){
                e.wait();
            }
        }
        return e.getResult();
    }

    public Task getNextWork() throws InterruptedException {
        Task task;
        synchronized (tasks){
            while(tasks.isEmpty()){
                tasks.wait();
            }
            task = tasks.poll();
        }
        return task;
    }

    public void releaseTask(final Task task){
        synchronized (task){
            task.notify();
        }
    }
}
