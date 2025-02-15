/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.objenesis.instantiator.basic.ConstructorInstantiator;

@Instantiator(value=Typology.NOT_COMPLIANT)
public class AccessibleInstantiator<T>
extends ConstructorInstantiator<T> {
    public AccessibleInstantiator(Class<T> type) {
        super(type);
        if (this.constructor != null) {
            this.constructor.setAccessible(true);
        }
    }
}

