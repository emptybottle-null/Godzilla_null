/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Interpreter;
import org.mozilla.javascript.InterpreterData;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.debug.DebuggableScript;

final class InterpretedFunction
extends NativeFunction
implements Script {
    static final long serialVersionUID = 541475680333911468L;
    InterpreterData idata;
    SecurityController securityController;
    Object securityDomain;

    private InterpretedFunction(InterpreterData idata, Object staticSecurityDomain) {
        Object dynamicDomain;
        this.idata = idata;
        Context cx = Context.getContext();
        SecurityController sc = cx.getSecurityController();
        if (sc != null) {
            dynamicDomain = sc.getDynamicSecurityDomain(staticSecurityDomain);
        } else {
            if (staticSecurityDomain != null) {
                throw new IllegalArgumentException();
            }
            dynamicDomain = null;
        }
        this.securityController = sc;
        this.securityDomain = dynamicDomain;
    }

    private InterpretedFunction(InterpretedFunction parent, int index) {
        this.idata = parent.idata.itsNestedFunctions[index];
        this.securityController = parent.securityController;
        this.securityDomain = parent.securityDomain;
    }

    static InterpretedFunction createScript(InterpreterData idata, Object staticSecurityDomain) {
        InterpretedFunction f = new InterpretedFunction(idata, staticSecurityDomain);
        return f;
    }

    static InterpretedFunction createFunction(Context cx, Scriptable scope, InterpreterData idata, Object staticSecurityDomain) {
        InterpretedFunction f = new InterpretedFunction(idata, staticSecurityDomain);
        f.initScriptFunction(cx, scope);
        return f;
    }

    static InterpretedFunction createFunction(Context cx, Scriptable scope, InterpretedFunction parent, int index) {
        InterpretedFunction f = new InterpretedFunction(parent, index);
        f.initScriptFunction(cx, scope);
        return f;
    }

    @Override
    public String getFunctionName() {
        return this.idata.itsName == null ? "" : this.idata.itsName;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!ScriptRuntime.hasTopCall(cx)) {
            return ScriptRuntime.doTopCall(this, cx, scope, thisObj, args);
        }
        return Interpreter.interpret(this, cx, scope, thisObj, args);
    }

    @Override
    public Object exec(Context cx, Scriptable scope) {
        if (!this.isScript()) {
            throw new IllegalStateException();
        }
        if (!ScriptRuntime.hasTopCall(cx)) {
            return ScriptRuntime.doTopCall(this, cx, scope, scope, ScriptRuntime.emptyArgs);
        }
        return Interpreter.interpret(this, cx, scope, scope, ScriptRuntime.emptyArgs);
    }

    public boolean isScript() {
        return this.idata.itsFunctionType == 0;
    }

    @Override
    public String getEncodedSource() {
        return Interpreter.getEncodedSource(this.idata);
    }

    @Override
    public DebuggableScript getDebuggableView() {
        return this.idata;
    }

    @Override
    public Object resumeGenerator(Context cx, Scriptable scope, int operation, Object state, Object value) {
        return Interpreter.resumeGenerator(cx, scope, operation, state, value);
    }

    @Override
    protected int getLanguageVersion() {
        return this.idata.languageVersion;
    }

    @Override
    protected int getParamCount() {
        return this.idata.argCount;
    }

    @Override
    protected int getParamAndVarCount() {
        return this.idata.argNames.length;
    }

    @Override
    protected String getParamOrVarName(int index) {
        return this.idata.argNames[index];
    }

    @Override
    protected boolean getParamOrVarConst(int index) {
        return this.idata.argIsConst[index];
    }
}

