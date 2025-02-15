/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Transformer;

public class ClosureTransformer<T>
implements Transformer<T, T>,
Serializable {
    private static final long serialVersionUID = 478466901448617286L;
    private final Closure<? super T> iClosure;

    public static <T> Transformer<T, T> closureTransformer(Closure<? super T> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null");
        }
        return new ClosureTransformer<T>(closure);
    }

    public ClosureTransformer(Closure<? super T> closure) {
        this.iClosure = closure;
    }

    @Override
    public T transform(T input) {
        this.iClosure.execute(input);
        return input;
    }

    public Closure<? super T> getClosure() {
        return this.iClosure;
    }
}

