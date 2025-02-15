/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.ConsString;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

public class FunctionObject
extends BaseFunction {
    static final long serialVersionUID = -5332312783643935019L;
    private static final short VARARGS_METHOD = -1;
    private static final short VARARGS_CTOR = -2;
    private static boolean sawSecurityException;
    public static final int JAVA_UNSUPPORTED_TYPE = 0;
    public static final int JAVA_STRING_TYPE = 1;
    public static final int JAVA_INT_TYPE = 2;
    public static final int JAVA_BOOLEAN_TYPE = 3;
    public static final int JAVA_DOUBLE_TYPE = 4;
    public static final int JAVA_SCRIPTABLE_TYPE = 5;
    public static final int JAVA_OBJECT_TYPE = 6;
    MemberBox member;
    private String functionName;
    private transient byte[] typeTags;
    private int parmsLength;
    private transient boolean hasVoidReturn;
    private transient int returnTypeTag;
    private boolean isStatic;

    public FunctionObject(String name, Member methodOrConstructor, Scriptable scope) {
        if (methodOrConstructor instanceof Constructor) {
            this.member = new MemberBox((Constructor)methodOrConstructor);
            this.isStatic = true;
        } else {
            this.member = new MemberBox((Method)methodOrConstructor);
            this.isStatic = this.member.isStatic();
        }
        String methodName = this.member.getName();
        this.functionName = name;
        Class<?>[] types = this.member.argTypes;
        int arity = types.length;
        if (arity == 4 && (types[1].isArray() || types[2].isArray())) {
            if (types[1].isArray()) {
                if (!this.isStatic || types[0] != ScriptRuntime.ContextClass || types[1].getComponentType() != ScriptRuntime.ObjectClass || types[2] != ScriptRuntime.FunctionClass || types[3] != Boolean.TYPE) {
                    throw Context.reportRuntimeError1("msg.varargs.ctor", methodName);
                }
                this.parmsLength = -2;
            } else {
                if (!this.isStatic || types[0] != ScriptRuntime.ContextClass || types[1] != ScriptRuntime.ScriptableClass || types[2].getComponentType() != ScriptRuntime.ObjectClass || types[3] != ScriptRuntime.FunctionClass) {
                    throw Context.reportRuntimeError1("msg.varargs.fun", methodName);
                }
                this.parmsLength = -1;
            }
        } else {
            this.parmsLength = arity;
            if (arity > 0) {
                this.typeTags = new byte[arity];
                for (int i = 0; i != arity; ++i) {
                    int tag = FunctionObject.getTypeTag(types[i]);
                    if (tag == 0) {
                        throw Context.reportRuntimeError2("msg.bad.parms", types[i].getName(), methodName);
                    }
                    this.typeTags[i] = (byte)tag;
                }
            }
        }
        if (this.member.isMethod()) {
            Method method = this.member.method();
            Class<?> returnType = method.getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = FunctionObject.getTypeTag(returnType);
            }
        } else {
            Class<?> ctorType = this.member.getDeclaringClass();
            if (!ScriptRuntime.ScriptableClass.isAssignableFrom(ctorType)) {
                throw Context.reportRuntimeError1("msg.bad.ctor.return", ctorType.getName());
            }
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scope);
    }

    public static int getTypeTag(Class<?> type) {
        if (type == ScriptRuntime.StringClass) {
            return 1;
        }
        if (type == ScriptRuntime.IntegerClass || type == Integer.TYPE) {
            return 2;
        }
        if (type == ScriptRuntime.BooleanClass || type == Boolean.TYPE) {
            return 3;
        }
        if (type == ScriptRuntime.DoubleClass || type == Double.TYPE) {
            return 4;
        }
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(type)) {
            return 5;
        }
        if (type == ScriptRuntime.ObjectClass) {
            return 6;
        }
        return 0;
    }

    public static Object convertArg(Context cx, Scriptable scope, Object arg, int typeTag) {
        switch (typeTag) {
            case 1: {
                if (arg instanceof String) {
                    return arg;
                }
                return ScriptRuntime.toString(arg);
            }
            case 2: {
                if (arg instanceof Integer) {
                    return arg;
                }
                return ScriptRuntime.toInt32(arg);
            }
            case 3: {
                if (arg instanceof Boolean) {
                    return arg;
                }
                return ScriptRuntime.toBoolean(arg) ? Boolean.TRUE : Boolean.FALSE;
            }
            case 4: {
                if (arg instanceof Double) {
                    return arg;
                }
                return new Double(ScriptRuntime.toNumber(arg));
            }
            case 5: {
                return ScriptRuntime.toObjectOrNull(cx, arg, scope);
            }
            case 6: {
                return arg;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int getArity() {
        return this.parmsLength < 0 ? 1 : this.parmsLength;
    }

    @Override
    public int getLength() {
        return this.getArity();
    }

    @Override
    public String getFunctionName() {
        return this.functionName == null ? "" : this.functionName;
    }

    public Member getMethodOrConstructor() {
        if (this.member.isMethod()) {
            return this.member.method();
        }
        return this.member.ctor();
    }

    static Method findSingleMethod(Method[] methods, String name) {
        Method found = null;
        int N = methods.length;
        for (int i = 0; i != N; ++i) {
            Method method = methods[i];
            if (method == null || !name.equals(method.getName())) continue;
            if (found != null) {
                throw Context.reportRuntimeError2("msg.no.overload", name, method.getDeclaringClass().getName());
            }
            found = method;
        }
        return found;
    }

    static Method[] getMethodList(Class<?> clazz) {
        Method[] methods = null;
        try {
            if (!sawSecurityException) {
                methods = clazz.getDeclaredMethods();
            }
        } catch (SecurityException e) {
            sawSecurityException = true;
        }
        if (methods == null) {
            methods = clazz.getMethods();
        }
        int count = 0;
        for (int i = 0; i < methods.length; ++i) {
            if (sawSecurityException ? methods[i].getDeclaringClass() != clazz : !Modifier.isPublic(methods[i].getModifiers())) {
                methods[i] = null;
                continue;
            }
            ++count;
        }
        Method[] result = new Method[count];
        int j = 0;
        for (int i = 0; i < methods.length; ++i) {
            if (methods[i] == null) continue;
            result[j++] = methods[i];
        }
        return result;
    }

    public void addAsConstructor(Scriptable scope, Scriptable prototype) {
        this.initAsConstructor(scope, prototype);
        FunctionObject.defineProperty(scope, prototype.getClassName(), this, 2);
    }

    void initAsConstructor(Scriptable scope, Scriptable prototype) {
        ScriptRuntime.setFunctionProtoAndParent(this, scope);
        this.setImmunePrototypeProperty(prototype);
        prototype.setParentScope(this);
        FunctionObject.defineProperty(prototype, "constructor", this, 7);
        this.setParentScope(scope);
    }

    @Deprecated
    public static Object convertArg(Context cx, Scriptable scope, Object arg, Class<?> desired) {
        int tag = FunctionObject.getTypeTag(desired);
        if (tag == 0) {
            throw Context.reportRuntimeError1("msg.cant.convert", desired.getName());
        }
        return FunctionObject.convertArg(cx, scope, arg, tag);
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object result;
        boolean checkMethodResult = false;
        int argsLength = args.length;
        for (int i = 0; i < argsLength; ++i) {
            if (!(args[i] instanceof ConsString)) continue;
            args[i] = args[i].toString();
        }
        if (this.parmsLength < 0) {
            if (this.parmsLength == -1) {
                Object[] invokeArgs = new Object[]{cx, thisObj, args, this};
                result = this.member.invoke(null, invokeArgs);
                checkMethodResult = true;
            } else {
                boolean inNewExpr = thisObj == null;
                Boolean b = inNewExpr ? Boolean.TRUE : Boolean.FALSE;
                Object[] invokeArgs = new Object[]{cx, args, this, b};
                result = this.member.isCtor() ? this.member.newInstance(invokeArgs) : this.member.invoke(null, invokeArgs);
            }
        } else {
            Object arg;
            int i;
            Object[] invokeArgs;
            Class<?> clazz;
            if (!this.isStatic && !(clazz = this.member.getDeclaringClass()).isInstance(thisObj)) {
                Scriptable parentScope;
                boolean compatible = false;
                if (thisObj == scope && scope != (parentScope = this.getParentScope()) && (compatible = clazz.isInstance(parentScope))) {
                    thisObj = parentScope;
                }
                if (!compatible) {
                    throw ScriptRuntime.typeError1("msg.incompat.call", this.functionName);
                }
            }
            if (this.parmsLength == argsLength) {
                invokeArgs = args;
                for (i = 0; i != this.parmsLength; ++i) {
                    arg = args[i];
                    Object converted = FunctionObject.convertArg(cx, scope, arg, this.typeTags[i]);
                    if (arg == converted) continue;
                    if (invokeArgs == args) {
                        invokeArgs = (Object[])args.clone();
                    }
                    invokeArgs[i] = converted;
                }
            } else if (this.parmsLength == 0) {
                invokeArgs = ScriptRuntime.emptyArgs;
            } else {
                invokeArgs = new Object[this.parmsLength];
                for (i = 0; i != this.parmsLength; ++i) {
                    arg = i < argsLength ? args[i] : Undefined.instance;
                    invokeArgs[i] = FunctionObject.convertArg(cx, scope, arg, this.typeTags[i]);
                }
            }
            if (this.member.isMethod()) {
                result = this.member.invoke(thisObj, invokeArgs);
                checkMethodResult = true;
            } else {
                result = this.member.newInstance(invokeArgs);
            }
        }
        if (checkMethodResult) {
            if (this.hasVoidReturn) {
                result = Undefined.instance;
            } else if (this.returnTypeTag == 0) {
                result = cx.getWrapFactory().wrap(cx, scope, result, null);
            }
        }
        return result;
    }

    @Override
    public Scriptable createObject(Context cx, Scriptable scope) {
        Scriptable result;
        if (this.member.isCtor() || this.parmsLength == -2) {
            return null;
        }
        try {
            result = (Scriptable)this.member.getDeclaringClass().newInstance();
        } catch (Exception ex) {
            throw Context.throwAsScriptRuntimeEx(ex);
        }
        result.setPrototype(this.getClassPrototype());
        result.setParentScope(this.getParentScope());
        return result;
    }

    boolean isVarArgsMethod() {
        return this.parmsLength == -1;
    }

    boolean isVarArgsConstructor() {
        return this.parmsLength == -2;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (this.parmsLength > 0) {
            Class<?>[] types = this.member.argTypes;
            this.typeTags = new byte[this.parmsLength];
            for (int i = 0; i != this.parmsLength; ++i) {
                this.typeTags[i] = (byte)FunctionObject.getTypeTag(types[i]);
            }
        }
        if (this.member.isMethod()) {
            Method method = this.member.method();
            Class<?> returnType = method.getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = FunctionObject.getTypeTag(returnType);
            }
        }
    }
}

