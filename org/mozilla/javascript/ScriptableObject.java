/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.ConstProperties;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ExternalArrayData;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.LazilyLoadedCtor;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;
import org.mozilla.javascript.debug.DebuggableObject;

public abstract class ScriptableObject
implements Scriptable,
Serializable,
DebuggableObject,
ConstProperties {
    static final long serialVersionUID = 2829861078851942586L;
    public static final int EMPTY = 0;
    public static final int READONLY = 1;
    public static final int DONTENUM = 2;
    public static final int PERMANENT = 4;
    public static final int UNINITIALIZED_CONST = 8;
    public static final int CONST = 13;
    private Scriptable prototypeObject;
    private Scriptable parentScopeObject;
    private transient Slot[] slots;
    private int count;
    private transient ExternalArrayData externalData;
    private transient Slot firstAdded;
    private transient Slot lastAdded;
    private volatile Map<Object, Object> associatedValues;
    private static final int SLOT_QUERY = 1;
    private static final int SLOT_MODIFY = 2;
    private static final int SLOT_MODIFY_CONST = 3;
    private static final int SLOT_MODIFY_GETTER_SETTER = 4;
    private static final int SLOT_CONVERT_ACCESSOR_TO_DATA = 5;
    private static final int INITIAL_SLOT_SIZE = 4;
    private boolean isExtensible = true;
    private static final Method GET_ARRAY_LENGTH;

    protected static ScriptableObject buildDataDescriptor(Scriptable scope, Object value, int attributes) {
        NativeObject desc = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
        desc.defineProperty("value", value, 0);
        desc.defineProperty("writable", (attributes & 1) == 0, 0);
        desc.defineProperty("enumerable", (attributes & 2) == 0, 0);
        desc.defineProperty("configurable", (attributes & 4) == 0, 0);
        return desc;
    }

    static void checkValidAttributes(int attributes) {
        int mask = 15;
        if ((attributes & 0xFFFFFFF0) != 0) {
            throw new IllegalArgumentException(String.valueOf(attributes));
        }
    }

    public ScriptableObject() {
    }

    public ScriptableObject(Scriptable scope, Scriptable prototype) {
        if (scope == null) {
            throw new IllegalArgumentException();
        }
        this.parentScopeObject = scope;
        this.prototypeObject = prototype;
    }

    public String getTypeOf() {
        return this.avoidObjectDetection() ? "undefined" : "object";
    }

    @Override
    public abstract String getClassName();

    @Override
    public boolean has(String name, Scriptable start) {
        return null != this.getSlot(name, 0, 1);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        if (this.externalData != null) {
            return index < this.externalData.getArrayLength();
        }
        return null != this.getSlot((String)null, index, 1);
    }

    @Override
    public Object get(String name, Scriptable start) {
        Slot slot = this.getSlot(name, 0, 1);
        if (slot == null) {
            return Scriptable.NOT_FOUND;
        }
        return slot.getValue(start);
    }

    @Override
    public Object get(int index, Scriptable start) {
        if (this.externalData != null) {
            if (index < this.externalData.getArrayLength()) {
                return this.externalData.getArrayElement(index);
            }
            return Scriptable.NOT_FOUND;
        }
        Slot slot = this.getSlot((String)null, index, 1);
        if (slot == null) {
            return Scriptable.NOT_FOUND;
        }
        return slot.getValue(start);
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        if (this.putImpl(name, 0, start, value)) {
            return;
        }
        if (start == this) {
            throw Kit.codeBug();
        }
        start.put(name, start, value);
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        if (this.externalData != null) {
            if (index >= this.externalData.getArrayLength()) {
                throw new JavaScriptException(ScriptRuntime.newNativeError(Context.getCurrentContext(), this, TopLevel.NativeErrors.RangeError, new Object[]{"External array index out of bounds "}), null, 0);
            }
            this.externalData.setArrayElement(index, value);
            return;
        }
        if (this.putImpl(null, index, start, value)) {
            return;
        }
        if (start == this) {
            throw Kit.codeBug();
        }
        start.put(index, start, value);
    }

    @Override
    public void delete(String name) {
        this.checkNotSealed(name, 0);
        this.removeSlot(name, 0);
    }

    @Override
    public void delete(int index) {
        this.checkNotSealed(null, index);
        this.removeSlot(null, index);
    }

    @Override
    public void putConst(String name, Scriptable start, Object value) {
        if (this.putConstImpl(name, 0, start, value, 1)) {
            return;
        }
        if (start == this) {
            throw Kit.codeBug();
        }
        if (start instanceof ConstProperties) {
            ((ConstProperties)((Object)start)).putConst(name, start, value);
        } else {
            start.put(name, start, value);
        }
    }

    @Override
    public void defineConst(String name, Scriptable start) {
        if (this.putConstImpl(name, 0, start, Undefined.instance, 8)) {
            return;
        }
        if (start == this) {
            throw Kit.codeBug();
        }
        if (start instanceof ConstProperties) {
            ((ConstProperties)((Object)start)).defineConst(name, start);
        }
    }

    @Override
    public boolean isConst(String name) {
        Slot slot = this.getSlot(name, 0, 1);
        if (slot == null) {
            return false;
        }
        return (slot.getAttributes() & 5) == 5;
    }

    @Deprecated
    public final int getAttributes(String name, Scriptable start) {
        return this.getAttributes(name);
    }

    @Deprecated
    public final int getAttributes(int index, Scriptable start) {
        return this.getAttributes(index);
    }

    @Deprecated
    public final void setAttributes(String name, Scriptable start, int attributes) {
        this.setAttributes(name, attributes);
    }

    @Deprecated
    public void setAttributes(int index, Scriptable start, int attributes) {
        this.setAttributes(index, attributes);
    }

    public int getAttributes(String name) {
        return this.findAttributeSlot(name, 0, 1).getAttributes();
    }

    public int getAttributes(int index) {
        return this.findAttributeSlot(null, index, 1).getAttributes();
    }

    public void setAttributes(String name, int attributes) {
        this.checkNotSealed(name, 0);
        this.findAttributeSlot(name, 0, 2).setAttributes(attributes);
    }

    public void setAttributes(int index, int attributes) {
        this.checkNotSealed(null, index);
        this.findAttributeSlot(null, index, 2).setAttributes(attributes);
    }

    public void setGetterOrSetter(String name, int index, Callable getterOrSetter, boolean isSetter) {
        this.setGetterOrSetter(name, index, getterOrSetter, isSetter, false);
    }

    private void setGetterOrSetter(String name, int index, Callable getterOrSetter, boolean isSetter, boolean force) {
        int attributes;
        GetterSlot gslot;
        if (name != null && index != 0) {
            throw new IllegalArgumentException(name);
        }
        if (!force) {
            this.checkNotSealed(name, index);
        }
        if (this.isExtensible()) {
            gslot = (GetterSlot)this.getSlot(name, index, 4);
        } else {
            Slot slot = ScriptableObject.unwrapSlot(this.getSlot(name, index, 1));
            if (!(slot instanceof GetterSlot)) {
                return;
            }
            gslot = (GetterSlot)slot;
        }
        if (!force && ((attributes = gslot.getAttributes()) & 1) != 0) {
            throw Context.reportRuntimeError1("msg.modify.readonly", name);
        }
        if (isSetter) {
            gslot.setter = getterOrSetter;
        } else {
            gslot.getter = getterOrSetter;
        }
        gslot.value = Undefined.instance;
    }

    public Object getGetterOrSetter(String name, int index, boolean isSetter) {
        if (name != null && index != 0) {
            throw new IllegalArgumentException(name);
        }
        Slot slot = ScriptableObject.unwrapSlot(this.getSlot(name, index, 1));
        if (slot == null) {
            return null;
        }
        if (slot instanceof GetterSlot) {
            GetterSlot gslot = (GetterSlot)slot;
            Object result = isSetter ? gslot.setter : gslot.getter;
            return result != null ? result : Undefined.instance;
        }
        return Undefined.instance;
    }

    protected boolean isGetterOrSetter(String name, int index, boolean setter) {
        Slot slot = ScriptableObject.unwrapSlot(this.getSlot(name, index, 1));
        if (slot instanceof GetterSlot) {
            if (setter && ((GetterSlot)slot).setter != null) {
                return true;
            }
            if (!setter && ((GetterSlot)slot).getter != null) {
                return true;
            }
        }
        return false;
    }

    void addLazilyInitializedValue(String name, int index, LazilyLoadedCtor init, int attributes) {
        if (name != null && index != 0) {
            throw new IllegalArgumentException(name);
        }
        this.checkNotSealed(name, index);
        GetterSlot gslot = (GetterSlot)this.getSlot(name, index, 4);
        gslot.setAttributes(attributes);
        gslot.getter = null;
        gslot.setter = null;
        gslot.value = init;
    }

    public void setExternalArrayData(ExternalArrayData array) {
        this.externalData = array;
        if (array == null) {
            this.delete("length");
        } else {
            this.defineProperty("length", null, GET_ARRAY_LENGTH, null, 3);
        }
    }

    public ExternalArrayData getExternalArrayData() {
        return this.externalData;
    }

    public Object getExternalArrayLength() {
        return this.externalData == null ? 0 : this.externalData.getArrayLength();
    }

    @Override
    public Scriptable getPrototype() {
        return this.prototypeObject;
    }

    @Override
    public void setPrototype(Scriptable m) {
        this.prototypeObject = m;
    }

    @Override
    public Scriptable getParentScope() {
        return this.parentScopeObject;
    }

    @Override
    public void setParentScope(Scriptable m) {
        this.parentScopeObject = m;
    }

    @Override
    public Object[] getIds() {
        return this.getIds(false);
    }

    @Override
    public Object[] getAllIds() {
        return this.getIds(true);
    }

    @Override
    public Object getDefaultValue(Class<?> typeHint) {
        return ScriptableObject.getDefaultValue(this, typeHint);
    }

    public static Object getDefaultValue(Scriptable object, Class<?> typeHint) {
        Context cx = null;
        for (int i = 0; i < 2; ++i) {
            Object u;
            Object[] args;
            String methodName;
            boolean tryToString;
            if (typeHint == ScriptRuntime.StringClass) {
                tryToString = i == 0;
            } else {
                boolean bl = tryToString = i == 1;
            }
            if (tryToString) {
                methodName = "toString";
                args = ScriptRuntime.emptyArgs;
            } else {
                String hint;
                methodName = "valueOf";
                args = new Object[1];
                if (typeHint == null) {
                    hint = "undefined";
                } else if (typeHint == ScriptRuntime.StringClass) {
                    hint = "string";
                } else if (typeHint == ScriptRuntime.ScriptableClass) {
                    hint = "object";
                } else if (typeHint == ScriptRuntime.FunctionClass) {
                    hint = "function";
                } else if (typeHint == ScriptRuntime.BooleanClass || typeHint == Boolean.TYPE) {
                    hint = "boolean";
                } else if (typeHint == ScriptRuntime.NumberClass || typeHint == ScriptRuntime.ByteClass || typeHint == Byte.TYPE || typeHint == ScriptRuntime.ShortClass || typeHint == Short.TYPE || typeHint == ScriptRuntime.IntegerClass || typeHint == Integer.TYPE || typeHint == ScriptRuntime.FloatClass || typeHint == Float.TYPE || typeHint == ScriptRuntime.DoubleClass || typeHint == Double.TYPE) {
                    hint = "number";
                } else {
                    throw Context.reportRuntimeError1("msg.invalid.type", typeHint.toString());
                }
                args[0] = hint;
            }
            Object v = ScriptableObject.getProperty(object, methodName);
            if (!(v instanceof Function)) continue;
            Function fun = (Function)v;
            if (cx == null) {
                cx = Context.getContext();
            }
            if ((v = fun.call(cx, fun.getParentScope(), object, args)) == null) continue;
            if (!(v instanceof Scriptable)) {
                return v;
            }
            if (typeHint == ScriptRuntime.ScriptableClass || typeHint == ScriptRuntime.FunctionClass) {
                return v;
            }
            if (!tryToString || !(v instanceof Wrapper) || !((u = ((Wrapper)v).unwrap()) instanceof String)) continue;
            return u;
        }
        String arg = typeHint == null ? "undefined" : typeHint.getName();
        throw ScriptRuntime.typeError1("msg.default.value", arg);
    }

    @Override
    public boolean hasInstance(Scriptable instance) {
        return ScriptRuntime.jsDelegatesTo(instance, this);
    }

    public boolean avoidObjectDetection() {
        return false;
    }

    protected Object equivalentValues(Object value) {
        return this == value ? Boolean.TRUE : Scriptable.NOT_FOUND;
    }

    public static <T extends Scriptable> void defineClass(Scriptable scope, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ScriptableObject.defineClass(scope, clazz, false, false);
    }

    public static <T extends Scriptable> void defineClass(Scriptable scope, Class<T> clazz, boolean sealed) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ScriptableObject.defineClass(scope, clazz, sealed, false);
    }

    public static <T extends Scriptable> String defineClass(Scriptable scope, Class<T> clazz, boolean sealed, boolean mapInheritance) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        BaseFunction ctor = ScriptableObject.buildClassCtor(scope, clazz, sealed, mapInheritance);
        if (ctor == null) {
            return null;
        }
        String name = ctor.getClassPrototype().getClassName();
        ScriptableObject.defineProperty(scope, name, ctor, 2);
        return name;
    }

    static <T extends Scriptable> BaseFunction buildClassCtor(Scriptable scope, Class<T> clazz, boolean sealed, boolean mapInheritance) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        FunctionObject ctor;
        Class<T> superScriptable;
        String name;
        Class<T> superClass;
        Object existingProto;
        AccessibleObject[] methods = FunctionObject.getMethodList(clazz);
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            if (!method.getName().equals("init")) continue;
            Class<?>[] parmTypes = method.getParameterTypes();
            if (parmTypes.length == 3 && parmTypes[0] == ScriptRuntime.ContextClass && parmTypes[1] == ScriptRuntime.ScriptableClass && parmTypes[2] == Boolean.TYPE && Modifier.isStatic(method.getModifiers())) {
                Object[] args = new Object[]{Context.getContext(), scope, sealed ? Boolean.TRUE : Boolean.FALSE};
                method.invoke(null, args);
                return null;
            }
            if (parmTypes.length != 1 || parmTypes[0] != ScriptRuntime.ScriptableClass || !Modifier.isStatic(method.getModifiers())) continue;
            Object[] args = new Object[]{scope};
            method.invoke(null, args);
            return null;
        }
        AccessibleObject[] ctors = clazz.getConstructors();
        Constructor<?> protoCtor = null;
        for (int i = 0; i < ctors.length; ++i) {
            if (ctors[i].getParameterTypes().length != 0) continue;
            protoCtor = ctors[i];
            break;
        }
        if (protoCtor == null) {
            throw Context.reportRuntimeError1("msg.zero.arg.ctor", clazz.getName());
        }
        Scriptable proto = (Scriptable)protoCtor.newInstance(ScriptRuntime.emptyArgs);
        String className = proto.getClassName();
        Object existing = ScriptableObject.getProperty(ScriptableObject.getTopLevelScope(scope), className);
        if (existing instanceof BaseFunction && (existingProto = ((BaseFunction)existing).getPrototypeProperty()) != null && clazz.equals(existingProto.getClass())) {
            return (BaseFunction)existing;
        }
        Scriptable superProto = null;
        if (mapInheritance && ScriptRuntime.ScriptableClass.isAssignableFrom(superClass = clazz.getSuperclass()) && !Modifier.isAbstract(superClass.getModifiers()) && (name = ScriptableObject.defineClass(scope, superScriptable = ScriptableObject.extendsScriptable(superClass), sealed, mapInheritance)) != null) {
            superProto = ScriptableObject.getClassPrototype(scope, name);
        }
        if (superProto == null) {
            superProto = ScriptableObject.getObjectPrototype(scope);
        }
        proto.setPrototype(superProto);
        String functionPrefix = "jsFunction_";
        String staticFunctionPrefix = "jsStaticFunction_";
        String getterPrefix = "jsGet_";
        String setterPrefix = "jsSet_";
        String ctorName = "jsConstructor";
        Object ctorMember = ScriptableObject.findAnnotatedMember(methods, JSConstructor.class);
        if (ctorMember == null) {
            ctorMember = ScriptableObject.findAnnotatedMember(ctors, JSConstructor.class);
        }
        if (ctorMember == null) {
            ctorMember = FunctionObject.findSingleMethod((Method[])methods, "jsConstructor");
        }
        if (ctorMember == null) {
            if (ctors.length == 1) {
                ctorMember = ctors[0];
            } else if (ctors.length == 2) {
                if (((Constructor)ctors[0]).getParameterTypes().length == 0) {
                    ctorMember = ctors[1];
                } else if (((Constructor)ctors[1]).getParameterTypes().length == 0) {
                    ctorMember = ctors[0];
                }
            }
            if (ctorMember == null) {
                throw Context.reportRuntimeError1("msg.ctor.multiple.parms", clazz.getName());
            }
        }
        if ((ctor = new FunctionObject(className, (Member)ctorMember, scope)).isVarArgsMethod()) {
            throw Context.reportRuntimeError1("msg.varargs.ctor", ctorMember.getName());
        }
        ctor.initAsConstructor(scope, proto);
        AccessibleObject finishInit = null;
        HashSet<String> staticNames = new HashSet<String>();
        HashSet instanceNames = new HashSet();
        for (AccessibleObject method : methods) {
            String propName;
            boolean isStatic;
            HashSet<String> names;
            Class<?>[] parmTypes;
            if (method == ctorMember) continue;
            String name2 = ((Method)method).getName();
            if (name2.equals("finishInit") && (parmTypes = ((Method)method).getParameterTypes()).length == 3 && parmTypes[0] == ScriptRuntime.ScriptableClass && parmTypes[1] == FunctionObject.class && parmTypes[2] == ScriptRuntime.ScriptableClass && Modifier.isStatic(((Method)method).getModifiers())) {
                finishInit = method;
                continue;
            }
            if (name2.indexOf(36) != -1 || name2.equals("jsConstructor")) continue;
            Annotation annotation = null;
            String prefix = null;
            if (method.isAnnotationPresent(JSFunction.class)) {
                annotation = ((Method)method).getAnnotation(JSFunction.class);
            } else if (method.isAnnotationPresent(JSStaticFunction.class)) {
                annotation = ((Method)method).getAnnotation(JSStaticFunction.class);
            } else if (method.isAnnotationPresent(JSGetter.class)) {
                annotation = ((Method)method).getAnnotation(JSGetter.class);
            } else if (method.isAnnotationPresent(JSSetter.class)) continue;
            if (annotation == null) {
                if (name2.startsWith("jsFunction_")) {
                    prefix = "jsFunction_";
                } else if (name2.startsWith("jsStaticFunction_")) {
                    prefix = "jsStaticFunction_";
                } else if (name2.startsWith("jsGet_")) {
                    prefix = "jsGet_";
                } else if (annotation == null) continue;
            }
            if ((names = (isStatic = annotation instanceof JSStaticFunction || prefix == "jsStaticFunction_") ? staticNames : instanceNames).contains(propName = ScriptableObject.getPropertyName(name2, prefix, annotation))) {
                throw Context.reportRuntimeError2("duplicate.defineClass.name", name2, propName);
            }
            names.add(propName);
            name2 = propName;
            if (annotation instanceof JSGetter || prefix == "jsGet_") {
                if (!(proto instanceof ScriptableObject)) {
                    throw Context.reportRuntimeError2("msg.extend.scriptable", proto.getClass().toString(), name2);
                }
                Method setter = ScriptableObject.findSetterMethod((Method[])methods, name2, "jsSet_");
                int attr = 6 | (setter != null ? 0 : 1);
                ((ScriptableObject)proto).defineProperty(name2, null, (Method)method, setter, attr);
                continue;
            }
            if (isStatic && !Modifier.isStatic(((Method)method).getModifiers())) {
                throw Context.reportRuntimeError("jsStaticFunction must be used with static method.");
            }
            FunctionObject f = new FunctionObject(name2, (Member)((Object)method), proto);
            if (f.isVarArgsConstructor()) {
                throw Context.reportRuntimeError1("msg.varargs.fun", ctorMember.getName());
            }
            ScriptableObject.defineProperty(isStatic ? ctor : proto, name2, f, 2);
            if (!sealed) continue;
            f.sealObject();
        }
        if (finishInit != null) {
            Object[] finishArgs = new Object[]{scope, ctor, proto};
            finishInit.invoke(null, finishArgs);
        }
        if (sealed) {
            ctor.sealObject();
            if (proto instanceof ScriptableObject) {
                ((ScriptableObject)proto).sealObject();
            }
        }
        return ctor;
    }

    private static Member findAnnotatedMember(AccessibleObject[] members, Class<? extends Annotation> annotation) {
        for (AccessibleObject member : members) {
            if (!member.isAnnotationPresent(annotation)) continue;
            return (Member)((Object)member);
        }
        return null;
    }

    private static Method findSetterMethod(Method[] methods, String name, String prefix) {
        String newStyleName = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        for (Method method : methods) {
            JSSetter annotation = method.getAnnotation(JSSetter.class);
            if (annotation == null || !name.equals(annotation.value()) && (!"".equals(annotation.value()) || !newStyleName.equals(method.getName()))) continue;
            return method;
        }
        String oldStyleName = prefix + name;
        for (Method method : methods) {
            if (!oldStyleName.equals(method.getName())) continue;
            return method;
        }
        return null;
    }

    private static String getPropertyName(String methodName, String prefix, Annotation annotation) {
        if (prefix != null) {
            return methodName.substring(prefix.length());
        }
        String propName = null;
        if (annotation instanceof JSGetter) {
            propName = ((JSGetter)annotation).value();
            if ((propName == null || propName.length() == 0) && methodName.length() > 3 && methodName.startsWith("get") && Character.isUpperCase((propName = methodName.substring(3)).charAt(0))) {
                if (propName.length() == 1) {
                    propName = propName.toLowerCase();
                } else if (!Character.isUpperCase(propName.charAt(1))) {
                    propName = Character.toLowerCase(propName.charAt(0)) + propName.substring(1);
                }
            }
        } else if (annotation instanceof JSFunction) {
            propName = ((JSFunction)annotation).value();
        } else if (annotation instanceof JSStaticFunction) {
            propName = ((JSStaticFunction)annotation).value();
        }
        if (propName == null || propName.length() == 0) {
            propName = methodName;
        }
        return propName;
    }

    private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> c) {
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(c)) {
            return c;
        }
        return null;
    }

    public void defineProperty(String propertyName, Object value, int attributes) {
        this.checkNotSealed(propertyName, 0);
        this.put(propertyName, (Scriptable)this, value);
        this.setAttributes(propertyName, attributes);
    }

    public static void defineProperty(Scriptable destination, String propertyName, Object value, int attributes) {
        if (!(destination instanceof ScriptableObject)) {
            destination.put(propertyName, destination, value);
            return;
        }
        ScriptableObject so = (ScriptableObject)destination;
        so.defineProperty(propertyName, value, attributes);
    }

    public static void defineConstProperty(Scriptable destination, String propertyName) {
        if (destination instanceof ConstProperties) {
            ConstProperties cp = (ConstProperties)((Object)destination);
            cp.defineConst(propertyName, destination);
        } else {
            ScriptableObject.defineProperty(destination, propertyName, Undefined.instance, 13);
        }
    }

    public void defineProperty(String propertyName, Class<?> clazz, int attributes) {
        int length = propertyName.length();
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        char[] buf = new char[3 + length];
        propertyName.getChars(0, length, buf, 3);
        buf[3] = Character.toUpperCase(buf[3]);
        buf[0] = 103;
        buf[1] = 101;
        buf[2] = 116;
        String getterName = new String(buf);
        buf[0] = 115;
        String setterName = new String(buf);
        Method[] methods = FunctionObject.getMethodList(clazz);
        Method getter = FunctionObject.findSingleMethod(methods, getterName);
        Method setter = FunctionObject.findSingleMethod(methods, setterName);
        if (setter == null) {
            attributes |= 1;
        }
        this.defineProperty(propertyName, null, getter, setter == null ? null : setter, attributes);
    }

    public void defineProperty(String propertyName, Object delegateTo, Method getter, Method setter, int attributes) {
        MemberBox getterBox = null;
        if (getter != null) {
            boolean delegatedForm;
            getterBox = new MemberBox(getter);
            if (!Modifier.isStatic(getter.getModifiers())) {
                delegatedForm = delegateTo != null;
                getterBox.delegateTo = delegateTo;
            } else {
                delegatedForm = true;
                getterBox.delegateTo = Void.TYPE;
            }
            String errorId = null;
            Class<?>[] parmTypes = getter.getParameterTypes();
            if (parmTypes.length == 0) {
                if (delegatedForm) {
                    errorId = "msg.obj.getter.parms";
                }
            } else if (parmTypes.length == 1) {
                Class<?> argType = parmTypes[0];
                if (argType != ScriptRuntime.ScriptableClass && argType != ScriptRuntime.ScriptableObjectClass) {
                    errorId = "msg.bad.getter.parms";
                } else if (!delegatedForm) {
                    errorId = "msg.bad.getter.parms";
                }
            } else {
                errorId = "msg.bad.getter.parms";
            }
            if (errorId != null) {
                throw Context.reportRuntimeError1(errorId, getter.toString());
            }
        }
        MemberBox setterBox = null;
        if (setter != null) {
            boolean delegatedForm;
            if (setter.getReturnType() != Void.TYPE) {
                throw Context.reportRuntimeError1("msg.setter.return", setter.toString());
            }
            setterBox = new MemberBox(setter);
            if (!Modifier.isStatic(setter.getModifiers())) {
                delegatedForm = delegateTo != null;
                setterBox.delegateTo = delegateTo;
            } else {
                delegatedForm = true;
                setterBox.delegateTo = Void.TYPE;
            }
            String errorId = null;
            Class<?>[] parmTypes = setter.getParameterTypes();
            if (parmTypes.length == 1) {
                if (delegatedForm) {
                    errorId = "msg.setter2.expected";
                }
            } else if (parmTypes.length == 2) {
                Class<?> argType = parmTypes[0];
                if (argType != ScriptRuntime.ScriptableClass && argType != ScriptRuntime.ScriptableObjectClass) {
                    errorId = "msg.setter2.parms";
                } else if (!delegatedForm) {
                    errorId = "msg.setter1.parms";
                }
            } else {
                errorId = "msg.setter.parms";
            }
            if (errorId != null) {
                throw Context.reportRuntimeError1(errorId, setter.toString());
            }
        }
        GetterSlot gslot = (GetterSlot)this.getSlot(propertyName, 0, 4);
        gslot.setAttributes(attributes);
        gslot.getter = getterBox;
        gslot.setter = setterBox;
    }

    public void defineOwnProperties(Context cx, ScriptableObject props) {
        int i;
        Object[] ids = props.getIds();
        ScriptableObject[] descs = new ScriptableObject[ids.length];
        int len = ids.length;
        for (i = 0; i < len; ++i) {
            Object descObj = ScriptRuntime.getObjectElem(props, ids[i], cx);
            ScriptableObject desc = ScriptableObject.ensureScriptableObject(descObj);
            this.checkPropertyDefinition(desc);
            descs[i] = desc;
        }
        len = ids.length;
        for (i = 0; i < len; ++i) {
            this.defineOwnProperty(cx, ids[i], descs[i]);
        }
    }

    public void defineOwnProperty(Context cx, Object id, ScriptableObject desc) {
        this.checkPropertyDefinition(desc);
        this.defineOwnProperty(cx, id, desc, true);
    }

    protected void defineOwnProperty(Context cx, Object id, ScriptableObject desc, boolean checkValid) {
        int attributes;
        boolean isNew;
        Slot slot = this.getSlot(cx, id, 1);
        boolean bl = isNew = slot == null;
        if (checkValid) {
            ScriptableObject current = slot == null ? null : slot.getPropertyDescriptor(cx, this);
            String name = ScriptRuntime.toString(id);
            this.checkPropertyChange(name, current, desc);
        }
        boolean isAccessor = this.isAccessorDescriptor(desc);
        if (slot == null) {
            slot = this.getSlot(cx, id, isAccessor ? 4 : 2);
            attributes = this.applyDescriptorToAttributeBitset(7, desc);
        } else {
            attributes = this.applyDescriptorToAttributeBitset(slot.getAttributes(), desc);
        }
        slot = ScriptableObject.unwrapSlot(slot);
        if (isAccessor) {
            Object setter;
            if (!(slot instanceof GetterSlot)) {
                slot = this.getSlot(cx, id, 4);
            }
            GetterSlot gslot = (GetterSlot)slot;
            Object getter = ScriptableObject.getProperty((Scriptable)desc, "get");
            if (getter != NOT_FOUND) {
                gslot.getter = getter;
            }
            if ((setter = ScriptableObject.getProperty((Scriptable)desc, "set")) != NOT_FOUND) {
                gslot.setter = setter;
            }
            gslot.value = Undefined.instance;
            gslot.setAttributes(attributes);
        } else {
            Object value;
            if (slot instanceof GetterSlot && this.isDataDescriptor(desc)) {
                slot = this.getSlot(cx, id, 5);
            }
            if ((value = ScriptableObject.getProperty((Scriptable)desc, "value")) != NOT_FOUND) {
                slot.value = value;
            } else if (isNew) {
                slot.value = Undefined.instance;
            }
            slot.setAttributes(attributes);
        }
    }

    protected void checkPropertyDefinition(ScriptableObject desc) {
        Object getter = ScriptableObject.getProperty((Scriptable)desc, "get");
        if (getter != NOT_FOUND && getter != Undefined.instance && !(getter instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(getter);
        }
        Object setter = ScriptableObject.getProperty((Scriptable)desc, "set");
        if (setter != NOT_FOUND && setter != Undefined.instance && !(setter instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(setter);
        }
        if (this.isDataDescriptor(desc) && this.isAccessorDescriptor(desc)) {
            throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");
        }
    }

    protected void checkPropertyChange(String id, ScriptableObject current, ScriptableObject desc) {
        if (current == null) {
            if (!this.isExtensible()) {
                throw ScriptRuntime.typeError0("msg.not.extensible");
            }
        } else if (ScriptableObject.isFalse(current.get("configurable", (Scriptable)current))) {
            if (ScriptableObject.isTrue(ScriptableObject.getProperty((Scriptable)desc, "configurable"))) {
                throw ScriptRuntime.typeError1("msg.change.configurable.false.to.true", id);
            }
            if (ScriptableObject.isTrue(current.get("enumerable", (Scriptable)current)) != ScriptableObject.isTrue(ScriptableObject.getProperty((Scriptable)desc, "enumerable"))) {
                throw ScriptRuntime.typeError1("msg.change.enumerable.with.configurable.false", id);
            }
            boolean isData = this.isDataDescriptor(desc);
            boolean isAccessor = this.isAccessorDescriptor(desc);
            if (isData || isAccessor) {
                if (isData && this.isDataDescriptor(current)) {
                    if (ScriptableObject.isFalse(current.get("writable", (Scriptable)current))) {
                        if (ScriptableObject.isTrue(ScriptableObject.getProperty((Scriptable)desc, "writable"))) {
                            throw ScriptRuntime.typeError1("msg.change.writable.false.to.true.with.configurable.false", id);
                        }
                        if (!this.sameValue(ScriptableObject.getProperty((Scriptable)desc, "value"), current.get("value", (Scriptable)current))) {
                            throw ScriptRuntime.typeError1("msg.change.value.with.writable.false", id);
                        }
                    }
                } else if (isAccessor && this.isAccessorDescriptor(current)) {
                    if (!this.sameValue(ScriptableObject.getProperty((Scriptable)desc, "set"), current.get("set", (Scriptable)current))) {
                        throw ScriptRuntime.typeError1("msg.change.setter.with.configurable.false", id);
                    }
                    if (!this.sameValue(ScriptableObject.getProperty((Scriptable)desc, "get"), current.get("get", (Scriptable)current))) {
                        throw ScriptRuntime.typeError1("msg.change.getter.with.configurable.false", id);
                    }
                } else {
                    if (this.isDataDescriptor(current)) {
                        throw ScriptRuntime.typeError1("msg.change.property.data.to.accessor.with.configurable.false", id);
                    }
                    throw ScriptRuntime.typeError1("msg.change.property.accessor.to.data.with.configurable.false", id);
                }
            }
        }
    }

    protected static boolean isTrue(Object value) {
        return value != NOT_FOUND && ScriptRuntime.toBoolean(value);
    }

    protected static boolean isFalse(Object value) {
        return !ScriptableObject.isTrue(value);
    }

    protected boolean sameValue(Object newValue, Object currentValue) {
        if (newValue == NOT_FOUND) {
            return true;
        }
        if (currentValue == NOT_FOUND) {
            currentValue = Undefined.instance;
        }
        if (currentValue instanceof Number && newValue instanceof Number) {
            double d1 = ((Number)currentValue).doubleValue();
            double d2 = ((Number)newValue).doubleValue();
            if (Double.isNaN(d1) && Double.isNaN(d2)) {
                return true;
            }
            if (d1 == 0.0 && Double.doubleToLongBits(d1) != Double.doubleToLongBits(d2)) {
                return false;
            }
        }
        return ScriptRuntime.shallowEq(currentValue, newValue);
    }

    protected int applyDescriptorToAttributeBitset(int attributes, ScriptableObject desc) {
        Object configurable;
        Object writable;
        Object enumerable = ScriptableObject.getProperty((Scriptable)desc, "enumerable");
        if (enumerable != NOT_FOUND) {
            int n = attributes = ScriptRuntime.toBoolean(enumerable) ? attributes & 0xFFFFFFFD : attributes | 2;
        }
        if ((writable = ScriptableObject.getProperty((Scriptable)desc, "writable")) != NOT_FOUND) {
            int n = attributes = ScriptRuntime.toBoolean(writable) ? attributes & 0xFFFFFFFE : attributes | 1;
        }
        if ((configurable = ScriptableObject.getProperty((Scriptable)desc, "configurable")) != NOT_FOUND) {
            attributes = ScriptRuntime.toBoolean(configurable) ? attributes & 0xFFFFFFFB : attributes | 4;
        }
        return attributes;
    }

    protected boolean isDataDescriptor(ScriptableObject desc) {
        return ScriptableObject.hasProperty((Scriptable)desc, "value") || ScriptableObject.hasProperty((Scriptable)desc, "writable");
    }

    protected boolean isAccessorDescriptor(ScriptableObject desc) {
        return ScriptableObject.hasProperty((Scriptable)desc, "get") || ScriptableObject.hasProperty((Scriptable)desc, "set");
    }

    protected boolean isGenericDescriptor(ScriptableObject desc) {
        return !this.isDataDescriptor(desc) && !this.isAccessorDescriptor(desc);
    }

    protected static Scriptable ensureScriptable(Object arg) {
        if (!(arg instanceof Scriptable)) {
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(arg));
        }
        return (Scriptable)arg;
    }

    protected static ScriptableObject ensureScriptableObject(Object arg) {
        if (!(arg instanceof ScriptableObject)) {
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(arg));
        }
        return (ScriptableObject)arg;
    }

    public void defineFunctionProperties(String[] names, Class<?> clazz, int attributes) {
        Method[] methods = FunctionObject.getMethodList(clazz);
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            Method m = FunctionObject.findSingleMethod(methods, name);
            if (m == null) {
                throw Context.reportRuntimeError2("msg.method.not.found", name, clazz.getName());
            }
            FunctionObject f = new FunctionObject(name, m, this);
            this.defineProperty(name, f, attributes);
        }
    }

    public static Scriptable getObjectPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(scope), TopLevel.Builtins.Object);
    }

    public static Scriptable getFunctionPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(scope), TopLevel.Builtins.Function);
    }

    public static Scriptable getArrayPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(scope), TopLevel.Builtins.Array);
    }

    public static Scriptable getClassPrototype(Scriptable scope, String className) {
        Object proto;
        Object ctor = ScriptableObject.getProperty(scope = ScriptableObject.getTopLevelScope(scope), className);
        if (ctor instanceof BaseFunction) {
            proto = ((BaseFunction)ctor).getPrototypeProperty();
        } else if (ctor instanceof Scriptable) {
            Scriptable ctorObj = (Scriptable)ctor;
            proto = ctorObj.get("prototype", ctorObj);
        } else {
            return null;
        }
        if (proto instanceof Scriptable) {
            return (Scriptable)proto;
        }
        return null;
    }

    public static Scriptable getTopLevelScope(Scriptable obj) {
        Scriptable parent;
        while ((parent = obj.getParentScope()) != null) {
            obj = parent;
        }
        return obj;
    }

    public boolean isExtensible() {
        return this.isExtensible;
    }

    public void preventExtensions() {
        this.isExtensible = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void sealObject() {
        if (this.count >= 0) {
            Slot slot = this.firstAdded;
            while (slot != null) {
                Object value = slot.value;
                if (value instanceof LazilyLoadedCtor) {
                    LazilyLoadedCtor initializer = (LazilyLoadedCtor)value;
                    try {
                        initializer.init();
                    } finally {
                        slot.value = initializer.getValue();
                    }
                }
                slot = slot.orderedNext;
            }
            this.count ^= 0xFFFFFFFF;
        }
    }

    public final boolean isSealed() {
        return this.count < 0;
    }

    private void checkNotSealed(String name, int index) {
        if (!this.isSealed()) {
            return;
        }
        String str = name != null ? name : Integer.toString(index);
        throw Context.reportRuntimeError1("msg.modify.sealed", str);
    }

    public static Object getProperty(Scriptable obj, String name) {
        Object result;
        Scriptable start = obj;
        while ((result = obj.get(name, start)) == Scriptable.NOT_FOUND && (obj = obj.getPrototype()) != null) {
        }
        return result;
    }

    public static <T> T getTypedProperty(Scriptable s, int index, Class<T> type) {
        Object val = ScriptableObject.getProperty(s, index);
        if (val == Scriptable.NOT_FOUND) {
            val = null;
        }
        return type.cast(Context.jsToJava(val, type));
    }

    public static Object getProperty(Scriptable obj, int index) {
        Object result;
        Scriptable start = obj;
        while ((result = obj.get(index, start)) == Scriptable.NOT_FOUND && (obj = obj.getPrototype()) != null) {
        }
        return result;
    }

    public static <T> T getTypedProperty(Scriptable s, String name, Class<T> type) {
        Object val = ScriptableObject.getProperty(s, name);
        if (val == Scriptable.NOT_FOUND) {
            val = null;
        }
        return type.cast(Context.jsToJava(val, type));
    }

    public static boolean hasProperty(Scriptable obj, String name) {
        return null != ScriptableObject.getBase(obj, name);
    }

    public static void redefineProperty(Scriptable obj, String name, boolean isConst) {
        ConstProperties cp;
        Scriptable base = ScriptableObject.getBase(obj, name);
        if (base == null) {
            return;
        }
        if (base instanceof ConstProperties && (cp = (ConstProperties)((Object)base)).isConst(name)) {
            throw ScriptRuntime.typeError1("msg.const.redecl", name);
        }
        if (isConst) {
            throw ScriptRuntime.typeError1("msg.var.redecl", name);
        }
    }

    public static boolean hasProperty(Scriptable obj, int index) {
        return null != ScriptableObject.getBase(obj, index);
    }

    public static void putProperty(Scriptable obj, String name, Object value) {
        Scriptable base = ScriptableObject.getBase(obj, name);
        if (base == null) {
            base = obj;
        }
        base.put(name, obj, value);
    }

    public static void putConstProperty(Scriptable obj, String name, Object value) {
        Scriptable base = ScriptableObject.getBase(obj, name);
        if (base == null) {
            base = obj;
        }
        if (base instanceof ConstProperties) {
            ((ConstProperties)((Object)base)).putConst(name, obj, value);
        }
    }

    public static void putProperty(Scriptable obj, int index, Object value) {
        Scriptable base = ScriptableObject.getBase(obj, index);
        if (base == null) {
            base = obj;
        }
        base.put(index, obj, value);
    }

    public static boolean deleteProperty(Scriptable obj, String name) {
        Scriptable base = ScriptableObject.getBase(obj, name);
        if (base == null) {
            return true;
        }
        base.delete(name);
        return !base.has(name, obj);
    }

    public static boolean deleteProperty(Scriptable obj, int index) {
        Scriptable base = ScriptableObject.getBase(obj, index);
        if (base == null) {
            return true;
        }
        base.delete(index);
        return !base.has(index, obj);
    }

    public static Object[] getPropertyIds(Scriptable obj) {
        if (obj == null) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] result = obj.getIds();
        ObjToIntMap map = null;
        while ((obj = obj.getPrototype()) != null) {
            int i;
            Object[] ids = obj.getIds();
            if (ids.length == 0) continue;
            if (map == null) {
                if (result.length == 0) {
                    result = ids;
                    continue;
                }
                map = new ObjToIntMap(result.length + ids.length);
                for (i = 0; i != result.length; ++i) {
                    map.intern(result[i]);
                }
                result = null;
            }
            for (i = 0; i != ids.length; ++i) {
                map.intern(ids[i]);
            }
        }
        if (map != null) {
            result = map.getKeys();
        }
        return result;
    }

    public static Object callMethod(Scriptable obj, String methodName, Object[] args) {
        return ScriptableObject.callMethod(null, obj, methodName, args);
    }

    public static Object callMethod(Context cx, Scriptable obj, String methodName, Object[] args) {
        Object funObj = ScriptableObject.getProperty(obj, methodName);
        if (!(funObj instanceof Function)) {
            throw ScriptRuntime.notFunctionError(obj, methodName);
        }
        Function fun = (Function)funObj;
        Scriptable scope = ScriptableObject.getTopLevelScope(obj);
        if (cx != null) {
            return fun.call(cx, scope, obj, args);
        }
        return Context.call(null, fun, scope, obj, args);
    }

    private static Scriptable getBase(Scriptable obj, String name) {
        while (!obj.has(name, obj) && (obj = obj.getPrototype()) != null) {
        }
        return obj;
    }

    private static Scriptable getBase(Scriptable obj, int index) {
        while (!obj.has(index, obj) && (obj = obj.getPrototype()) != null) {
        }
        return obj;
    }

    public final Object getAssociatedValue(Object key) {
        Map<Object, Object> h = this.associatedValues;
        if (h == null) {
            return null;
        }
        return h.get(key);
    }

    public static Object getTopScopeValue(Scriptable scope, Object key) {
        scope = ScriptableObject.getTopLevelScope(scope);
        do {
            ScriptableObject so;
            Object value;
            if (!(scope instanceof ScriptableObject) || (value = (so = (ScriptableObject)scope).getAssociatedValue(key)) == null) continue;
            return value;
        } while ((scope = scope.getPrototype()) != null);
        return null;
    }

    public final synchronized Object associateValue(Object key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Map<Object, Object> h = this.associatedValues;
        if (h == null) {
            this.associatedValues = h = new HashMap<Object, Object>();
        }
        return Kit.initHash(h, key, value);
    }

    private boolean putImpl(String name, int index, Scriptable start, Object value) {
        Slot slot;
        if (this != start) {
            slot = this.getSlot(name, index, 1);
            if (slot == null) {
                return false;
            }
        } else if (!this.isExtensible) {
            slot = this.getSlot(name, index, 1);
            if (slot == null) {
                return true;
            }
        } else {
            if (this.count < 0) {
                this.checkNotSealed(name, index);
            }
            slot = this.getSlot(name, index, 2);
        }
        return slot.setValue(value, this, start);
    }

    private boolean putConstImpl(String name, int index, Scriptable start, Object value, int constFlag) {
        Slot slot;
        assert (constFlag != 0);
        if (this != start) {
            slot = this.getSlot(name, index, 1);
            if (slot == null) {
                return false;
            }
        } else if (!this.isExtensible()) {
            slot = this.getSlot(name, index, 1);
            if (slot == null) {
                return true;
            }
        } else {
            this.checkNotSealed(name, index);
            Slot slot2 = ScriptableObject.unwrapSlot(this.getSlot(name, index, 3));
            int attr = slot2.getAttributes();
            if ((attr & 1) == 0) {
                throw Context.reportRuntimeError1("msg.var.redecl", name);
            }
            if ((attr & 8) != 0) {
                slot2.value = value;
                if (constFlag != 8) {
                    slot2.setAttributes(attr & 0xFFFFFFF7);
                }
            }
            return true;
        }
        return slot.setValue(value, this, start);
    }

    private Slot findAttributeSlot(String name, int index, int accessType) {
        Slot slot = this.getSlot(name, index, accessType);
        if (slot == null) {
            String str = name != null ? name : Integer.toString(index);
            throw Context.reportRuntimeError1("msg.prop.not.found", str);
        }
        return slot;
    }

    private static Slot unwrapSlot(Slot slot) {
        return slot instanceof RelinkedSlot ? ((RelinkedSlot)slot).slot : slot;
    }

    private Slot getSlot(String name, int index, int accessType) {
        int indexOrHash;
        Slot[] slotsLocalRef = this.slots;
        if (slotsLocalRef == null && accessType == 1) {
            return null;
        }
        int n = indexOrHash = name != null ? name.hashCode() : index;
        if (slotsLocalRef != null) {
            int slotIndex = ScriptableObject.getSlotIndex(slotsLocalRef.length, indexOrHash);
            Slot slot = slotsLocalRef[slotIndex];
            while (slot != null) {
                String sname = slot.name;
                if (indexOrHash == slot.indexOrHash && (sname == name || name != null && name.equals(sname))) break;
                slot = slot.next;
            }
            switch (accessType) {
                case 1: {
                    return slot;
                }
                case 2: 
                case 3: {
                    if (slot == null) break;
                    return slot;
                }
                case 4: {
                    slot = ScriptableObject.unwrapSlot(slot);
                    if (!(slot instanceof GetterSlot)) break;
                    return slot;
                }
                case 5: {
                    slot = ScriptableObject.unwrapSlot(slot);
                    if (slot instanceof GetterSlot) break;
                    return slot;
                }
            }
        }
        return this.createSlot(name, indexOrHash, accessType);
    }

    private synchronized Slot createSlot(String name, int indexOrHash, int accessType) {
        Slot newSlot;
        int insertPos;
        Slot[] slotsLocalRef = this.slots;
        if (this.count == 0) {
            slotsLocalRef = new Slot[4];
            this.slots = slotsLocalRef;
            insertPos = ScriptableObject.getSlotIndex(slotsLocalRef.length, indexOrHash);
        } else {
            Slot prev;
            int tableSize = slotsLocalRef.length;
            insertPos = ScriptableObject.getSlotIndex(tableSize, indexOrHash);
            Slot slot = prev = slotsLocalRef[insertPos];
            while (!(slot == null || slot.indexOrHash == indexOrHash && (slot.name == name || name != null && name.equals(slot.name)))) {
                prev = slot;
                slot = slot.next;
            }
            if (slot != null) {
                Slot newSlot2;
                Slot inner = ScriptableObject.unwrapSlot(slot);
                if (accessType == 4 && !(inner instanceof GetterSlot)) {
                    newSlot2 = new GetterSlot(name, indexOrHash, inner.getAttributes());
                } else if (accessType == 5 && inner instanceof GetterSlot) {
                    newSlot2 = new Slot(name, indexOrHash, inner.getAttributes());
                } else {
                    if (accessType == 3) {
                        return null;
                    }
                    return inner;
                }
                newSlot2.value = inner.value;
                newSlot2.next = slot.next;
                if (this.lastAdded != null) {
                    this.lastAdded.orderedNext = newSlot2;
                }
                if (this.firstAdded == null) {
                    this.firstAdded = newSlot2;
                }
                this.lastAdded = newSlot2;
                if (prev == slot) {
                    slotsLocalRef[insertPos] = newSlot2;
                } else {
                    prev.next = newSlot2;
                }
                slot.markDeleted();
                return newSlot2;
            }
            if (4 * (this.count + 1) > 3 * slotsLocalRef.length) {
                slotsLocalRef = new Slot[slotsLocalRef.length * 2];
                ScriptableObject.copyTable(this.slots, slotsLocalRef, this.count);
                this.slots = slotsLocalRef;
                insertPos = ScriptableObject.getSlotIndex(slotsLocalRef.length, indexOrHash);
            }
        }
        Slot slot = newSlot = accessType == 4 ? new GetterSlot(name, indexOrHash, 0) : new Slot(name, indexOrHash, 0);
        if (accessType == 3) {
            newSlot.setAttributes(13);
        }
        ++this.count;
        if (this.lastAdded != null) {
            this.lastAdded.orderedNext = newSlot;
        }
        if (this.firstAdded == null) {
            this.firstAdded = newSlot;
        }
        this.lastAdded = newSlot;
        ScriptableObject.addKnownAbsentSlot(slotsLocalRef, newSlot, insertPos);
        return newSlot;
    }

    private synchronized void removeSlot(String name, int index) {
        int indexOrHash = name != null ? name.hashCode() : index;
        Slot[] slotsLocalRef = this.slots;
        if (this.count != 0) {
            Slot prev;
            int tableSize = slotsLocalRef.length;
            int slotIndex = ScriptableObject.getSlotIndex(tableSize, indexOrHash);
            Slot slot = prev = slotsLocalRef[slotIndex];
            while (!(slot == null || slot.indexOrHash == indexOrHash && (slot.name == name || name != null && name.equals(slot.name)))) {
                prev = slot;
                slot = slot.next;
            }
            if (slot != null && (slot.getAttributes() & 4) == 0) {
                --this.count;
                if (prev == slot) {
                    slotsLocalRef[slotIndex] = slot.next;
                } else {
                    prev.next = slot.next;
                }
                Slot deleted = ScriptableObject.unwrapSlot(slot);
                if (deleted == this.firstAdded) {
                    prev = null;
                    this.firstAdded = deleted.orderedNext;
                } else {
                    prev = this.firstAdded;
                    while (prev.orderedNext != deleted) {
                        prev = prev.orderedNext;
                    }
                    prev.orderedNext = deleted.orderedNext;
                }
                if (deleted == this.lastAdded) {
                    this.lastAdded = prev;
                }
                slot.markDeleted();
            }
        }
    }

    private static int getSlotIndex(int tableSize, int indexOrHash) {
        return indexOrHash & tableSize - 1;
    }

    private static void copyTable(Slot[] oldSlots, Slot[] newSlots, int count) {
        if (count == 0) {
            throw Kit.codeBug();
        }
        int tableSize = newSlots.length;
        int i = oldSlots.length;
        block0: while (true) {
            Slot slot = oldSlots[--i];
            do {
                if (slot == null) continue block0;
                int insertPos = ScriptableObject.getSlotIndex(tableSize, slot.indexOrHash);
                Slot insSlot = slot.next == null ? slot : new RelinkedSlot(slot);
                ScriptableObject.addKnownAbsentSlot(newSlots, insSlot, insertPos);
                slot = slot.next;
            } while (--count != 0);
            break;
        }
    }

    private static void addKnownAbsentSlot(Slot[] slots, Slot slot, int insertPos) {
        if (slots[insertPos] == null) {
            slots[insertPos] = slot;
        } else {
            Slot prev = slots[insertPos];
            Slot next = prev.next;
            while (next != null) {
                prev = next;
                next = prev.next;
            }
            prev.next = slot;
        }
    }

    Object[] getIds(boolean getAll) {
        Object[] a;
        int externalLen;
        Slot[] s = this.slots;
        int n = externalLen = this.externalData == null ? 0 : this.externalData.getArrayLength();
        if (externalLen == 0) {
            a = ScriptRuntime.emptyArgs;
        } else {
            a = new Object[externalLen];
            for (int i = 0; i < externalLen; ++i) {
                a[i] = i;
            }
        }
        if (s == null) {
            return a;
        }
        int c = externalLen;
        Slot slot = this.firstAdded;
        while (slot != null && slot.wasDeleted) {
            slot = slot.orderedNext;
        }
        while (slot != null) {
            if (getAll || (slot.getAttributes() & 2) == 0) {
                if (c == externalLen) {
                    Object[] oldA = a;
                    a = new Object[s.length + externalLen];
                    if (oldA != null) {
                        System.arraycopy(oldA, 0, a, 0, externalLen);
                    }
                }
                a[c++] = slot.name != null ? slot.name : Integer.valueOf(slot.indexOrHash);
            }
            slot = slot.orderedNext;
            while (slot != null && slot.wasDeleted) {
                slot = slot.orderedNext;
            }
        }
        if (c == a.length + externalLen) {
            return a;
        }
        Object[] result = new Object[c];
        System.arraycopy(a, 0, result, 0, c);
        return result;
    }

    private synchronized void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        int objectsCount = this.count;
        if (objectsCount < 0) {
            objectsCount ^= 0xFFFFFFFF;
        }
        if (objectsCount == 0) {
            out.writeInt(0);
        } else {
            out.writeInt(this.slots.length);
            Slot slot = this.firstAdded;
            while (slot != null && slot.wasDeleted) {
                slot = slot.orderedNext;
            }
            this.firstAdded = slot;
            while (slot != null) {
                out.writeObject(slot);
                Slot next = slot.orderedNext;
                while (next != null && next.wasDeleted) {
                    next = next.orderedNext;
                }
                slot.orderedNext = next;
                slot = next;
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int tableSize = in.readInt();
        if (tableSize != 0) {
            if ((tableSize & tableSize - 1) != 0) {
                int newSize;
                if (tableSize > 0x40000000) {
                    throw new RuntimeException("Property table overflow");
                }
                for (newSize = 4; newSize < tableSize; newSize <<= 1) {
                }
                tableSize = newSize;
            }
            this.slots = new Slot[tableSize];
            int objectsCount = this.count;
            if (objectsCount < 0) {
                objectsCount ^= 0xFFFFFFFF;
            }
            Slot prev = null;
            for (int i = 0; i != objectsCount; ++i) {
                this.lastAdded = (Slot)in.readObject();
                if (i == 0) {
                    this.firstAdded = this.lastAdded;
                } else {
                    prev.orderedNext = this.lastAdded;
                }
                int slotIndex = ScriptableObject.getSlotIndex(tableSize, this.lastAdded.indexOrHash);
                ScriptableObject.addKnownAbsentSlot(this.slots, this.lastAdded, slotIndex);
                prev = this.lastAdded;
            }
        }
    }

    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
        Slot slot = this.getSlot(cx, id, 1);
        if (slot == null) {
            return null;
        }
        Scriptable scope = this.getParentScope();
        return slot.getPropertyDescriptor(cx, scope == null ? this : scope);
    }

    protected Slot getSlot(Context cx, Object id, int accessType) {
        String name = ScriptRuntime.toStringIdOrIndex(cx, id);
        if (name == null) {
            return this.getSlot((String)null, ScriptRuntime.lastIndexResult(cx), accessType);
        }
        return this.getSlot(name, 0, accessType);
    }

    public int size() {
        return this.count < 0 ? ~this.count : this.count;
    }

    public boolean isEmpty() {
        return this.count == 0 || this.count == -1;
    }

    public Object get(Object key) {
        Object value = null;
        if (key instanceof String) {
            value = this.get((String)key, (Scriptable)this);
        } else if (key instanceof Number) {
            value = this.get(((Number)key).intValue(), (Scriptable)this);
        }
        if (value == Scriptable.NOT_FOUND || value == Undefined.instance) {
            return null;
        }
        if (value instanceof Wrapper) {
            return ((Wrapper)value).unwrap();
        }
        return value;
    }

    static {
        try {
            GET_ARRAY_LENGTH = ScriptableObject.class.getMethod("getExternalArrayLength", new Class[0]);
        } catch (NoSuchMethodException nsm) {
            throw new RuntimeException(nsm);
        }
    }

    private static class RelinkedSlot
    extends Slot {
        final Slot slot;

        RelinkedSlot(Slot slot) {
            super(slot.name, slot.indexOrHash, slot.attributes);
            this.slot = ScriptableObject.unwrapSlot(slot);
        }

        @Override
        boolean setValue(Object value, Scriptable owner, Scriptable start) {
            return this.slot.setValue(value, owner, start);
        }

        @Override
        Object getValue(Scriptable start) {
            return this.slot.getValue(start);
        }

        @Override
        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
            return this.slot.getPropertyDescriptor(cx, scope);
        }

        @Override
        int getAttributes() {
            return this.slot.getAttributes();
        }

        @Override
        void setAttributes(int value) {
            this.slot.setAttributes(value);
        }

        @Override
        void markDeleted() {
            super.markDeleted();
            this.slot.markDeleted();
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(this.slot);
        }
    }

    private static final class GetterSlot
    extends Slot {
        static final long serialVersionUID = -4900574849788797588L;
        Object getter;
        Object setter;

        GetterSlot(String name, int indexOrHash, int attributes) {
            super(name, indexOrHash, attributes);
        }

        @Override
        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
            int attr = this.getAttributes();
            NativeObject desc = new NativeObject();
            ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
            desc.defineProperty("enumerable", (attr & 2) == 0, 0);
            desc.defineProperty("configurable", (attr & 4) == 0, 0);
            if (this.getter != null) {
                desc.defineProperty("get", this.getter, 0);
            }
            if (this.setter != null) {
                desc.defineProperty("set", this.setter, 0);
            }
            return desc;
        }

        @Override
        boolean setValue(Object value, Scriptable owner, Scriptable start) {
            if (this.setter == null) {
                if (this.getter != null) {
                    if (Context.getContext().hasFeature(11)) {
                        throw ScriptRuntime.typeError1("msg.set.prop.no.setter", this.name);
                    }
                    return true;
                }
            } else {
                Context cx = Context.getContext();
                if (this.setter instanceof MemberBox) {
                    Object[] args;
                    Object setterThis;
                    MemberBox nativeSetter = (MemberBox)this.setter;
                    Class<?>[] pTypes = nativeSetter.argTypes;
                    Class<?> valueType = pTypes[pTypes.length - 1];
                    int tag = FunctionObject.getTypeTag(valueType);
                    Object actualArg = FunctionObject.convertArg(cx, start, value, tag);
                    if (nativeSetter.delegateTo == null) {
                        setterThis = start;
                        args = new Object[]{actualArg};
                    } else {
                        setterThis = nativeSetter.delegateTo;
                        args = new Object[]{start, actualArg};
                    }
                    nativeSetter.invoke(setterThis, args);
                } else if (this.setter instanceof Function) {
                    Function f = (Function)this.setter;
                    f.call(cx, f.getParentScope(), start, new Object[]{value});
                }
                return true;
            }
            return super.setValue(value, owner, start);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        Object getValue(Scriptable start) {
            Object val;
            if (this.getter != null) {
                if (this.getter instanceof MemberBox) {
                    Object[] args;
                    Object getterThis;
                    MemberBox nativeGetter = (MemberBox)this.getter;
                    if (nativeGetter.delegateTo == null) {
                        getterThis = start;
                        args = ScriptRuntime.emptyArgs;
                    } else {
                        getterThis = nativeGetter.delegateTo;
                        args = new Object[]{start};
                    }
                    return nativeGetter.invoke(getterThis, args);
                }
                if (this.getter instanceof Function) {
                    Function f = (Function)this.getter;
                    Context cx = Context.getContext();
                    return f.call(cx, f.getParentScope(), start, ScriptRuntime.emptyArgs);
                }
            }
            if ((val = this.value) instanceof LazilyLoadedCtor) {
                LazilyLoadedCtor initializer = (LazilyLoadedCtor)val;
                try {
                    initializer.init();
                } finally {
                    this.value = val = initializer.getValue();
                }
            }
            return val;
        }

        @Override
        void markDeleted() {
            super.markDeleted();
            this.getter = null;
            this.setter = null;
        }
    }

    private static class Slot
    implements Serializable {
        private static final long serialVersionUID = -6090581677123995491L;
        String name;
        int indexOrHash;
        private volatile short attributes;
        volatile transient boolean wasDeleted;
        volatile Object value;
        transient Slot next;
        volatile transient Slot orderedNext;

        Slot(String name, int indexOrHash, int attributes) {
            this.name = name;
            this.indexOrHash = indexOrHash;
            this.attributes = (short)attributes;
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            if (this.name != null) {
                this.indexOrHash = this.name.hashCode();
            }
        }

        boolean setValue(Object value, Scriptable owner, Scriptable start) {
            if ((this.attributes & 1) != 0) {
                return true;
            }
            if (owner == start) {
                this.value = value;
                return true;
            }
            return false;
        }

        Object getValue(Scriptable start) {
            return this.value;
        }

        int getAttributes() {
            return this.attributes;
        }

        synchronized void setAttributes(int value) {
            ScriptableObject.checkValidAttributes(value);
            this.attributes = (short)value;
        }

        void markDeleted() {
            this.wasDeleted = true;
            this.value = null;
            this.name = null;
        }

        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
            return ScriptableObject.buildDataDescriptor(scope, this.value, this.attributes);
        }
    }
}

