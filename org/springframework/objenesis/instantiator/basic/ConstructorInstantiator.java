/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.NOT_COMPLIANT)
public class ConstructorInstantiator<T>
implements ObjectInstantiator<T> {
    protected Constructor<T> constructor;

    public ConstructorInstantiator(Class<T> type) {
        try {
            this.constructor = type.getDeclaredConstructor(null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    @Override
    public T newInstance() {
        try {
            return this.constructor.newInstance(null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}

