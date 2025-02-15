/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.mutable.Mutable;

public class MutableBoolean
implements Mutable<Boolean>,
Serializable,
Comparable<MutableBoolean> {
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;

    public MutableBoolean() {
    }

    public MutableBoolean(boolean value) {
        this.value = value;
    }

    public MutableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    public void setFalse() {
        this.value = false;
    }

    public void setTrue() {
        this.value = true;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    public boolean isTrue() {
        return this.value;
    }

    public boolean isFalse() {
        return !this.value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    public Boolean toBoolean() {
        return this.booleanValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MutableBoolean) {
            return this.value == ((MutableBoolean)obj).booleanValue();
        }
        return false;
    }

    public int hashCode() {
        return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
    }

    @Override
    public int compareTo(MutableBoolean other) {
        return BooleanUtils.compare(this.value, other.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

