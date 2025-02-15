/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableToLongFunction<T, E extends Throwable> {
    public static final FailableToLongFunction NOP = t -> 0L;

    public static <T, E extends Throwable> FailableToLongFunction<T, E> nop() {
        return NOP;
    }

    public long applyAsLong(T var1) throws E;
}

