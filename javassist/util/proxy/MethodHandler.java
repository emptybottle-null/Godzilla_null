/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.lang.reflect.Method;

public interface MethodHandler {
    public Object invoke(Object var1, Method var2, Method var3, Object[] var4) throws Throwable;
}

