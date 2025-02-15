/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.strategy;

import org.springframework.objenesis.instantiator.ObjectInstantiator;

public interface InstantiatorStrategy {
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> var1);
}

