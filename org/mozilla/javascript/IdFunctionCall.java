/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Scriptable;

public interface IdFunctionCall {
    public Object execIdCall(IdFunctionObject var1, Context var2, Scriptable var3, Scriptable var4, Object[] var5);
}

