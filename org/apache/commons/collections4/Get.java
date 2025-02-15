/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Get<K, V> {
    public boolean containsKey(Object var1);

    public boolean containsValue(Object var1);

    public Set<Map.Entry<K, V>> entrySet();

    public V get(Object var1);

    public V remove(Object var1);

    public boolean isEmpty();

    public Set<K> keySet();

    public int size();

    public Collection<V> values();
}

