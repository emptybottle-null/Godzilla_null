/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongFunction<R, E extends Throwable> {
    public static final FailableLongFunction NOP = t -> null;

    public static <R, E extends Throwable> FailableLongFunction<R, E> nop() {
        return NOP;
    }

    public R apply(long var1) throws E;
}

