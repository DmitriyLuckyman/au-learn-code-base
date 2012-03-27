package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:41
 */
public class MultiplyOperator implements Operator {
    public Operand left() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Operand right() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer evaluate() {
        Integer result = left().getValue();
        if(result != 0){
            result *= right().getValue();
        }
        return result;
    }
}
