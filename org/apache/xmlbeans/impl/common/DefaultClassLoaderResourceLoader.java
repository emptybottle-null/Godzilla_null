/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.InputStream;
import org.apache.xmlbeans.ResourceLoader;

public class DefaultClassLoaderResourceLoader
implements ResourceLoader {
    @Override
    public InputStream getResourceAsStream(String resourceName) {
        InputStream in = null;
        try {
            in = this.getResourceAsStream(Thread.currentThread().getContextClassLoader(), resourceName);
        } catch (SecurityException securityException) {
            // empty catch block
        }
        if (in == null) {
            in = this.getResourceAsStream(DefaultClassLoaderResourceLoader.class.getClassLoader(), resourceName);
        }
        if (in == null) {
            in = DefaultClassLoaderResourceLoader.class.getResourceAsStream(resourceName);
        }
        return in;
    }

    @Override
    public void close() {
    }

    private InputStream getResourceAsStream(ClassLoader loader, String resourceName) {
        return loader == null ? null : loader.getResourceAsStream(resourceName);
    }
}

