/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public interface Function
extends Scriptable,
Callable {
    @Override
    public Object call(Context var1, Scriptable var2, Scriptable var3, Object[] var4);

    public Scriptable construct(Context var1, Scriptable var2, Object[] var3);
}

