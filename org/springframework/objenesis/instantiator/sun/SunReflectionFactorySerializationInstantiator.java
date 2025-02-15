/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.SerializationInstantiatorHelper;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.sun.SunReflectionFactoryHelper;

@Instantiator(value=Typology.SERIALIZATION)
public class SunReflectionFactorySerializationInstantiator<T>
implements ObjectInstantiator<T> {
    private final Constructor<T> mungedConstructor;

    public SunReflectionFactorySerializationInstantiator(Class<T> type) {
        Constructor<T> nonSerializableAncestorConstructor;
        Class<T> nonSerializableAncestor = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);
        try {
            nonSerializableAncestorConstructor = nonSerializableAncestor.getDeclaredConstructor(null);
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException(new NotSerializableException(type + " has no suitable superclass constructor"));
        }
        this.mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(type, nonSerializableAncestorConstructor);
        this.mungedConstructor.setAccessible(true);
    }

    @Override
    public T newInstance() {
        try {
            return this.mungedConstructor.newInstance(null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}

