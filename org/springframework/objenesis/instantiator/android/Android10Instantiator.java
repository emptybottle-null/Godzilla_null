/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.android;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.STANDARD)
public class Android10Instantiator<T>
implements ObjectInstantiator<T> {
    private final Class<T> type;
    private final Method newStaticMethod;

    public Android10Instantiator(Class<T> type) {
        this.type = type;
        this.newStaticMethod = Android10Instantiator.getNewStaticMethod();
    }

    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.newStaticMethod.invoke(null, this.type, Object.class));
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    private static Method getNewStaticMethod() {
        try {
            Method newStaticMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
            newStaticMethod.setAccessible(true);
            return newStaticMethod;
        } catch (NoSuchMethodException | RuntimeException e) {
            throw new ObjenesisException(e);
        }
    }
}

