/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.strategy.InstantiatorStrategy;

public class ObjenesisBase
implements Objenesis {
    protected final InstantiatorStrategy strategy;
    protected ConcurrentHashMap<String, ObjectInstantiator<?>> cache;

    public ObjenesisBase(InstantiatorStrategy strategy) {
        this(strategy, true);
    }

    public ObjenesisBase(InstantiatorStrategy strategy, boolean useCache) {
        if (strategy == null) {
            throw new IllegalArgumentException("A strategy can't be null");
        }
        this.strategy = strategy;
        this.cache = useCache ? new ConcurrentHashMap() : null;
    }

    public String toString() {
        return this.getClass().getName() + " using " + this.strategy.getClass().getName() + (this.cache == null ? " without" : " with") + " caching";
    }

    @Override
    public <T> T newInstance(Class<T> clazz) {
        return this.getInstantiatorOf(clazz).newInstance();
    }

    @Override
    public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> clazz) {
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("Primitive types can't be instantiated in Java");
        }
        if (this.cache == null) {
            return this.strategy.newInstantiatorOf(clazz);
        }
        ObjectInstantiator<Object> instantiator = this.cache.get(clazz.getName());
        if (instantiator == null) {
            ObjectInstantiator<T> newInstantiator = this.strategy.newInstantiatorOf(clazz);
            instantiator = this.cache.putIfAbsent(clazz.getName(), newInstantiator);
            if (instantiator == null) {
                instantiator = newInstantiator;
            }
        }
        return instantiator;
    }
}

