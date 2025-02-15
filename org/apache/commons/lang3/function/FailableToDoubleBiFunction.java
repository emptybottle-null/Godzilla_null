/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToDoubleBiFunction<T, U, E extends Throwable> {
    public static final FailableToDoubleBiFunction NOP = (t, u) -> 0.0;

    public static <T, U, E extends Throwable> FailableToDoubleBiFunction<T, U, E> nop() {
        return NOP;
    }

    public double applyAsDouble(T var1, U var2) throws E;
}

