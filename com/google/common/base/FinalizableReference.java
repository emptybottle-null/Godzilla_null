/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
public interface FinalizableReference {
    public void finalizeReferent();
}

