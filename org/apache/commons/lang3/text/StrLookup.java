/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.util.Map;

@Deprecated
public abstract class StrLookup<V> {
    private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup<String>(null);
    private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = new SystemPropertiesStrLookup();

    public static StrLookup<?> noneLookup() {
        return NONE_LOOKUP;
    }

    public static StrLookup<String> systemPropertiesLookup() {
        return SYSTEM_PROPERTIES_LOOKUP;
    }

    public static <V> StrLookup<V> mapLookup(Map<String, V> map) {
        return new MapStrLookup<V>(map);
    }

    protected StrLookup() {
    }

    public abstract String lookup(String var1);

    private static class SystemPropertiesStrLookup
    extends StrLookup<String> {
        private SystemPropertiesStrLookup() {
        }

        @Override
        public String lookup(String key) {
            if (!key.isEmpty()) {
                try {
                    return System.getProperty(key);
                } catch (SecurityException securityException) {
                    // empty catch block
                }
            }
            return null;
        }
    }

    static class MapStrLookup<V>
    extends StrLookup<V> {
        private final Map<String, V> map;

        MapStrLookup(Map<String, V> map) {
            this.map = map;
        }

        @Override
        public String lookup(String key) {
            if (this.map == null) {
                return null;
            }
            V obj = this.map.get(key);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
    }
}

