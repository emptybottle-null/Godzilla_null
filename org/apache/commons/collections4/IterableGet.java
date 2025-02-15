/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.Get;
import org.apache.commons.collections4.MapIterator;

public interface IterableGet<K, V>
extends Get<K, V> {
    public MapIterator<K, V> mapIterator();
}

