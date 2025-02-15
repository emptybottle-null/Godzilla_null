/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;

public final class UnmodifiableMapEntry<K, V>
extends AbstractMapEntry<K, V>
implements Unmodifiable {
    public UnmodifiableMapEntry(K key, V value) {
        super(key, value);
    }

    public UnmodifiableMapEntry(KeyValue<? extends K, ? extends V> pair) {
        super(pair.getKey(), pair.getValue());
    }

    public UnmodifiableMapEntry(Map.Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }
}

