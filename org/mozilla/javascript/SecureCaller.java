/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public abstract class SecureCaller {
    private static final byte[] secureCallerImplBytecode = SecureCaller.loadBytecode();
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>>();

    public abstract Object call(Callable var1, Context var2, Scriptable var3, Scriptable var4, Object[] var5);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Object callSecurely(final CodeSource codeSource, Callable callable, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        SecureCaller caller;
        Map<ClassLoader, SoftReference<SecureCaller>> classLoaderMap;
        final Thread thread = Thread.currentThread();
        final ClassLoader classLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction<Object>(){

            @Override
            public Object run() {
                return thread.getContextClassLoader();
            }
        });
        Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> map = callers;
        synchronized (map) {
            classLoaderMap = callers.get(codeSource);
            if (classLoaderMap == null) {
                classLoaderMap = new WeakHashMap<ClassLoader, SoftReference<SecureCaller>>();
                callers.put(codeSource, classLoaderMap);
            }
        }
        Map<ClassLoader, SoftReference<SecureCaller>> map2 = classLoaderMap;
        synchronized (map2) {
            SoftReference<SecureCaller> ref = classLoaderMap.get(classLoader);
            caller = ref != null ? ref.get() : null;
            if (caller == null) {
                try {
                    caller = (SecureCaller)AccessController.doPrivileged(new PrivilegedExceptionAction<Object>(){

                        @Override
                        public Object run() throws Exception {
                            Class<?> thisClass = this.getClass();
                            ClassLoader effectiveClassLoader = classLoader.loadClass(thisClass.getName()) != thisClass ? thisClass.getClassLoader() : classLoader;
                            SecureClassLoaderImpl secCl = new SecureClassLoaderImpl(effectiveClassLoader);
                            Class<?> c = secCl.defineAndLinkClass(SecureCaller.class.getName() + "Impl", secureCallerImplBytecode, codeSource);
                            return c.newInstance();
                        }
                    });
                    classLoaderMap.put(classLoader, new SoftReference<SecureCaller>(caller));
                } catch (PrivilegedActionException ex) {
                    throw new UndeclaredThrowableException(ex.getCause());
                }
            }
        }
        return caller.call(callable, cx, scope, thisObj, args);
    }

    private static byte[] loadBytecode() {
        return (byte[])AccessController.doPrivileged(new PrivilegedAction<Object>(){

            @Override
            public Object run() {
                return SecureCaller.loadBytecodePrivileged();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static byte[] loadBytecodePrivileged() {
        URL url = SecureCaller.class.getResource("SecureCallerImpl.clazz");
        try {
            InputStream in = url.openStream();
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                while (true) {
                    int r;
                    if ((r = in.read()) == -1) {
                        byte[] byArray = bout.toByteArray();
                        return byArray;
                    }
                    bout.write(r);
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    private static class SecureClassLoaderImpl
    extends SecureClassLoader {
        SecureClassLoaderImpl(ClassLoader parent) {
            super(parent);
        }

        Class<?> defineAndLinkClass(String name, byte[] bytes, CodeSource cs) {
            Class<?> cl = this.defineClass(name, bytes, 0, bytes.length, cs);
            this.resolveClass(cl);
            return cl;
        }
    }
}

