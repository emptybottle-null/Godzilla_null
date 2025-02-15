/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import org.dom4j.util.SingletonStrategy;

public class SimpleSingleton<T>
implements SingletonStrategy<T> {
    private String singletonClassName = null;
    private T singletonInstance = null;

    @Override
    public T instance() {
        return this.singletonInstance;
    }

    @Override
    public void reset() {
        if (this.singletonClassName != null) {
            try {
                Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
                this.singletonInstance = clazz.newInstance();
            } catch (Exception ignore) {
                try {
                    Class<?> clazz = Class.forName(this.singletonClassName);
                    this.singletonInstance = clazz.newInstance();
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    @Override
    public void setSingletonClassName(String singletonClassName) {
        this.singletonClassName = singletonClassName;
        this.reset();
    }
}

