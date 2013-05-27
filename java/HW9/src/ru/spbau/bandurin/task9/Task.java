package ru.spbau.bandurin.task9;

/**
 * Hold task information for worker
 * @author Dmitriy Bandurin
 *         Date: 27.05.13
 */
public class Task {
    private final int inputValue;
    private Integer result;

    /**
     * Create New Instance of Task with initial value
     * @param inputValue initial value for current task
     */
    public Task(int inputValue) {
        this.inputValue = inputValue;
    }

    /**
     *
     * @return initial value;
     */
    public int getInputValue() {
        return inputValue;
    }

    /**
     * Result of this task. if result == null.this task isn't run.
     * @return result of task execution or null if task not executed.
     */
    public Integer getResult() {
        return result;
    }

    /**
     * Set the result value for task
     * @param result result of task execution.
     */
    public void setResult(int result) {
        this.result = result;
    }
}
