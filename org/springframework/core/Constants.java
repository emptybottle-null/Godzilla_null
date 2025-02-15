/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class Constants {
    private final String className;
    private final Map<String, Object> fieldCache = new HashMap<String, Object>();

    public Constants(Class<?> clazz) {
        Field[] fields;
        Assert.notNull(clazz, "Class must not be null");
        this.className = clazz.getName();
        for (Field field : fields = clazz.getFields()) {
            if (!ReflectionUtils.isPublicStaticFinal(field)) continue;
            String name = field.getName();
            try {
                Object value = field.get(null);
                this.fieldCache.put(name, value);
            } catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
    }

    public final String getClassName() {
        return this.className;
    }

    public final int getSize() {
        return this.fieldCache.size();
    }

    protected final Map<String, Object> getFieldCache() {
        return this.fieldCache;
    }

    public Number asNumber(String code) throws ConstantException {
        Object obj = this.asObject(code);
        if (!(obj instanceof Number)) {
            throw new ConstantException(this.className, code, "not a Number");
        }
        return (Number)obj;
    }

    public String asString(String code) throws ConstantException {
        return this.asObject(code).toString();
    }

    public Object asObject(String code) throws ConstantException {
        Assert.notNull((Object)code, "Code must not be null");
        String codeToUse = code.toUpperCase(Locale.ENGLISH);
        Object val = this.fieldCache.get(codeToUse);
        if (val == null) {
            throw new ConstantException(this.className, codeToUse, "not found");
        }
        return val;
    }

    public Set<String> getNames(@Nullable String namePrefix) {
        String prefixToUse = namePrefix != null ? namePrefix.trim().toUpperCase(Locale.ENGLISH) : "";
        HashSet<String> names = new HashSet<String>();
        for (String code : this.fieldCache.keySet()) {
            if (!code.startsWith(prefixToUse)) continue;
            names.add(code);
        }
        return names;
    }

    public Set<String> getNamesForProperty(String propertyName) {
        return this.getNames(this.propertyToConstantNamePrefix(propertyName));
    }

    public Set<String> getNamesForSuffix(@Nullable String nameSuffix) {
        String suffixToUse = nameSuffix != null ? nameSuffix.trim().toUpperCase(Locale.ENGLISH) : "";
        HashSet<String> names = new HashSet<String>();
        for (String code : this.fieldCache.keySet()) {
            if (!code.endsWith(suffixToUse)) continue;
            names.add(code);
        }
        return names;
    }

    public Set<Object> getValues(@Nullable String namePrefix) {
        String prefixToUse = namePrefix != null ? namePrefix.trim().toUpperCase(Locale.ENGLISH) : "";
        HashSet<Object> values = new HashSet<Object>();
        this.fieldCache.forEach((code, value) -> {
            if (code.startsWith(prefixToUse)) {
                values.add(value);
            }
        });
        return values;
    }

    public Set<Object> getValuesForProperty(String propertyName) {
        return this.getValues(this.propertyToConstantNamePrefix(propertyName));
    }

    public Set<Object> getValuesForSuffix(@Nullable String nameSuffix) {
        String suffixToUse = nameSuffix != null ? nameSuffix.trim().toUpperCase(Locale.ENGLISH) : "";
        HashSet<Object> values = new HashSet<Object>();
        this.fieldCache.forEach((code, value) -> {
            if (code.endsWith(suffixToUse)) {
                values.add(value);
            }
        });
        return values;
    }

    public String toCode(Object value, @Nullable String namePrefix) throws ConstantException {
        String prefixToUse = namePrefix != null ? namePrefix.trim().toUpperCase(Locale.ENGLISH) : "";
        for (Map.Entry<String, Object> entry : this.fieldCache.entrySet()) {
            if (!entry.getKey().startsWith(prefixToUse) || !entry.getValue().equals(value)) continue;
            return entry.getKey();
        }
        throw new ConstantException(this.className, prefixToUse, value);
    }

    public String toCodeForProperty(Object value, String propertyName) throws ConstantException {
        return this.toCode(value, this.propertyToConstantNamePrefix(propertyName));
    }

    public String toCodeForSuffix(Object value, @Nullable String nameSuffix) throws ConstantException {
        String suffixToUse = nameSuffix != null ? nameSuffix.trim().toUpperCase(Locale.ENGLISH) : "";
        for (Map.Entry<String, Object> entry : this.fieldCache.entrySet()) {
            if (!entry.getKey().endsWith(suffixToUse) || !entry.getValue().equals(value)) continue;
            return entry.getKey();
        }
        throw new ConstantException(this.className, suffixToUse, value);
    }

    public String propertyToConstantNamePrefix(String propertyName) {
        StringBuilder parsedPrefix = new StringBuilder();
        for (int i = 0; i < propertyName.length(); ++i) {
            char c = propertyName.charAt(i);
            if (Character.isUpperCase(c)) {
                parsedPrefix.append('_');
                parsedPrefix.append(c);
                continue;
            }
            parsedPrefix.append(Character.toUpperCase(c));
        }
        return parsedPrefix.toString();
    }

    public static class ConstantException
    extends IllegalArgumentException {
        public ConstantException(String className, String field, String message) {
            super("Field '" + field + "' " + message + " in class [" + className + "]");
        }

        public ConstantException(String className, String namePrefix, Object value) {
            super("No '" + namePrefix + "' field with value '" + value + "' found in class [" + className + "]");
        }
    }
}

