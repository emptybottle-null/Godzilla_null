/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeCallSite;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ScriptStackElement;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

final class NativeError
extends IdScriptableObject {
    static final long serialVersionUID = -5338413581437645187L;
    private static final Object ERROR_TAG = "Error";
    private static final Method ERROR_DELEGATE_GET_STACK;
    private static final Method ERROR_DELEGATE_SET_STACK;
    public static final int DEFAULT_STACK_LIMIT = -1;
    private static final String STACK_HIDE_KEY = "_stackHide";
    private RhinoException stackProvider;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toSource = 3;
    private static final int ConstructorId_captureStackTrace = -1;
    private static final int MAX_PROTOTYPE_ID = 3;

    NativeError() {
    }

    static void init(Scriptable scope, boolean sealed) {
        NativeError obj = new NativeError();
        ScriptableObject.putProperty((Scriptable)obj, "name", (Object)"Error");
        ScriptableObject.putProperty((Scriptable)obj, "message", (Object)"");
        ScriptableObject.putProperty((Scriptable)obj, "fileName", (Object)"");
        ScriptableObject.putProperty((Scriptable)obj, "lineNumber", (Object)0);
        obj.setAttributes("name", 2);
        obj.setAttributes("message", 2);
        obj.exportAsJSClass(3, scope, sealed);
        NativeCallSite.init(obj, sealed);
    }

    static NativeError make(Context cx, Scriptable scope, IdFunctionObject ctorObj, Object[] args) {
        Scriptable proto = (Scriptable)ctorObj.get("prototype", (Scriptable)ctorObj);
        NativeError obj = new NativeError();
        obj.setPrototype(proto);
        obj.setParentScope(scope);
        int arglen = args.length;
        if (arglen >= 1) {
            if (args[0] != Undefined.instance) {
                ScriptableObject.putProperty((Scriptable)obj, "message", (Object)ScriptRuntime.toString(args[0]));
            }
            if (arglen >= 2) {
                ScriptableObject.putProperty((Scriptable)obj, "fileName", args[1]);
                if (arglen >= 3) {
                    int line = ScriptRuntime.toInt32(args[2]);
                    ScriptableObject.putProperty((Scriptable)obj, "lineNumber", (Object)line);
                }
            }
        }
        return obj;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        this.addIdFunctionProperty(ctor, ERROR_TAG, -1, "captureStackTrace", 2);
        ProtoProps protoProps = new ProtoProps();
        this.associateValue("_ErrorPrototypeProps", protoProps);
        ctor.defineProperty("stackTraceLimit", protoProps, ProtoProps.GET_STACK_LIMIT, ProtoProps.SET_STACK_LIMIT, 0);
        ctor.defineProperty("prepareStackTrace", protoProps, ProtoProps.GET_PREPARE_STACK, ProtoProps.SET_PREPARE_STACK, 0);
        super.fillConstructorProperties(ctor);
    }

    @Override
    public String getClassName() {
        return "Error";
    }

    public String toString() {
        Object toString = NativeError.js_toString(this);
        return toString instanceof String ? (String)toString : super.toString();
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 1;
                s = "constructor";
                break;
            }
            case 2: {
                arity = 0;
                s = "toString";
                break;
            }
            case 3: {
                arity = 0;
                s = "toSource";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(ERROR_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(ERROR_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                return NativeError.make(cx, scope, f, args);
            }
            case 2: {
                return NativeError.js_toString(thisObj);
            }
            case 3: {
                return NativeError.js_toSource(cx, scope, thisObj);
            }
            case -1: {
                NativeError.js_captureStackTrace(cx, thisObj, args);
                return Undefined.instance;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    public void setStackProvider(RhinoException re) {
        if (this.stackProvider == null) {
            this.stackProvider = re;
            this.defineProperty("stack", this, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 2);
        }
    }

    public Object getStackDelegated(Scriptable target) {
        if (this.stackProvider == null) {
            return NOT_FOUND;
        }
        int limit = -1;
        Function prepare = null;
        NativeError cons = (NativeError)this.getPrototype();
        ProtoProps pp = (ProtoProps)cons.getAssociatedValue("_ErrorPrototypeProps");
        if (pp != null) {
            limit = pp.getStackTraceLimit();
            prepare = pp.getPrepareStackTrace();
        }
        String hideFunc = (String)this.getAssociatedValue(STACK_HIDE_KEY);
        ScriptStackElement[] stack = this.stackProvider.getScriptStack(limit, hideFunc);
        Object value = prepare == null ? RhinoException.formatStackTrace(stack, this.stackProvider.details()) : this.callPrepareStack(prepare, stack);
        this.setStackDelegated(target, value);
        return value;
    }

    public void setStackDelegated(Scriptable target, Object value) {
        target.delete("stack");
        this.stackProvider = null;
        target.put("stack", target, value);
    }

    private Object callPrepareStack(Function prepare, ScriptStackElement[] stack) {
        Context cx = Context.getCurrentContext();
        Object[] elts = new Object[stack.length];
        for (int i = 0; i < stack.length; ++i) {
            NativeCallSite site = (NativeCallSite)cx.newObject(this, "CallSite");
            site.setElement(stack[i]);
            elts[i] = site;
        }
        Scriptable eltArray = cx.newArray((Scriptable)this, elts);
        return prepare.call(cx, prepare, this, new Object[]{this, eltArray});
    }

    private static Object js_toString(Scriptable thisObj) {
        Object name = ScriptableObject.getProperty(thisObj, "name");
        name = name == NOT_FOUND || name == Undefined.instance ? "Error" : ScriptRuntime.toString(name);
        Object msg = ScriptableObject.getProperty(thisObj, "message");
        msg = msg == NOT_FOUND || msg == Undefined.instance ? "" : ScriptRuntime.toString(msg);
        if (name.toString().length() == 0) {
            return msg;
        }
        if (msg.toString().length() == 0) {
            return name;
        }
        return (String)name + ": " + (String)msg;
    }

    private static String js_toSource(Context cx, Scriptable scope, Scriptable thisObj) {
        Object name = ScriptableObject.getProperty(thisObj, "name");
        Object message = ScriptableObject.getProperty(thisObj, "message");
        Object fileName = ScriptableObject.getProperty(thisObj, "fileName");
        Object lineNumber = ScriptableObject.getProperty(thisObj, "lineNumber");
        StringBuilder sb = new StringBuilder();
        sb.append("(new ");
        if (name == NOT_FOUND) {
            name = Undefined.instance;
        }
        sb.append(ScriptRuntime.toString(name));
        sb.append("(");
        if (message != NOT_FOUND || fileName != NOT_FOUND || lineNumber != NOT_FOUND) {
            if (message == NOT_FOUND) {
                message = "";
            }
            sb.append(ScriptRuntime.uneval(cx, scope, message));
            if (fileName != NOT_FOUND || lineNumber != NOT_FOUND) {
                int line;
                sb.append(", ");
                if (fileName == NOT_FOUND) {
                    fileName = "";
                }
                sb.append(ScriptRuntime.uneval(cx, scope, fileName));
                if (lineNumber != NOT_FOUND && (line = ScriptRuntime.toInt32(lineNumber)) != 0) {
                    sb.append(", ");
                    sb.append(ScriptRuntime.toString(line));
                }
            }
        }
        sb.append("))");
        return sb.toString();
    }

    private static void js_captureStackTrace(Context cx, Scriptable thisObj, Object[] args) {
        Object funcName;
        ScriptableObject obj = (ScriptableObject)ScriptRuntime.toObjectOrNull(cx, args[0], thisObj);
        Function func = null;
        if (args.length > 1) {
            func = (Function)ScriptRuntime.toObjectOrNull(cx, args[1], thisObj);
        }
        NativeError err = (NativeError)cx.newObject(thisObj, "Error");
        err.setStackProvider(new EvaluatorException("[object Object]"));
        if (func != null && (funcName = func.get("name", (Scriptable)func)) != null && !Undefined.instance.equals(funcName)) {
            err.associateValue(STACK_HIDE_KEY, Context.toString(funcName));
        }
        obj.defineProperty("stack", err, ERROR_DELEGATE_GET_STACK, ERROR_DELEGATE_SET_STACK, 0);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block6: {
            id = 0;
            String X = null;
            int s_length = s.length();
            if (s_length == 8) {
                char c = s.charAt(3);
                if (c == 'o') {
                    X = "toSource";
                    id = 3;
                } else if (c == 't') {
                    X = "toString";
                    id = 2;
                }
            } else if (s_length == 11) {
                X = "constructor";
                id = 1;
            }
            if (X == null || X == s || X.equals(s)) break block6;
            id = 0;
        }
        return id;
    }

    static {
        try {
            ERROR_DELEGATE_GET_STACK = NativeError.class.getMethod("getStackDelegated", Scriptable.class);
            ERROR_DELEGATE_SET_STACK = NativeError.class.getMethod("setStackDelegated", Scriptable.class, Object.class);
        } catch (NoSuchMethodException nsm) {
            throw new RuntimeException(nsm);
        }
    }

    private static final class ProtoProps
    implements Serializable {
        static final String KEY = "_ErrorPrototypeProps";
        static final Method GET_STACK_LIMIT;
        static final Method SET_STACK_LIMIT;
        static final Method GET_PREPARE_STACK;
        static final Method SET_PREPARE_STACK;
        private static final long serialVersionUID = 1907180507775337939L;
        private int stackTraceLimit = -1;
        private Function prepareStackTrace;

        private ProtoProps() {
        }

        public Object getStackTraceLimit(Scriptable thisObj) {
            if (this.stackTraceLimit >= 0) {
                return this.stackTraceLimit;
            }
            return Double.POSITIVE_INFINITY;
        }

        public int getStackTraceLimit() {
            return this.stackTraceLimit;
        }

        public void setStackTraceLimit(Scriptable thisObj, Object value) {
            double limit = Context.toNumber(value);
            this.stackTraceLimit = Double.isNaN(limit) || Double.isInfinite(limit) ? -1 : (int)limit;
        }

        public Object getPrepareStackTrace(Scriptable thisObj) {
            Function ps = this.getPrepareStackTrace();
            return ps == null ? Undefined.instance : ps;
        }

        public Function getPrepareStackTrace() {
            return this.prepareStackTrace;
        }

        public void setPrepareStackTrace(Scriptable thisObj, Object value) {
            if (value == null || Undefined.instance.equals(value)) {
                this.prepareStackTrace = null;
            } else if (value instanceof Function) {
                this.prepareStackTrace = (Function)value;
            }
        }

        static {
            try {
                GET_STACK_LIMIT = ProtoProps.class.getMethod("getStackTraceLimit", Scriptable.class);
                SET_STACK_LIMIT = ProtoProps.class.getMethod("setStackTraceLimit", Scriptable.class, Object.class);
                GET_PREPARE_STACK = ProtoProps.class.getMethod("getPrepareStackTrace", Scriptable.class);
                SET_PREPARE_STACK = ProtoProps.class.getMethod("setPrepareStackTrace", Scriptable.class, Object.class);
            } catch (NoSuchMethodException nsm) {
                throw new RuntimeException(nsm);
            }
        }
    }
}

