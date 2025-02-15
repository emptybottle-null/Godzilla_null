/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable(containerOf={"B"})
@GwtIncompatible
public final class ImmutableClassToInstanceMap<B>
extends ForwardingMap<Class<? extends B>, B>
implements ClassToInstanceMap<B>,
Serializable {
    private static final ImmutableClassToInstanceMap<Object> EMPTY = new ImmutableClassToInstanceMap(ImmutableMap.of());
    private final ImmutableMap<Class<? extends B>, B> delegate;

    public static <B> ImmutableClassToInstanceMap<B> of() {
        return EMPTY;
    }

    public static <B, T extends B> ImmutableClassToInstanceMap<B> of(Class<T> type, T value) {
        ImmutableMap<Class<T>, T> map = ImmutableMap.of(type, value);
        return new ImmutableClassToInstanceMap<T>(map);
    }

    public static <B> Builder<B> builder() {
        return new Builder();
    }

    public static <B, S extends B> ImmutableClassToInstanceMap<B> copyOf(Map<? extends Class<? extends S>, ? extends S> map) {
        if (map instanceof ImmutableClassToInstanceMap) {
            ImmutableClassToInstanceMap cast = (ImmutableClassToInstanceMap)map;
            return cast;
        }
        return new Builder().putAll(map).build();
    }

    private ImmutableClassToInstanceMap(ImmutableMap<Class<? extends B>, B> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    @Override
    public <T extends B> @Nullable T getInstance(Class<T> type) {
        return (T)this.delegate.get(Preconditions.checkNotNull(type));
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> type, T value) {
        throw new UnsupportedOperationException();
    }

    Object readResolve() {
        return this.isEmpty() ? ImmutableClassToInstanceMap.of() : this;
    }

    public static final class Builder<B> {
        private final ImmutableMap.Builder<Class<? extends B>, B> mapBuilder = ImmutableMap.builder();

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(Class<T> key, T value) {
            this.mapBuilder.put(key, value);
            return this;
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> putAll(Map<? extends Class<? extends T>, ? extends T> map) {
            for (Map.Entry<Class<T>, T> entry : map.entrySet()) {
                Class<? extends T> type = entry.getKey();
                T value = entry.getValue();
                this.mapBuilder.put(type, Builder.cast(type, value));
            }
            return this;
        }

        private static <B, T extends B> T cast(Class<T> type, B value) {
            return Primitives.wrap(type).cast(value);
        }

        public ImmutableClassToInstanceMap<B> build() {
            ImmutableMap<Class<B>, B> map = this.mapBuilder.build();
            if (map.isEmpty()) {
                return ImmutableClassToInstanceMap.of();
            }
            return new ImmutableClassToInstanceMap(map);
        }
    }
}

