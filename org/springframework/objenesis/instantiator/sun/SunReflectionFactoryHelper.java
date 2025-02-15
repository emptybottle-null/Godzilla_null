/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.sun;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.objenesis.ObjenesisException;

class SunReflectionFactoryHelper {
    SunReflectionFactoryHelper() {
    }

    public static <T> Constructor<T> newConstructorForSerialization(Class<T> type, Constructor<?> constructor) {
        Class<?> reflectionFactoryClass = SunReflectionFactoryHelper.getReflectionFactoryClass();
        Object reflectionFactory = SunReflectionFactoryHelper.createReflectionFactory(reflectionFactoryClass);
        Method newConstructorForSerializationMethod = SunReflectionFactoryHelper.getNewConstructorForSerializationMethod(reflectionFactoryClass);
        try {
            return (Constructor)newConstructorForSerializationMethod.invoke(reflectionFactory, type, constructor);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }

    private static Class<?> getReflectionFactoryClass() {
        try {
            return Class.forName("sun.reflect.ReflectionFactory");
        } catch (ClassNotFoundException e) {
            throw new ObjenesisException(e);
        }
    }

    private static Object createReflectionFactory(Class<?> reflectionFactoryClass) {
        try {
            Method method = reflectionFactoryClass.getDeclaredMethod("getReflectionFactory", new Class[0]);
            return method.invoke(null, new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }

    private static Method getNewConstructorForSerializationMethod(Class<?> reflectionFactoryClass) {
        try {
            return reflectionFactoryClass.getDeclaredMethod("newConstructorForSerialization", Class.class, Constructor.class);
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException(e);
        }
    }
}

