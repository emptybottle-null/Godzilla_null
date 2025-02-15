/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Delegator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;

public class Synchronizer
extends Delegator {
    private Object syncObject;

    public Synchronizer(Scriptable obj) {
        super(obj);
    }

    public Synchronizer(Scriptable obj, Object syncObject) {
        super(obj);
        this.syncObject = syncObject;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object sync = this.syncObject != null ? this.syncObject : thisObj;
        Object object = sync instanceof Wrapper ? ((Wrapper)sync).unwrap() : sync;
        synchronized (object) {
            return ((Function)this.obj).call(cx, scope, thisObj, args);
        }
    }
}

