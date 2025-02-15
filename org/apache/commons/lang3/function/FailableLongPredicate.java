/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableLongPredicate<E extends Throwable> {
    public static final FailableLongPredicate FALSE = t -> false;
    public static final FailableLongPredicate TRUE = t -> true;

    public static <E extends Throwable> FailableLongPredicate<E> falsePredicate() {
        return FALSE;
    }

    public static <E extends Throwable> FailableLongPredicate<E> truePredicate() {
        return TRUE;
    }

    default public FailableLongPredicate<E> and(FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) && other.test(t);
    }

    default public FailableLongPredicate<E> negate() {
        return t -> !this.test(t);
    }

    default public FailableLongPredicate<E> or(FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) || other.test(t);
    }

    public boolean test(long var1) throws E;
}

