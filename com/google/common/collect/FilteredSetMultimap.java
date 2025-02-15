/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.FilteredMultimap;
import com.google.common.collect.SetMultimap;

@GwtCompatible
interface FilteredSetMultimap<K, V>
extends FilteredMultimap<K, V>,
SetMultimap<K, V> {
    @Override
    public SetMultimap<K, V> unfiltered();
}

