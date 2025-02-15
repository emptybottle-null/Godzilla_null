/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.keyvalue.AbstractKeyValue;

public abstract class AbstractMapEntry<K, V>
extends AbstractKeyValue<K, V>
implements Map.Entry<K, V> {
    protected AbstractMapEntry(K key, V value) {
        super(key, value);
    }

    @Override
    public V setValue(V value) {
        return super.setValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry other = (Map.Entry)obj;
        return (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey())) && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    @Override
    public int hashCode() {
        return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
    }
}

