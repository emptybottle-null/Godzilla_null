/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToLongBiFunction<T, U, E extends Throwable> {
    public static final FailableToLongBiFunction NOP = (t, u) -> 0L;

    public static <T, U, E extends Throwable> FailableToLongBiFunction<T, U, E> nop() {
        return NOP;
    }

    public long applyAsLong(T var1, U var2) throws E;
}

