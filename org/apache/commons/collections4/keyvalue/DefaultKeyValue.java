/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.AbstractKeyValue;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;

public class DefaultKeyValue<K, V>
extends AbstractKeyValue<K, V> {
    public DefaultKeyValue() {
        super(null, null);
    }

    public DefaultKeyValue(K key, V value) {
        super(key, value);
    }

    public DefaultKeyValue(KeyValue<? extends K, ? extends V> pair) {
        super(pair.getKey(), pair.getValue());
    }

    public DefaultKeyValue(Map.Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }

    @Override
    public K setKey(K key) {
        if (key == this) {
            throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a key.");
        }
        return super.setKey(key);
    }

    @Override
    public V setValue(V value) {
        if (value == this) {
            throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a value.");
        }
        return super.setValue(value);
    }

    public Map.Entry<K, V> toMapEntry() {
        return new DefaultMapEntry(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultKeyValue)) {
            return false;
        }
        DefaultKeyValue other = (DefaultKeyValue)obj;
        return (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey())) && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    public int hashCode() {
        return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
    }
}

