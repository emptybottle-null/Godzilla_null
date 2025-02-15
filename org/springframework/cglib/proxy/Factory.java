/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import org.springframework.cglib.proxy.Callback;

public interface Factory {
    public Object newInstance(Callback var1);

    public Object newInstance(Callback[] var1);

    public Object newInstance(Class[] var1, Object[] var2, Callback[] var3);

    public Callback getCallback(int var1);

    public void setCallback(int var1, Callback var2);

    public void setCallbacks(Callback[] var1);

    public Callback[] getCallbacks();
}

