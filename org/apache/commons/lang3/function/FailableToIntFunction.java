/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToIntFunction<T, E extends Throwable> {
    public static final FailableToIntFunction NOP = t -> 0;

    public static <T, E extends Throwable> FailableToIntFunction<T, E> nop() {
        return NOP;
    }

    public int applyAsInt(T var1) throws E;
}

