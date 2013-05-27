package ru.spbau.bandurin.task5;

/**
 * Abstract class for function with 2-arity
 * @author Dmitriy Bandurin
 *         Date: 25.03.13
 */
public abstract class Function2<P1, P2, R> {
    /**
     * Apply Function to Input parameters
     * @param p1 first input parameter for current function
     * @param p2 second input parameter for current function
     * @return result of function applying
     */
    public abstract R apply(P1 p1, P2 p2);

    /**
     * Composite this Function with another
     * @param g function to send result of current function as input parameter
     * @return new composite function
     */
    public final <C> Function2<P1, P2, C> then(final Function1<? super R , C> g) {
        return new Function2<P1, P2, C>() {
            public C apply(final P1 p1, final P2 p2) {
                return g.apply(Function2.this.apply(p1, p2));
            }
        };
    }

    /**
     * Curry first parameter of function and return 1-arity function
     * @param p1 first parameter value for currying
     * @return new 1-arity function
     */
    public final Function1<P2, R> bind1(final P1 p1){
        return new Function1<P2, R>() {
            @Override
            public R apply(final P2 p2) {
                return Function2.this.apply(p1, p2);
            }
        };
    }

    /**
     * Curry second parameter of function and return 1-arity function
     * @param p2 second parameter value for currying
     * @return new 1-arity function
     */
    public final Function1<P1, R> bind2(final P2 p2){
        return new Function1<P1, R>() {
            @Override
            public R apply(final P1 p1) {
                return Function2.this.apply(p1, p2);
            }
        };
    }
}


