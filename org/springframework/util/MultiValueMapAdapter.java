/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

public class MultiValueMapAdapter<K, V>
implements MultiValueMap<K, V>,
Serializable {
    private final Map<K, List<V>> targetMap;

    public MultiValueMapAdapter(Map<K, List<V>> targetMap) {
        Assert.notNull(targetMap, "'targetMap' must not be null");
        this.targetMap = targetMap;
    }

    @Override
    @Nullable
    public V getFirst(K key) {
        List<V> values = this.targetMap.get(key);
        return values != null && !values.isEmpty() ? (V)values.get(0) : null;
    }

    @Override
    public void add(K key, @Nullable V value) {
        List values = this.targetMap.computeIfAbsent(key, k -> new ArrayList(1));
        values.add(value);
    }

    @Override
    public void addAll(K key, List<? extends V> values) {
        List currentValues = this.targetMap.computeIfAbsent(key, k -> new ArrayList(1));
        currentValues.addAll(values);
    }

    @Override
    public void addAll(MultiValueMap<K, V> values) {
        for (Map.Entry entry : values.entrySet()) {
            this.addAll(entry.getKey(), (List)entry.getValue());
        }
    }

    @Override
    public void set(K key, @Nullable V value) {
        ArrayList<V> values = new ArrayList<V>(1);
        values.add(value);
        this.targetMap.put(key, values);
    }

    @Override
    public void setAll(Map<K, V> values) {
        values.forEach(this::set);
    }

    @Override
    public Map<K, V> toSingleValueMap() {
        LinkedHashMap singleValueMap = CollectionUtils.newLinkedHashMap(this.targetMap.size());
        this.targetMap.forEach((key, values) -> {
            if (values != null && !values.isEmpty()) {
                singleValueMap.put(key, values.get(0));
            }
        });
        return singleValueMap;
    }

    @Override
    public int size() {
        return this.targetMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.targetMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.targetMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.targetMap.containsValue(value);
    }

    @Override
    @Nullable
    public List<V> get(Object key) {
        return this.targetMap.get(key);
    }

    @Override
    @Nullable
    public List<V> put(K key, List<V> value) {
        return this.targetMap.put(key, value);
    }

    @Override
    @Nullable
    public List<V> remove(Object key) {
        return this.targetMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<V>> map) {
        this.targetMap.putAll(map);
    }

    @Override
    public void clear() {
        this.targetMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.targetMap.keySet();
    }

    @Override
    public Collection<List<V>> values() {
        return this.targetMap.values();
    }

    @Override
    public Set<Map.Entry<K, List<V>>> entrySet() {
        return this.targetMap.entrySet();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || this.targetMap.equals(other);
    }

    @Override
    public int hashCode() {
        return this.targetMap.hashCode();
    }

    public String toString() {
        return this.targetMap.toString();
    }
}

