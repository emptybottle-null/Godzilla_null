/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.Scriptable;

public interface RefCallable
extends Callable {
    public Ref refCall(Context var1, Scriptable var2, Object[] var3);
}

