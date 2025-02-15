/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis;

import org.springframework.objenesis.instantiator.ObjectInstantiator;

public interface Objenesis {
    public <T> T newInstance(Class<T> var1);

    public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> var1);
}

