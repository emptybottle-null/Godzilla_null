/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import com.google.common.collect.Multimap;
import java.util.Map;

@GwtCompatible
interface FilteredMultimap<K, V>
extends Multimap<K, V> {
    public Multimap<K, V> unfiltered();

    public Predicate<? super Map.Entry<K, V>> entryPredicate();
}

