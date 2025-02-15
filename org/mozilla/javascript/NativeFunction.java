/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Decompiler;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.NativeCall;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.UintMap;
import org.mozilla.javascript.debug.DebuggableScript;

public abstract class NativeFunction
extends BaseFunction {
    static final long serialVersionUID = 8713897114082216401L;

    public final void initScriptFunction(Context cx, Scriptable scope) {
        ScriptRuntime.setFunctionProtoAndParent(this, scope);
    }

    @Override
    final String decompile(int indent, int flags) {
        String encodedSource = this.getEncodedSource();
        if (encodedSource == null) {
            return super.decompile(indent, flags);
        }
        UintMap properties = new UintMap(1);
        properties.put(1, indent);
        return Decompiler.decompile(encodedSource, flags, properties);
    }

    @Override
    public int getLength() {
        int paramCount = this.getParamCount();
        if (this.getLanguageVersion() != 120) {
            return paramCount;
        }
        Context cx = Context.getContext();
        NativeCall activation = ScriptRuntime.findFunctionActivation(cx, this);
        if (activation == null) {
            return paramCount;
        }
        return activation.originalArgs.length;
    }

    @Override
    public int getArity() {
        return this.getParamCount();
    }

    @Deprecated
    public String jsGet_name() {
        return this.getFunctionName();
    }

    public String getEncodedSource() {
        return null;
    }

    public DebuggableScript getDebuggableView() {
        return null;
    }

    public Object resumeGenerator(Context cx, Scriptable scope, int operation, Object state, Object value) {
        throw new EvaluatorException("resumeGenerator() not implemented");
    }

    protected abstract int getLanguageVersion();

    protected abstract int getParamCount();

    protected abstract int getParamAndVarCount();

    protected abstract String getParamOrVarName(int var1);

    protected boolean getParamOrVarConst(int index) {
        return false;
    }
}

