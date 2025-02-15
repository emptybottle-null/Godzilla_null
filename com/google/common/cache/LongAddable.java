/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface LongAddable {
    public void increment();

    public void add(long var1);

    public long sum();
}

