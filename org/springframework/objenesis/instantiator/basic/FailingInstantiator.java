/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.NOT_COMPLIANT)
public class FailingInstantiator<T>
implements ObjectInstantiator<T> {
    public FailingInstantiator(Class<T> type) {
    }

    @Override
    public T newInstance() {
        throw new ObjenesisException("Always failing");
    }
}

