package ru.spbau.bandurin.task5;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class ComparatorTest {
    @Test
    public void testCompare() throws Exception {
        //Comparator with identity function
        Comparator<Integer, Integer> cmp = new Comparator<Integer, Integer>(new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        });
        assertEquals("Test comparison of equals elements ", 0, cmp.compare(1, 1));
        assertEquals("Test comparison first less second", -1, cmp.compare(1, 2));
        assertEquals("Test comparison second less first", 1, cmp.compare(2, 1));
    }

    @Test
    public void testCompareDifferentElementsAreEquals(){
        //test that passed function are applied
        Comparator<Object, Integer> cmp = new Comparator<Object, Integer>(new Function1<Object, Integer>() {
            @Override
            public Integer apply(Object integer) {
                return 0;
            }
        });
        Object object = new Object();
        assertEquals("equals elements should be equals", 0, cmp.compare(object, object));
        assertEquals("Different objects are equals", 0, cmp.compare(new Object(), object));
    }
}
