/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;

public interface MultiValuedMap<K, V> {
    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object var1);

    public boolean containsValue(Object var1);

    public boolean containsMapping(Object var1, Object var2);

    public Collection<V> get(K var1);

    public boolean put(K var1, V var2);

    public boolean putAll(K var1, Iterable<? extends V> var2);

    public boolean putAll(Map<? extends K, ? extends V> var1);

    public boolean putAll(MultiValuedMap<? extends K, ? extends V> var1);

    public Collection<V> remove(Object var1);

    public boolean removeMapping(Object var1, Object var2);

    public void clear();

    public Collection<Map.Entry<K, V>> entries();

    public MultiSet<K> keys();

    public Set<K> keySet();

    public Collection<V> values();

    public Map<K, Collection<V>> asMap();

    public MapIterator<K, V> mapIterator();
}

