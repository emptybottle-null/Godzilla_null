/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

public class InstantiateTransformer<T>
implements Transformer<Class<? extends T>, T> {
    private static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
    private final Class<?>[] iParamTypes;
    private final Object[] iArgs;

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer() {
        return NO_ARG_INSTANCE;
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(Class<?>[] paramTypes, Object[] args) {
        if (paramTypes == null && args != null || paramTypes != null && args == null || paramTypes != null && args != null && paramTypes.length != args.length) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        }
        if (paramTypes == null || paramTypes.length == 0) {
            return new InstantiateTransformer<T>();
        }
        return new InstantiateTransformer<T>(paramTypes, args);
    }

    private InstantiateTransformer() {
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InstantiateTransformer(Class<?>[] paramTypes, Object[] args) {
        this.iParamTypes = paramTypes != null ? (Class[])paramTypes.clone() : null;
        this.iArgs = args != null ? (Object[])args.clone() : null;
    }

    @Override
    public T transform(Class<? extends T> input) {
        try {
            if (input == null) {
                throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a null object");
            }
            Constructor<T> con = input.getConstructor(this.iParamTypes);
            return con.newInstance(this.iArgs);
        } catch (NoSuchMethodException ex) {
            throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
        } catch (InstantiationException ex) {
            throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
        } catch (IllegalAccessException ex) {
            throw new FunctorException("InstantiateTransformer: Constructor must be public", ex);
        } catch (InvocationTargetException ex) {
            throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex);
        }
    }
}

