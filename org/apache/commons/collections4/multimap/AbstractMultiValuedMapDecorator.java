/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;

public abstract class AbstractMultiValuedMapDecorator<K, V>
implements MultiValuedMap<K, V>,
Serializable {
    private static final long serialVersionUID = 20150612L;
    private final MultiValuedMap<K, V> map;

    protected AbstractMultiValuedMapDecorator(MultiValuedMap<K, V> map) {
        if (map == null) {
            throw new NullPointerException("MultiValuedMap must not be null.");
        }
        this.map = map;
    }

    protected MultiValuedMap<K, V> decorated() {
        return this.map;
    }

    @Override
    public int size() {
        return this.decorated().size();
    }

    @Override
    public boolean isEmpty() {
        return this.decorated().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.decorated().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.decorated().containsValue(value);
    }

    @Override
    public boolean containsMapping(Object key, Object value) {
        return this.decorated().containsMapping(key, value);
    }

    @Override
    public Collection<V> get(K key) {
        return this.decorated().get(key);
    }

    @Override
    public Collection<V> remove(Object key) {
        return this.decorated().remove(key);
    }

    @Override
    public boolean removeMapping(Object key, Object item) {
        return this.decorated().removeMapping(key, item);
    }

    @Override
    public void clear() {
        this.decorated().clear();
    }

    @Override
    public boolean put(K key, V value) {
        return this.decorated().put(key, value);
    }

    @Override
    public Set<K> keySet() {
        return this.decorated().keySet();
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        return this.decorated().entries();
    }

    @Override
    public MultiSet<K> keys() {
        return this.decorated().keys();
    }

    @Override
    public Collection<V> values() {
        return this.decorated().values();
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return this.decorated().asMap();
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        return this.decorated().putAll(key, values);
    }

    @Override
    public boolean putAll(Map<? extends K, ? extends V> map) {
        return this.decorated().putAll(map);
    }

    @Override
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> map) {
        return this.decorated().putAll(map);
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return this.decorated().mapIterator();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return this.decorated().equals(object);
    }

    public int hashCode() {
        return this.decorated().hashCode();
    }

    public String toString() {
        return this.decorated().toString();
    }
}

