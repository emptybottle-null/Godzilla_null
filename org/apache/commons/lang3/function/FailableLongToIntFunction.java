/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToIntFunction<E extends Throwable> {
    public static final FailableLongToIntFunction NOP = t -> 0;

    public static <E extends Throwable> FailableLongToIntFunction<E> nop() {
        return NOP;
    }

    public int applyAsInt(long var1) throws E;
}

