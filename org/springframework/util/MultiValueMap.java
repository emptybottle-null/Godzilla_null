/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;

public interface MultiValueMap<K, V>
extends Map<K, List<V>> {
    @Nullable
    public V getFirst(K var1);

    public void add(K var1, @Nullable V var2);

    public void addAll(K var1, List<? extends V> var2);

    public void addAll(MultiValueMap<K, V> var1);

    default public void addIfAbsent(K key, @Nullable V value) {
        if (!this.containsKey(key)) {
            this.add(key, value);
        }
    }

    public void set(K var1, @Nullable V var2);

    public void setAll(Map<K, V> var1);

    public Map<K, V> toSingleValueMap();
}

