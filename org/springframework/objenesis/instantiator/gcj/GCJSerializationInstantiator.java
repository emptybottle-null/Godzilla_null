/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.gcj;

import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.SerializationInstantiatorHelper;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.gcj.GCJInstantiatorBase;

@Instantiator(value=Typology.SERIALIZATION)
public class GCJSerializationInstantiator<T>
extends GCJInstantiatorBase<T> {
    private final Class<? super T> superType;

    public GCJSerializationInstantiator(Class<T> type) {
        super(type);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);
    }

    @Override
    public T newInstance() {
        try {
            return this.type.cast(newObjectMethod.invoke(dummyStream, this.type, this.superType));
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}

