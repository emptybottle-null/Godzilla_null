/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

public abstract class DataKey<T> {
    public int hashCode() {
        return this.getClass().hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && this.getClass().equals(obj.getClass());
    }
}

