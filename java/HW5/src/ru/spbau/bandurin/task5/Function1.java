package ru.spbau.bandurin.task5;

/**
 * Abstract class for function with 1-arity
 * @author Dmitriy Bandurin
 *         Date: 19.03.13
 */
public abstract class Function1< P1, R >{
    /**
     * Apply Function to Input parameter
     * @param p1 input parameter for current function
     * @return result of function applying
     */
    public abstract R apply(final P1 p1);

    /**
     * Composite this Function with another
     * @param g function to send result of current function as input parameter
     * @return new composite function
     */
    public final <C> Function1<P1, C> then(final Function1<R, C> g) {
        return new Function1<P1, C>() {
            public C apply(final P1 p1) {
                return g.apply(Function1.this.apply(p1));
            }
        };
    }
}
