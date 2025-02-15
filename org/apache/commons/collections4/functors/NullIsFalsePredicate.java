/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.PredicateDecorator;

public final class NullIsFalsePredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = -2997501534564735525L;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> nullIsFalsePredicate(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new NullIsFalsePredicate<T>(predicate);
    }

    public NullIsFalsePredicate(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    @Override
    public boolean evaluate(T object) {
        if (object == null) {
            return false;
        }
        return this.iPredicate.evaluate(object);
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }
}

