/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class Delegator
implements Function {
    protected Scriptable obj = null;

    public Delegator() {
    }

    public Delegator(Scriptable obj) {
        this.obj = obj;
    }

    protected Delegator newInstance() {
        try {
            return (Delegator)this.getClass().newInstance();
        } catch (Exception ex) {
            throw Context.throwAsScriptRuntimeEx(ex);
        }
    }

    public Scriptable getDelegee() {
        return this.obj;
    }

    public void setDelegee(Scriptable obj) {
        this.obj = obj;
    }

    @Override
    public String getClassName() {
        return this.obj.getClassName();
    }

    @Override
    public Object get(String name, Scriptable start) {
        return this.obj.get(name, start);
    }

    @Override
    public Object get(int index, Scriptable start) {
        return this.obj.get(index, start);
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return this.obj.has(name, start);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return this.obj.has(index, start);
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        this.obj.put(name, start, value);
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        this.obj.put(index, start, value);
    }

    @Override
    public void delete(String name) {
        this.obj.delete(name);
    }

    @Override
    public void delete(int index) {
        this.obj.delete(index);
    }

    @Override
    public Scriptable getPrototype() {
        return this.obj.getPrototype();
    }

    @Override
    public void setPrototype(Scriptable prototype) {
        this.obj.setPrototype(prototype);
    }

    @Override
    public Scriptable getParentScope() {
        return this.obj.getParentScope();
    }

    @Override
    public void setParentScope(Scriptable parent) {
        this.obj.setParentScope(parent);
    }

    @Override
    public Object[] getIds() {
        return this.obj.getIds();
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        return hint == null || hint == ScriptRuntime.ScriptableClass || hint == ScriptRuntime.FunctionClass ? this : this.obj.getDefaultValue(hint);
    }

    @Override
    public boolean hasInstance(Scriptable instance) {
        return this.obj.hasInstance(instance);
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return ((Function)this.obj).call(cx, scope, thisObj, args);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        if (this.obj == null) {
            Delegator n = this.newInstance();
            Scriptable delegee = args.length == 0 ? new NativeObject() : ScriptRuntime.toObject(cx, scope, args[0]);
            n.setDelegee(delegee);
            return n;
        }
        return ((Function)this.obj).construct(cx, scope, args);
    }
}

