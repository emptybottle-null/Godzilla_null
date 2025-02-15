/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.IndexedImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class ImmutableMapKeySet<K, V>
extends IndexedImmutableSet<K> {
    private final ImmutableMap<K, V> map;

    ImmutableMapKeySet(ImmutableMap<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public UnmodifiableIterator<K> iterator() {
        return this.map.keyIterator();
    }

    @Override
    public Spliterator<K> spliterator() {
        return this.map.keySpliterator();
    }

    @Override
    public boolean contains(@Nullable Object object) {
        return this.map.containsKey(object);
    }

    @Override
    K get(int index) {
        return ((Map.Entry)((ImmutableSet)this.map.entrySet()).asList().get(index)).getKey();
    }

    @Override
    public void forEach(Consumer<? super K> action) {
        Preconditions.checkNotNull(action);
        this.map.forEach((? super K k, ? super V v) -> action.accept((Object)k));
    }

    @Override
    boolean isPartialView() {
        return true;
    }

    @Override
    @GwtIncompatible
    Object writeReplace() {
        return new KeySetSerializedForm<K>(this.map);
    }

    @GwtIncompatible
    private static class KeySetSerializedForm<K>
    implements Serializable {
        final ImmutableMap<K, ?> map;
        private static final long serialVersionUID = 0L;

        KeySetSerializedForm(ImmutableMap<K, ?> map) {
            this.map = map;
        }

        Object readResolve() {
            return this.map.keySet();
        }
    }
}

