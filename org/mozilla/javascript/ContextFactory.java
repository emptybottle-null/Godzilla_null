/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.ConsString;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.DefiningClassLoader;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.xml.XMLLib;

public class ContextFactory {
    private static volatile boolean hasCustomGlobal;
    private static ContextFactory global;
    private volatile boolean sealed;
    private final Object listenersLock = new Object();
    private volatile Object listeners;
    private boolean disabledListening;
    private ClassLoader applicationClassLoader;

    public static ContextFactory getGlobal() {
        return global;
    }

    public static boolean hasExplicitGlobal() {
        return hasCustomGlobal;
    }

    public static synchronized void initGlobal(ContextFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException();
        }
        if (hasCustomGlobal) {
            throw new IllegalStateException();
        }
        hasCustomGlobal = true;
        global = factory;
    }

    public static synchronized GlobalSetter getGlobalSetter() {
        if (hasCustomGlobal) {
            throw new IllegalStateException();
        }
        hasCustomGlobal = true;
        class GlobalSetterImpl
        implements GlobalSetter {
            GlobalSetterImpl() {
            }

            @Override
            public void setContextFactoryGlobal(ContextFactory factory) {
                global = factory == null ? new ContextFactory() : factory;
            }

            @Override
            public ContextFactory getContextFactoryGlobal() {
                return global;
            }
        }
        return new GlobalSetterImpl();
    }

    protected Context makeContext() {
        return new Context(this);
    }

    protected boolean hasFeature(Context cx, int featureIndex) {
        switch (featureIndex) {
            case 1: {
                int version = cx.getLanguageVersion();
                return version == 100 || version == 110 || version == 120;
            }
            case 2: {
                return false;
            }
            case 3: {
                return true;
            }
            case 4: {
                int version = cx.getLanguageVersion();
                return version == 120;
            }
            case 5: {
                return true;
            }
            case 6: {
                int version = cx.getLanguageVersion();
                return version == 0 || version >= 160;
            }
            case 7: {
                return false;
            }
            case 8: {
                return false;
            }
            case 9: {
                return false;
            }
            case 10: {
                return false;
            }
            case 11: {
                return false;
            }
            case 12: {
                return false;
            }
            case 13: {
                return false;
            }
            case 14: {
                return true;
            }
        }
        throw new IllegalArgumentException(String.valueOf(featureIndex));
    }

    private boolean isDom3Present() {
        Class<?> nodeClass = Kit.classOrNull("org.w3c.dom.Node");
        if (nodeClass == null) {
            return false;
        }
        try {
            nodeClass.getMethod("getUserData", String.class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    protected XMLLib.Factory getE4xImplementationFactory() {
        if (this.isDom3Present()) {
            return XMLLib.Factory.create("org.mozilla.javascript.xmlimpl.XMLLibImpl");
        }
        return null;
    }

    protected GeneratedClassLoader createClassLoader(final ClassLoader parent) {
        return AccessController.doPrivileged(new PrivilegedAction<DefiningClassLoader>(){

            @Override
            public DefiningClassLoader run() {
                return new DefiningClassLoader(parent);
            }
        });
    }

    public final ClassLoader getApplicationClassLoader() {
        return this.applicationClassLoader;
    }

    public final void initApplicationClassLoader(ClassLoader loader) {
        if (loader == null) {
            throw new IllegalArgumentException("loader is null");
        }
        if (!Kit.testIfCanLoadRhinoClasses(loader)) {
            throw new IllegalArgumentException("Loader can not resolve Rhino classes");
        }
        if (this.applicationClassLoader != null) {
            throw new IllegalStateException("applicationClassLoader can only be set once");
        }
        this.checkNotSealed();
        this.applicationClassLoader = loader;
    }

    protected Object doTopCall(Callable callable, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object result = callable.call(cx, scope, thisObj, args);
        return result instanceof ConsString ? result.toString() : result;
    }

    protected void observeInstructionCount(Context cx, int instructionCount) {
    }

    protected void onContextCreated(Context cx) {
        Listener l;
        Object listeners = this.listeners;
        int i = 0;
        while ((l = (Listener)Kit.getListener(listeners, i)) != null) {
            l.contextCreated(cx);
            ++i;
        }
    }

    protected void onContextReleased(Context cx) {
        Listener l;
        Object listeners = this.listeners;
        int i = 0;
        while ((l = (Listener)Kit.getListener(listeners, i)) != null) {
            l.contextReleased(cx);
            ++i;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void addListener(Listener listener) {
        this.checkNotSealed();
        Object object = this.listenersLock;
        synchronized (object) {
            if (this.disabledListening) {
                throw new IllegalStateException();
            }
            this.listeners = Kit.addListener(this.listeners, listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void removeListener(Listener listener) {
        this.checkNotSealed();
        Object object = this.listenersLock;
        synchronized (object) {
            if (this.disabledListening) {
                throw new IllegalStateException();
            }
            this.listeners = Kit.removeListener(this.listeners, listener);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final void disableContextListening() {
        this.checkNotSealed();
        Object object = this.listenersLock;
        synchronized (object) {
            this.disabledListening = true;
            this.listeners = null;
        }
    }

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        this.checkNotSealed();
        this.sealed = true;
    }

    protected final void checkNotSealed() {
        if (this.sealed) {
            throw new IllegalStateException();
        }
    }

    public final Object call(ContextAction action) {
        return Context.call(this, action);
    }

    public Context enterContext() {
        return this.enterContext(null);
    }

    @Deprecated
    public final Context enter() {
        return this.enterContext(null);
    }

    @Deprecated
    public final void exit() {
        Context.exit();
    }

    public final Context enterContext(Context cx) {
        return Context.enter(cx, this);
    }

    static {
        global = new ContextFactory();
    }

    public static interface GlobalSetter {
        public void setContextFactoryGlobal(ContextFactory var1);

        public ContextFactory getContextFactoryGlobal();
    }

    public static interface Listener {
        public void contextCreated(Context var1);

        public void contextReleased(Context var1);
    }
}

