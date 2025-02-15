/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Absent;
import com.google.common.base.AbstractIterator;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Present;
import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
public abstract class Optional<T>
implements Serializable {
    private static final long serialVersionUID = 0L;

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T reference) {
        return new Present<T>(Preconditions.checkNotNull(reference));
    }

    public static <T> Optional<T> fromNullable(@Nullable T nullableReference) {
        return nullableReference == null ? Optional.absent() : new Present<T>(nullableReference);
    }

    public static <T> @Nullable Optional<T> fromJavaUtil(@Nullable java.util.Optional<T> javaUtilOptional) {
        return javaUtilOptional == null ? null : Optional.fromNullable(javaUtilOptional.orElse(null));
    }

    public static <T> @Nullable java.util.Optional<T> toJavaUtil(@Nullable Optional<T> googleOptional) {
        return googleOptional == null ? null : googleOptional.toJavaUtil();
    }

    public java.util.Optional<T> toJavaUtil() {
        return java.util.Optional.ofNullable(this.orNull());
    }

    Optional() {
    }

    public abstract boolean isPresent();

    public abstract T get();

    public abstract T or(T var1);

    public abstract Optional<T> or(Optional<? extends T> var1);

    @Beta
    public abstract T or(Supplier<? extends T> var1);

    public abstract @Nullable T orNull();

    public abstract Set<T> asSet();

    public abstract <V> Optional<V> transform(Function<? super T, V> var1);

    public abstract boolean equals(@Nullable Object var1);

    public abstract int hashCode();

    public abstract String toString();

    @Beta
    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> optionals) {
        Preconditions.checkNotNull(optionals);
        return new Iterable<T>(){

            @Override
            public Iterator<T> iterator() {
                return new AbstractIterator<T>(){
                    private final Iterator<? extends Optional<? extends T>> iterator;
                    {
                        this.iterator = Preconditions.checkNotNull(optionals.iterator());
                    }

                    @Override
                    protected T computeNext() {
                        while (this.iterator.hasNext()) {
                            Optional optional = this.iterator.next();
                            if (!optional.isPresent()) continue;
                            return optional.get();
                        }
                        return this.endOfData();
                    }
                };
            }
        };
    }
}

