/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.JavaMembers;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ResolvedOverload;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

public class NativeJavaMethod
extends BaseFunction {
    static final long serialVersionUID = -3440381785576412928L;
    private static final int PREFERENCE_EQUAL = 0;
    private static final int PREFERENCE_FIRST_ARG = 1;
    private static final int PREFERENCE_SECOND_ARG = 2;
    private static final int PREFERENCE_AMBIGUOUS = 3;
    private static final boolean debug = false;
    MemberBox[] methods;
    private String functionName;
    private transient CopyOnWriteArrayList<ResolvedOverload> overloadCache;

    NativeJavaMethod(MemberBox[] methods) {
        this.functionName = methods[0].getName();
        this.methods = methods;
    }

    NativeJavaMethod(MemberBox[] methods, String name) {
        this.functionName = name;
        this.methods = methods;
    }

    NativeJavaMethod(MemberBox method, String name) {
        this.functionName = name;
        this.methods = new MemberBox[]{method};
    }

    public NativeJavaMethod(Method method, String name) {
        this(new MemberBox(method), name);
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    static String scriptSignature(Object[] values) {
        StringBuilder sig = new StringBuilder();
        for (int i = 0; i != values.length; ++i) {
            String s;
            Object value = values[i];
            if (value == null) {
                s = "null";
            } else if (value instanceof Boolean) {
                s = "boolean";
            } else if (value instanceof String) {
                s = "string";
            } else if (value instanceof Number) {
                s = "number";
            } else if (value instanceof Scriptable) {
                if (value instanceof Undefined) {
                    s = "undefined";
                } else if (value instanceof Wrapper) {
                    Object wrapped = ((Wrapper)value).unwrap();
                    s = wrapped.getClass().getName();
                } else {
                    s = value instanceof Function ? "function" : "object";
                }
            } else {
                s = JavaMembers.javaSignature(value.getClass());
            }
            if (i != 0) {
                sig.append(',');
            }
            sig.append(s);
        }
        return sig.toString();
    }

    @Override
    String decompile(int indent, int flags) {
        boolean justbody;
        StringBuilder sb = new StringBuilder();
        boolean bl = justbody = 0 != (flags & 1);
        if (!justbody) {
            sb.append("function ");
            sb.append(this.getFunctionName());
            sb.append("() {");
        }
        sb.append("/*\n");
        sb.append(this.toString());
        sb.append(justbody ? "*/\n" : "*/}\n");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int N = this.methods.length;
        for (int i = 0; i != N; ++i) {
            if (this.methods[i].isMethod()) {
                Method method = this.methods[i].method();
                sb.append(JavaMembers.javaSignature(method.getReturnType()));
                sb.append(' ');
                sb.append(method.getName());
            } else {
                sb.append(this.methods[i].getName());
            }
            sb.append(JavaMembers.liveConnectSignature(this.methods[i].argTypes));
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object javaObject;
        if (this.methods.length == 0) {
            throw new RuntimeException("No methods defined for call");
        }
        int index = this.findCachedFunction(cx, args);
        if (index < 0) {
            Class<?> c = this.methods[0].method().getDeclaringClass();
            String sig = c.getName() + '.' + this.getFunctionName() + '(' + NativeJavaMethod.scriptSignature(args) + ')';
            throw Context.reportRuntimeError1("msg.java.no_such_method", sig);
        }
        MemberBox meth = this.methods[index];
        Class<?>[] argTypes = meth.argTypes;
        if (meth.vararg) {
            Object varArgs;
            Object[] newArgs = new Object[argTypes.length];
            for (int i = 0; i < argTypes.length - 1; ++i) {
                newArgs[i] = Context.jsToJava(args[i], argTypes[i]);
            }
            if (args.length == argTypes.length && (args[args.length - 1] == null || args[args.length - 1] instanceof NativeArray || args[args.length - 1] instanceof NativeJavaArray)) {
                varArgs = Context.jsToJava(args[args.length - 1], argTypes[argTypes.length - 1]);
            } else {
                Class<?> componentType = argTypes[argTypes.length - 1].getComponentType();
                varArgs = Array.newInstance(componentType, args.length - argTypes.length + 1);
                for (int i = 0; i < Array.getLength(varArgs); ++i) {
                    Object value = Context.jsToJava(args[argTypes.length - 1 + i], componentType);
                    Array.set(varArgs, i, value);
                }
            }
            newArgs[argTypes.length - 1] = varArgs;
            args = newArgs;
        } else {
            Object[] origArgs = args;
            for (int i = 0; i < args.length; ++i) {
                Object arg = args[i];
                Object coerced = Context.jsToJava(arg, argTypes[i]);
                if (coerced == arg) continue;
                if (origArgs == args) {
                    args = (Object[])args.clone();
                }
                args[i] = coerced;
            }
        }
        if (meth.isStatic()) {
            javaObject = null;
        } else {
            Scriptable o = thisObj;
            Class<?> c = meth.getDeclaringClass();
            while (true) {
                if (o == null) {
                    throw Context.reportRuntimeError3("msg.nonjava.method", this.getFunctionName(), ScriptRuntime.toString(thisObj), c.getName());
                }
                if (o instanceof Wrapper && c.isInstance(javaObject = ((Wrapper)((Object)o)).unwrap())) break;
                o = o.getPrototype();
            }
        }
        Object retval = meth.invoke(javaObject, args);
        Class<?> staticType = meth.method().getReturnType();
        Object wrapped = cx.getWrapFactory().wrap(cx, scope, retval, staticType);
        if (wrapped == null && staticType == Void.TYPE) {
            wrapped = Undefined.instance;
        }
        return wrapped;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    int findCachedFunction(Context cx, Object[] args) {
        if (this.methods.length > 1) {
            if (this.overloadCache != null) {
                for (ResolvedOverload ovl : this.overloadCache) {
                    if (!ovl.matches(args)) continue;
                    return ovl.index;
                }
            } else {
                this.overloadCache = new CopyOnWriteArrayList();
            }
            int index = NativeJavaMethod.findFunction(cx, this.methods, args);
            if (this.overloadCache.size() < this.methods.length * 2) {
                CopyOnWriteArrayList<ResolvedOverload> copyOnWriteArrayList = this.overloadCache;
                synchronized (copyOnWriteArrayList) {
                    ResolvedOverload ovl = new ResolvedOverload(args, index);
                    if (!this.overloadCache.contains(ovl)) {
                        this.overloadCache.add(0, ovl);
                    }
                }
            }
            return index;
        }
        return NativeJavaMethod.findFunction(cx, this.methods, args);
    }

    static int findFunction(Context cx, MemberBox[] methodsOrCtors, Object[] args) {
        if (methodsOrCtors.length == 0) {
            return -1;
        }
        if (methodsOrCtors.length == 1) {
            MemberBox member = methodsOrCtors[0];
            Class<?>[] argTypes = member.argTypes;
            int alength = argTypes.length;
            if (member.vararg ? --alength > args.length : alength != args.length) {
                return -1;
            }
            for (int j = 0; j != alength; ++j) {
                if (NativeJavaObject.canConvert(args[j], argTypes[j])) continue;
                return -1;
            }
            return 0;
        }
        int firstBestFit = -1;
        int[] extraBestFits = null;
        int extraBestFitsCount = 0;
        block1: for (int i = 0; i < methodsOrCtors.length; ++i) {
            MemberBox member = methodsOrCtors[i];
            Class<?>[] argTypes = member.argTypes;
            int alength = argTypes.length;
            if (member.vararg ? --alength > args.length : alength != args.length) continue;
            for (int j = 0; j < alength; ++j) {
                if (!NativeJavaObject.canConvert(args[j], argTypes[j])) continue block1;
            }
            if (firstBestFit < 0) {
                firstBestFit = i;
                continue;
            }
            int betterCount = 0;
            int worseCount = 0;
            for (int j = -1; j != extraBestFitsCount; ++j) {
                int bestFitIndex = j == -1 ? firstBestFit : extraBestFits[j];
                MemberBox bestFit = methodsOrCtors[bestFitIndex];
                if (cx.hasFeature(13) && (bestFit.member().getModifiers() & 1) != (member.member().getModifiers() & 1)) {
                    if ((bestFit.member().getModifiers() & 1) == 0) {
                        ++betterCount;
                        continue;
                    }
                    ++worseCount;
                    continue;
                }
                int preference = NativeJavaMethod.preferSignature(args, argTypes, member.vararg, bestFit.argTypes, bestFit.vararg);
                if (preference == 3) break;
                if (preference == 1) {
                    ++betterCount;
                    continue;
                }
                if (preference == 2) {
                    ++worseCount;
                    continue;
                }
                if (preference != 0) {
                    Kit.codeBug();
                }
                if (!bestFit.isStatic() || !bestFit.getDeclaringClass().isAssignableFrom(member.getDeclaringClass())) continue block1;
                if (j == -1) {
                    firstBestFit = i;
                    continue block1;
                }
                extraBestFits[j] = i;
                continue block1;
            }
            if (betterCount == 1 + extraBestFitsCount) {
                firstBestFit = i;
                extraBestFitsCount = 0;
                continue;
            }
            if (worseCount == 1 + extraBestFitsCount) continue;
            if (extraBestFits == null) {
                extraBestFits = new int[methodsOrCtors.length - 1];
            }
            extraBestFits[extraBestFitsCount] = i;
            ++extraBestFitsCount;
        }
        if (firstBestFit < 0) {
            return -1;
        }
        if (extraBestFitsCount == 0) {
            return firstBestFit;
        }
        StringBuilder buf = new StringBuilder();
        for (int j = -1; j != extraBestFitsCount; ++j) {
            int bestFitIndex = j == -1 ? firstBestFit : extraBestFits[j];
            buf.append("\n    ");
            buf.append(methodsOrCtors[bestFitIndex].toJavaDeclaration());
        }
        MemberBox firstFitMember = methodsOrCtors[firstBestFit];
        String memberName = firstFitMember.getName();
        String memberClass = firstFitMember.getDeclaringClass().getName();
        if (methodsOrCtors[0].isCtor()) {
            throw Context.reportRuntimeError3("msg.constructor.ambiguous", memberName, NativeJavaMethod.scriptSignature(args), buf.toString());
        }
        throw Context.reportRuntimeError4("msg.method.ambiguous", memberClass, memberName, NativeJavaMethod.scriptSignature(args), buf.toString());
    }

    private static int preferSignature(Object[] args, Class<?>[] sig1, boolean vararg1, Class<?>[] sig2, boolean vararg2) {
        int totalPreference = 0;
        for (int j = 0; j < args.length; ++j) {
            int rank2;
            Class<?> type2;
            Class<?> type1 = vararg1 && j >= sig1.length ? sig1[sig1.length - 1] : sig1[j];
            Class<?> clazz = type2 = vararg2 && j >= sig2.length ? sig2[sig2.length - 1] : sig2[j];
            if (type1 == type2) continue;
            Object arg = args[j];
            int rank1 = NativeJavaObject.getConversionWeight(arg, type1);
            int preference = rank1 < (rank2 = NativeJavaObject.getConversionWeight(arg, type2)) ? 1 : (rank1 > rank2 ? 2 : (rank1 == 0 ? (type1.isAssignableFrom(type2) ? 2 : (type2.isAssignableFrom(type1) ? 1 : 3)) : 3));
            if ((totalPreference |= preference) == 3) break;
        }
        return totalPreference;
    }

    private static void printDebug(String msg, MemberBox member, Object[] args) {
    }
}

