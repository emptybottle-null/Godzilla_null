/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.lang.Nullable;

abstract class ReadOnlySystemAttributesMap
implements Map<String, String> {
    ReadOnlySystemAttributesMap() {
    }

    @Override
    public boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    @Override
    @Nullable
    public String get(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Type of key [" + key.getClass().getName() + "] must be java.lang.String");
        }
        return this.getSystemAttribute((String)key);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    protected abstract String getSystemAttribute(String var1);

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
        return Collections.emptySet();
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return Collections.emptySet();
    }
}

