/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Field;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeJavaMethod;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

class FieldAndMethods
extends NativeJavaMethod {
    static final long serialVersionUID = -9222428244284796755L;
    Field field;
    Object javaObject;

    FieldAndMethods(Scriptable scope, MemberBox[] methods, Field field) {
        super(methods);
        this.field = field;
        this.setParentScope(scope);
        this.setPrototype(ScriptableObject.getFunctionPrototype(scope));
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        Class<?> type;
        Object rval;
        if (hint == ScriptRuntime.FunctionClass) {
            return this;
        }
        try {
            rval = this.field.get(this.javaObject);
            type = this.field.getType();
        } catch (IllegalAccessException accEx) {
            throw Context.reportRuntimeError1("msg.java.internal.private", this.field.getName());
        }
        Context cx = Context.getContext();
        rval = cx.getWrapFactory().wrap(cx, this, rval, type);
        if (rval instanceof Scriptable) {
            rval = ((Scriptable)rval).getDefaultValue(hint);
        }
        return rval;
    }
}

