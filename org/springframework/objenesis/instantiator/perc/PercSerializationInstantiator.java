/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.SERIALIZATION)
public class PercSerializationInstantiator<T>
implements ObjectInstantiator<T> {
    private final Object[] typeArgs;
    private final Method newInstanceMethod;

    public PercSerializationInstantiator(Class<T> type) {
        Class<T> unserializableType = type;
        while (Serializable.class.isAssignableFrom(unserializableType)) {
            unserializableType = unserializableType.getSuperclass();
        }
        try {
            Class<?> percMethodClass = Class.forName("COM.newmonics.PercClassLoader.Method");
            this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("noArgConstruct", Class.class, Object.class, percMethodClass);
            this.newInstanceMethod.setAccessible(true);
            Class<?> percClassClass = Class.forName("COM.newmonics.PercClassLoader.PercClass");
            Method getPercClassMethod = percClassClass.getDeclaredMethod("getPercClass", Class.class);
            Object someObject = getPercClassMethod.invoke(null, unserializableType);
            Method findMethodMethod = someObject.getClass().getDeclaredMethod("findMethod", String.class);
            Object percMethod = findMethodMethod.invoke(someObject, "<init>()V");
            this.typeArgs = new Object[]{unserializableType, type, percMethod};
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }

    @Override
    public T newInstance() {
        try {
            return (T)this.newInstanceMethod.invoke(null, this.typeArgs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }
}

