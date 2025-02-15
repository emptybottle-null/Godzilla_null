/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.BoundFunction;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.DefaultErrorReporter;
import org.mozilla.javascript.Delegator;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeCall;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;

public class BaseFunction
extends IdScriptableObject
implements Function {
    static final long serialVersionUID = 5311394446546053859L;
    private static final Object FUNCTION_TAG = "Function";
    private static final int Id_length = 1;
    private static final int Id_arity = 2;
    private static final int Id_name = 3;
    private static final int Id_prototype = 4;
    private static final int Id_arguments = 5;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toSource = 3;
    private static final int Id_apply = 4;
    private static final int Id_call = 5;
    private static final int Id_bind = 6;
    private static final int MAX_PROTOTYPE_ID = 6;
    private Object prototypeProperty;
    private Object argumentsObj = NOT_FOUND;
    private int prototypePropertyAttributes = 6;
    private int argumentsAttributes = 6;

    static void init(Scriptable scope, boolean sealed) {
        BaseFunction obj = new BaseFunction();
        obj.prototypePropertyAttributes = 7;
        obj.exportAsJSClass(6, scope, sealed);
    }

    public BaseFunction() {
    }

    public BaseFunction(Scriptable scope, Scriptable prototype) {
        super(scope, prototype);
    }

    @Override
    public String getClassName() {
        return "Function";
    }

    @Override
    public String getTypeOf() {
        return this.avoidObjectDetection() ? "undefined" : "function";
    }

    @Override
    public boolean hasInstance(Scriptable instance) {
        Object protoProp = ScriptableObject.getProperty((Scriptable)this, "prototype");
        if (protoProp instanceof Scriptable) {
            return ScriptRuntime.jsDelegatesTo(instance, (Scriptable)protoProp);
        }
        throw ScriptRuntime.typeError1("msg.instanceof.bad.prototype", this.getFunctionName());
    }

    @Override
    protected int getMaxInstanceId() {
        return 5;
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int attr;
        int id = 0;
        String X = null;
        switch (s.length()) {
            case 4: {
                X = "name";
                id = 3;
                break;
            }
            case 5: {
                X = "arity";
                id = 2;
                break;
            }
            case 6: {
                X = "length";
                id = 1;
                break;
            }
            case 9: {
                char c = s.charAt(0);
                if (c == 'a') {
                    X = "arguments";
                    id = 5;
                    break;
                }
                if (c != 'p') break;
                X = "prototype";
                id = 4;
                break;
            }
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1: 
            case 2: 
            case 3: {
                attr = 7;
                break;
            }
            case 4: {
                if (!this.hasPrototypeProperty()) {
                    return 0;
                }
                attr = this.prototypePropertyAttributes;
                break;
            }
            case 5: {
                attr = this.argumentsAttributes;
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
        return BaseFunction.instanceIdInfo(attr, id);
    }

    @Override
    protected String getInstanceIdName(int id) {
        switch (id) {
            case 1: {
                return "length";
            }
            case 2: {
                return "arity";
            }
            case 3: {
                return "name";
            }
            case 4: {
                return "prototype";
            }
            case 5: {
                return "arguments";
            }
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        switch (id) {
            case 1: {
                return ScriptRuntime.wrapInt(this.getLength());
            }
            case 2: {
                return ScriptRuntime.wrapInt(this.getArity());
            }
            case 3: {
                return this.getFunctionName();
            }
            case 4: {
                return this.getPrototypeProperty();
            }
            case 5: {
                return this.getArguments();
            }
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
        switch (id) {
            case 4: {
                if ((this.prototypePropertyAttributes & 1) == 0) {
                    this.prototypeProperty = value != null ? value : UniqueTag.NULL_VALUE;
                }
                return;
            }
            case 5: {
                if (value == NOT_FOUND) {
                    Kit.codeBug();
                }
                if (this.defaultHas("arguments")) {
                    this.defaultPut("arguments", value);
                } else if ((this.argumentsAttributes & 1) == 0) {
                    this.argumentsObj = value;
                }
                return;
            }
            case 1: 
            case 2: 
            case 3: {
                return;
            }
        }
        super.setInstanceIdValue(id, value);
    }

    @Override
    protected void setInstanceIdAttributes(int id, int attr) {
        switch (id) {
            case 4: {
                this.prototypePropertyAttributes = attr;
                return;
            }
            case 5: {
                this.argumentsAttributes = attr;
                return;
            }
        }
        super.setInstanceIdAttributes(id, attr);
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        ctor.setPrototype(this);
        super.fillConstructorProperties(ctor);
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
                arity = 1;
                s = "toSource";
                break;
            }
            case 4: {
                arity = 2;
                s = "apply";
                break;
            }
            case 5: {
                arity = 1;
                s = "call";
                break;
            }
            case 6: {
                arity = 1;
                s = "bind";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(FUNCTION_TAG, id, s, arity);
    }

    static boolean isApply(IdFunctionObject f) {
        return f.hasTag(FUNCTION_TAG) && f.methodId() == 4;
    }

    static boolean isApplyOrCall(IdFunctionObject f) {
        if (f.hasTag(FUNCTION_TAG)) {
            switch (f.methodId()) {
                case 4: 
                case 5: {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(FUNCTION_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                return BaseFunction.jsConstructor(cx, scope, args);
            }
            case 2: {
                BaseFunction realf = this.realFunction(thisObj, f);
                int indent = ScriptRuntime.toInt32(args, 0);
                return realf.decompile(indent, 0);
            }
            case 3: {
                BaseFunction realf = this.realFunction(thisObj, f);
                int indent = 0;
                int flags = 2;
                if (args.length != 0) {
                    indent = ScriptRuntime.toInt32(args[0]);
                    if (indent >= 0) {
                        flags = 0;
                    } else {
                        indent = 0;
                    }
                }
                return realf.decompile(indent, flags);
            }
            case 4: 
            case 5: {
                return ScriptRuntime.applyOrCall(id == 4, cx, scope, thisObj, args);
            }
            case 6: {
                Object[] boundArgs;
                Scriptable boundThis;
                if (!(thisObj instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(thisObj);
                }
                Callable targetFunction = (Callable)((Object)thisObj);
                int argc = args.length;
                if (argc > 0) {
                    boundThis = ScriptRuntime.toObjectOrNull(cx, args[0], scope);
                    boundArgs = new Object[argc - 1];
                    System.arraycopy(args, 1, boundArgs, 0, argc - 1);
                } else {
                    boundThis = null;
                    boundArgs = ScriptRuntime.emptyArgs;
                }
                return new BoundFunction(cx, scope, targetFunction, boundThis, boundArgs);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private BaseFunction realFunction(Scriptable thisObj, IdFunctionObject f) {
        Object x = thisObj.getDefaultValue(ScriptRuntime.FunctionClass);
        if (x instanceof Delegator) {
            x = ((Delegator)x).getDelegee();
        }
        if (x instanceof BaseFunction) {
            return (BaseFunction)x;
        }
        throw ScriptRuntime.typeError1("msg.incompat.call", f.getFunctionName());
    }

    public void setImmunePrototypeProperty(Object value) {
        if ((this.prototypePropertyAttributes & 1) != 0) {
            throw new IllegalStateException();
        }
        this.prototypeProperty = value != null ? value : UniqueTag.NULL_VALUE;
        this.prototypePropertyAttributes = 7;
    }

    protected Scriptable getClassPrototype() {
        Object protoVal = this.getPrototypeProperty();
        if (protoVal instanceof Scriptable) {
            return (Scriptable)protoVal;
        }
        return ScriptableObject.getObjectPrototype(this);
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return Undefined.instance;
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        Scriptable result = this.createObject(cx, scope);
        if (result != null) {
            Object val = this.call(cx, scope, result, args);
            if (val instanceof Scriptable) {
                result = (Scriptable)val;
            }
        } else {
            Scriptable parent;
            Scriptable proto;
            Object val = this.call(cx, scope, null, args);
            if (!(val instanceof Scriptable)) {
                throw new IllegalStateException("Bad implementaion of call as constructor, name=" + this.getFunctionName() + " in " + this.getClass().getName());
            }
            result = (Scriptable)val;
            if (result.getPrototype() == null && result != (proto = this.getClassPrototype())) {
                result.setPrototype(proto);
            }
            if (result.getParentScope() == null && result != (parent = this.getParentScope())) {
                result.setParentScope(parent);
            }
        }
        return result;
    }

    public Scriptable createObject(Context cx, Scriptable scope) {
        NativeObject newInstance = new NativeObject();
        newInstance.setPrototype(this.getClassPrototype());
        newInstance.setParentScope(this.getParentScope());
        return newInstance;
    }

    String decompile(int indent, int flags) {
        boolean justbody;
        StringBuilder sb = new StringBuilder();
        boolean bl = justbody = 0 != (flags & 1);
        if (!justbody) {
            sb.append("function ");
            sb.append(this.getFunctionName());
            sb.append("() {\n\t");
        }
        sb.append("[native code, arity=");
        sb.append(this.getArity());
        sb.append("]\n");
        if (!justbody) {
            sb.append("}\n");
        }
        return sb.toString();
    }

    public int getArity() {
        return 0;
    }

    public int getLength() {
        return 0;
    }

    public String getFunctionName() {
        return "";
    }

    protected boolean hasPrototypeProperty() {
        return this.prototypeProperty != null || this instanceof NativeFunction;
    }

    protected Object getPrototypeProperty() {
        Object result = this.prototypeProperty;
        if (result == null) {
            result = this instanceof NativeFunction ? this.setupDefaultPrototype() : Undefined.instance;
        } else if (result == UniqueTag.NULL_VALUE) {
            result = null;
        }
        return result;
    }

    private synchronized Object setupDefaultPrototype() {
        if (this.prototypeProperty != null) {
            return this.prototypeProperty;
        }
        NativeObject obj = new NativeObject();
        int attr = 2;
        obj.defineProperty("constructor", this, 2);
        this.prototypeProperty = obj;
        Scriptable proto = BaseFunction.getObjectPrototype(this);
        if (proto != obj) {
            obj.setPrototype(proto);
        }
        return obj;
    }

    private Object getArguments() {
        Object value;
        Object object = value = this.defaultHas("arguments") ? this.defaultGet("arguments") : this.argumentsObj;
        if (value != NOT_FOUND) {
            return value;
        }
        Context cx = Context.getContext();
        NativeCall activation = ScriptRuntime.findFunctionActivation(cx, this);
        return activation == null ? null : activation.get("arguments", (Scriptable)activation);
    }

    private static Object jsConstructor(Context cx, Scriptable scope, Object[] args) {
        int arglen = args.length;
        StringBuilder sourceBuf = new StringBuilder();
        sourceBuf.append("function ");
        if (cx.getLanguageVersion() != 120) {
            sourceBuf.append("anonymous");
        }
        sourceBuf.append('(');
        for (int i = 0; i < arglen - 1; ++i) {
            if (i > 0) {
                sourceBuf.append(',');
            }
            sourceBuf.append(ScriptRuntime.toString(args[i]));
        }
        sourceBuf.append(") {");
        if (arglen != 0) {
            String funBody = ScriptRuntime.toString(args[arglen - 1]);
            sourceBuf.append(funBody);
        }
        sourceBuf.append("\n}");
        String source = sourceBuf.toString();
        int[] linep = new int[1];
        String filename = Context.getSourcePositionFromStack(linep);
        if (filename == null) {
            filename = "<eval'ed string>";
            linep[0] = 1;
        }
        String sourceURI = ScriptRuntime.makeUrlForGeneratedScript(false, filename, linep[0]);
        Scriptable global = ScriptableObject.getTopLevelScope(scope);
        ErrorReporter reporter = DefaultErrorReporter.forEval(cx.getErrorReporter());
        Evaluator evaluator = Context.createInterpreter();
        if (evaluator == null) {
            throw new JavaScriptException("Interpreter not present", filename, linep[0]);
        }
        return cx.compileFunction(global, source, evaluator, reporter, sourceURI, 1, null);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block8: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 4: {
                    char c = s.charAt(0);
                    if (c == 'b') {
                        X = "bind";
                        id = 6;
                        break;
                    }
                    if (c != 'c') break;
                    X = "call";
                    id = 5;
                    break;
                }
                case 5: {
                    X = "apply";
                    id = 4;
                    break;
                }
                case 8: {
                    char c = s.charAt(3);
                    if (c == 'o') {
                        X = "toSource";
                        id = 3;
                        break;
                    }
                    if (c != 't') break;
                    X = "toString";
                    id = 2;
                    break;
                }
                case 11: {
                    X = "constructor";
                    id = 1;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block8;
            id = 0;
        }
        return id;
    }
}

