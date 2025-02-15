/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongToDoubleFunction<E extends Throwable> {
    public static final FailableLongToDoubleFunction NOP = t -> 0.0;

    public static <E extends Throwable> FailableLongToDoubleFunction<E> nop() {
        return NOP;
    }

    public double applyAsDouble(long var1) throws E;
}

