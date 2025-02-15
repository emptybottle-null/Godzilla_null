/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.asm.Type;

public abstract class ClassInfo {
    protected ClassInfo() {
    }

    public abstract Type getType();

    public abstract Type getSuperType();

    public abstract Type[] getInterfaces();

    public abstract int getModifiers();

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ClassInfo)) {
            return false;
        }
        return this.getType().equals(((ClassInfo)o).getType());
    }

    public int hashCode() {
        return this.getType().hashCode();
    }

    public String toString() {
        return this.getType().getClassName();
    }
}

