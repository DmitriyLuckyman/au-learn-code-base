package ru.spbau.bandurin.task5;

/**
 * Predicate base Class. Function with transformation P1 -> boolean
 * @author Dmitriy Bandurin
 *         Date: 25.03.13
 */
public abstract class Predicate<P1> extends Function1<P1, Boolean> {
    /**
     * Test parameter value by predicate function
     * @param p1 input parameter for current function
     * @return true/false
     */
    public abstract Boolean apply(final P1 p1);

    /**
     * Negation of current predicate
     * @return new predicate with negation of current
     */
    public final Predicate<P1> not(){
        return not(this);
    }

    /**
     * Implements logical operation "and"
     * @param predicate second argument of and operator
     * @return new predicate
     */
    public final Predicate<? super P1> and(final Predicate<? super P1> predicate){
        return and(this, predicate);
    }

    /**
     * Implements logical operation "or"
     * @param predicate second argument of or operator
     * @return new predicate
     */
    public final Predicate<? super P1> or(final Predicate<? super P1> predicate){
        return or(this, predicate);
    }

    /**
     * Negation of predicate
     * @param predicate source predicate
     * @return new predicate
     */
    public static <P1> Predicate<P1> not(final Predicate<P1> predicate){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return !predicate.apply(p1);
            }
        };
    }

    /**
     * Implements logical operation "and"
     * @param firstPredicate first argument of and operator
     * @param secondPredicate second argument of and operation
     * @return new predicate
     */
    public static <P1> Predicate<? super P1> and(final Predicate<? super P1> firstPredicate, final Predicate<? super P1> secondPredicate){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return firstPredicate.apply(p1) && secondPredicate.apply(p1);
            }
        };
    }

    /**
     * Implements logical operation "or"
     * @param firstPredicate first argument of or operator
     * @param secondPredicate second argument of or operation
     * @return new predicate
     */
    public static <P1> Predicate<? super P1> or(final Predicate<? super P1> firstPredicate, final Predicate<? super P1> secondPredicate){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return firstPredicate.apply(p1) || secondPredicate.apply(p1);
            }
        };
    }

    /**
     * Predicate which always return true
     * @return new predicate
     */
    public static <P1> Predicate<P1> alwaysTrue(){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return true;
            }
        };
    }

    /**
     * Predicate which always return false
     * @return new predicate
     */
    public static <P1> Predicate<P1> alwaysFalse(){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return false;
            }
        };
    }

    /**
     * Predicate which test some object is equals to null
     * @return new predicate
     */
    public static <P1> Predicate<P1> isNull(){
        return new Predicate<P1>() {
            @Override
            public Boolean apply(final P1 p1) {
                return p1 == null;
            }
        };
    }

    /**
     * Predicate which test some object is not equals to null
     * @return new predicate
     */
    public static <P1> Predicate<P1> notNull(){
        return Predicate.<P1>isNull().not();
    }

    /**
     * Create predicate to test some value for equals with passed parameter
     * @param p1 base value to equals
     * @return new predicate
     */
    public static <T extends Comparable<? super T>> Predicate<T> eq(final T p1){
        return new Predicate<T>() {
            @Override
            public Boolean apply(final T comparable) {
                return comparable.compareTo(p1) == 0;
            }
        };
    }

    /**
     * Create predicate to test some value for less with passed parameter
     * @param p1 base value
     * @return new predicate
     */
    public static <T extends Comparable<? super T>> Predicate<T> less(final T p1){
        return new Predicate<T>() {
            @Override
            public Boolean apply(final T comparable) {
                return comparable.compareTo(p1) < 0;
            }
        };
    }
}
