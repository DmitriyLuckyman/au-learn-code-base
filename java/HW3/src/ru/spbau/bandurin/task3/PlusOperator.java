package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:39
 */
public class PlusOperator implements Operator {
    public Operand left() {
        return null;
    }

    public Operand right() {
        return null;
    }

    public Integer evaluate() {
        return left().getValue() + right().getValue();
    }
}
