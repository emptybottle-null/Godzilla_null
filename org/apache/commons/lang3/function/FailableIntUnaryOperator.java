/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableIntUnaryOperator<E extends Throwable> {
    public static final FailableIntUnaryOperator NOP = t -> 0;

    public static <E extends Throwable> FailableIntUnaryOperator<E> identity() {
        return t -> t;
    }

    public static <E extends Throwable> FailableIntUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableIntUnaryOperator<E> andThen(FailableIntUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(this.applyAsInt(t));
    }

    public int applyAsInt(int var1) throws E;

    default public FailableIntUnaryOperator<E> compose(FailableIntUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v -> this.applyAsInt(before.applyAsInt(v));
    }
}

