/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractEmptyMapIterator;

public class EmptyOrderedMapIterator<K, V>
extends AbstractEmptyMapIterator<K, V>
implements OrderedMapIterator<K, V>,
ResettableIterator<K> {
    public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();

    public static <K, V> OrderedMapIterator<K, V> emptyOrderedMapIterator() {
        return INSTANCE;
    }

    protected EmptyOrderedMapIterator() {
    }
}

