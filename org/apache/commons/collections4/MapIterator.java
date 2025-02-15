/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Iterator;

public interface MapIterator<K, V>
extends Iterator<K> {
    @Override
    public boolean hasNext();

    @Override
    public K next();

    public K getKey();

    public V getValue();

    @Override
    public void remove();

    public V setValue(V var1);
}

