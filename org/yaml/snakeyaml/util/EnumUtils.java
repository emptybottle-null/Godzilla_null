/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.util;

public class EnumUtils {
    public static <T extends Enum<T>> T findEnumInsensitiveCase(Class<T> enumType, String name) {
        for (Enum constant : (Enum[])enumType.getEnumConstants()) {
            if (constant.name().compareToIgnoreCase(name) != 0) continue;
            return (T)constant;
        }
        throw new IllegalArgumentException("No enum constant " + enumType.getCanonicalName() + "." + name);
    }
}

