/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.InputStream;
import org.apache.xmlbeans.ResourceLoader;

public class ClassLoaderResourceLoader
implements ResourceLoader {
    ClassLoader _classLoader;

    public ClassLoaderResourceLoader(ClassLoader classLoader) {
        this._classLoader = classLoader;
    }

    @Override
    public InputStream getResourceAsStream(String resourceName) {
        return this._classLoader.getResourceAsStream(resourceName);
    }

    @Override
    public void close() {
    }
}

