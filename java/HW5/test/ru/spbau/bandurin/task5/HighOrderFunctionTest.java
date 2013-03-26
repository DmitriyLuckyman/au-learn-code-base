package ru.spbau.bandurin.task5;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class HighOrderFunctionTest {

    @Test
    public void testMapWithNull() throws Exception {
        assertNull("Test function is null", HighOrderFunction.map(new ArrayList<Integer>(), null));
        assertNull("Test source is null", HighOrderFunction.map(null, Predicate.alwaysFalse()));
        assertNull("Test function and source are null", HighOrderFunction.map(null, null));
    }

    @Test
    public void testMap() throws Exception {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterable<Integer> result = HighOrderFunction.map(source, new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        });
        Iterator<Integer> iterator = result.iterator();
        assertTrue("Test iterator is not empty", iterator.hasNext());
        assertEquals("Test mapped value is correct", Integer.valueOf(2), iterator.next());
        assertTrue("Test has more records in iterator", iterator.hasNext());
        assertEquals("Test mapped value is correct", Integer.valueOf(3), iterator.next());
        assertTrue("Test has more records in iterator", iterator.hasNext());
        assertEquals("Test mapped value is correct", Integer.valueOf(4), iterator.next());
        assertFalse("Test no more records in iterator", iterator.hasNext());
    }

    @Test
    public void testFilter() throws Exception {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterable<Integer> result = HighOrderFunction.filter(source, Predicate.eq(2));
        Iterator<Integer> iterator = result.iterator();
        assertTrue("Test iterator is not empty", iterator.hasNext());
        assertEquals("Test found value is correct", Integer.valueOf(2), iterator.next());
        assertFalse("Test no more records in iterator", iterator.hasNext());
    }

    @Test
    public void testFilterNotFound() throws Exception {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Iterable<Integer> result = HighOrderFunction.filter(source, Predicate.eq(4));
        Iterator<Integer> iterator = result.iterator();
        assertFalse("Test records not found.iterator is empty", iterator.hasNext());
    }

    @Test
    public void testFilterWithNull() throws Exception {
        assertNull("Test function is null", HighOrderFunction.filter(new ArrayList<Integer>(), null));
        assertNull("Test source is null", HighOrderFunction.filter(null, Predicate.eq(4)));
        assertNull("Test function and source are null", HighOrderFunction.filter(null, null));
    }

    @Test
    public void testTake() throws Exception {
        List<Integer> source = Arrays.asList(1, 2, 3);
        assertEquals("Test value found", Integer.valueOf(2), HighOrderFunction.take(source, Predicate.eq(2)));
        assertEquals("Test not found", null, HighOrderFunction.take(source, Predicate.eq(4)));
        assertNull("Test function is null", HighOrderFunction.take(source, null));
        assertNull("Test source is null", HighOrderFunction.take(null, Predicate.eq(4)));
        assertNull("Test function and source are null", HighOrderFunction.take(null, null));
    }

    @Test
    public void testFold() throws Exception {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Function2<Integer, Integer, Integer> accumulator = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer o, Integer o2) {
                return o + o2;
            }
        };
        assertNull("Test function is null", HighOrderFunction.fold(source, null, null));
        assertNull("Test source is null", HighOrderFunction.fold(null, accumulator, null));
        assertNull("Test function and source are null", HighOrderFunction.fold(null, null, null));
        assertEquals("Test initialValue with function is null", Integer.valueOf(0), HighOrderFunction.fold(source, null, 0));
        assertEquals("Test initialValue with source is null", Integer.valueOf(0), HighOrderFunction.fold(null, accumulator, 0));
        assertEquals("Test initialValue with function and source are null",Integer.valueOf(0), HighOrderFunction.fold(null, null, 0));

        assertEquals("Test fold main scenario", Integer.valueOf(6), HighOrderFunction.fold(source, accumulator, 0));
        assertEquals("Test empty source", Integer.valueOf(0), HighOrderFunction.fold( new ArrayList<Integer>(), accumulator, 0));
    }
}
