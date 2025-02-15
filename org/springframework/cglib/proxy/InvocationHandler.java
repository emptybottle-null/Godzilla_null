/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Callback;

public interface InvocationHandler
extends Callback {
    public Object invoke(Object var1, Method var2, Object[] var3) throws Throwable;
}

