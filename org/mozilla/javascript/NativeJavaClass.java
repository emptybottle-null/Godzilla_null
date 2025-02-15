/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.FieldAndMethods;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.JavaMembers;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeJavaMethod;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.WrapFactory;
import org.mozilla.javascript.Wrapper;

public class NativeJavaClass
extends NativeJavaObject
implements Function {
    static final long serialVersionUID = -6460763940409461664L;
    static final String javaClassPropertyName = "__javaObject__";
    private Map<String, FieldAndMethods> staticFieldAndMethods;

    public NativeJavaClass() {
    }

    public NativeJavaClass(Scriptable scope, Class<?> cl) {
        this(scope, cl, false);
    }

    public NativeJavaClass(Scriptable scope, Class<?> cl, boolean isAdapter) {
        super(scope, cl, null, isAdapter);
    }

    @Override
    protected void initMembers() {
        Class cl = (Class)this.javaObject;
        this.members = JavaMembers.lookupClass(this.parent, cl, cl, this.isAdapter);
        this.staticFieldAndMethods = this.members.getFieldAndMethodsObjects(this, cl, true);
    }

    @Override
    public String getClassName() {
        return "JavaClass";
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return this.members.has(name, true) || javaClassPropertyName.equals(name);
    }

    @Override
    public Object get(String name, Scriptable start) {
        FieldAndMethods result;
        if (name.equals("prototype")) {
            return null;
        }
        if (this.staticFieldAndMethods != null && (result = this.staticFieldAndMethods.get(name)) != null) {
            return result;
        }
        if (this.members.has(name, true)) {
            return this.members.get(this, name, this.javaObject, true);
        }
        Context cx = Context.getContext();
        Scriptable scope = ScriptableObject.getTopLevelScope(start);
        WrapFactory wrapFactory = cx.getWrapFactory();
        if (javaClassPropertyName.equals(name)) {
            return wrapFactory.wrap(cx, scope, this.javaObject, ScriptRuntime.ClassClass);
        }
        Class<?> nestedClass = NativeJavaClass.findNestedClass(this.getClassObject(), name);
        if (nestedClass != null) {
            Scriptable nestedValue = wrapFactory.wrapJavaClass(cx, scope, nestedClass);
            nestedValue.setParentScope(this);
            return nestedValue;
        }
        throw this.members.reportMemberNotFound(name);
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        this.members.put(this, name, this.javaObject, value, true);
    }

    @Override
    public Object[] getIds() {
        return this.members.getIds(true);
    }

    public Class<?> getClassObject() {
        return (Class)super.unwrap();
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        if (hint == null || hint == ScriptRuntime.StringClass) {
            return this.toString();
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
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (args.length == 1 && args[0] instanceof Scriptable) {
            Class<?> c = this.getClassObject();
            Scriptable p = (Scriptable)args[0];
            do {
                Object o;
                if (!(p instanceof Wrapper) || !c.isInstance(o = ((Wrapper)((Object)p)).unwrap())) continue;
                return p;
            } while ((p = p.getPrototype()) != null);
        }
        return this.construct(cx, scope, args);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        String msg;
        Class<?> classObject;
        block7: {
            classObject = this.getClassObject();
            int modifiers = classObject.getModifiers();
            if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers)) {
                NativeJavaMethod ctors = this.members.ctors;
                int index = ctors.findCachedFunction(cx, args);
                if (index < 0) {
                    String sig = NativeJavaMethod.scriptSignature(args);
                    throw Context.reportRuntimeError2("msg.no.java.ctor", classObject.getName(), sig);
                }
                return NativeJavaClass.constructSpecific(cx, scope, args, ctors.methods[index]);
            }
            if (args.length == 0) {
                throw Context.reportRuntimeError0("msg.adapter.zero.args");
            }
            Scriptable topLevel = ScriptableObject.getTopLevelScope(this);
            msg = "";
            try {
                if ("Dalvik".equals(System.getProperty("java.vm.name")) && classObject.isInterface()) {
                    Object obj = NativeJavaClass.createInterfaceAdapter(classObject, ScriptableObject.ensureScriptableObject(args[0]));
                    return cx.getWrapFactory().wrapAsJavaObject(cx, scope, obj, null);
                }
                Object v = topLevel.get("JavaAdapter", topLevel);
                if (v != NOT_FOUND) {
                    Function f = (Function)v;
                    Object[] adapterArgs = new Object[]{this, args[0]};
                    return f.construct(cx, topLevel, adapterArgs);
                }
            } catch (Exception ex) {
                String m = ex.getMessage();
                if (m == null) break block7;
                msg = m;
            }
        }
        throw Context.reportRuntimeError2("msg.cant.instantiate", msg, classObject.getName());
    }

    static Scriptable constructSpecific(Context cx, Scriptable scope, Object[] args, MemberBox ctor) {
        Object instance = NativeJavaClass.constructInternal(args, ctor);
        Scriptable topLevel = ScriptableObject.getTopLevelScope(scope);
        return cx.getWrapFactory().wrapNewObject(cx, topLevel, instance);
    }

    static Object constructInternal(Object[] args, MemberBox ctor) {
        Class<?>[] argTypes = ctor.argTypes;
        if (ctor.vararg) {
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
                Object x = Context.jsToJava(arg, argTypes[i]);
                if (x == arg) continue;
                if (args == origArgs) {
                    args = (Object[])origArgs.clone();
                }
                args[i] = x;
            }
        }
        return ctor.newInstance(args);
    }

    public String toString() {
        return "[JavaClass " + this.getClassObject().getName() + "]";
    }

    @Override
    public boolean hasInstance(Scriptable value) {
        if (value instanceof Wrapper && !(value instanceof NativeJavaClass)) {
            Object instance = ((Wrapper)((Object)value)).unwrap();
            return this.getClassObject().isInstance(instance);
        }
        return false;
    }

    private static Class<?> findNestedClass(Class<?> parentClass, String name) {
        String nestedClassName = parentClass.getName() + '$' + name;
        ClassLoader loader = parentClass.getClassLoader();
        if (loader == null) {
            return Kit.classOrNull(nestedClassName);
        }
        return Kit.classOrNull(loader, nestedClassName);
    }
}

