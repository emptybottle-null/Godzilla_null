/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xml;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.Scriptable;

public abstract class XMLObject
extends IdScriptableObject {
    static final long serialVersionUID = 8455156490438576500L;

    public XMLObject() {
    }

    public XMLObject(Scriptable scope, Scriptable prototype) {
        super(scope, prototype);
    }

    public abstract boolean has(Context var1, Object var2);

    public abstract Object get(Context var1, Object var2);

    public abstract void put(Context var1, Object var2, Object var3);

    public abstract boolean delete(Context var1, Object var2);

    public abstract Object getFunctionProperty(Context var1, String var2);

    public abstract Object getFunctionProperty(Context var1, int var2);

    public abstract Scriptable getExtraMethodSource(Context var1);

    public abstract Ref memberRef(Context var1, Object var2, int var3);

    public abstract Ref memberRef(Context var1, Object var2, Object var3, int var4);

    public abstract NativeWith enterWith(Scriptable var1);

    public abstract NativeWith enterDotQuery(Scriptable var1);

    public Object addValues(Context cx, boolean thisIsLeft, Object value) {
        return Scriptable.NOT_FOUND;
    }

    @Override
    public String getTypeOf() {
        return this.avoidObjectDetection() ? "undefined" : "xml";
    }
}

