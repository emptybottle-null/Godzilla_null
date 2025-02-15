/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.keyvalue;

import org.apache.commons.collections4.KeyValue;

public abstract class AbstractKeyValue<K, V>
implements KeyValue<K, V> {
    private K key;
    private V value;

    protected AbstractKeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    protected K setKey(K key) {
        K old = this.key;
        this.key = key;
        return old;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    protected V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public String toString() {
        return "" + this.getKey() + '=' + this.getValue();
    }
}

