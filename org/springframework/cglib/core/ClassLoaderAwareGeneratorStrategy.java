/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.DefaultGeneratorStrategy;

public class ClassLoaderAwareGeneratorStrategy
extends DefaultGeneratorStrategy {
    private final ClassLoader classLoader;

    public ClassLoaderAwareGeneratorStrategy(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] generate(ClassGenerator cg) throws Exception {
        boolean overrideClassLoader;
        ClassLoader threadContextClassLoader;
        if (this.classLoader == null) {
            return super.generate(cg);
        }
        Thread currentThread = Thread.currentThread();
        try {
            threadContextClassLoader = currentThread.getContextClassLoader();
        } catch (Throwable ex) {
            return super.generate(cg);
        }
        boolean bl = overrideClassLoader = !this.classLoader.equals(threadContextClassLoader);
        if (overrideClassLoader) {
            currentThread.setContextClassLoader(this.classLoader);
        }
        try {
            byte[] byArray = super.generate(cg);
            return byArray;
        } finally {
            if (overrideClassLoader) {
                currentThread.setContextClassLoader(threadContextClassLoader);
            }
        }
    }
}

