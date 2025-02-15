/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.cache.RemovalCause;
import java.util.AbstractMap;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class RemovalNotification<K, V>
extends AbstractMap.SimpleImmutableEntry<K, V> {
    private final RemovalCause cause;
    private static final long serialVersionUID = 0L;

    public static <K, V> RemovalNotification<K, V> create(@Nullable K key, @Nullable V value, RemovalCause cause) {
        return new RemovalNotification<K, V>(key, value, cause);
    }

    private RemovalNotification(@Nullable K key, @Nullable V value, RemovalCause cause) {
        super(key, value);
        this.cause = Preconditions.checkNotNull(cause);
    }

    public RemovalCause getCause() {
        return this.cause;
    }

    public boolean wasEvicted() {
        return this.cause.wasEvicted();
    }
}

