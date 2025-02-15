/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@GwtIncompatible
public final class Primitives {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE;

    private Primitives() {
    }

    private static void add(Map<Class<?>, Class<?>> forward, Map<Class<?>, Class<?>> backward, Class<?> key, Class<?> value) {
        forward.put(key, value);
        backward.put(value, key);
    }

    public static Set<Class<?>> allPrimitiveTypes() {
        return PRIMITIVE_TO_WRAPPER_TYPE.keySet();
    }

    public static Set<Class<?>> allWrapperTypes() {
        return WRAPPER_TO_PRIMITIVE_TYPE.keySet();
    }

    public static boolean isWrapperType(Class<?> type) {
        return WRAPPER_TO_PRIMITIVE_TYPE.containsKey(Preconditions.checkNotNull(type));
    }

    public static <T> Class<T> wrap(Class<T> type) {
        Preconditions.checkNotNull(type);
        Class<?> wrapped = PRIMITIVE_TO_WRAPPER_TYPE.get(type);
        return wrapped == null ? type : wrapped;
    }

    public static <T> Class<T> unwrap(Class<T> type) {
        Preconditions.checkNotNull(type);
        Class<?> unwrapped = WRAPPER_TO_PRIMITIVE_TYPE.get(type);
        return unwrapped == null ? type : unwrapped;
    }

    static {
        LinkedHashMap primToWrap = new LinkedHashMap(16);
        LinkedHashMap wrapToPrim = new LinkedHashMap(16);
        Primitives.add(primToWrap, wrapToPrim, Boolean.TYPE, Boolean.class);
        Primitives.add(primToWrap, wrapToPrim, Byte.TYPE, Byte.class);
        Primitives.add(primToWrap, wrapToPrim, Character.TYPE, Character.class);
        Primitives.add(primToWrap, wrapToPrim, Double.TYPE, Double.class);
        Primitives.add(primToWrap, wrapToPrim, Float.TYPE, Float.class);
        Primitives.add(primToWrap, wrapToPrim, Integer.TYPE, Integer.class);
        Primitives.add(primToWrap, wrapToPrim, Long.TYPE, Long.class);
        Primitives.add(primToWrap, wrapToPrim, Short.TYPE, Short.class);
        Primitives.add(primToWrap, wrapToPrim, Void.TYPE, Void.class);
        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
        WRAPPER_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(wrapToPrim);
    }
}

