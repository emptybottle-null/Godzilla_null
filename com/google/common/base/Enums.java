/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Enums {
    @GwtIncompatible
    private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap();

    private Enums() {
    }

    @GwtIncompatible
    public static Field getField(Enum<?> enumValue) {
        Class<?> clazz = enumValue.getDeclaringClass();
        try {
            return clazz.getDeclaredField(enumValue.name());
        } catch (NoSuchFieldException impossible) {
            throw new AssertionError((Object)impossible);
        }
    }

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> enumClass, String value) {
        Preconditions.checkNotNull(enumClass);
        Preconditions.checkNotNull(value);
        return Platform.getEnumIfPresent(enumClass, value);
    }

    @GwtIncompatible
    private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> enumClass) {
        HashMap result = new HashMap();
        for (Enum enumInstance : EnumSet.allOf(enumClass)) {
            result.put(enumInstance.name(), new WeakReference<Enum>(enumInstance));
        }
        enumConstantCache.put(enumClass, result);
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @GwtIncompatible
    static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> enumClass) {
        Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> map = enumConstantCache;
        synchronized (map) {
            Map<String, WeakReference<Enum>> constants = enumConstantCache.get(enumClass);
            if (constants == null) {
                constants = Enums.populateCache(enumClass);
            }
            return constants;
        }
    }

    public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> enumClass) {
        return new StringConverter<T>(enumClass);
    }

    private static final class StringConverter<T extends Enum<T>>
    extends Converter<String, T>
    implements Serializable {
        private final Class<T> enumClass;
        private static final long serialVersionUID = 0L;

        StringConverter(Class<T> enumClass) {
            this.enumClass = Preconditions.checkNotNull(enumClass);
        }

        @Override
        protected T doForward(String value) {
            return Enum.valueOf(this.enumClass, value);
        }

        @Override
        protected String doBackward(T enumValue) {
            return ((Enum)enumValue).name();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object instanceof StringConverter) {
                StringConverter that = (StringConverter)object;
                return this.enumClass.equals(that.enumClass);
            }
            return false;
        }

        public int hashCode() {
            return this.enumClass.hashCode();
        }

        public String toString() {
            return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
        }
    }
}

