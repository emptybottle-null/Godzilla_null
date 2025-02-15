/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.asm.Type;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.cglib.core.Signature;

public abstract class MethodInfo {
    protected MethodInfo() {
    }

    public abstract ClassInfo getClassInfo();

    public abstract int getModifiers();

    public abstract Signature getSignature();

    public abstract Type[] getExceptionTypes();

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof MethodInfo)) {
            return false;
        }
        return this.getSignature().equals(((MethodInfo)o).getSignature());
    }

    public int hashCode() {
        return this.getSignature().hashCode();
    }

    public String toString() {
        return this.getSignature().toString();
    }
}

