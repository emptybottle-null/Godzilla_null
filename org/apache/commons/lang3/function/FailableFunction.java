/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableFunction<T, R, E extends Throwable> {
    public static final FailableFunction NOP = t -> null;

    public static <T, E extends Throwable> FailableFunction<T, T, E> identity() {
        return t -> t;
    }

    public static <T, R, E extends Throwable> FailableFunction<T, R, E> nop() {
        return NOP;
    }

    default public <V> FailableFunction<T, V, E> andThen(FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return t -> after.apply((R)this.apply(t));
    }

    public R apply(T var1) throws E;

    default public <V> FailableFunction<V, R, E> compose(FailableFunction<? super V, ? extends T, E> before) {
        Objects.requireNonNull(before);
        return v -> this.apply(before.apply((Object)v));
    }
}

