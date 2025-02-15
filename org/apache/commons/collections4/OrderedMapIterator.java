/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedIterator;

public interface OrderedMapIterator<K, V>
extends MapIterator<K, V>,
OrderedIterator<K> {
    @Override
    public boolean hasPrevious();

    @Override
    public K previous();
}

