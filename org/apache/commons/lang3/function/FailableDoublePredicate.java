/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableDoublePredicate<E extends Throwable> {
    public static final FailableDoublePredicate FALSE = t -> false;
    public static final FailableDoublePredicate TRUE = t -> true;

    public static <E extends Throwable> FailableDoublePredicate<E> falsePredicate() {
        return FALSE;
    }

    public static <E extends Throwable> FailableDoublePredicate<E> truePredicate() {
        return TRUE;
    }

    default public FailableDoublePredicate<E> and(FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) && other.test(t);
    }

    default public FailableDoublePredicate<E> negate() {
        return t -> !this.test(t);
    }

    default public FailableDoublePredicate<E> or(FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return t -> this.test(t) || other.test(t);
    }

    public boolean test(double var1) throws E;
}

