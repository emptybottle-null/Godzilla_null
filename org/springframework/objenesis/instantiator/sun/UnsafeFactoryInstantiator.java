/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.sun;

import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

@Instantiator(value=Typology.STANDARD)
public class UnsafeFactoryInstantiator<T>
implements ObjectInstantiator<T> {
    private final Unsafe unsafe = UnsafeUtils.getUnsafe();
    private final Class<T> type;

    public UnsafeFactoryInstantiator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.unsafe.allocateInstance(this.type));
        } catch (InstantiationException e) {
            throw new ObjenesisException(e);
        }
    }
}

