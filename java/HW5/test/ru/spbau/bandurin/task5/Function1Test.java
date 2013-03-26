package ru.spbau.bandurin.task5;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class Function1Test {
    @Test
    public void testThen() throws Exception {
        Function1<String, String> f1 = new Function1<String, String>() {
            @Override
            public String apply(String s) {
                return s + "1";
            }
        };
        Function1<String, String> f2 = new Function1<String, String>() {

            @Override
            public String apply(String s) {
                return s + "2";
            }
        };
        Function1<String, String> compositeFunction = f1.then(f2);
        assertEquals("12", compositeFunction.apply(""));
    }
}
