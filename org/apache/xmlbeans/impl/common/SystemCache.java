/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.lang.ref.SoftReference;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;

public class SystemCache {
    private static SystemCache INSTANCE = new SystemCache();
    private ThreadLocal tl_saxLoaders = new ThreadLocal();

    public static final synchronized void set(SystemCache instance) {
        INSTANCE = instance;
    }

    public static final SystemCache get() {
        return INSTANCE;
    }

    public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader cl) {
        return null;
    }

    public void addToTypeLoaderCache(SchemaTypeLoader stl, ClassLoader cl) {
    }

    public void clearThreadLocals() {
        this.tl_saxLoaders.remove();
    }

    public Object getSaxLoader() {
        SoftReference s = (SoftReference)this.tl_saxLoaders.get();
        if (s == null) {
            return null;
        }
        return s.get();
    }

    public void setSaxLoader(Object saxLoader) {
        this.tl_saxLoaders.set(new SoftReference<Object>(saxLoader));
    }

    static {
        String cacheClass = SystemProperties.getProperty("xmlbean.systemcacheimpl");
        Object impl = null;
        if (cacheClass != null) {
            try {
                impl = Class.forName(cacheClass).newInstance();
                if (!(impl instanceof SystemCache)) {
                    throw new ClassCastException("Value for system property \"xmlbean.systemcacheimpl\" points to a class (" + cacheClass + ") which does not derive from SystemCache");
                }
            } catch (ClassNotFoundException cnfe) {
                throw new RuntimeException("Cache class " + cacheClass + " specified by \"xmlbean.systemcacheimpl\" was not found.", cnfe);
            } catch (InstantiationException ie) {
                throw new RuntimeException("Could not instantiate class " + cacheClass + " as specified by \"xmlbean.systemcacheimpl\"." + " An empty constructor may be missing.", ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException("Could not instantiate class " + cacheClass + " as specified by \"xmlbean.systemcacheimpl\"." + " A public empty constructor may be missing.", iae);
            }
        }
        if (impl != null) {
            INSTANCE = impl;
        }
    }
}

