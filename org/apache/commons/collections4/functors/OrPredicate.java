/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.PredicateDecorator;

public final class OrPredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = -8791518325735182855L;
    private final Predicate<? super T> iPredicate1;
    private final Predicate<? super T> iPredicate2;

    public static <T> Predicate<T> orPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        if (predicate1 == null || predicate2 == null) {
            throw new NullPointerException("Predicate must not be null");
        }
        return new OrPredicate<T>(predicate1, predicate2);
    }

    public OrPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        this.iPredicate1 = predicate1;
        this.iPredicate2 = predicate2;
    }

    @Override
    public boolean evaluate(T object) {
        return this.iPredicate1.evaluate(object) || this.iPredicate2.evaluate(object);
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate1, this.iPredicate2};
    }
}

