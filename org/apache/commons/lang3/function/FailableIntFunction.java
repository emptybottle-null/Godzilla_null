/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntFunction<R, E extends Throwable> {
    public static final FailableIntFunction NOP = t -> null;

    public static <R, E extends Throwable> FailableIntFunction<R, E> nop() {
        return NOP;
    }

    public R apply(int var1) throws E;
}

