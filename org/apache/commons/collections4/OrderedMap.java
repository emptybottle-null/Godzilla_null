/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.OrderedMapIterator;

public interface OrderedMap<K, V>
extends IterableMap<K, V> {
    @Override
    public OrderedMapIterator<K, V> mapIterator();

    public K firstKey();

    public K lastKey();

    public K nextKey(K var1);

    public K previousKey(K var1);
}

