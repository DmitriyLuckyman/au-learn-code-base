package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:37
 */
public interface Operator {
    Operand left();
    Operand right();
    Integer evaluate();
}
