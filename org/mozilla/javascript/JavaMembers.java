/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.BeanProperty;
import org.mozilla.javascript.ClassCache;
import org.mozilla.javascript.ClassShutter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.FieldAndMethods;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeJavaConstructor;
import org.mozilla.javascript.NativeJavaMethod;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

class JavaMembers {
    private Class<?> cl;
    private Map<String, Object> members;
    private Map<String, FieldAndMethods> fieldAndMethods;
    private Map<String, Object> staticMembers;
    private Map<String, FieldAndMethods> staticFieldAndMethods;
    NativeJavaMethod ctors;

    JavaMembers(Scriptable scope, Class<?> cl) {
        this(scope, cl, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    JavaMembers(Scriptable scope, Class<?> cl, boolean includeProtected) {
        try {
            Context cx = ContextFactory.getGlobal().enterContext();
            ClassShutter shutter = cx.getClassShutter();
            if (shutter != null && !shutter.visibleToScripts(cl.getName())) {
                throw Context.reportRuntimeError1("msg.access.prohibited", cl.getName());
            }
            this.members = new HashMap<String, Object>();
            this.staticMembers = new HashMap<String, Object>();
            this.cl = cl;
            boolean includePrivate = cx.hasFeature(13);
            this.reflect(scope, includeProtected, includePrivate);
        } finally {
            Context.exit();
        }
    }

    boolean has(String name, boolean isStatic) {
        Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
        Object obj = ht.get(name);
        if (obj != null) {
            return true;
        }
        return this.findExplicitFunction(name, isStatic) != null;
    }

    Object get(Scriptable scope, String name, Object javaObject, boolean isStatic) {
        Class<?> type;
        Object rval;
        Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
        Object member = ht.get(name);
        if (!isStatic && member == null) {
            member = this.staticMembers.get(name);
        }
        if (member == null && (member = this.getExplicitFunction(scope, name, javaObject, isStatic)) == null) {
            return Scriptable.NOT_FOUND;
        }
        if (member instanceof Scriptable) {
            return member;
        }
        Context cx = Context.getContext();
        try {
            if (member instanceof BeanProperty) {
                BeanProperty bp = (BeanProperty)member;
                if (bp.getter == null) {
                    return Scriptable.NOT_FOUND;
                }
                rval = bp.getter.invoke(javaObject, Context.emptyArgs);
                type = bp.getter.method().getReturnType();
            } else {
                Field field = (Field)member;
                rval = field.get(isStatic ? null : javaObject);
                type = field.getType();
            }
        } catch (Exception ex) {
            throw Context.throwAsScriptRuntimeEx(ex);
        }
        scope = ScriptableObject.getTopLevelScope(scope);
        return cx.getWrapFactory().wrap(cx, scope, rval, type);
    }

    void put(Scriptable scope, String name, Object javaObject, Object value, boolean isStatic) {
        Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
        Object member = ht.get(name);
        if (!isStatic && member == null) {
            member = this.staticMembers.get(name);
        }
        if (member == null) {
            throw this.reportMemberNotFound(name);
        }
        if (member instanceof FieldAndMethods) {
            FieldAndMethods fam = (FieldAndMethods)ht.get(name);
            member = fam.field;
        }
        if (member instanceof BeanProperty) {
            BeanProperty bp = (BeanProperty)member;
            if (bp.setter == null) {
                throw this.reportMemberNotFound(name);
            }
            if (bp.setters == null || value == null) {
                Class<?> setType = bp.setter.argTypes[0];
                Object[] args = new Object[]{Context.jsToJava(value, setType)};
                try {
                    bp.setter.invoke(javaObject, args);
                } catch (Exception ex) {
                    throw Context.throwAsScriptRuntimeEx(ex);
                }
            } else {
                Object[] args = new Object[]{value};
                bp.setters.call(Context.getContext(), ScriptableObject.getTopLevelScope(scope), scope, args);
            }
        } else {
            if (!(member instanceof Field)) {
                String str = member == null ? "msg.java.internal.private" : "msg.java.method.assign";
                throw Context.reportRuntimeError1(str, name);
            }
            Field field = (Field)member;
            Object javaValue = Context.jsToJava(value, field.getType());
            try {
                field.set(javaObject, javaValue);
            } catch (IllegalAccessException accessEx) {
                if ((field.getModifiers() & 0x10) != 0) {
                    return;
                }
                throw Context.throwAsScriptRuntimeEx(accessEx);
            } catch (IllegalArgumentException argEx) {
                throw Context.reportRuntimeError3("msg.java.internal.field.type", value.getClass().getName(), field, javaObject.getClass().getName());
            }
        }
    }

    Object[] getIds(boolean isStatic) {
        Map<String, Object> map = isStatic ? this.staticMembers : this.members;
        return map.keySet().toArray(new Object[map.size()]);
    }

    static String javaSignature(Class<?> type) {
        if (!type.isArray()) {
            return type.getName();
        }
        int arrayDimension = 0;
        do {
            ++arrayDimension;
        } while ((type = type.getComponentType()).isArray());
        String name = type.getName();
        String suffix = "[]";
        if (arrayDimension == 1) {
            return name.concat(suffix);
        }
        int length = name.length() + arrayDimension * suffix.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(name);
        while (arrayDimension != 0) {
            --arrayDimension;
            sb.append(suffix);
        }
        return sb.toString();
    }

    static String liveConnectSignature(Class<?>[] argTypes) {
        int N = argTypes.length;
        if (N == 0) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i != N; ++i) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(JavaMembers.javaSignature(argTypes[i]));
        }
        sb.append(')');
        return sb.toString();
    }

