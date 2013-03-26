package ru.spbau.bandurin.task5;
import java.util.ArrayList;
import java.util.Collection;

/**
 * High Order function
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class HighOrderFunction {
    private HighOrderFunction(){}

    /**
     * Convert iterable collection with type P1 to new type using passed function
     * @param source source collection to transform
     * @param transformer transform function
     * @return new Collection with transformed values.If source or condition are null return null.
     */
    public static <P1, R> Iterable<R> map(Iterable<P1> source, Function1<? super P1, ? extends R> transformer) {
        final Collection<R> result;
        Predicate<Object> notNull = Predicate.notNull();
        if(notNull.apply(source) && notNull.apply(transformer)){
            result = new ArrayList<R>();
            for (final P1 element : source){
                result.add(transformer.apply(element));
            }
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Find all elements that meets condition
     * @param source source collection to search
     * @param condition condition function
     * @return Iterable of finds elements or empty list. If source or condition are null return null.
     */
    public static <R> Iterable<R> filter(final Iterable<R> source, final Predicate<R> condition) {
        final Collection<R> result;
        Predicate<Object> notNull = Predicate.notNull();
        if(notNull.apply(source) && notNull.apply(condition)){
            result = new ArrayList<R>();
            for (final R element : source){
                if(condition.apply(element)){
                    result.add(element);
                }
            }
        } else {
            result = null;
        }

        return result;
    }

    /**
     * Get the first matched by condition element
     * @param source source collection to search
     * @param condition condition function
     * @return First matched elements or null. If source or condition are null return null.
     */
    public static <R> R take(final Iterable<R> source, final Predicate<R> condition) {
        Predicate<Object> isNull = Predicate.isNull();
        if(isNull.apply(source) || isNull.apply(condition)){
            return null;
        }
        for (final R element : source){
            if(condition.apply(element)){
                return element;
            }
        }
        return null;
    }

    /**
     * Fold collection to accumulated value
     * @param source source to accumulate
     * @param accumulator accumulate function
     * @param initialValue initial value for accumulator
     * @return accumulated value.If source or accumulator is null return initialValue.
     */
    public static <T, R> R fold(final Iterable<? extends T> source,
                                final Function2<? super R, ? super T, ? extends R> accumulator, final R initialValue) {
        R acc = initialValue;
        Predicate<Object> notNull = Predicate.notNull();
        if (notNull.apply(source) && notNull.apply(accumulator)) {
            for (T aSource : source) {
                acc = accumulator.apply(acc, aSource);
            }
        }
        return acc;
    }
}
