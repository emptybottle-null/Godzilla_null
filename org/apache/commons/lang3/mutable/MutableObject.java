/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import org.apache.commons.lang3.mutable.Mutable;

public class MutableObject<T>
implements Mutable<T>,
Serializable {
    private static final long serialVersionUID = 86241875189L;
    private T value;

    public MutableObject() {
    }

    public MutableObject(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            MutableObject that = (MutableObject)obj;
            return this.value.equals(that.value);
        }
        return false;
    }

    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }

    public String toString() {
        return this.value == null ? "null" : this.value.toString();
    }
}

