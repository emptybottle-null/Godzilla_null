/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;

public abstract class AbstractMapEntryDecorator<K, V>
implements Map.Entry<K, V>,
KeyValue<K, V> {
    private final Map.Entry<K, V> entry;

    public AbstractMapEntryDecorator(Map.Entry<K, V> entry) {
        if (entry == null) {
            throw new NullPointerException("Map Entry must not be null.");
        }
        this.entry = entry;
    }

    protected Map.Entry<K, V> getMapEntry() {
        return this.entry;
    }

    @Override
    public K getKey() {
        return this.entry.getKey();
    }

    @Override
    public V getValue() {
        return this.entry.getValue();
    }

    @Override
    public V setValue(V object) {
        return this.entry.setValue(object);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return this.entry.equals(object);
    }

    @Override
    public int hashCode() {
        return this.entry.hashCode();
    }

    public String toString() {
        return this.entry.toString();
    }
}

