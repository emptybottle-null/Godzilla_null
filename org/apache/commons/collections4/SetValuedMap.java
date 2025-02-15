/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Set;
import org.apache.commons.collections4.MultiValuedMap;

public interface SetValuedMap<K, V>
extends MultiValuedMap<K, V> {
    @Override
    public Set<V> get(K var1);

    @Override
    public Set<V> remove(Object var1);
}

