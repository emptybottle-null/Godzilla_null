/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableDoubleUnaryOperator<E extends Throwable> {
    public static final FailableDoubleUnaryOperator NOP = t -> 0.0;

    public static <E extends Throwable> FailableDoubleUnaryOperator<E> identity() {
        return t -> t;
    }

    public static <E extends Throwable> FailableDoubleUnaryOperator<E> nop() {
        return NOP;
    }

    default public FailableDoubleUnaryOperator<E> andThen(FailableDoubleUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(this.applyAsDouble(t));
    }

    public double applyAsDouble(double var1) throws E;

    default public FailableDoubleUnaryOperator<E> compose(FailableDoubleUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return v -> this.applyAsDouble(before.applyAsDouble(v));
    }
}

