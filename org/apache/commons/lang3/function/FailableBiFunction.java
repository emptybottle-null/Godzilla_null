/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;
import org.apache.commons.lang3.function.FailableFunction;

@FunctionalInterface
public interface FailableBiFunction<T, U, R, E extends Throwable> {
    public static final FailableBiFunction NOP = (t, u) -> null;

    public static <T, U, R, E extends Throwable> FailableBiFunction<T, U, R, E> nop() {
        return NOP;
    }

    default public <V> FailableBiFunction<T, U, V, E> andThen(FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply((R)this.apply(t, u));
    }

    public R apply(T var1, U var2) throws E;
}

