/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import java.lang.ref.WeakReference;
import org.dom4j.util.SingletonStrategy;

public class PerThreadSingleton<T>
implements SingletonStrategy<T> {
    private String singletonClassName = null;
    private ThreadLocal<WeakReference<T>> perThreadCache = new ThreadLocal();

    @Override
    public void reset() {
        this.perThreadCache = new ThreadLocal();
    }

    @Override
    public T instance() {
        Object singletonInstancePerThread = null;
        WeakReference<T> ref = this.perThreadCache.get();
        if (ref == null || ref.get() == null) {
            Class<?> clazz = null;
            try {
                clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
                singletonInstancePerThread = clazz.newInstance();
            } catch (Exception ignore) {
                try {
                    clazz = Class.forName(this.singletonClassName);
                    singletonInstancePerThread = clazz.newInstance();
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            this.perThreadCache.set(new WeakReference<Object>(singletonInstancePerThread));
        } else {
            singletonInstancePerThread = ref.get();
        }
        return singletonInstancePerThread;
    }

    @Override
    public void setSingletonClassName(String singletonClassName) {
        this.singletonClassName = singletonClassName;
    }
}

