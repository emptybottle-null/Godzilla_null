/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.STANDARD)
public class PercInstantiator<T>
implements ObjectInstantiator<T> {
    private final Method newInstanceMethod;
    private final Object[] typeArgs = new Object[]{null, Boolean.FALSE};

    public PercInstantiator(Class<T> type) {
        this.typeArgs[0] = type;
        try {
            this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Boolean.TYPE);
            this.newInstanceMethod.setAccessible(true);
        } catch (NoSuchMethodException | RuntimeException e) {
            throw new ObjenesisException(e);
        }
    }

    @Override
    public T newInstance() {
        try {
            return (T)this.newInstanceMethod.invoke(null, this.typeArgs);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}

