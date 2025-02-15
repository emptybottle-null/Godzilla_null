/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class ForwardingObject {
    protected ForwardingObject() {
    }

    protected abstract Object delegate();

    public String toString() {
        return this.delegate().toString();
    }
}

