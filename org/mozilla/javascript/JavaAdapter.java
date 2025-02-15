/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.ClassCache;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.IdFunctionCall;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.NativeJavaMethod;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

public final class JavaAdapter
implements IdFunctionCall {
    private static final Object FTAG = "JavaAdapter";
    private static final int Id_JavaAdapter = 1;

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        JavaAdapter obj = new JavaAdapter();
        IdFunctionObject ctor = new IdFunctionObject(obj, FTAG, 1, "JavaAdapter", 1, scope);
        ctor.markAsConstructor(null);
        if (sealed) {
            ctor.sealObject();
        }
        ctor.exportAsScopeProperty();
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (f.hasTag(FTAG) && f.methodId() == 1) {
            return JavaAdapter.js_createAdapter(cx, scope, args);
        }
        throw f.unknown();
    }

    public static Object convertResult(Object result, Class<?> c) {
        if (result == Undefined.instance && c != ScriptRuntime.ObjectClass && c != ScriptRuntime.StringClass) {
            return null;
        }
        return Context.jsToJava(result, c);
    }

    public static Scriptable createAdapterWrapper(Scriptable obj, Object adapter) {
        Scriptable scope = ScriptableObject.getTopLevelScope(obj);
        NativeJavaObject res = new NativeJavaObject(scope, adapter, null, true);
        res.setPrototype(obj);
        return res;
    }

    public static Object getAdapterSelf(Class<?> adapterClass, Object adapter) throws NoSuchFieldException, IllegalAccessException {
        Field self = adapterClass.getDeclaredField("self");
        return self.get(adapter);
    }

    static Object js_createAdapter(Context cx, Scriptable scope, Object[] args) {
        Object arg;
        int classCount;
        int N = args.length;
        if (N == 0) {
            throw ScriptRuntime.typeError0("msg.adapter.zero.args");
        }
        for (classCount = 0; classCount < N - 1 && !((arg = args[classCount]) instanceof NativeObject); ++classCount) {
            if (arg instanceof NativeJavaClass) continue;
            throw ScriptRuntime.typeError2("msg.not.java.class.arg", String.valueOf(classCount), ScriptRuntime.toString(arg));
        }
        Class<?> superClass = null;
        Class[] intfs = new Class[classCount];
        int interfaceCount = 0;
        for (int i = 0; i < classCount; ++i) {
            Class<?> c = ((NativeJavaClass)args[i]).getClassObject();
            if (!c.isInterface()) {
                if (superClass != null) {
                    throw ScriptRuntime.typeError2("msg.only.one.super", superClass.getName(), c.getName());
                }
                superClass = c;
                continue;
            }
            intfs[interfaceCount++] = c;
        }
        if (superClass == null) {
            superClass = ScriptRuntime.ObjectClass;
        }
        Class[] interfaces = new Class[interfaceCount];
        System.arraycopy(intfs, 0, interfaces, 0, interfaceCount);
        Scriptable obj = ScriptableObject.ensureScriptable(args[classCount]);
        Class<?> adapterClass = JavaAdapter.getAdapterClass(scope, superClass, interfaces, obj);
        int argsCount = N - classCount - 1;
        try {
            Object unwrapped;
            Object adapter;
            if (argsCount > 0) {
                Object[] ctorArgs = new Object[argsCount + 2];
                ctorArgs[0] = obj;
                ctorArgs[1] = cx.getFactory();
                System.arraycopy(args, classCount + 1, ctorArgs, 2, argsCount);
                NativeJavaClass classWrapper = new NativeJavaClass(scope, adapterClass, true);
                NativeJavaMethod ctors = classWrapper.members.ctors;
                int index = ctors.findCachedFunction(cx, ctorArgs);
                if (index < 0) {
                    String sig = NativeJavaMethod.scriptSignature(args);
                    throw Context.reportRuntimeError2("msg.no.java.ctor", adapterClass.getName(), sig);
                }
                adapter = NativeJavaClass.constructInternal(ctorArgs, ctors.methods[index]);
            } else {
                Class[] ctorParms = new Class[]{ScriptRuntime.ScriptableClass, ScriptRuntime.ContextFactoryClass};
                Object[] ctorArgs = new Object[]{obj, cx.getFactory()};
                adapter = adapterClass.getConstructor(ctorParms).newInstance(ctorArgs);
            }
            Object self = JavaAdapter.getAdapterSelf(adapterClass, adapter);
            if (self instanceof Wrapper && (unwrapped = ((Wrapper)self).unwrap()) instanceof Scriptable) {
                if (unwrapped instanceof ScriptableObject) {
                    ScriptRuntime.setObjectProtoAndParent((ScriptableObject)unwrapped, scope);
                }
                return unwrapped;
            }
            return self;
        } catch (Exception ex) {
            throw Context.throwAsScriptRuntimeEx(ex);
        }
    }

    public static void writeAdapterObject(Object javaObject, ObjectOutputStream out) throws IOException {
        Class<?> cl = javaObject.getClass();
        out.writeObject(cl.getSuperclass().getName());
        Class<?>[] interfaces = cl.getInterfaces();
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; ++i) {
            interfaceNames[i] = interfaces[i].getName();
        }
        out.writeObject(interfaceNames);
        try {
            Object delegee = cl.getField("delegee").get(javaObject);
            out.writeObject(delegee);
            return;
        } catch (IllegalAccessException e) {
        } catch (NoSuchFieldException e) {
            // empty catch block
        }
        throw new IOException();
    }

    public static Object readAdapterObject(Scriptable self, ObjectInputStream in) throws IOException, ClassNotFoundException {
        Context cx = Context.getCurrentContext();
        ContextFactory factory = cx != null ? cx.getFactory() : null;
        Class<?> superClass = Class.forName((String)in.readObject());
        String[] interfaceNames = (String[])in.readObject();
        Class[] interfaces = new Class[interfaceNames.length];
        for (int i = 0; i < interfaceNames.length; ++i) {
            interfaces[i] = Class.forName(interfaceNames[i]);
        }
        Scriptable delegee = (Scriptable)in.readObject();
        Class<?> adapterClass = JavaAdapter.getAdapterClass(self, superClass, interfaces, delegee);
        Class[] ctorParms = new Class[]{ScriptRuntime.ContextFactoryClass, ScriptRuntime.ScriptableClass, ScriptRuntime.ScriptableClass};
        Object[] ctorArgs = new Object[]{factory, delegee, self};
        try {
            return adapterClass.getConstructor(ctorParms).newInstance(ctorArgs);
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
            // empty catch block
        }
        throw new ClassNotFoundException("adapter");
    }

    private static ObjToIntMap getObjectFunctionNames(Scriptable obj) {
        Object[] ids = ScriptableObject.getPropertyIds(obj);
        ObjToIntMap map = new ObjToIntMap(ids.length);
        for (int i = 0; i != ids.length; ++i) {
            String id;
            Object value;
            if (!(ids[i] instanceof String) || !((value = ScriptableObject.getProperty(obj, id = (String)ids[i])) instanceof Function)) continue;
            Function f = (Function)value;
            int length = ScriptRuntime.toInt32(ScriptableObject.getProperty((Scriptable)f, "length"));
            if (length < 0) {
                length = 0;
            }
            map.put(id, length);
        }
        return map;
    }

    private static Class<?> getAdapterClass(Scriptable scope, Class<?> superClass, Class<?>[] interfaces, Scriptable obj) {
        ObjToIntMap names;
        JavaAdapterSignature sig;
        ClassCache cache = ClassCache.get(scope);
        Map<JavaAdapterSignature, Class<?>> generated = cache.getInterfaceAdapterCacheMap();
        Class<?> adapterClass = generated.get(sig = new JavaAdapterSignature(superClass, interfaces, names = JavaAdapter.getObjectFunctionNames(obj)));
        if (adapterClass == null) {
            String adapterName = "adapter" + cache.newClassSerialNumber();
            byte[] code = JavaAdapter.createAdapterCode(names, adapterName, superClass, interfaces, null);
            adapterClass = JavaAdapter.loadAdapterClass(adapterName, code);
            if (cache.isCachingEnabled()) {
                generated.put(sig, adapterClass);
            }
        }
        return adapterClass;
    }

    public static byte[] createAdapterCode(ObjToIntMap functionNames, String adapterName, Class<?> superClass, Class<?>[] interfaces, String scriptClassName) {
        String methodKey;
        String methodSignature;
        Class<?>[] argTypes;
        String methodName;
        Constructor<?>[] ctors;
        ClassFileWriter cfw = new ClassFileWriter(adapterName, superClass.getName(), "<adapter>");
        cfw.addField("factory", "Lorg/mozilla/javascript/ContextFactory;", (short)17);
        cfw.addField("delegee", "Lorg/mozilla/javascript/Scriptable;", (short)17);
        cfw.addField("self", "Lorg/mozilla/javascript/Scriptable;", (short)17);
        int interfacesCount = interfaces == null ? 0 : interfaces.length;
        for (int i = 0; i < interfacesCount; ++i) {
            if (interfaces[i] == null) continue;
            cfw.addInterface(interfaces[i].getName());
        }
        String superName = superClass.getName().replace('.', '/');
        for (Constructor<?> ctor : ctors = superClass.getDeclaredConstructors()) {
            int mod = ctor.getModifiers();
            if (!Modifier.isPublic(mod) && !Modifier.isProtected(mod)) continue;
            JavaAdapter.generateCtor(cfw, adapterName, superName, ctor);
        }
        JavaAdapter.generateSerialCtor(cfw, adapterName, superName);
        if (scriptClassName != null) {
            JavaAdapter.generateEmptyCtor(cfw, adapterName, superName, scriptClassName);
        }
        ObjToIntMap generatedOverrides = new ObjToIntMap();
        ObjToIntMap generatedMethods = new ObjToIntMap();
        for (int i = 0; i < interfacesCount; ++i) {
            Method[] methods = interfaces[i].getMethods();
            for (int j = 0; j < methods.length; ++j) {
                Method method = methods[j];
                int mods = method.getModifiers();
                if (Modifier.isStatic(mods) || Modifier.isFinal(mods)) continue;
                methodName = method.getName();
                argTypes = method.getParameterTypes();
                if (!functionNames.has(methodName)) {
                    try {
                        superClass.getMethod(methodName, argTypes);
                        continue;
                    } catch (NoSuchMethodException e) {
                        // empty catch block
                    }
                }
                methodSignature = JavaAdapter.getMethodSignature(method, argTypes);
                methodKey = methodName + methodSignature;
                if (generatedOverrides.has(methodKey)) continue;
                JavaAdapter.generateMethod(cfw, adapterName, methodName, argTypes, method.getReturnType(), true);
                generatedOverrides.put(methodKey, 0);
                generatedMethods.put(methodName, 0);
            }
        }
        Method[] methods = JavaAdapter.getOverridableMethods(superClass);
        for (int j = 0; j < methods.length; ++j) {
            Method method = methods[j];
            int mods = method.getModifiers();
            boolean isAbstractMethod = Modifier.isAbstract(mods);
            methodName = method.getName();
            if (!isAbstractMethod && !functionNames.has(methodName)) continue;
            argTypes = method.getParameterTypes();
            methodSignature = JavaAdapter.getMethodSignature(method, argTypes);
            methodKey = methodName + methodSignature;
            if (generatedOverrides.has(methodKey)) continue;
            JavaAdapter.generateMethod(cfw, adapterName, methodName, argTypes, method.getReturnType(), true);
            generatedOverrides.put(methodKey, 0);
            generatedMethods.put(methodName, 0);
            if (isAbstractMethod) continue;
            JavaAdapter.generateSuper(cfw, adapterName, superName, methodName, methodSignature, argTypes, method.getReturnType());
        }
        ObjToIntMap.Iterator iter = new ObjToIntMap.Iterator(functionNames);
        iter.start();
        while (!iter.done()) {
            String functionName = (String)iter.getKey();
            if (!generatedMethods.has(functionName)) {
                int length = iter.getValue();
                Class[] parms = new Class[length];
                for (int k = 0; k < length; ++k) {
                    parms[k] = ScriptRuntime.ObjectClass;
                }
                JavaAdapter.generateMethod(cfw, adapterName, functionName, parms, ScriptRuntime.ObjectClass, false);
            }
            iter.next();
        }
        return cfw.toByteArray();
    }

    static Method[] getOverridableMethods(Class<?> clazz) {
        Class<?> c;
        ArrayList<Method> list = new ArrayList<Method>();
        HashSet<String> skip = new HashSet<String>();
        for (c = clazz; c != null; c = c.getSuperclass()) {
            JavaAdapter.appendOverridableMethods(c, list, skip);
        }
        for (c = clazz; c != null; c = c.getSuperclass()) {
            for (Class<?> intf : c.getInterfaces()) {
                JavaAdapter.appendOverridableMethods(intf, list, skip);
            }
        }
        return list.toArray(new Method[list.size()]);
    }

    private static void appendOverridableMethods(Class<?> c, ArrayList<Method> list, HashSet<String> skip) {
        Method[] methods = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            int mods;
            String methodKey = methods[i].getName() + JavaAdapter.getMethodSignature(methods[i], methods[i].getParameterTypes());
            if (skip.contains(methodKey) || Modifier.isStatic(mods = methods[i].getModifiers())) continue;
            if (Modifier.isFinal(mods)) {
                skip.add(methodKey);
                continue;
            }
            if (!Modifier.isPublic(mods) && !Modifier.isProtected(mods)) continue;
            list.add(methods[i]);
            skip.add(methodKey);
        }
    }

    static Class<?> loadAdapterClass(String className, byte[] classBytes) {
        Object staticDomain;
        Class<?> domainClass = SecurityController.getStaticSecurityDomainClass();
        if (domainClass == CodeSource.class || domainClass == ProtectionDomain.class) {
            ProtectionDomain protectionDomain = SecurityUtilities.getScriptProtectionDomain();
            if (protectionDomain == null) {
                protectionDomain = JavaAdapter.class.getProtectionDomain();
            }
            staticDomain = domainClass == CodeSource.class ? (protectionDomain == null ? null : protectionDomain.getCodeSource()) : protectionDomain;
        } else {
            staticDomain = null;
        }
        GeneratedClassLoader loader = SecurityController.createLoader(null, staticDomain);
        Class<?> result = loader.defineClass(className, classBytes);
        loader.linkClass(result);
        return result;
    }

    public static Function getFunction(Scriptable obj, String functionName) {
        Object x = ScriptableObject.getProperty(obj, functionName);
        if (x == Scriptable.NOT_FOUND) {
            return null;
        }
        if (!(x instanceof Function)) {
            throw ScriptRuntime.notFunctionError(x, functionName);
        }
        return (Function)x;
    }

    public static Object callMethod(ContextFactory factory, final Scriptable thisObj, final Function f, final Object[] args, final long argsToWrap) {
        if (f == null) {
            return null;
        }
        if (factory == null) {
            factory = ContextFactory.getGlobal();
        }
        final Scriptable scope = f.getParentScope();
        if (argsToWrap == 0L) {
            return Context.call(factory, f, scope, thisObj, args);
        }
        Context cx = Context.getCurrentContext();
        if (cx != null) {
            return JavaAdapter.doCall(cx, scope, thisObj, f, args, argsToWrap);
        }
        return factory.call(new ContextAction(){

            @Override
            public Object run(Context cx) {
                return JavaAdapter.doCall(cx, scope, thisObj, f, args, argsToWrap);
            }
        });
    }

    private static Object doCall(Context cx, Scriptable scope, Scriptable thisObj, Function f, Object[] args, long argsToWrap) {
        for (int i = 0; i != args.length; ++i) {
            Object arg;
            if (0L == (argsToWrap & (long)(1 << i)) || (arg = args[i]) instanceof Scriptable) continue;
            args[i] = cx.getWrapFactory().wrap(cx, scope, arg, null);
        }
        return f.call(cx, scope, thisObj, args);
    }

    public static Scriptable runScript(final Script script) {
        return (Scriptable)ContextFactory.getGlobal().call(new ContextAction(){

            @Override
            public Object run(Context cx) {
                ScriptableObject global = ScriptRuntime.getGlobal(cx);
                script.exec(cx, global);
                return global;
            }
        });
    }

    private static void generateCtor(ClassFileWriter cfw, String adapterName, String superName, Constructor<?> superCtor) {
        short locals = 3;
        Class<?>[] parameters = superCtor.getParameterTypes();
        if (parameters.length == 0) {
            cfw.startMethod("<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;)V", (short)1);
            cfw.add(42);
            cfw.addInvoke(183, superName, "<init>", "()V");
        } else {
            StringBuilder sig = new StringBuilder("(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;");
            int marker = sig.length();
            for (Class<?> c : parameters) {
                JavaAdapter.appendTypeString(sig, c);
            }
            sig.append(")V");
            cfw.startMethod("<init>", sig.toString(), (short)1);
            cfw.add(42);
            short paramOffset = 3;
            for (Class<?> parameter : parameters) {
                paramOffset = (short)(paramOffset + JavaAdapter.generatePushParam(cfw, paramOffset, parameter));
            }
            locals = paramOffset;
            sig.delete(1, marker);
            cfw.addInvoke(183, superName, "<init>", sig.toString());
        }
        cfw.add(42);
        cfw.add(43);
        cfw.add(181, adapterName, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(42);
        cfw.add(44);
        cfw.add(181, adapterName, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        cfw.add(42);
        cfw.add(43);
        cfw.add(42);
        cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        cfw.add(181, adapterName, "self", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(177);
        cfw.stopMethod(locals);
    }

    private static void generateSerialCtor(ClassFileWriter cfw, String adapterName, String superName) {
        cfw.startMethod("<init>", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)V", (short)1);
        cfw.add(42);
        cfw.addInvoke(183, superName, "<init>", "()V");
        cfw.add(42);
        cfw.add(43);
        cfw.add(181, adapterName, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        cfw.add(42);
        cfw.add(44);
        cfw.add(181, adapterName, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(42);
        cfw.add(45);
        cfw.add(181, adapterName, "self", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(177);
        cfw.stopMethod((short)4);
    }

    private static void generateEmptyCtor(ClassFileWriter cfw, String adapterName, String superName, String scriptClassName) {
        cfw.startMethod("<init>", "()V", (short)1);
        cfw.add(42);
        cfw.addInvoke(183, superName, "<init>", "()V");
        cfw.add(42);
        cfw.add(1);
        cfw.add(181, adapterName, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        cfw.add(187, scriptClassName);
        cfw.add(89);
        cfw.addInvoke(183, scriptClassName, "<init>", "()V");
        cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "runScript", "(Lorg/mozilla/javascript/Script;)Lorg/mozilla/javascript/Scriptable;");
        cfw.add(76);
        cfw.add(42);
        cfw.add(43);
        cfw.add(181, adapterName, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(42);
        cfw.add(43);
        cfw.add(42);
        cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        cfw.add(181, adapterName, "self", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(177);
        cfw.stopMethod((short)2);
    }

    static void generatePushWrappedArgs(ClassFileWriter cfw, Class<?>[] argTypes, int arrayLength) {
        cfw.addPush(arrayLength);
        cfw.add(189, "java/lang/Object");
        int paramOffset = 1;
        for (int i = 0; i != argTypes.length; ++i) {
            cfw.add(89);
            cfw.addPush(i);
            paramOffset += JavaAdapter.generateWrapArg(cfw, paramOffset, argTypes[i]);
            cfw.add(83);
        }
    }

    private static int generateWrapArg(ClassFileWriter cfw, int paramOffset, Class<?> argType) {
        int size = 1;
        if (!argType.isPrimitive()) {
            cfw.add(25, paramOffset);
        } else if (argType == Boolean.TYPE) {
            cfw.add(187, "java/lang/Boolean");
            cfw.add(89);
            cfw.add(21, paramOffset);
            cfw.addInvoke(183, "java/lang/Boolean", "<init>", "(Z)V");
        } else if (argType == Character.TYPE) {
            cfw.add(21, paramOffset);
            cfw.addInvoke(184, "java/lang/String", "valueOf", "(C)Ljava/lang/String;");
        } else {
            cfw.add(187, "java/lang/Double");
            cfw.add(89);
            String typeName = argType.getName();
            switch (typeName.charAt(0)) {
                case 'b': 
                case 'i': 
                case 's': {
                    cfw.add(21, paramOffset);
                    cfw.add(135);
                    break;
                }
                case 'l': {
                    cfw.add(22, paramOffset);
                    cfw.add(138);
                    size = 2;
                    break;
                }
                case 'f': {
                    cfw.add(23, paramOffset);
                    cfw.add(141);
                    break;
                }
                case 'd': {
                    cfw.add(24, paramOffset);
                    size = 2;
                }
            }
            cfw.addInvoke(183, "java/lang/Double", "<init>", "(D)V");
        }
        return size;
    }

    static void generateReturnResult(ClassFileWriter cfw, Class<?> retType, boolean callConvertResult) {
        if (retType == Void.TYPE) {
            cfw.add(87);
            cfw.add(177);
        } else if (retType == Boolean.TYPE) {
            cfw.addInvoke(184, "org/mozilla/javascript/Context", "toBoolean", "(Ljava/lang/Object;)Z");
            cfw.add(172);
        } else if (retType == Character.TYPE) {
            cfw.addInvoke(184, "org/mozilla/javascript/Context", "toString", "(Ljava/lang/Object;)Ljava/lang/String;");
            cfw.add(3);
            cfw.addInvoke(182, "java/lang/String", "charAt", "(I)C");
            cfw.add(172);
        } else if (retType.isPrimitive()) {
            cfw.addInvoke(184, "org/mozilla/javascript/Context", "toNumber", "(Ljava/lang/Object;)D");
            String typeName = retType.getName();
            switch (typeName.charAt(0)) {
                case 'b': 
                case 'i': 
                case 's': {
                    cfw.add(142);
                    cfw.add(172);
                    break;
                }
                case 'l': {
                    cfw.add(143);
                    cfw.add(173);
                    break;
                }
                case 'f': {
                    cfw.add(144);
                    cfw.add(174);
                    break;
                }
                case 'd': {
                    cfw.add(175);
                    break;
                }
                default: {
                    throw new RuntimeException("Unexpected return type " + retType.toString());
                }
            }
        } else {
            String retTypeStr = retType.getName();
            if (callConvertResult) {
                cfw.addLoadConstant(retTypeStr);
                cfw.addInvoke(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
                cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "convertResult", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
            }
            cfw.add(192, retTypeStr);
            cfw.add(176);
        }
    }

    private static void generateMethod(ClassFileWriter cfw, String genName, String methodName, Class<?>[] parms, Class<?> returnType, boolean convertResult) {
        StringBuilder sb = new StringBuilder();
        int paramsEnd = JavaAdapter.appendMethodSignature(parms, returnType, sb);
        String methodSignature = sb.toString();
        cfw.startMethod(methodName, methodSignature, (short)1);
        cfw.add(42);
        cfw.add(180, genName, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        cfw.add(42);
        cfw.add(180, genName, "self", "Lorg/mozilla/javascript/Scriptable;");
        cfw.add(42);
        cfw.add(180, genName, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        cfw.addPush(methodName);
        cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "getFunction", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Function;");
        JavaAdapter.generatePushWrappedArgs(cfw, parms, parms.length);
        if (parms.length > 64) {
            throw Context.reportRuntimeError0("JavaAdapter can not subclass methods with more then 64 arguments.");
        }
        long convertionMask = 0L;
        for (int i = 0; i != parms.length; ++i) {
            if (parms[i].isPrimitive()) continue;
            convertionMask |= (long)(1 << i);
        }
        cfw.addPush(convertionMask);
        cfw.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "callMethod", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Function;[Ljava/lang/Object;J)Ljava/lang/Object;");
        JavaAdapter.generateReturnResult(cfw, returnType, convertResult);
        cfw.stopMethod((short)paramsEnd);
    }

    private static int generatePushParam(ClassFileWriter cfw, int paramOffset, Class<?> paramType) {
        if (!paramType.isPrimitive()) {
            cfw.addALoad(paramOffset);
            return 1;
        }
        String typeName = paramType.getName();
        switch (typeName.charAt(0)) {
            case 'b': 
            case 'c': 
            case 'i': 
            case 's': 
            case 'z': {
                cfw.addILoad(paramOffset);
                return 1;
            }
            case 'l': {
                cfw.addLLoad(paramOffset);
                return 2;
            }
            case 'f': {
                cfw.addFLoad(paramOffset);
                return 1;
            }
            case 'd': {
                cfw.addDLoad(paramOffset);
                return 2;
            }
        }
        throw Kit.codeBug();
    }

    private static void generatePopResult(ClassFileWriter cfw, Class<?> retType) {
        if (retType.isPrimitive()) {
            String typeName = retType.getName();
            switch (typeName.charAt(0)) {
                case 'b': 
                case 'c': 
                case 'i': 
                case 's': 
                case 'z': {
                    cfw.add(172);
                    break;
                }
                case 'l': {
                    cfw.add(173);
                    break;
                }
                case 'f': {
                    cfw.add(174);
                    break;
                }
                case 'd': {
                    cfw.add(175);
                }
            }
        } else {
            cfw.add(176);
        }
    }

    private static void generateSuper(ClassFileWriter cfw, String genName, String superName, String methodName, String methodSignature, Class<?>[] parms, Class<?> returnType) {
        cfw.startMethod("super$" + methodName, methodSignature, (short)1);
        cfw.add(25, 0);
        int paramOffset = 1;
        for (Class<?> parm : parms) {
            paramOffset += JavaAdapter.generatePushParam(cfw, paramOffset, parm);
        }
        cfw.addInvoke(183, superName, methodName, methodSignature);
        Class<?> retType = returnType;
        if (!retType.equals(Void.TYPE)) {
            JavaAdapter.generatePopResult(cfw, retType);
        } else {
            cfw.add(177);
        }
        cfw.stopMethod((short)(paramOffset + 1));
    }

    private static String getMethodSignature(Method method, Class<?>[] argTypes) {
        StringBuilder sb = new StringBuilder();
        JavaAdapter.appendMethodSignature(argTypes, method.getReturnType(), sb);
        return sb.toString();
    }

    static int appendMethodSignature(Class<?>[] argTypes, Class<?> returnType, StringBuilder sb) {
        sb.append('(');
        int firstLocal = 1 + argTypes.length;
        for (Class<?> type : argTypes) {
            JavaAdapter.appendTypeString(sb, type);
            if (type != Long.TYPE && type != Double.TYPE) continue;
            ++firstLocal;
        }
        sb.append(')');
        JavaAdapter.appendTypeString(sb, returnType);
        return firstLocal;
    }

    private static StringBuilder appendTypeString(StringBuilder sb, Class<?> type) {
        while (type.isArray()) {
            sb.append('[');
            type = type.getComponentType();
        }
        if (type.isPrimitive()) {
            char typeLetter;
            if (type == Boolean.TYPE) {
                typeLetter = 'Z';
            } else if (type == Long.TYPE) {
                typeLetter = 'J';
            } else {
                String typeName = type.getName();
                typeLetter = Character.toUpperCase(typeName.charAt(0));
            }
            sb.append(typeLetter);
        } else {
            sb.append('L');
            sb.append(type.getName().replace('.', '/'));
            sb.append(';');
        }
        return sb;
    }

    static int[] getArgsToConvert(Class<?>[] argTypes) {
        int count = 0;
        for (int i = 0; i != argTypes.length; ++i) {
            if (argTypes[i].isPrimitive()) continue;
            ++count;
        }
        if (count == 0) {
            return null;
        }
        int[] array = new int[count];
        count = 0;
        for (int i = 0; i != argTypes.length; ++i) {
            if (argTypes[i].isPrimitive()) continue;
            array[count++] = i;
        }
        return array;
    }

    static class JavaAdapterSignature {
        Class<?> superClass;
        Class<?>[] interfaces;
        ObjToIntMap names;

        JavaAdapterSignature(Class<?> superClass, Class<?>[] interfaces, ObjToIntMap names) {
            this.superClass = superClass;
            this.interfaces = interfaces;
            this.names = names;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof JavaAdapterSignature)) {
                return false;
            }
            JavaAdapterSignature sig = (JavaAdapterSignature)obj;
            if (this.superClass != sig.superClass) {
                return false;
            }
            if (this.interfaces != sig.interfaces) {
                if (this.interfaces.length != sig.interfaces.length) {
                    return false;
                }
                for (int i = 0; i < this.interfaces.length; ++i) {
                    if (this.interfaces[i] == sig.interfaces[i]) continue;
                    return false;
                }
            }
            if (this.names.size() != sig.names.size()) {
                return false;
            }
            ObjToIntMap.Iterator iter = new ObjToIntMap.Iterator(this.names);
            iter.start();
            while (!iter.done()) {
                String name = (String)iter.getKey();
                int arity = iter.getValue();
                if (arity != sig.names.get(name, arity + 1)) {
                    return false;
                }
                iter.next();
            }
            return true;
        }

        public int hashCode() {
            return this.superClass.hashCode() + Arrays.hashCode(this.interfaces) ^ this.names.size();
        }
    }
}

