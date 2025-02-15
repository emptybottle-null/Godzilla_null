/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

public abstract class SecurityController {
    private static SecurityController global;

    static SecurityController global() {
        return global;
    }

    public static boolean hasGlobal() {
        return global != null;
    }

    public static void initGlobal(SecurityController controller) {
        if (controller == null) {
            throw new IllegalArgumentException();
        }
        if (global != null) {
            throw new SecurityException("Cannot overwrite already installed global SecurityController");
        }
        global = controller;
    }

    public abstract GeneratedClassLoader createClassLoader(ClassLoader var1, Object var2);

    public static GeneratedClassLoader createLoader(ClassLoader parent, Object staticDomain) {
        GeneratedClassLoader loader;
        SecurityController sc;
        Context cx = Context.getContext();
        if (parent == null) {
            parent = cx.getApplicationClassLoader();
        }
        if ((sc = cx.getSecurityController()) == null) {
            loader = cx.createClassLoader(parent);
        } else {
            Object dynamicDomain = sc.getDynamicSecurityDomain(staticDomain);
            loader = sc.createClassLoader(parent, dynamicDomain);
        }
        return loader;
    }

    public static Class<?> getStaticSecurityDomainClass() {
        SecurityController sc = Context.getContext().getSecurityController();
        return sc == null ? null : sc.getStaticSecurityDomainClassInternal();
    }

    public Class<?> getStaticSecurityDomainClassInternal() {
        return null;
    }

    public abstract Object getDynamicSecurityDomain(Object var1);

    public Object callWithDomain(Object securityDomain, Context cx, final Callable callable, Scriptable scope, final Scriptable thisObj, final Object[] args) {
        return this.execWithDomain(cx, scope, new Script(){

            @Override
            public Object exec(Context cx, Scriptable scope) {
                return callable.call(cx, scope, thisObj, args);
            }
        }, securityDomain);
    }

    @Deprecated
    public Object execWithDomain(Context cx, Scriptable scope, Script script, Object securityDomain) {
        throw new IllegalStateException("callWithDomain should be overridden");
    }
}

