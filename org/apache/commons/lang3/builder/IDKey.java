/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

final class IDKey {
    private final Object value;
    private final int id;

    IDKey(Object _value) {
        this.id = System.identityHashCode(_value);
        this.value = _value;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object other) {
        if (!(other instanceof IDKey)) {
            return false;
        }
        IDKey idKey = (IDKey)other;
        if (this.id != idKey.id) {
            return false;
        }
        return this.value == idKey.value;
    }
}