    private MemberBox findExplicitFunction(String name, boolean isStatic) {
        boolean isCtor;
        int sigStart = name.indexOf(40);
        if (sigStart < 0) {
            return null;
        }
        Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
        MemberBox[] methodsOrCtors = null;
        boolean bl = isCtor = isStatic && sigStart == 0;
        if (isCtor) {
            methodsOrCtors = this.ctors.methods;
        } else {
            String trueName = name.substring(0, sigStart);
            Object obj = ht.get(trueName);
            if (!isStatic && obj == null) {
                obj = this.staticMembers.get(trueName);
            }
            if (obj instanceof NativeJavaMethod) {
                NativeJavaMethod njm = (NativeJavaMethod)obj;
                methodsOrCtors = njm.methods;
            }
        }
        if (methodsOrCtors != null) {
            for (MemberBox methodsOrCtor : methodsOrCtors) {
                Class<?>[] type = methodsOrCtor.argTypes;
                String sig = JavaMembers.liveConnectSignature(type);
                if (sigStart + sig.length() != name.length() || !name.regionMatches(sigStart, sig, 0, sig.length())) continue;
                return methodsOrCtor;
            }
        }
        return null;
    }

    private Object getExplicitFunction(Scriptable scope, String name, Object javaObject, boolean isStatic) {
        Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
        Object member = null;
        MemberBox methodOrCtor = this.findExplicitFunction(name, isStatic);
        if (methodOrCtor != null) {
            Scriptable prototype = ScriptableObject.getFunctionPrototype(scope);
            if (methodOrCtor.isCtor()) {
                NativeJavaConstructor fun = new NativeJavaConstructor(methodOrCtor);
                fun.setPrototype(prototype);
                member = fun;
                ht.put(name, fun);
            } else {
                String trueName = methodOrCtor.getName();
                member = ht.get(trueName);
                if (member instanceof NativeJavaMethod && ((NativeJavaMethod)member).methods.length > 1) {
                    NativeJavaMethod fun = new NativeJavaMethod(methodOrCtor, name);
                    fun.setPrototype(prototype);
                    ht.put(name, fun);
                    member = fun;
                }
            }
        }
        return member;
    }

    private static Method[] discoverAccessibleMethods(Class<?> clazz, boolean includeProtected, boolean includePrivate) {
        HashMap<MethodSignature, Method> map = new HashMap<MethodSignature, Method>();
        JavaMembers.discoverAccessibleMethods(clazz, map, includeProtected, includePrivate);
        return map.values().toArray(new Method[map.size()]);
    }

