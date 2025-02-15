/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Map;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class MapPropertySource
extends EnumerablePropertySource<Map<String, Object>> {
    public MapPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    @Nullable
    public Object getProperty(String name) {
        return ((Map)this.source).get(name);
    }

    @Override
    public boolean containsProperty(String name) {
        return ((Map)this.source).containsKey(name);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(((Map)this.source).keySet());
    }
}

