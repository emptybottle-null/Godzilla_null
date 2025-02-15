/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import org.apache.commons.collections4.IterableMap;

@Deprecated
public interface MultiMap<K, V>
extends IterableMap<K, Object> {
    public boolean removeMapping(K var1, V var2);

    @Override
    public int size();

    @Override
    public Object get(Object var1);

    @Override
    public boolean containsValue(Object var1);

    @Override
    public Object put(K var1, Object var2);

    @Override
    public Object remove(Object var1);

    @Override
    public Collection<Object> values();
}

