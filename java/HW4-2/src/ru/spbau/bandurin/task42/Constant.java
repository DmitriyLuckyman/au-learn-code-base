package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:48
 */
public class Constant implements Operand {
    private Integer value;

    public Constant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
