/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.EnumMap;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class TopLevel
extends IdScriptableObject {
    static final long serialVersionUID = -4648046356662472260L;
    private EnumMap<Builtins, BaseFunction> ctors;
    private EnumMap<NativeErrors, BaseFunction> errors;

    @Override
    public String getClassName() {
        return "global";
    }

    public void cacheBuiltins() {
        Object value;
        this.ctors = new EnumMap(Builtins.class);
        for (Builtins builtins : Builtins.values()) {
            value = ScriptableObject.getProperty((Scriptable)this, builtins.name());
            if (!(value instanceof BaseFunction)) continue;
            this.ctors.put(builtins, (BaseFunction)value);
        }
        this.errors = new EnumMap(NativeErrors.class);
        for (Enum enum_ : NativeErrors.values()) {
            value = ScriptableObject.getProperty((Scriptable)this, enum_.name());
            if (!(value instanceof BaseFunction)) continue;
            this.errors.put((NativeErrors)enum_, (BaseFunction)value);
        }
    }

    public static Function getBuiltinCtor(Context cx, Scriptable scope, Builtins type) {
        BaseFunction result;
        assert (scope.getParentScope() == null);
        if (scope instanceof TopLevel && (result = ((TopLevel)scope).getBuiltinCtor(type)) != null) {
            return result;
        }
        return ScriptRuntime.getExistingCtor(cx, scope, type.name());
    }

    static Function getNativeErrorCtor(Context cx, Scriptable scope, NativeErrors type) {
        BaseFunction result;
        assert (scope.getParentScope() == null);
        if (scope instanceof TopLevel && (result = ((TopLevel)scope).getNativeErrorCtor(type)) != null) {
            return result;
        }
        return ScriptRuntime.getExistingCtor(cx, scope, type.name());
    }

    public static Scriptable getBuiltinPrototype(Scriptable scope, Builtins type) {
        Scriptable result;
        assert (scope.getParentScope() == null);
        if (scope instanceof TopLevel && (result = ((TopLevel)scope).getBuiltinPrototype(type)) != null) {
            return result;
        }
        return ScriptableObject.getClassPrototype(scope, type.name());
    }

    public BaseFunction getBuiltinCtor(Builtins type) {
        return this.ctors != null ? this.ctors.get((Object)type) : null;
    }

    BaseFunction getNativeErrorCtor(NativeErrors type) {
        return this.errors != null ? this.errors.get((Object)type) : null;
    }

    public Scriptable getBuiltinPrototype(Builtins type) {
        BaseFunction func = this.getBuiltinCtor(type);
        Object proto = func != null ? func.getPrototypeProperty() : null;
        return proto instanceof Scriptable ? (Scriptable)proto : null;
    }

    static enum NativeErrors {
        Error,
        EvalError,
        RangeError,
        ReferenceError,
        SyntaxError,
        TypeError,
        URIError,
        InternalError,
        JavaException;

    }

    public static enum Builtins {
        Object,
        Array,
        Function,
        String,
        Number,
        Boolean,
        RegExp,
        Error;

    }
}

