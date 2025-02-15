/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.IterableMap;

public interface BoundedMap<K, V>
extends IterableMap<K, V> {
    public boolean isFull();

    public int maxSize();
}

