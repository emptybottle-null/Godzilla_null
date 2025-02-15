/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.apache.commons.collections4.functors.ExceptionFactory;
import org.apache.commons.collections4.functors.InstantiateFactory;
import org.apache.commons.collections4.functors.PrototypeFactory;

public class FactoryUtils {
    private FactoryUtils() {
    }

    public static <T> Factory<T> exceptionFactory() {
        return ExceptionFactory.exceptionFactory();
    }

    public static <T> Factory<T> nullFactory() {
        return ConstantFactory.constantFactory(null);
    }

    public static <T> Factory<T> constantFactory(T constantToReturn) {
        return ConstantFactory.constantFactory(constantToReturn);
    }

    public static <T> Factory<T> prototypeFactory(T prototype) {
        return PrototypeFactory.prototypeFactory(prototype);
    }

    public static <T> Factory<T> instantiateFactory(Class<T> classToInstantiate) {
        return InstantiateFactory.instantiateFactory(classToInstantiate, null, null);
    }

    public static <T> Factory<T> instantiateFactory(Class<T> classToInstantiate, Class<?>[] paramTypes, Object[] args) {
        return InstantiateFactory.instantiateFactory(classToInstantiate, paramTypes, args);
    }
}

