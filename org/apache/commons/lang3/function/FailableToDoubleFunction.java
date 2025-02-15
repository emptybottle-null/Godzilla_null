/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleFunction<T, E extends Throwable> {
    public static final FailableToDoubleFunction NOP = t -> 0.0;

    public static <T, E extends Throwable> FailableToDoubleFunction<T, E> nop() {
        return NOP;
    }

    public double applyAsDouble(T var1) throws E;
}

