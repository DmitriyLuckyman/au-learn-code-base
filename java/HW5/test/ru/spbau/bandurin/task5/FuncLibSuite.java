package ru.spbau.bandurin.task5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComparatorTest.class,
        PredicateTest.class,
        Function1Test.class,
        Function2Test.class,
        HighOrderFunctionTest.class
})
public class FuncLibSuite {

}
