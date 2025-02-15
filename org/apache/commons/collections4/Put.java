/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Map;

public interface Put<K, V> {
    public void clear();

    public Object put(K var1, V var2);

    public void putAll(Map<? extends K, ? extends V> var1);
}

