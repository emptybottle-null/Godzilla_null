/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOSupplier;

@FunctionalInterface
public interface IOFunction<T, R> {
    public R apply(T var1) throws IOException;

    default public <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "before");
        return v -> this.apply(before.apply((Object)v));
    }

    default public <V> IOFunction<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "before");
        return v -> this.apply(before.apply((Object)v));
    }

    default public IOSupplier<R> compose(IOSupplier<? extends T> before) {
        Objects.requireNonNull(before, "before");
        return () -> this.apply(before.get());
    }

    default public IOSupplier<R> compose(Supplier<? extends T> before) {
        Objects.requireNonNull(before, "before");
        return () -> this.apply(before.get());
    }

    default public <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return t -> after.apply((R)this.apply(t));
    }

    default public <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return t -> after.apply((R)this.apply(t));
    }

    default public IOConsumer<T> andThen(IOConsumer<? super R> after) {
        Objects.requireNonNull(after, "after");
        return t -> after.accept((R)this.apply(t));
    }

    default public IOConsumer<T> andThen(Consumer<? super R> after) {
        Objects.requireNonNull(after, "after");
        return t -> after.accept((R)this.apply(t));
    }

    public static <T> IOFunction<T, T> identity() {
        return t -> t;
    }
}

