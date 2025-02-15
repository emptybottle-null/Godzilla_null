/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableLongUnaryOperator<E extends Throwable> {
    public static final FailableLongUnaryOperator NOP = t -> 0L;

    public static <E extends Throwable> FailableLongUnaryOperator<E> identity() {
        return t -> t;
    }

    public static <E extends Throwable> FailableLongUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableLongUnaryOperator<E> andThen(FailableLongUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(this.applyAsLong(t));
    }

    public long applyAsLong(long var1) throws E;

    default public FailableLongUnaryOperator<E> compose(FailableLongUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v -> this.applyAsLong(before.applyAsLong(v));
    }
}

