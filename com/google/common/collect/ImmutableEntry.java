/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractMapEntry;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
class ImmutableEntry<K, V>
extends AbstractMapEntry<K, V>
implements Serializable {
    final @Nullable K key;
    final @Nullable V value;
    private static final long serialVersionUID = 0L;

    ImmutableEntry(@Nullable K key, @Nullable V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final @Nullable K getKey() {
        return this.key;
    }

    @Override
    public final @Nullable V getValue() {
        return this.value;
    }

    @Override
    public final V setValue(V value) {
        throw new UnsupportedOperationException();
    }
}

