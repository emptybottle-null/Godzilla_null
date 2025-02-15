/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaMembers;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.Scriptable;

public class NativeJavaConstructor
extends BaseFunction {
    static final long serialVersionUID = -8149253217482668463L;
    MemberBox ctor;

    public NativeJavaConstructor(MemberBox ctor) {
        this.ctor = ctor;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return NativeJavaClass.constructSpecific(cx, scope, args, this.ctor);
    }

    @Override
    public String getFunctionName() {
        String sig = JavaMembers.liveConnectSignature(this.ctor.argTypes);
        return "<init>".concat(sig);
    }

    public String toString() {
        return "[JavaConstructor " + this.ctor.getName() + "]";
    }
}

