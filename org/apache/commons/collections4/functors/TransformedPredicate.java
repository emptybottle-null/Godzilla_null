/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.PredicateDecorator;

public final class TransformedPredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = -5596090919668315834L;
    private final Transformer<? super T, ? extends T> iTransformer;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> transformedPredicate(Transformer<? super T, ? extends T> transformer, Predicate<? super T> predicate) {
        if (transformer == null) {
            throw new NullPointerException("The transformer to call must not be null");
        }
        if (predicate == null) {
            throw new NullPointerException("The predicate to call must not be null");
        }
        return new TransformedPredicate<T>(transformer, predicate);
    }

    public TransformedPredicate(Transformer<? super T, ? extends T> transformer, Predicate<? super T> predicate) {
        this.iTransformer = transformer;
        this.iPredicate = predicate;
    }

    @Override
    public boolean evaluate(T object) {
        T result = this.iTransformer.transform(object);
        return this.iPredicate.evaluate(result);
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }

    public Transformer<? super T, ? extends T> getTransformer() {
        return this.iTransformer;
    }
}

