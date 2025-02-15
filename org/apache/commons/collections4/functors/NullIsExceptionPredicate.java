/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.PredicateDecorator;

public final class NullIsExceptionPredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = 3243449850504576071L;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> nullIsExceptionPredicate(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new NullIsExceptionPredicate<T>(predicate);
    }

    public NullIsExceptionPredicate(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    @Override
    public boolean evaluate(T object) {
        if (object == null) {
            throw new FunctorException("Input Object must not be null");
        }
        return this.iPredicate.evaluate(object);
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }
}

