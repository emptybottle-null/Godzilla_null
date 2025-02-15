/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailablePredicate<T, E extends Throwable> {
    public static final FailablePredicate FALSE = t -> false;
    public static final FailablePredicate TRUE = t -> true;

    public static <T, E extends Throwable> FailablePredicate<T, E> falsePredicate() {
        return FALSE;
    }

    public static <T, E extends Throwable> FailablePredicate<T, E> truePredicate() {
        return TRUE;
    }

    default public FailablePredicate<T, E> and(FailablePredicate<? super T, E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) && other.test(t);
    }

    default public FailablePredicate<T, E> negate() {
        return t -> !this.test(t);
    }

    default public FailablePredicate<T, E> or(FailablePredicate<? super T, E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) || other.test(t);
    }

    public boolean test(T var1) throws E;
}

