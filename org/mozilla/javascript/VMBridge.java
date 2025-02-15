/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Member;
import java.util.Iterator;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.InterfaceAdapter;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;

public abstract class VMBridge {
    static final VMBridge instance = VMBridge.makeInstance();

    private static VMBridge makeInstance() {
        String[] classNames = new String[]{"org.mozilla.javascript.VMBridge_custom", "org.mozilla.javascript.jdk15.VMBridge_jdk15", "org.mozilla.javascript.jdk13.VMBridge_jdk13", "org.mozilla.javascript.jdk11.VMBridge_jdk11"};
        for (int i = 0; i != classNames.length; ++i) {
            VMBridge bridge;
            String className = classNames[i];
            Class<?> cl = Kit.classOrNull(className);
            if (cl == null || (bridge = (VMBridge)Kit.newInstanceOrNull(cl)) == null) continue;
            return bridge;
        }
        throw new IllegalStateException("Failed to create VMBridge instance");
    }

    protected abstract Object getThreadContextHelper();

    protected abstract Context getContext(Object var1);

    protected abstract void setContext(Object var1, Context var2);

    protected abstract ClassLoader getCurrentThreadClassLoader();

    protected abstract boolean tryToMakeAccessible(Object var1);

    protected Object getInterfaceProxyHelper(ContextFactory cf, Class<?>[] interfaces) {
        throw Context.reportRuntimeError("VMBridge.getInterfaceProxyHelper is not supported");
    }

    protected Object newInterfaceProxy(Object proxyHelper, ContextFactory cf, InterfaceAdapter adapter, Object target, Scriptable topScope) {
        throw Context.reportRuntimeError("VMBridge.newInterfaceProxy is not supported");
    }

    protected abstract boolean isVarArgs(Member var1);

    public Iterator<?> getJavaIterator(Context cx, Scriptable scope, Object obj) {
        if (obj instanceof Wrapper) {
            Object unwrapped = ((Wrapper)obj).unwrap();
            Iterator iterator = null;
            if (unwrapped instanceof Iterator) {
                iterator = (Iterator)unwrapped;
            }
            return iterator;
        }
        return null;
    }
}

