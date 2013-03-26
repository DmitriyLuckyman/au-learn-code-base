package ru.spbau.bandurin.task5;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class Function2Test {
    @Test
    public void testThen() throws Exception {
        Function2<String, String, String> f1 = new Function2<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return "1";
            }
        };
        Function1<String, String> f2 = new Function1<String, String>() {

            @Override
            public String apply(String s) {
                return s + "2";
            }
        };
        Function2<String, String, String> compositeFunction = f1.then(f2);
        assertEquals("Test composite function", "12", compositeFunction.apply("", ""));
    }

    @Test
    public void testBind1() throws Exception {
        Function2<String, String, String> f1 = new Function2<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s;
            }
        };
        String p1Value = "BindParameter";
        Function1<String, String> bindParameter = f1.bind1(p1Value);
        assertEquals("Test bind first parameter", p1Value, bindParameter.apply("noBindParameter"));
    }

    @Test
    public void testBind2() throws Exception {
        Function2<String, String, String> f1 = new Function2<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s2;
            }
        };
        String p1Value = "BindParameter";
        Function1<String, String> bindParameter = f1.bind2(p1Value);
        assertEquals("Test bind second parameter", p1Value, bindParameter.apply("noBindParameter"));

    }
}
