/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Map;
import java.util.Properties;
import org.springframework.core.env.MapPropertySource;

public class PropertiesPropertySource
extends MapPropertySource {
    public PropertiesPropertySource(String name, Properties source) {
        super(name, source);
    }

    protected PropertiesPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String[] getPropertyNames() {
        Map map = (Map)this.source;
        synchronized (map) {
            return super.getPropertyNames();
        }
    }
}

