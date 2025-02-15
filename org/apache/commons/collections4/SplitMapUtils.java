/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Get;
import org.apache.commons.collections4.IterableGet;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Put;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class SplitMapUtils {
    private SplitMapUtils() {
    }

    public static <K, V> IterableMap<K, V> readableMap(Get<K, V> get) {
        if (get == null) {
            throw new NullPointerException("Get must not be null");
        }
        if (get instanceof Map) {
            return get instanceof IterableMap ? (IterableMap)get : MapUtils.iterableMap((Map)((Object)get));
        }
        return new WrappedGet(get);
    }

    public static <K, V> Map<K, V> writableMap(Put<K, V> put) {
        if (put == null) {
            throw new NullPointerException("Put must not be null");
        }
        if (put instanceof Map) {
            return (Map)((Object)put);
        }
        return new WrappedPut(put);
    }

    private static class WrappedPut<K, V>
    implements Map<K, V>,
    Put<K, V> {
        private final Put<K, V> put;

        private WrappedPut(Put<K, V> put) {
            this.put = put;
        }

        @Override
        public void clear() {
            this.put.clear();
        }

        @Override
        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj instanceof WrappedPut && ((WrappedPut)obj).put.equals(this.put);
        }

        @Override
        public V get(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int hashCode() {
            return "WrappedPut".hashCode() << 4 | this.put.hashCode();
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<K> keySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public V put(K key, V value) {
            return (V)this.put.put(key, value);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> t) {
            this.put.putAll(t);
        }

        @Override
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<V> values() {
            throw new UnsupportedOperationException();
        }
    }

    private static class WrappedGet<K, V>
    implements IterableMap<K, V>,
    Unmodifiable {
        private final Get<K, V> get;

        private WrappedGet(Get<K, V> get) {
            this.get = get;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.get.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.get.containsValue(value);
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            return UnmodifiableEntrySet.unmodifiableEntrySet(this.get.entrySet());
        }

        @Override
        public boolean equals(Object arg0) {
            if (arg0 == this) {
                return true;
            }
            return arg0 instanceof WrappedGet && ((WrappedGet)arg0).get.equals(this.get);
        }

        @Override
        public V get(Object key) {
            return this.get.get(key);
        }

        @Override
        public int hashCode() {
            return "WrappedGet".hashCode() << 4 | this.get.hashCode();
        }

        @Override
        public boolean isEmpty() {
            return this.get.isEmpty();
        }

        @Override
        public Set<K> keySet() {
            return UnmodifiableSet.unmodifiableSet(this.get.keySet());
        }

        @Override
        public V put(K key, V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V remove(Object key) {
            return this.get.remove(key);
        }

        @Override
        public int size() {
            return this.get.size();
        }

        @Override
        public Collection<V> values() {
            return UnmodifiableCollection.unmodifiableCollection(this.get.values());
        }

        @Override
        public MapIterator<K, V> mapIterator() {
            MapIterator it = this.get instanceof IterableGet ? ((IterableGet)this.get).mapIterator() : new EntrySetToMapIteratorAdapter<K, V>(this.get.entrySet());
            return UnmodifiableMapIterator.unmodifiableMapIterator(it);
        }
    }
}

