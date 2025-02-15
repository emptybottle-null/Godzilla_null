/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.SecurityUtilities;

public class DefiningClassLoader
extends ClassLoader
implements GeneratedClassLoader {
    private final ClassLoader parentLoader;

    public DefiningClassLoader() {
        this.parentLoader = this.getClass().getClassLoader();
    }

    public DefiningClassLoader(ClassLoader parentLoader) {
        this.parentLoader = parentLoader;
    }

    @Override
    public Class<?> defineClass(String name, byte[] data) {
        return super.defineClass(name, data, 0, data.length, SecurityUtilities.getProtectionDomain(this.getClass()));
    }

    @Override
    public void linkClass(Class<?> cl) {
        this.resolveClass(cl);
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> cl = this.findLoadedClass(name);
        if (cl == null) {
            cl = this.parentLoader != null ? this.parentLoader.loadClass(name) : this.findSystemClass(name);
        }
        if (resolve) {
            this.resolveClass(cl);
        }
        return cl;
    }
}

