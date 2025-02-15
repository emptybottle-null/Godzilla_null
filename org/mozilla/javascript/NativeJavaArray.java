/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Array;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

public class NativeJavaArray
extends NativeJavaObject {
    static final long serialVersionUID = -924022554283675333L;
    Object array;
    int length;
    Class<?> cls;

    @Override
    public String getClassName() {
        return "JavaArray";
    }

    public static NativeJavaArray wrap(Scriptable scope, Object array) {
        return new NativeJavaArray(scope, array);
    }

    @Override
    public Object unwrap() {
        return this.array;
    }

    public NativeJavaArray(Scriptable scope, Object array) {
        super(scope, null, ScriptRuntime.ObjectClass);
        Class<?> cl = array.getClass();
        if (!cl.isArray()) {
            throw new RuntimeException("Array expected");
        }
        this.array = array;
        this.length = Array.getLength(array);
        this.cls = cl.getComponentType();
    }

    @Override
    public boolean has(String id, Scriptable start) {
        return id.equals("length") || super.has(id, start);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return 0 <= index && index < this.length;
    }

    @Override
    public Object get(String id, Scriptable start) {
        if (id.equals("length")) {
            return this.length;
        }
        Object result = super.get(id, start);
        if (result == NOT_FOUND && !ScriptableObject.hasProperty(this.getPrototype(), id)) {
            throw Context.reportRuntimeError2("msg.java.member.not.found", this.array.getClass().getName(), id);
        }
        return result;
    }

    @Override
    public Object get(int index, Scriptable start) {
        if (0 <= index && index < this.length) {
            Context cx = Context.getContext();
            Object obj = Array.get(this.array, index);
            return cx.getWrapFactory().wrap(cx, this, obj, this.cls);
        }
        return Undefined.instance;
    }

    @Override
    public void put(String id, Scriptable start, Object value) {
        if (!id.equals("length")) {
            throw Context.reportRuntimeError1("msg.java.array.member.not.found", id);
        }
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        if (0 > index || index >= this.length) {
            throw Context.reportRuntimeError2("msg.java.array.index.out.of.bounds", String.valueOf(index), String.valueOf(this.length - 1));
        }
        Array.set(this.array, index, Context.jsToJava(value, this.cls));
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        if (hint == null || hint == ScriptRuntime.StringClass) {
            return this.array.toString();
        }
        if (hint == ScriptRuntime.BooleanClass) {
            return Boolean.TRUE;
        }
        if (hint == ScriptRuntime.NumberClass) {
            return ScriptRuntime.NaNobj;
        }
        return this;
    }

    @Override
    public Object[] getIds() {
        Object[] result = new Object[this.length];
        int i = this.length;
        while (--i >= 0) {
            result[i] = i;
        }
        return result;
    }

    @Override
    public boolean hasInstance(Scriptable value) {
        if (!(value instanceof Wrapper)) {
            return false;
        }
        Object instance = ((Wrapper)((Object)value)).unwrap();
        return this.cls.isInstance(instance);
    }

    @Override
    public Scriptable getPrototype() {
        if (this.prototype == null) {
            this.prototype = ScriptableObject.getArrayPrototype(this.getParentScope());
        }
        return this.prototype;
    }
}

