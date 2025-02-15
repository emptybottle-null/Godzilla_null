/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.cache.LocalCache;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
interface ReferenceEntry<K, V> {
    public LocalCache.ValueReference<K, V> getValueReference();

    public void setValueReference(LocalCache.ValueReference<K, V> var1);

    public @Nullable ReferenceEntry<K, V> getNext();

    public int getHash();

    public @Nullable K getKey();

    public long getAccessTime();

    public void setAccessTime(long var1);

    public ReferenceEntry<K, V> getNextInAccessQueue();

    public void setNextInAccessQueue(ReferenceEntry<K, V> var1);

    public ReferenceEntry<K, V> getPreviousInAccessQueue();

    public void setPreviousInAccessQueue(ReferenceEntry<K, V> var1);

    public long getWriteTime();

    public void setWriteTime(long var1);

    public ReferenceEntry<K, V> getNextInWriteQueue();

    public void setNextInWriteQueue(ReferenceEntry<K, V> var1);

    public ReferenceEntry<K, V> getPreviousInWriteQueue();

    public void setPreviousInWriteQueue(ReferenceEntry<K, V> var1);
}

