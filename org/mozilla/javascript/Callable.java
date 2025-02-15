/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public interface Callable {
    public Object call(Context var1, Scriptable var2, Scriptable var3, Object[] var4);
}

