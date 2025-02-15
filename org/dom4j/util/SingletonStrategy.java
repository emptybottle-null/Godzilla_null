/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

public interface SingletonStrategy<T> {
    public T instance();

    public void reset();

    public void setSingletonClassName(String var1);
}

