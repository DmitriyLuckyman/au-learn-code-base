package ru.spbau.bandurin.task5;

/**
 * Comparator for elements which transforms with specified function to comparable state
 * @author Dmitriy Bandurin
 *         Date: 26.03.13
 */
public class Comparator<P1, R extends Comparable<? super R>> implements java.util.Comparator<P1> {
    private Function1<P1, R> transformer;

    /**
     * Create new instance of Comparator with specified transformer
     * @param transformer transformer for convert from Type P1 to R extends Comparable
     */
    public Comparator(final Function1<P1, R> transformer) {
        this.transformer = transformer;
    }

    public int compare(P1 o1, P1 o2) {
        return transformer.apply(o1).compareTo(transformer.apply(o2));
    }
}
