/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

class FunctorUtils {
    private FunctorUtils() {
    }

    static <T> Predicate<T>[] copy(Predicate<? super T> ... predicates) {
        if (predicates == null) {
            return null;
        }
        return (Predicate[])predicates.clone();
    }

    static <T> Predicate<T> coerce(Predicate<? super T> predicate) {
        return predicate;
    }

    static void validate(Predicate<?> ... predicates) {
        if (predicates == null) {
            throw new NullPointerException("The predicate array must not be null");
        }
        for (int i = 0; i < predicates.length; ++i) {
            if (predicates[i] != null) continue;
            throw new NullPointerException("The predicate array must not contain a null predicate, index " + i + " was null");
        }
    }

    static <T> Predicate<? super T>[] validate(Collection<? extends Predicate<? super T>> predicates) {
        if (predicates == null) {
            throw new NullPointerException("The predicate collection must not be null");
        }
        Predicate[] preds = new Predicate[predicates.size()];
        int i = 0;
        Iterator<Predicate<T>> iterator = predicates.iterator();
        while (iterator.hasNext()) {
            Predicate<? super T> predicate;
            preds[i] = predicate = iterator.next();
            if (preds[i] == null) {
                throw new NullPointerException("The predicate collection must not contain a null predicate, index " + i + " was null");
            }
            ++i;
        }
        return preds;
    }

    static <E> Closure<E>[] copy(Closure<? super E> ... closures) {
        if (closures == null) {
            return null;
        }
        return (Closure[])closures.clone();
    }

    static void validate(Closure<?> ... closures) {
        if (closures == null) {
            throw new NullPointerException("The closure array must not be null");
        }
        for (int i = 0; i < closures.length; ++i) {
            if (closures[i] != null) continue;
            throw new NullPointerException("The closure array must not contain a null closure, index " + i + " was null");
        }
    }

    static <T> Closure<T> coerce(Closure<? super T> closure) {
        return closure;
    }

    static <I, O> Transformer<I, O>[] copy(Transformer<? super I, ? extends O> ... transformers) {
        if (transformers == null) {
            return null;
        }
        return (Transformer[])transformers.clone();
    }

    static void validate(Transformer<?, ?> ... transformers) {
        if (transformers == null) {
            throw new NullPointerException("The transformer array must not be null");
        }
        for (int i = 0; i < transformers.length; ++i) {
            if (transformers[i] != null) continue;
            throw new NullPointerException("The transformer array must not contain a null transformer, index " + i + " was null");
        }
    }

    static <I, O> Transformer<I, O> coerce(Transformer<? super I, ? extends O> transformer) {
        return transformer;
    }
}