    private static void discoverAccessibleMethods(Class<?> clazz, Map<MethodSignature, Method> map, boolean includeProtected, boolean includePrivate) {
        Class<?>[] interfaces;
        if (Modifier.isPublic(clazz.getModifiers()) || includePrivate) {
            try {
                if (includeProtected || includePrivate) {
                    while (clazz != null) {
                        MethodSignature sig;
                        try {
                            Method[] methods;
                            for (Method method : methods = clazz.getDeclaredMethods()) {
                                int mods = method.getModifiers();
                                if (!Modifier.isPublic(mods) && !Modifier.isProtected(mods) && !includePrivate || map.containsKey(sig = new MethodSignature(method))) continue;
                                if (includePrivate && !method.isAccessible()) {
                                    method.setAccessible(true);
                                }
                                map.put(sig, method);
                            }
                            clazz = clazz.getSuperclass();
                        } catch (SecurityException e) {
                            Method[] methods;
                            for (Method method : methods = clazz.getMethods()) {
                                sig = new MethodSignature(method);
                                if (map.containsKey(sig)) continue;
                                map.put(sig, method);
                            }
                            break;
                        }
                    }
                } else {
                    Method[] methods;
                    for (Method method : methods = clazz.getMethods()) {
                        MethodSignature sig = new MethodSignature(method);
                        if (map.containsKey(sig)) continue;
                        map.put(sig, method);
                    }
                }
                return;
            } catch (SecurityException e) {
                Context.reportWarning("Could not discover accessible methods of class " + clazz.getName() + " due to lack of privileges, " + "attemping superclasses/interfaces.");
            }
        }
        for (Class<?> intface : interfaces = clazz.getInterfaces()) {
            JavaMembers.discoverAccessibleMethods(intface, map, includeProtected, includePrivate);
        }
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            JavaMembers.discoverAccessibleMethods(superclass, map, includeProtected, includePrivate);
        }
    }

    private void reflect(Scriptable scope, boolean includeProtected, boolean includePrivate) {
        Field[] fields;
        Object method;
        Method[] methods;
        for (Method method2 : methods = JavaMembers.discoverAccessibleMethods(this.cl, includeProtected, includePrivate)) {
            ObjArray overloadedMethods;
            String name;
            int mods = method2.getModifiers();
            boolean isStatic = Modifier.isStatic(mods);
            Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
            Object value = ht.get(name = method2.getName());
            if (value == null) {
                ht.put(name, method2);
                continue;
            }
            if (value instanceof ObjArray) {
                overloadedMethods = (ObjArray)value;
            } else {
                if (!(value instanceof Method)) {
                    Kit.codeBug();
                }
                overloadedMethods = new ObjArray();
                overloadedMethods.add(value);
                ht.put(name, overloadedMethods);
            }
            overloadedMethods.add(method2);
        }
        for (int tableCursor = 0; tableCursor != 2; ++tableCursor) {
            boolean isStatic = tableCursor == 0;
            Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
            for (Map.Entry<String, Object> entry : ht.entrySet()) {
                MemberBox[] methodBoxes;
                Object value = entry.getValue();
                if (value instanceof Method) {
                    methodBoxes = new MemberBox[]{new MemberBox((Method)value)};
                } else {
                    ObjArray overloadedMethods = (ObjArray)value;
                    int N = overloadedMethods.size();
                    if (N < 2) {
                        Kit.codeBug();
                    }
                    methodBoxes = new MemberBox[N];
                    for (int i = 0; i != N; ++i) {
                        method = (Method)overloadedMethods.get(i);
                        methodBoxes[i] = new MemberBox((Method)method);
                    }
                }
                NativeJavaMethod fun = new NativeJavaMethod(methodBoxes);
                if (scope != null) {
                    ScriptRuntime.setFunctionProtoAndParent(fun, scope);
                }
                ht.put(entry.getKey(), fun);
            }
        }
        for (Field field : fields = this.getAccessibleFields(includeProtected, includePrivate)) {
            String name = field.getName();
            int mods = field.getModifiers();
            try {
                boolean isStatic = Modifier.isStatic(mods);
                Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
                Object member = ht.get(name);
                if (member == null) {
                    ht.put(name, field);
                    continue;
                }
                if (member instanceof NativeJavaMethod) {
                    Map<String, FieldAndMethods> fmht;
                    method = (NativeJavaMethod)member;
                    FieldAndMethods fam = new FieldAndMethods(scope, ((NativeJavaMethod)method).methods, field);
                    Map<String, FieldAndMethods> map = fmht = isStatic ? this.staticFieldAndMethods : this.fieldAndMethods;
                    if (fmht == null) {
                        fmht = new HashMap<String, FieldAndMethods>();
                        if (isStatic) {
                            this.staticFieldAndMethods = fmht;
                        } else {
                            this.fieldAndMethods = fmht;
                        }
                    }
                    fmht.put(name, fam);
                    ht.put(name, fam);
                    continue;
                }
                if (member instanceof Field) {
                    Field oldField = (Field)member;
                    if (!oldField.getDeclaringClass().isAssignableFrom(field.getDeclaringClass())) continue;
                    ht.put(name, field);
                    continue;
                }
                Kit.codeBug();
            } catch (SecurityException e) {
                Context.reportWarning("Could not access field " + name + " of class " + this.cl.getName() + " due to lack of privileges.");
            }
        }
        for (int tableCursor = 0; tableCursor != 2; ++tableCursor) {
            boolean isStatic = tableCursor == 0;
            Map<String, Object> ht = isStatic ? this.staticMembers : this.members;
            HashMap<String, BeanProperty> toAdd = new HashMap<String, BeanProperty>();
            for (String name : ht.keySet()) {
                Object member;
                Object v;
                String nameComponent;
                boolean memberIsGetMethod = name.startsWith("get");
                boolean memberIsSetMethod = name.startsWith("set");
                boolean memberIsIsMethod = name.startsWith("is");
                if (!memberIsGetMethod && !memberIsIsMethod && !memberIsSetMethod || (nameComponent = name.substring(memberIsIsMethod ? 2 : 3)).length() == 0) continue;
                String beanPropertyName = nameComponent;
                char ch0 = nameComponent.charAt(0);
                if (Character.isUpperCase(ch0)) {
                    if (nameComponent.length() == 1) {
                        beanPropertyName = nameComponent.toLowerCase();
                    } else {
                        char ch1 = nameComponent.charAt(1);
                        if (!Character.isUpperCase(ch1)) {
                            beanPropertyName = Character.toLowerCase(ch0) + nameComponent.substring(1);
                        }
                    }
                }
                if (toAdd.containsKey(beanPropertyName) || (v = ht.get(beanPropertyName)) != null && (!includePrivate || !(v instanceof Member) || !Modifier.isPrivate(((Member)v).getModifiers()))) continue;
                MemberBox getter = null;
                getter = this.findGetter(isStatic, ht, "get", nameComponent);
                if (getter == null) {
                    getter = this.findGetter(isStatic, ht, "is", nameComponent);
                }
                MemberBox setter = null;
                NativeJavaMethod setters = null;
                String setterName = "set".concat(nameComponent);
                if (ht.containsKey(setterName) && (member = ht.get(setterName)) instanceof NativeJavaMethod) {
                    NativeJavaMethod njmSet = (NativeJavaMethod)member;
                    if (getter != null) {
                        Class<?> type = getter.method().getReturnType();
                        setter = JavaMembers.extractSetMethod(type, njmSet.methods, isStatic);
                    } else {
                        setter = JavaMembers.extractSetMethod(njmSet.methods, isStatic);
                    }
                    if (njmSet.methods.length > 1) {
                        setters = njmSet;
                    }
                }
                BeanProperty bp = new BeanProperty(getter, setter, setters);
                toAdd.put(beanPropertyName, bp);
            }
            for (String key : toAdd.keySet()) {
                Object value = toAdd.get(key);
                ht.put(key, value);
            }
        }
        Constructor<?>[] constructors = this.getAccessibleConstructors(includePrivate);
        MemberBox[] ctorMembers = new MemberBox[constructors.length];
        for (int i = 0; i != constructors.length; ++i) {
            ctorMembers[i] = new MemberBox(constructors[i]);
        }
        this.ctors = new NativeJavaMethod(ctorMembers, this.cl.getSimpleName());
    }

    private Constructor<?>[] getAccessibleConstructors(boolean includePrivate) {
        if (includePrivate && this.cl != ScriptRuntime.ClassClass) {
            try {
                AccessibleObject[] cons = this.cl.getDeclaredConstructors();
                AccessibleObject.setAccessible(cons, true);
                return cons;
            } catch (SecurityException e) {
                Context.reportWarning("Could not access constructor  of class " + this.cl.getName() + " due to lack of privileges.");
            }
        }
        return this.cl.getConstructors();
    }

    private Field[] getAccessibleFields(boolean includeProtected, boolean includePrivate) {
        if (includePrivate || includeProtected) {
            try {
                ArrayList<Field> fieldsList = new ArrayList<Field>();
                for (Class<?> currentClass = this.cl; currentClass != null; currentClass = currentClass.getSuperclass()) {
                    Field[] declared;
                    for (Field field : declared = currentClass.getDeclaredFields()) {
                        int mod = field.getModifiers();
                        if (!includePrivate && !Modifier.isPublic(mod) && !Modifier.isProtected(mod)) continue;
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        fieldsList.add(field);
                    }
                }
                return fieldsList.toArray(new Field[fieldsList.size()]);
            } catch (SecurityException securityException) {
                // empty catch block
            }
        }
        return this.cl.getFields();
    }

    private MemberBox findGetter(boolean isStatic, Map<String, Object> ht, String prefix, String propertyName) {
        Object member;
        String getterName = prefix.concat(propertyName);
        if (ht.containsKey(getterName) && (member = ht.get(getterName)) instanceof NativeJavaMethod) {
            NativeJavaMethod njmGet = (NativeJavaMethod)member;
            return JavaMembers.extractGetMethod(njmGet.methods, isStatic);
        }
        return null;
    }

    private static MemberBox extractGetMethod(MemberBox[] methods, boolean isStatic) {
        for (MemberBox method : methods) {
            if (method.argTypes.length != 0 || isStatic && !method.isStatic()) continue;
            Class<?> type = method.method().getReturnType();
            if (type == Void.TYPE) break;
            return method;
        }
        return null;
    }

    private static MemberBox extractSetMethod(Class<?> type, MemberBox[] methods, boolean isStatic) {
        for (int pass = 1; pass <= 2; ++pass) {
            for (MemberBox method : methods) {
                Class<?>[] params;
                if (isStatic && !method.isStatic() || (params = method.argTypes).length != 1) continue;
                if (pass == 1) {
                    if (params[0] != type) continue;
                    return method;
                }
                if (pass != 2) {
                    Kit.codeBug();
                }
                if (!params[0].isAssignableFrom(type)) continue;
                return method;
            }
        }
        return null;
    }

    private static MemberBox extractSetMethod(MemberBox[] methods, boolean isStatic) {
        for (MemberBox method : methods) {
            if (isStatic && !method.isStatic() || method.method().getReturnType() != Void.TYPE || method.argTypes.length != 1) continue;
            return method;
        }
        return null;
    }

    Map<String, FieldAndMethods> getFieldAndMethodsObjects(Scriptable scope, Object javaObject, boolean isStatic) {
        Map<String, FieldAndMethods> ht;
        Map<String, FieldAndMethods> map = ht = isStatic ? this.staticFieldAndMethods : this.fieldAndMethods;
        if (ht == null) {
            return null;
        }
        int len = ht.size();
        HashMap<String, FieldAndMethods> result = new HashMap<String, FieldAndMethods>(len);
        for (FieldAndMethods fam : ht.values()) {
            FieldAndMethods famNew = new FieldAndMethods(scope, fam.methods, fam.field);
            famNew.javaObject = javaObject;
            result.put(fam.field.getName(), famNew);
        }
        return result;
    }

    static JavaMembers lookupClass(Scriptable scope, Class<?> dynamicType, Class<?> staticType, boolean includeProtected) {
        JavaMembers members;
        ClassCache cache = ClassCache.get(scope);
        Map<Class<?>, JavaMembers> ct = cache.getClassCacheMap();
        Class<?> cl = dynamicType;
        while (true) {
            if ((members = ct.get(cl)) != null) {
                if (cl != dynamicType) {
                    ct.put(dynamicType, members);
                }
                return members;
            }
            try {
                members = new JavaMembers(cache.getAssociatedScope(), cl, includeProtected);
            } catch (SecurityException e) {
                if (staticType != null && staticType.isInterface()) {
                    cl = staticType;
                    staticType = null;
                    continue;
                }
                Class<?> parent = cl.getSuperclass();
                if (parent == null) {
                    if (cl.isInterface()) {
                        parent = ScriptRuntime.ObjectClass;
                    } else {
                        throw e;
                    }
                }
                cl = parent;
                continue;
            }
            break;
        }
        if (cache.isCachingEnabled()) {
            ct.put(cl, members);
            if (cl != dynamicType) {
                ct.put(dynamicType, members);
            }
        }
        return members;
    }

    RuntimeException reportMemberNotFound(String memberName) {
        return Context.reportRuntimeError2("msg.java.member.not.found", this.cl.getName(), memberName);
    }

    private static final class MethodSignature {
        private final String name;
        private final Class<?>[] args;

        private MethodSignature(String name, Class<?>[] args) {
            this.name = name;
            this.args = args;
        }

        MethodSignature(Method method) {
            this(method.getName(), method.getParameterTypes());
        }

        public boolean equals(Object o) {
            if (o instanceof MethodSignature) {
                MethodSignature ms = (MethodSignature)o;
                return ms.name.equals(this.name) && Arrays.equals(this.args, ms.args);
            }
            return false;
        }

        public int hashCode() {
            return this.name.hashCode() ^ this.args.length;
        }
    }
}

