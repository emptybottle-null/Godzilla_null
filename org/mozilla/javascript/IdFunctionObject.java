/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionCall;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class IdFunctionObject
extends BaseFunction {
    static final long serialVersionUID = -5332312783643935019L;
    private final IdFunctionCall idcall;
    private final Object tag;
    private final int methodId;
    private int arity;
    private boolean useCallAsConstructor;
    private String functionName;

    public IdFunctionObject(IdFunctionCall idcall, Object tag, int id, int arity) {
        if (arity < 0) {
            throw new IllegalArgumentException();
        }
        this.idcall = idcall;
        this.tag = tag;
        this.methodId = id;
        this.arity = arity;
        if (arity < 0) {
            throw new IllegalArgumentException();
        }
    }

    public IdFunctionObject(IdFunctionCall idcall, Object tag, int id, String name, int arity, Scriptable scope) {
        super(scope, null);
        if (arity < 0) {
            throw new IllegalArgumentException();
        }
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.idcall = idcall;
        this.tag = tag;
        this.methodId = id;
        this.arity = arity;
        this.functionName = name;
    }

    public void initFunction(String name, Scriptable scope) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (scope == null) {
            throw new IllegalArgumentException();
        }
        this.functionName = name;
        this.setParentScope(scope);
    }

    public final boolean hasTag(Object tag) {
        return tag == null ? this.tag == null : tag.equals(this.tag);
    }

    public final int methodId() {
        return this.methodId;
    }

    public final void markAsConstructor(Scriptable prototypeProperty) {
        this.useCallAsConstructor = true;
        this.setImmunePrototypeProperty(prototypeProperty);
    }

    public final void addAsProperty(Scriptable target) {
        ScriptableObject.defineProperty(target, this.functionName, this, 2);
    }

    public void exportAsScopeProperty() {
        this.addAsProperty(this.getParentScope());
    }

    @Override
    public Scriptable getPrototype() {
        Scriptable proto = super.getPrototype();
        if (proto == null) {
            proto = IdFunctionObject.getFunctionPrototype(this.getParentScope());
            this.setPrototype(proto);
        }
        return proto;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return this.idcall.execIdCall(this, cx, scope, thisObj, args);
    }

    @Override
    public Scriptable createObject(Context cx, Scriptable scope) {
        if (this.useCallAsConstructor) {
            return null;
        }
        throw ScriptRuntime.typeError1("msg.not.ctor", this.functionName);
    }

    @Override
    String decompile(int indent, int flags) {
        boolean justbody;
        StringBuilder sb = new StringBuilder();
        boolean bl = justbody = 0 != (flags & 1);
        if (!justbody) {
            sb.append("function ");
            sb.append(this.getFunctionName());
            sb.append("() { ");
        }
        sb.append("[native code for ");
        if (this.idcall instanceof Scriptable) {
            Scriptable sobj = (Scriptable)((Object)this.idcall);
            sb.append(sobj.getClassName());
            sb.append('.');
        }
        sb.append(this.getFunctionName());
        sb.append(", arity=");
        sb.append(this.getArity());
        sb.append(justbody ? "]\n" : "] }\n");
        return sb.toString();
    }

    @Override
    public int getArity() {
        return this.arity;
    }

    @Override
    public int getLength() {
        return this.getArity();
    }

    @Override
    public String getFunctionName() {
        return this.functionName == null ? "" : this.functionName;
    }

    public final RuntimeException unknown() {
        return new IllegalArgumentException("BAD FUNCTION ID=" + this.methodId + " MASTER=" + this.idcall);
    }
}

