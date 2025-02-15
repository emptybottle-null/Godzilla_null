/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class AbstractInvocationHandler
implements InvocationHandler {
    private static final Object[] NO_ARGS = new Object[0];

    @Override
    public final Object invoke(Object proxy, Method method, Object @Nullable [] args) throws Throwable {
        if (args == null) {
            args = NO_ARGS;
        }
        if (args.length == 0 && method.getName().equals("hashCode")) {
            return this.hashCode();
        }
        if (args.length == 1 && method.getName().equals("equals") && method.getParameterTypes()[0] == Object.class) {
            Object arg = args[0];
            if (arg == null) {
                return false;
            }
            if (proxy == arg) {
                return true;
            }
            return AbstractInvocationHandler.isProxyOfSameInterfaces(arg, proxy.getClass()) && this.equals(Proxy.getInvocationHandler(arg));
        }
        if (args.length == 0 && method.getName().equals("toString")) {
            return this.toString();
        }
        return this.handleInvocation(proxy, method, args);
    }

    protected abstract Object handleInvocation(Object var1, Method var2, Object[] var3) throws Throwable;

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    private static boolean isProxyOfSameInterfaces(Object arg, Class<?> proxyClass) {
        return proxyClass.isInstance(arg) || Proxy.isProxyClass(arg.getClass()) && Arrays.equals(arg.getClass().getInterfaces(), proxyClass.getInterfaces());
    }
}

