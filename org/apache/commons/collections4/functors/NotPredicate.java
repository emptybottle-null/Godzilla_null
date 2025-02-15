/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.PredicateDecorator;

public final class NotPredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = -2654603322338049674L;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> notPredicate(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new NotPredicate<T>(predicate);
    }

    public NotPredicate(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    @Override
    public boolean evaluate(T object) {
        return !this.iPredicate.evaluate(object);
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }
}

