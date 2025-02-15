/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import java.util.HashMap;
import java.util.Map;

public class ClassUtils {
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap();
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isPrimitive() || ClassUtils.isPrimitiveWrapper(type);
    }

    public static boolean isPrimitiveWrapper(Class<?> type) {
        return wrapperPrimitiveMap.containsKey(type);
    }

    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        for (Class<?> primitiveClass : primitiveWrapperMap.keySet()) {
            Class<?> wrapperClass;
            if (primitiveClass.equals(wrapperClass = primitiveWrapperMap.get(primitiveClass))) continue;
            wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
        }
    }
}

