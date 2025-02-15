/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToIntBiFunction<T, U, E extends Throwable> {
    public static final FailableToIntBiFunction NOP = (t, u) -> 0;

    public static <T, U, E extends Throwable> FailableToIntBiFunction<T, U, E> nop() {
        return NOP;
    }

    public int applyAsInt(T var1, U var2) throws E;
}

