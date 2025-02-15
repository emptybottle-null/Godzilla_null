/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToInstanceMap;
import com.google.common.reflect.TypeToken;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;

@Beta
public final class ImmutableTypeToInstanceMap<B>
extends ForwardingMap<TypeToken<? extends B>, B>
implements TypeToInstanceMap<B> {
    private final ImmutableMap<TypeToken<? extends B>, B> delegate;

    public static <B> ImmutableTypeToInstanceMap<B> of() {
        return new ImmutableTypeToInstanceMap(ImmutableMap.of());
    }

    public static <B> Builder<B> builder() {
        return new Builder();
    }

    private ImmutableTypeToInstanceMap(ImmutableMap<TypeToken<? extends B>, B> delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T extends B> T getInstance(TypeToken<T> type) {
        return this.trustedGet(type.rejectTypeVariables());
    }

    @Override
    public <T extends B> T getInstance(Class<T> type) {
        return this.trustedGet(TypeToken.of(type));
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(TypeToken<T> type, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> type, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public B put(TypeToken<? extends B> key, B value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Map<TypeToken<? extends B>, B> delegate() {
        return this.delegate;
    }

    private <T extends B> T trustedGet(TypeToken<T> type) {
        return (T)this.delegate.get(type);
    }

    @Beta
    public static final class Builder<B> {
        private final ImmutableMap.Builder<TypeToken<? extends B>, B> mapBuilder = ImmutableMap.builder();

        private Builder() {
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(Class<T> key, T value) {
            this.mapBuilder.put(TypeToken.of(key), value);
            return this;
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(TypeToken<T> key, T value) {
            this.mapBuilder.put(key.rejectTypeVariables(), value);
            return this;
        }

        public ImmutableTypeToInstanceMap<B> build() {
            return new ImmutableTypeToInstanceMap(this.mapBuilder.build());
        }
    }
}

