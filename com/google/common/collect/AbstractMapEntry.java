/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractMapEntry<K, V>
implements Map.Entry<K, V> {
    AbstractMapEntry() {
    }

    @Override
    public abstract K getKey();

    @Override
    public abstract V getValue();

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object instanceof Map.Entry) {
            Map.Entry that = (Map.Entry)object;
            return Objects.equal(this.getKey(), that.getKey()) && Objects.equal(this.getValue(), that.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        K k = this.getKey();
        V v = this.getValue();
        return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
    }

    public String toString() {
        return this.getKey() + "=" + this.getValue();
    }
}

