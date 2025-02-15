/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public abstract class Ref
implements Serializable {
    static final long serialVersionUID = 4044540354730911424L;

    public boolean has(Context cx) {
        return true;
    }

    public abstract Object get(Context var1);

    @Deprecated
    public abstract Object set(Context var1, Object var2);

    public Object set(Context cx, Scriptable scope, Object value) {
        return this.set(cx, value);
    }

    public boolean delete(Context cx) {
        return false;
    }
}

