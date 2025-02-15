/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis;

import org.springframework.core.SpringProperties;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.strategy.InstantiatorStrategy;
import org.springframework.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.util.ConcurrentReferenceHashMap;

public class SpringObjenesis
implements Objenesis {
    public static final String IGNORE_OBJENESIS_PROPERTY_NAME = "spring.objenesis.ignore";
    private final InstantiatorStrategy strategy;
    private final ConcurrentReferenceHashMap<Class<?>, ObjectInstantiator<?>> cache = new ConcurrentReferenceHashMap();
    private volatile Boolean worthTrying;

    public SpringObjenesis() {
        this(null);
    }

    public SpringObjenesis(InstantiatorStrategy strategy) {
        InstantiatorStrategy instantiatorStrategy = this.strategy = strategy != null ? strategy : new StdInstantiatorStrategy();
        if (SpringProperties.getFlag(IGNORE_OBJENESIS_PROPERTY_NAME)) {
            this.worthTrying = Boolean.FALSE;
        }
    }

    public boolean isWorthTrying() {
        return this.worthTrying != Boolean.FALSE;
    }

    public <T> T newInstance(Class<T> clazz, boolean useCache) {
        if (!useCache) {
            return this.newInstantiatorOf(clazz).newInstance();
        }
        return this.getInstantiatorOf(clazz).newInstance();
    }

    @Override
    public <T> T newInstance(Class<T> clazz) {
        return this.getInstantiatorOf(clazz).newInstance();
    }

    @Override
    public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> clazz) {
        ObjectInstantiator<T> newInstantiator;
        ObjectInstantiator<Object> instantiator = this.cache.get(clazz);
        if (instantiator == null && (instantiator = this.cache.putIfAbsent(clazz, newInstantiator = this.newInstantiatorOf(clazz))) == null) {
            instantiator = newInstantiator;
        }
        return instantiator;
    }

    protected <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> clazz) {
        Boolean currentWorthTrying = this.worthTrying;
        try {
            ObjectInstantiator<T> instantiator = this.strategy.newInstantiatorOf(clazz);
            if (currentWorthTrying == null) {
                this.worthTrying = Boolean.TRUE;
            }
            return instantiator;
        } catch (ObjenesisException ex) {
            Throwable cause;
            if (currentWorthTrying == null && ((cause = ex.getCause()) instanceof ClassNotFoundException || cause instanceof IllegalAccessException)) {
                this.worthTrying = Boolean.FALSE;
            }
            throw ex;
        } catch (NoClassDefFoundError err) {
            if (currentWorthTrying == null) {
                this.worthTrying = Boolean.FALSE;
            }
            throw new ObjenesisException(err);
        }
    }
}

