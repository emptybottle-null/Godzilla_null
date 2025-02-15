/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public class ConfigurableObjectInputStream
extends ObjectInputStream {
    @Nullable
    private final ClassLoader classLoader;
    private final boolean acceptProxyClasses;

    public ConfigurableObjectInputStream(InputStream in, @Nullable ClassLoader classLoader) throws IOException {
        this(in, classLoader, true);
    }

    public ConfigurableObjectInputStream(InputStream in, @Nullable ClassLoader classLoader, boolean acceptProxyClasses) throws IOException {
        super(in);
        this.classLoader = classLoader;
        this.acceptProxyClasses = acceptProxyClasses;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass classDesc) throws IOException, ClassNotFoundException {
        try {
            if (this.classLoader != null) {
                return ClassUtils.forName(classDesc.getName(), this.classLoader);
            }
            return super.resolveClass(classDesc);
        } catch (ClassNotFoundException ex) {
            return this.resolveFallbackIfPossible(classDesc.getName(), ex);
        }
    }

    @Override
    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
        if (!this.acceptProxyClasses) {
            throw new NotSerializableException("Not allowed to accept serialized proxy classes");
        }
        if (this.classLoader != null) {
            Class[] resolvedInterfaces = new Class[interfaces.length];
            for (int i = 0; i < interfaces.length; ++i) {
                try {
                    resolvedInterfaces[i] = ClassUtils.forName(interfaces[i], this.classLoader);
                    continue;
                } catch (ClassNotFoundException ex) {
                    resolvedInterfaces[i] = this.resolveFallbackIfPossible(interfaces[i], ex);
                }
            }
            try {
                return ClassUtils.createCompositeInterface(resolvedInterfaces, this.classLoader);
            } catch (IllegalArgumentException ex) {
                throw new ClassNotFoundException(null, ex);
            }
        }
        try {
            return super.resolveProxyClass(interfaces);
        } catch (ClassNotFoundException ex) {
            Class[] resolvedInterfaces = new Class[interfaces.length];
            for (int i = 0; i < interfaces.length; ++i) {
                resolvedInterfaces[i] = this.resolveFallbackIfPossible(interfaces[i], ex);
            }
            return ClassUtils.createCompositeInterface(resolvedInterfaces, this.getFallbackClassLoader());
        }
    }

    protected Class<?> resolveFallbackIfPossible(String className, ClassNotFoundException ex) throws IOException, ClassNotFoundException {
        throw ex;
    }

    @Nullable
    protected ClassLoader getFallbackClassLoader() throws IOException {
        return null;
    }
}

