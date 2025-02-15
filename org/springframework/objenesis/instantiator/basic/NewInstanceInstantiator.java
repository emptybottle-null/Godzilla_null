/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.util.ClassUtils;

@Instantiator(value=Typology.NOT_COMPLIANT)
public class NewInstanceInstantiator<T>
implements ObjectInstantiator<T> {
    private final Class<T> type;

    public NewInstanceInstantiator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T newInstance() {
        return ClassUtils.newInstance(this.type);
    }
}

