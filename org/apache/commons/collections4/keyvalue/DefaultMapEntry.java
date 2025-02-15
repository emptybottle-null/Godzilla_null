/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;

public final class DefaultMapEntry<K, V>
extends AbstractMapEntry<K, V> {
    public DefaultMapEntry(K key, V value) {
        super(key, value);
    }

    public DefaultMapEntry(KeyValue<? extends K, ? extends V> pair) {
        super(pair.getKey(), pair.getValue());
    }

    public DefaultMapEntry(Map.Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }
}

