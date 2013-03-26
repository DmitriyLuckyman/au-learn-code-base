package ru.spbau.bandurin.task5;

/**
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */

import org.junit.Test;

import static org.junit.Assert.*;


public class PredicateTest {

    @Test
    public void testAnd(){
        Predicate<Object> falseP = Predicate.alwaysFalse();
        Predicate<Object> trueP = Predicate.alwaysTrue();
        Object dummy = new Object();
        assertFalse("Test false and true should return false", falseP.and(trueP).apply(dummy));
        assertFalse("Test false and false should return false", falseP.and(falseP).apply(dummy));
        assertFalse("Test true and false should return false", trueP.and(falseP).apply(dummy));
        assertTrue("Test true and true should return true", trueP.and(trueP).apply(dummy));
    }

    @Test
    public void testAndPredicatesWithDifferentTypes() throws Exception {
        Predicate<Integer> p = Predicate.less(7);

        Predicate<Number> n = new Predicate<Number>() {
            @Override
            public Boolean apply(Number number) {
                return number.intValue() > 5;
            }
        };

        Predicate<? super Integer> between5And7 = p.and(n);
        assertTrue(between5And7.apply(6));
        assertFalse(between5And7.apply(5));
        assertFalse(between5And7.apply(7));
    }

    @Test
    public void testOr() throws Exception {
        Predicate<Object> falseP = Predicate.alwaysFalse();
        Predicate<Object> trueP = Predicate.alwaysTrue();
        Object dummy = new Object();
        assertFalse("Test false and false should return false", falseP.or(falseP).apply(dummy));
        assertTrue("Test false and true should return true", falseP.or(trueP).apply(dummy));
        assertTrue("Test true and false should return true", trueP.or(falseP).apply(dummy));
        assertTrue("Test true and true should return true", trueP.or(trueP).apply(dummy));
    }

    @Test
    public void testOrWithDiffTypes() throws Exception {
        Predicate<Integer> p = Predicate.less(5);

        Predicate<Number> n = new Predicate<Number>() {
            @Override
            public Boolean apply(Number number) {
                return number.intValue() > 7;
            }
        };

        Predicate<? super Integer> less5More7 = p.or(n);
        assertFalse(less5More7.apply(6));
        assertFalse(less5More7.apply(5));
        assertFalse(less5More7.apply(7));
        assertTrue(less5More7.apply(4));
        assertTrue(less5More7.apply(8));
    }

    @Test
    public void testNot() throws Exception {
        Predicate<Object> falseP = Predicate.alwaysFalse();
        Predicate<Object> trueP = Predicate.alwaysTrue();

        Object dummy = new Object();
        assertTrue("Test false with not should return true", falseP.not().apply(dummy));
        assertFalse("Test true with not should return false", trueP.not().apply(dummy));
    }

    @Test
    public void testAlwaysTrue() throws Exception {
        assertTrue("Test with dummy object", Predicate.alwaysTrue().apply(new Object()));
        assertTrue("Test with true parameter value", Predicate.alwaysTrue().apply(false));
        assertTrue("Test with false parameter value", Predicate.alwaysTrue().apply(true));
    }

    @Test
    public void testAlwaysFalse() throws Exception {
        assertFalse("Test with dummy object", Predicate.alwaysFalse().apply(new Object()));
        assertFalse("Test with true parameter value", Predicate.alwaysFalse().apply(false));
        assertFalse("Test with false parameter value", Predicate.alwaysFalse().apply(true));
    }

    @Test
    public void testIsNull() throws Exception {
        Predicate<Object> isNull = Predicate.isNull();
        assertTrue("Test for null value", isNull.apply(null));
        assertFalse("Test for not null value", isNull.apply(new Object()));
    }

    @Test
    public void testNotNull() throws Exception {
        Predicate<Object> notNull = Predicate.notNull();
        assertFalse("Test for null value", notNull.apply(null));
        assertTrue("Test for not null value", notNull.apply(new Object()));
    }

    @Test
    public void testEquals() throws Exception {
        Predicate<Integer> eq = Predicate.eq(1);

        assertTrue("Test equals Object", eq.apply(1));
        assertFalse("Test not equals Object(more)", eq.apply(2));
        assertFalse("Test not equals Object(less)", eq.apply(0));
    }

    @Test
    public void testLess() throws Exception {
        Predicate<Integer> less = Predicate.less(1);

        assertFalse("Test equals Object", less.apply(1));
        assertFalse("Test not equals Object(more)", less.apply(2));
        assertTrue("Test not equals Object(less)", less.apply(0));

    }
}
