/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

public class LinkedCaseInsensitiveMap<V>
implements Map<String, V>,
Serializable,
Cloneable {
    private final LinkedHashMap<String, V> targetMap;
    private final HashMap<String, String> caseInsensitiveKeys;
    private final Locale locale;
    @Nullable
    private volatile transient Set<String> keySet;
    @Nullable
    private volatile transient Collection<V> values;
    @Nullable
    private volatile transient Set<Map.Entry<String, V>> entrySet;

    public LinkedCaseInsensitiveMap() {
        this((Locale)null);
    }

    public LinkedCaseInsensitiveMap(@Nullable Locale locale) {
        this(12, locale);
    }

    public LinkedCaseInsensitiveMap(int expectedSize) {
        this(expectedSize, null);
    }

    public LinkedCaseInsensitiveMap(int expectedSize, @Nullable Locale locale) {
        this.targetMap = new LinkedHashMap<String, V>((int)((float)expectedSize / 0.75f), 0.75f){

            @Override
            public boolean containsKey(Object key) {
                return LinkedCaseInsensitiveMap.this.containsKey(key);
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, V> eldest) {
                boolean doRemove = LinkedCaseInsensitiveMap.this.removeEldestEntry(eldest);
                if (doRemove) {
                    LinkedCaseInsensitiveMap.this.removeCaseInsensitiveKey(eldest.getKey());
                }
                return doRemove;
            }
        };
        this.caseInsensitiveKeys = CollectionUtils.newHashMap(expectedSize);
        this.locale = locale != null ? locale : Locale.getDefault();
    }

    private LinkedCaseInsensitiveMap(LinkedCaseInsensitiveMap<V> other) {
        this.targetMap = (LinkedHashMap)other.targetMap.clone();
        this.caseInsensitiveKeys = (HashMap)other.caseInsensitiveKeys.clone();
        this.locale = other.locale;
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
        return key instanceof String && this.caseInsensitiveKeys.containsKey(this.convertKey((String)key));
    }

    @Override
    public boolean containsValue(Object value) {
        return this.targetMap.containsValue(value);
    }

    @Override
    @Nullable
    public V get(Object key) {
        String caseInsensitiveKey;
        if (key instanceof String && (caseInsensitiveKey = this.caseInsensitiveKeys.get(this.convertKey((String)key))) != null) {
            return this.targetMap.get(caseInsensitiveKey);
        }
        return null;
    }

    @Override
    @Nullable
    public V getOrDefault(Object key, V defaultValue) {
        String caseInsensitiveKey;
        if (key instanceof String && (caseInsensitiveKey = this.caseInsensitiveKeys.get(this.convertKey((String)key))) != null) {
            return this.targetMap.get(caseInsensitiveKey);
        }
        return defaultValue;
    }

    @Override
    @Nullable
    public V put(String key, @Nullable V value) {
        String oldKey = this.caseInsensitiveKeys.put(this.convertKey(key), key);
        V oldKeyValue = null;
        if (oldKey != null && !oldKey.equals(key)) {
            oldKeyValue = this.targetMap.remove(oldKey);
        }
        V oldValue = this.targetMap.put(key, value);
        return oldKeyValue != null ? oldKeyValue : (V)oldValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        if (map.isEmpty()) {
            return;
        }
        map.forEach(this::put);
    }

    @Override
    @Nullable
    public V putIfAbsent(String key, @Nullable V value) {
        String oldKey = this.caseInsensitiveKeys.putIfAbsent(this.convertKey(key), key);
        if (oldKey != null) {
            V oldKeyValue = this.targetMap.get(oldKey);
            if (oldKeyValue != null) {
                return oldKeyValue;
            }
            key = oldKey;
        }
        return this.targetMap.putIfAbsent(key, value);
    }

    @Override
    @Nullable
    public V computeIfAbsent(String key, Function<? super String, ? extends V> mappingFunction) {
        String oldKey = this.caseInsensitiveKeys.putIfAbsent(this.convertKey(key), key);
        if (oldKey != null) {
            V oldKeyValue = this.targetMap.get(oldKey);
            if (oldKeyValue != null) {
                return oldKeyValue;
            }
            key = oldKey;
        }
        return this.targetMap.computeIfAbsent(key, mappingFunction);
    }

    @Override
    @Nullable
    public V remove(Object key) {
        String caseInsensitiveKey;
        if (key instanceof String && (caseInsensitiveKey = this.removeCaseInsensitiveKey((String)key)) != null) {
            return this.targetMap.remove(caseInsensitiveKey);
        }
        return null;
    }

    @Override
    public void clear() {
        this.caseInsensitiveKeys.clear();
        this.targetMap.clear();
    }

    @Override
    public Set<String> keySet() {
        KeySet keySet = this.keySet;
        if (keySet == null) {
            this.keySet = keySet = new KeySet(this.targetMap.keySet());
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Values values = this.values;
        if (values == null) {
            this.values = values = new Values(this.targetMap.values());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        EntrySet entrySet = this.entrySet;
        if (entrySet == null) {
            this.entrySet = entrySet = new EntrySet(this.targetMap.entrySet());
        }
        return entrySet;
    }

    public LinkedCaseInsensitiveMap<V> clone() {
        return new LinkedCaseInsensitiveMap<V>(this);
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

    public Locale getLocale() {
        return this.locale;
    }

    protected String convertKey(String key) {
        return key.toLowerCase(this.getLocale());
    }

    protected boolean removeEldestEntry(Map.Entry<String, V> eldest) {
        return false;
    }

    @Nullable
    private String removeCaseInsensitiveKey(String key) {
        return this.caseInsensitiveKeys.remove(this.convertKey(key));
    }

    private class EntrySetIterator
    extends EntryIterator<Map.Entry<String, V>> {
        private EntrySetIterator() {
        }

        @Override
        public Map.Entry<String, V> next() {
            return this.nextEntry();
        }
    }

    private class ValuesIterator
    extends EntryIterator<V> {
        private ValuesIterator() {
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    private class KeySetIterator
    extends EntryIterator<String> {
        private KeySetIterator() {
        }

        @Override
        public String next() {
            return this.nextEntry().getKey();
        }
    }

    private abstract class EntryIterator<T>
    implements Iterator<T> {
        private final Iterator<Map.Entry<String, V>> delegate;
        @Nullable
        private Map.Entry<String, V> last;

        public EntryIterator() {
            this.delegate = LinkedCaseInsensitiveMap.this.targetMap.entrySet().iterator();
        }

        protected Map.Entry<String, V> nextEntry() {
            Map.Entry entry = this.delegate.next();
            this.last = entry;
            return entry;
        }

        @Override
        public boolean hasNext() {
            return this.delegate.hasNext();
        }

        @Override
        public void remove() {
            this.delegate.remove();
            if (this.last != null) {
                LinkedCaseInsensitiveMap.this.removeCaseInsensitiveKey(this.last.getKey());
                this.last = null;
            }
        }
    }

    private class EntrySet
    extends AbstractSet<Map.Entry<String, V>> {
        private final Set<Map.Entry<String, V>> delegate;

        public EntrySet(Set<Map.Entry<String, V>> delegate) {
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return this.delegate.size();
        }

        @Override
        public boolean contains(Object o) {
            return this.delegate.contains(o);
        }

        @Override
        public Iterator<Map.Entry<String, V>> iterator() {
            return new EntrySetIterator();
        }

        @Override
        public boolean remove(Object o) {
            if (this.delegate.remove(o)) {
                LinkedCaseInsensitiveMap.this.removeCaseInsensitiveKey((String)((Map.Entry)o).getKey());
                return true;
            }
            return false;
        }

        @Override
        public void clear() {
            this.delegate.clear();
            LinkedCaseInsensitiveMap.this.caseInsensitiveKeys.clear();
        }

        @Override
        public Spliterator<Map.Entry<String, V>> spliterator() {
            return this.delegate.spliterator();
        }

        @Override
        public void forEach(Consumer<? super Map.Entry<String, V>> action) {
            this.delegate.forEach(action);
        }
    }

    private class Values
    extends AbstractCollection<V> {
        private final Collection<V> delegate;

        Values(Collection<V> delegate) {
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return this.delegate.size();
        }

        @Override
        public boolean contains(Object o) {
            return this.delegate.contains(o);
        }

        @Override
        public Iterator<V> iterator() {
            return new ValuesIterator();
        }

        @Override
        public void clear() {
            LinkedCaseInsensitiveMap.this.clear();
        }

        @Override
        public Spliterator<V> spliterator() {
            return this.delegate.spliterator();
        }

        @Override
        public void forEach(Consumer<? super V> action) {
            this.delegate.forEach(action);
        }
    }

    private class KeySet
    extends AbstractSet<String> {
        private final Set<String> delegate;

        KeySet(Set<String> delegate) {
            this.delegate = delegate;
        }

        @Override
        public int size() {
            return this.delegate.size();
        }

        @Override
        public boolean contains(Object o) {
            return this.delegate.contains(o);
        }

        @Override
        public Iterator<String> iterator() {
            return new KeySetIterator();
        }

        @Override
        public boolean remove(Object o) {
            return LinkedCaseInsensitiveMap.this.remove(o) != null;
        }

        @Override
        public void clear() {
            LinkedCaseInsensitiveMap.this.clear();
        }

        @Override
        public Spliterator<String> spliterator() {
            return this.delegate.spliterator();
        }

        @Override
        public void forEach(Consumer<? super String> action) {
            this.delegate.forEach(action);
        }
    }
}

