/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;

@Internal
public final class GenericRecordUtil {
    private GenericRecordUtil() {
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1) {
        return Collections.unmodifiableMap(Collections.singletonMap(val1, sup1));
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2) {
        return GenericRecordUtil.getGenericProperties(val1, sup1, val2, sup2, null, null, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3) {
        return GenericRecordUtil.getGenericProperties(val1, sup1, val2, sup2, val3, sup3, null, null, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4) {
        return GenericRecordUtil.getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, null, null, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5) {
        return GenericRecordUtil.getGenericProperties(val1, sup1, val2, sup2, val3, sup3, val4, sup4, val5, sup5, null, null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String val1, Supplier<?> sup1, String val2, Supplier<?> sup2, String val3, Supplier<?> sup3, String val4, Supplier<?> sup4, String val5, Supplier<?> sup5, String val6, Supplier<?> sup6) {
        LinkedHashMap<String, Supplier> m = new LinkedHashMap<String, Supplier>();
        String[] vals = new String[]{val1, val2, val3, val4, val5, val6};
        Supplier[] sups = new Supplier[]{sup1, sup2, sup3, sup4, sup5, sup6};
        for (int i = 0; i < vals.length && vals[i] != null; ++i) {
            assert (sups[i] != null);
            if ("base".equals(vals[i])) {
                Object baseMap = sups[i].get();
                assert (baseMap instanceof Map);
                m.putAll((Map)baseMap);
                continue;
            }
            m.put(vals[i], sups[i]);
        }
        return Collections.unmodifiableMap(m);
    }

    public static <T extends Enum> Supplier<T> safeEnum(T[] values, Supplier<Number> ordinal) {
        return GenericRecordUtil.safeEnum(values, ordinal, null);
    }

    public static <T extends Enum> Supplier<T> safeEnum(T[] values, Supplier<Number> ordinal, T defaultVal) {
        int ord = ordinal.get().intValue();
        return () -> 0 <= ord && ord < values.length ? values[ord] : defaultVal;
    }

    public static Supplier<AnnotatedFlag> getBitsAsString(Supplier<Number> flags, int[] masks, String[] names) {
        return () -> new AnnotatedFlag(flags, masks, names, false);
    }

    public static Supplier<AnnotatedFlag> getEnumBitsAsString(Supplier<Number> flags, int[] masks, String[] names) {
        return () -> new AnnotatedFlag(flags, masks, names, true);
    }

    public static class AnnotatedFlag {
        private final Supplier<Number> value;
        private final Map<Integer, String> masks = new LinkedHashMap<Integer, String>();
        private final boolean exactMatch;

        AnnotatedFlag(Supplier<Number> value, int[] masks, String[] names, boolean exactMatch) {
            assert (masks.length == names.length);
            this.value = value;
            this.exactMatch = exactMatch;
            for (int i = 0; i < masks.length; ++i) {
                this.masks.put(masks[i], names[i]);
            }
        }

        public Supplier<Number> getValue() {
            return this.value;
        }

        public String getDescription() {
            int val = this.value.get().intValue();
            return this.masks.entrySet().stream().filter(e -> this.match(val, (Integer)e.getKey())).map(Map.Entry::getValue).collect(Collectors.joining(" | "));
        }

        private boolean match(int val, int mask) {
            return this.exactMatch ? val == mask : (val & mask) == mask;
        }
    }
}

