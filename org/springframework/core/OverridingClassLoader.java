/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.DecoratingClassLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;

public class OverridingClassLoader
extends DecoratingClassLoader {
    public static final String[] DEFAULT_EXCLUDED_PACKAGES = new String[]{"java.", "javax.", "sun.", "oracle.", "javassist.", "org.aspectj.", "net.sf.cglib."};
    private static final String CLASS_FILE_SUFFIX = ".class";
    @Nullable
    private final ClassLoader overrideDelegate;

    public OverridingClassLoader(@Nullable ClassLoader parent) {
        this(parent, null);
    }

    public OverridingClassLoader(@Nullable ClassLoader parent, @Nullable ClassLoader overrideDelegate) {
        super(parent);
        this.overrideDelegate = overrideDelegate;
        for (String packageName : DEFAULT_EXCLUDED_PACKAGES) {
            this.excludePackage(packageName);
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (this.overrideDelegate != null && this.isEligibleForOverriding(name)) {
            return this.overrideDelegate.loadClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result;
        if (this.isEligibleForOverriding(name) && (result = this.loadClassForOverriding(name)) != null) {
            if (resolve) {
                this.resolveClass(result);
            }
            return result;
        }
        return super.loadClass(name, resolve);
    }

    protected boolean isEligibleForOverriding(String className) {
        return !this.isExcluded(className);
    }

    @Nullable
    protected Class<?> loadClassForOverriding(String name) throws ClassNotFoundException {
        byte[] bytes;
        Class<?> result = this.findLoadedClass(name);
        if (result == null && (bytes = this.loadBytesForClass(name)) != null) {
            result = this.defineClass(name, bytes, 0, bytes.length);
        }
        return result;
    }

    @Nullable
    protected byte[] loadBytesForClass(String name) throws ClassNotFoundException {
        InputStream is = this.openStreamForClass(name);
        if (is == null) {
            return null;
        }
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(is);
            return this.transformIfNecessary(name, bytes);
        } catch (IOException ex) {
            throw new ClassNotFoundException("Cannot load resource for class [" + name + "]", ex);
        }
    }

    @Nullable
    protected InputStream openStreamForClass(String name) {
        String internalName = name.replace('.', '/') + CLASS_FILE_SUFFIX;
        return this.getParent().getResourceAsStream(internalName);
    }

    protected byte[] transformIfNecessary(String name, byte[] bytes) {
        return bytes;
    }

    static {
        ClassLoader.registerAsParallelCapable();
    }
}

