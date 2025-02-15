/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.FinalizableReference;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public class FinalizableReferenceQueue
implements Closeable {
    private static final Logger logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
    private static final String FINALIZER_CLASS_NAME = "com.google.common.base.internal.Finalizer";
    private static final Method startFinalizer;
    final ReferenceQueue<Object> queue = new ReferenceQueue();
    final PhantomReference<Object> frqRef = new PhantomReference<Object>(this, this.queue);
    final boolean threadStarted;

    public FinalizableReferenceQueue() {
        boolean threadStarted = false;
        try {
            startFinalizer.invoke(null, FinalizableReference.class, this.queue, this.frqRef);
            threadStarted = true;
        } catch (IllegalAccessException impossible) {
            throw new AssertionError((Object)impossible);
        } catch (Throwable t) {
            logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", t);
        }
        this.threadStarted = threadStarted;
    }

    @Override
    public void close() {
        this.frqRef.enqueue();
        this.cleanUp();
    }

    void cleanUp() {
        Reference<Object> reference;
        if (this.threadStarted) {
            return;
        }
        while ((reference = this.queue.poll()) != null) {
            reference.clear();
            try {
                ((FinalizableReference)((Object)reference)).finalizeReferent();
            } catch (Throwable t) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", t);
            }
        }
    }

    private static Class<?> loadFinalizer(FinalizerLoader ... loaders) {
        for (FinalizerLoader loader : loaders) {
            Class<?> finalizer = loader.loadFinalizer();
            if (finalizer == null) continue;
            return finalizer;
        }
        throw new AssertionError();
    }

    static Method getStartFinalizer(Class<?> finalizer) {
        try {
            return finalizer.getMethod("startFinalizer", Class.class, ReferenceQueue.class, PhantomReference.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError((Object)e);
        }
    }

    static {
        Class<?> finalizer = FinalizableReferenceQueue.loadFinalizer(new SystemLoader(), new DecoupledLoader(), new DirectLoader());
        startFinalizer = FinalizableReferenceQueue.getStartFinalizer(finalizer);
    }

    static class DirectLoader
    implements FinalizerLoader {
        DirectLoader() {
        }

        @Override
        public Class<?> loadFinalizer() {
            try {
                return Class.forName(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                throw new AssertionError((Object)e);
            }
        }
    }

    static class DecoupledLoader
    implements FinalizerLoader {
        private static final String LOADING_ERROR = "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.";

        DecoupledLoader() {
        }

        @Override
        public @Nullable Class<?> loadFinalizer() {
            try {
                URLClassLoader finalizerLoader = this.newLoader(this.getBaseUrl());
                return finalizerLoader.loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (Exception e) {
                logger.log(Level.WARNING, LOADING_ERROR, e);
                return null;
            }
        }

        URL getBaseUrl() throws IOException {
            String finalizerPath = FinalizableReferenceQueue.FINALIZER_CLASS_NAME.replace('.', '/') + ".class";
            URL finalizerUrl = this.getClass().getClassLoader().getResource(finalizerPath);
            if (finalizerUrl == null) {
                throw new FileNotFoundException(finalizerPath);
            }
            String urlString = finalizerUrl.toString();
            if (!urlString.endsWith(finalizerPath)) {
                throw new IOException("Unsupported path style: " + urlString);
            }
            urlString = urlString.substring(0, urlString.length() - finalizerPath.length());
            return new URL(finalizerUrl, urlString);
        }

        URLClassLoader newLoader(URL base) {
            return new URLClassLoader(new URL[]{base}, null);
        }
    }

    static class SystemLoader
    implements FinalizerLoader {
        @VisibleForTesting
        static boolean disabled;

        SystemLoader() {
        }

        @Override
        public @Nullable Class<?> loadFinalizer() {
            ClassLoader systemLoader;
            if (disabled) {
                return null;
            }
            try {
                systemLoader = ClassLoader.getSystemClassLoader();
            } catch (SecurityException e) {
                logger.info("Not allowed to access system class loader.");
                return null;
            }
            if (systemLoader != null) {
                try {
                    return systemLoader.loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }
            return null;
        }
    }

    static interface FinalizerLoader {
        public @Nullable Class<?> loadFinalizer();
    }
}

