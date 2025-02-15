/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.RootDoc
 */
package org.apache.xmlbeans.impl.jam.internal.javadoc;

import com.sun.javadoc.RootDoc;
import java.lang.reflect.Method;

public class JavadocResults {
    private static final JavadocResults INSTANCE = new JavadocResults();
    private ThreadLocal mRootsPerThread = new ThreadLocal();

    public static void prepare() {
        Thread.currentThread().setContextClassLoader(JavadocResults.class.getClassLoader());
    }

    public static void setRoot(RootDoc root) {
        try {
            Object holder = JavadocResults.getHolder();
            Method setter = holder.getClass().getMethod("_setRoot", RootDoc.class);
            setter.invoke(holder, root);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static RootDoc getRoot() {
        try {
            Object holder = JavadocResults.getHolder();
            Method getter = holder.getClass().getMethod("_getRoot", new Class[0]);
            return (RootDoc)getter.invoke(holder, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void _setRoot(RootDoc root) {
        this.mRootsPerThread.set(root);
    }

    public RootDoc _getRoot() {
        return (RootDoc)this.mRootsPerThread.get();
    }

    public static JavadocResults getInstance() {
        return INSTANCE;
    }

    private static Object getHolder() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = classLoader.loadClass(JavadocResults.class.getName());
        Method method = clazz.getMethod("getInstance", new Class[0]);
        return method.invoke(null, new Object[0]);
    }
}

