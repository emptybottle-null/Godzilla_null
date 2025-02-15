/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

public abstract class DelegatingToExoticInstantiator<T>
implements ObjectInstantiator<T> {
    private final ObjectInstantiator<T> wrapped;

    protected DelegatingToExoticInstantiator(String className, Class<T> type) {
        Class<ObjectInstantiator<T>> clazz = this.instantiatorClass(className);
        Constructor<ObjectInstantiator<T>> constructor = this.instantiatorConstructor(className, clazz);
        this.wrapped = this.instantiator(className, type, constructor);
    }

    private ObjectInstantiator<T> instantiator(String className, Class<T> type, Constructor<ObjectInstantiator<T>> constructor) {
        try {
            return constructor.newInstance(type);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Failed to call constructor of " + className, e);
        }
    }

    private Class<ObjectInstantiator<T>> instantiatorClass(String className) {
        try {
            Class<ObjectInstantiator<T>> clazz = Class.forName(className);
            return clazz;
        } catch (ClassNotFoundException e) {
            throw new ObjenesisException(this.getClass().getSimpleName() + " now requires objenesis-exotic to be in the classpath", e);
        }
    }

    private Constructor<ObjectInstantiator<T>> instantiatorConstructor(String className, Class<ObjectInstantiator<T>> clazz) {
        try {
            return clazz.getConstructor(Class.class);
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException("Try to find constructor taking a Class<T> in parameter on " + className + " but can't find it", e);
        }
    }

    @Override
    public T newInstance() {
        return this.wrapped.newInstance();
    }
}

