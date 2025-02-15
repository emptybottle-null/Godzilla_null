/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.gcj;

import java.lang.reflect.InvocationTargetException;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.gcj.GCJInstantiatorBase;

@Instantiator(value=Typology.STANDARD)
public class GCJInstantiator<T>
extends GCJInstantiatorBase<T> {
    public GCJInstantiator(Class<T> type) {
        super(type);
    }

    @Override
    public T newInstance() {
        try {
            return this.type.cast(newObjectMethod.invoke(dummyStream, this.type, Object.class));
        } catch (IllegalAccessException | RuntimeException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }
}

