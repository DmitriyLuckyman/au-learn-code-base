package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:55
 */
public class EvalOperator implements Operator {
    public Operand left() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Operand right() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer evaluate() {
        return right().getValue();
    }
}
