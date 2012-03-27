package ru.spbau.bandurin.task3;

/**
 * User: Dmitriy Bandurin
 * Date: 28.02.12
 * Time: 18:43
 */
public class DivideOperator implements Operator {
    public Operand left() {
        return null;
    }

    public Operand right() {
        return null;
    }

    public Integer evaluate() {
        Integer result = left().getValue();
        if(result != 0){
            result /= right().getValue();
        }
        return result;
    }
}
