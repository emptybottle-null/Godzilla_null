/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@FunctionalInterface
@GwtCompatible
public interface Weigher<K, V> {
    public int weigh(K var1, V var2);
}

