/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntToLongFunction<E extends Throwable> {
    public static final FailableIntToLongFunction NOP = t -> 0L;

    public static <E extends Throwable> FailableIntToLongFunction<E> nop() {
        return NOP;
    }

    public long applyAsLong(int var1) throws E;
}

