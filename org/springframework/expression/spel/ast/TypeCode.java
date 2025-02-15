/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

public enum TypeCode {
    OBJECT(Object.class),
    BOOLEAN(Boolean.TYPE),
    BYTE(Byte.TYPE),
    CHAR(Character.TYPE),
    DOUBLE(Double.TYPE),
    FLOAT(Float.TYPE),
    INT(Integer.TYPE),
    LONG(Long.TYPE),
    SHORT(Short.TYPE);

    private Class<?> type;

    private TypeCode(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return this.type;
    }

    public static TypeCode forName(String name) {
        TypeCode[] tcs = TypeCode.values();
        for (int i = 1; i < tcs.length; ++i) {
            if (!tcs[i].name().equalsIgnoreCase(name)) continue;
            return tcs[i];
        }
        return OBJECT;
    }

    public static TypeCode forClass(Class<?> clazz) {
        TypeCode[] allValues;
        for (TypeCode typeCode : allValues = TypeCode.values()) {
            if (clazz != typeCode.getType()) continue;
            return typeCode;
        }
        return OBJECT;
    }
}

