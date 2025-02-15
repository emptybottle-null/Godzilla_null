/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis;

import org.springframework.objenesis.ObjenesisBase;
import org.springframework.objenesis.strategy.SerializingInstantiatorStrategy;

public class ObjenesisSerializer
extends ObjenesisBase {
    public ObjenesisSerializer() {
        super(new SerializingInstantiatorStrategy());
    }

    public ObjenesisSerializer(boolean useCache) {
        super(new SerializingInstantiatorStrategy(), useCache);
    }
}

