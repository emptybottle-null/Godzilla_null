/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;

public class InstantiateFactory<T>
implements Factory<T> {
    private final Class<T> iClassToInstantiate;
    private final Class<?>[] iParamTypes;
    private final Object[] iArgs;
    private transient Constructor<T> iConstructor = null;

    public static <T> Factory<T> instantiateFactory(Class<T> classToInstantiate, Class<?>[] paramTypes, Object[] args) {
        if (classToInstantiate == null) {
            throw new NullPointerException("Class to instantiate must not be null");
        }
        if (paramTypes == null && args != null || paramTypes != null && args == null || paramTypes != null && args != null && paramTypes.length != args.length) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        }
        if (paramTypes == null || paramTypes.length == 0) {
            return new InstantiateFactory<T>(classToInstantiate);
        }
        return new InstantiateFactory<T>(classToInstantiate, paramTypes, args);
    }

    public InstantiateFactory(Class<T> classToInstantiate) {
        this.iClassToInstantiate = classToInstantiate;
        this.iParamTypes = null;
        this.iArgs = null;
        this.findConstructor();
    }

    public InstantiateFactory(Class<T> classToInstantiate, Class<?>[] paramTypes, Object[] args) {
        this.iClassToInstantiate = classToInstantiate;
        this.iParamTypes = (Class[])paramTypes.clone();
        this.iArgs = (Object[])args.clone();
        this.findConstructor();
    }

    private void findConstructor() {
        try {
            this.iConstructor = this.iClassToInstantiate.getConstructor(this.iParamTypes);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("InstantiateFactory: The constructor must exist and be public ");
        }
    }

    @Override
    public T create() {
        if (this.iConstructor == null) {
            this.findConstructor();
        }
        try {
            return this.iConstructor.newInstance(this.iArgs);
        } catch (InstantiationException ex) {
            throw new FunctorException("InstantiateFactory: InstantiationException", ex);
        } catch (IllegalAccessException ex) {
            throw new FunctorException("InstantiateFactory: Constructor must be public", ex);
        } catch (InvocationTargetException ex) {
            throw new FunctorException("InstantiateFactory: Constructor threw an exception", ex);
        }
    }
}

