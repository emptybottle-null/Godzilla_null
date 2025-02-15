/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractListMultimap;
import java.util.Collection;
import java.util.Map;

@GwtCompatible(emulated=true)
abstract class ArrayListMultimapGwtSerializationDependencies<K, V>
extends AbstractListMultimap<K, V> {
    ArrayListMultimapGwtSerializationDependencies(Map<K, Collection<V>> map) {
        super(map);
    }
}

