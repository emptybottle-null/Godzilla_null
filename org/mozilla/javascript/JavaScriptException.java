/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeError;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class JavaScriptException
extends RhinoException {
    static final long serialVersionUID = -7666130513694669293L;
    private Object value;

    @Deprecated
    public JavaScriptException(Object value) {
        this(value, "", 0);
    }

    public JavaScriptException(Object value, String sourceName, int lineNumber) {
        this.recordErrorOrigin(sourceName, lineNumber, null, 0);
        this.value = value;
        if (value instanceof NativeError && Context.getContext().hasFeature(10)) {
            NativeError error = (NativeError)value;
            if (!error.has("fileName", (Scriptable)error)) {
                error.put("fileName", (Scriptable)error, (Object)sourceName);
            }
            if (!error.has("lineNumber", (Scriptable)error)) {
                error.put("lineNumber", (Scriptable)error, (Object)lineNumber);
            }
            error.setStackProvider(this);
        }
    }

    @Override
    public String details() {
        if (this.value == null) {
            return "null";
        }
        if (this.value instanceof NativeError) {
            return this.value.toString();
        }
        try {
            return ScriptRuntime.toString(this.value);
        } catch (RuntimeException rte) {
            if (this.value instanceof Scriptable) {
                return ScriptRuntime.defaultObjectToString((Scriptable)this.value);
            }
            return this.value.toString();
        }
    }

    public Object getValue() {
        return this.value;
    }

    @Deprecated
    public String getSourceName() {
        return this.sourceName();
    }

    @Deprecated
    public int getLineNumber() {
        return this.lineNumber();
    }
}

