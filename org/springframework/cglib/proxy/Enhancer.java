/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.DuplicatesPredicate;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.MethodInfoTransformer;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.cglib.core.ObjectSwitchCallback;
import org.springframework.cglib.core.ProcessSwitchCallback;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.RejectModifierPredicate;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.Transformer;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.core.VisibilityPredicate;
import org.springframework.cglib.core.WeakCacheKey;
import org.springframework.cglib.proxy.BridgeMethodResolver;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.CallbackGenerator;
import org.springframework.cglib.proxy.CallbackInfo;
import org.springframework.cglib.proxy.Factory;

public class Enhancer
extends AbstractClassGenerator {
    private static final CallbackFilter ALL_ZERO = new CallbackFilter(){

        @Override
        public int accept(Method method) {
            return 0;
        }
    };
    private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(Enhancer.class.getName());
    private static final EnhancerKey KEY_FACTORY = (EnhancerKey)((Object)KeyFactory.create(EnhancerKey.class, KeyFactory.HASH_ASM_TYPE, null));
    private static final String BOUND_FIELD = "CGLIB$BOUND";
    private static final String FACTORY_DATA_FIELD = "CGLIB$FACTORY_DATA";
    private static final String THREAD_CALLBACKS_FIELD = "CGLIB$THREAD_CALLBACKS";
    private static final String STATIC_CALLBACKS_FIELD = "CGLIB$STATIC_CALLBACKS";
    private static final String SET_THREAD_CALLBACKS_NAME = "CGLIB$SET_THREAD_CALLBACKS";
    private static final String SET_STATIC_CALLBACKS_NAME = "CGLIB$SET_STATIC_CALLBACKS";
    private static final String CONSTRUCTED_FIELD = "CGLIB$CONSTRUCTED";
    private static final String CALLBACK_FILTER_FIELD = "CGLIB$CALLBACK_FILTER";
    private static final Type OBJECT_TYPE = TypeUtils.parseType("Object");
    private static final Type FACTORY = TypeUtils.parseType("org.springframework.cglib.proxy.Factory");
    private static final Type ILLEGAL_STATE_EXCEPTION = TypeUtils.parseType("IllegalStateException");
    private static final Type ILLEGAL_ARGUMENT_EXCEPTION = TypeUtils.parseType("IllegalArgumentException");
    private static final Type THREAD_LOCAL = TypeUtils.parseType("ThreadLocal");
    private static final Type CALLBACK = TypeUtils.parseType("org.springframework.cglib.proxy.Callback");
    private static final Type CALLBACK_ARRAY = Type.getType(Callback[].class);
    private static final Signature CSTRUCT_NULL = TypeUtils.parseConstructor("");
    private static final Signature SET_THREAD_CALLBACKS = new Signature("CGLIB$SET_THREAD_CALLBACKS", Type.VOID_TYPE, new Type[]{CALLBACK_ARRAY});
    private static final Signature SET_STATIC_CALLBACKS = new Signature("CGLIB$SET_STATIC_CALLBACKS", Type.VOID_TYPE, new Type[]{CALLBACK_ARRAY});
    private static final Signature NEW_INSTANCE = new Signature("newInstance", Constants.TYPE_OBJECT, new Type[]{CALLBACK_ARRAY});
    private static final Signature MULTIARG_NEW_INSTANCE = new Signature("newInstance", Constants.TYPE_OBJECT, new Type[]{Constants.TYPE_CLASS_ARRAY, Constants.TYPE_OBJECT_ARRAY, CALLBACK_ARRAY});
    private static final Signature SINGLE_NEW_INSTANCE = new Signature("newInstance", Constants.TYPE_OBJECT, new Type[]{CALLBACK});
    private static final Signature SET_CALLBACK = new Signature("setCallback", Type.VOID_TYPE, new Type[]{Type.INT_TYPE, CALLBACK});
    private static final Signature GET_CALLBACK = new Signature("getCallback", CALLBACK, new Type[]{Type.INT_TYPE});
    private static final Signature SET_CALLBACKS = new Signature("setCallbacks", Type.VOID_TYPE, new Type[]{CALLBACK_ARRAY});
    private static final Signature GET_CALLBACKS = new Signature("getCallbacks", CALLBACK_ARRAY, new Type[0]);
    private static final Signature THREAD_LOCAL_GET = TypeUtils.parseSignature("Object get()");
    private static final Signature THREAD_LOCAL_SET = TypeUtils.parseSignature("void set(Object)");
    private static final Signature BIND_CALLBACKS = TypeUtils.parseSignature("void CGLIB$BIND_CALLBACKS(Object)");
    private EnhancerFactoryData currentData;
    private Object currentKey;
    private Class[] interfaces;
    private CallbackFilter filter;
    private Callback[] callbacks;
    private Type[] callbackTypes;
    private boolean validateCallbackTypes;
    private boolean classOnly;
    private Class superclass;
    private Class[] argumentTypes;
    private Object[] arguments;
    private boolean useFactory = true;
    private Long serialVersionUID;
    private boolean interceptDuringConstruction = true;

    public Enhancer() {
        super(SOURCE);
    }

    public void setSuperclass(Class superclass) {
        if (superclass != null && superclass.isInterface()) {
            this.setInterfaces(new Class[]{superclass});
            this.setContextClass(superclass);
        } else if (superclass != null && superclass.equals(Object.class)) {
            this.superclass = null;
        } else {
            this.superclass = superclass;
            this.setContextClass(superclass);
        }
    }

    public void setInterfaces(Class[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setCallbackFilter(CallbackFilter filter) {
        this.filter = filter;
    }

    public void setCallback(Callback callback) {
        this.setCallbacks(new Callback[]{callback});
    }

    public void setCallbacks(Callback[] callbacks) {
        if (callbacks != null && callbacks.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        this.callbacks = callbacks;
    }

    public void setUseFactory(boolean useFactory) {
        this.useFactory = useFactory;
    }

    public void setInterceptDuringConstruction(boolean interceptDuringConstruction) {
        this.interceptDuringConstruction = interceptDuringConstruction;
    }

    public void setCallbackType(Class callbackType) {
        this.setCallbackTypes(new Class[]{callbackType});
    }

    public void setCallbackTypes(Class[] callbackTypes) {
        if (callbackTypes != null && callbackTypes.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        this.callbackTypes = CallbackInfo.determineTypes(callbackTypes);
    }

    public Object create() {
        this.classOnly = false;
        this.argumentTypes = null;
        return this.createHelper();
    }

    public Object create(Class[] argumentTypes, Object[] arguments) {
        this.classOnly = false;
        if (argumentTypes == null || arguments == null || argumentTypes.length != arguments.length) {
            throw new IllegalArgumentException("Arguments must be non-null and of equal length");
        }
        this.argumentTypes = argumentTypes;
        this.arguments = arguments;
        return this.createHelper();
    }

    public Class createClass() {
        this.classOnly = true;
        return (Class)this.createHelper();
    }

    public void setSerialVersionUID(Long sUID) {
        this.serialVersionUID = sUID;
    }

    private void preValidate() {
        if (this.callbackTypes == null) {
            this.callbackTypes = CallbackInfo.determineTypes(this.callbacks, false);
            this.validateCallbackTypes = true;
        }
        if (this.filter == null) {
            if (this.callbackTypes.length > 1) {
                throw new IllegalStateException("Multiple callback types possible but no filter specified");
            }
            this.filter = ALL_ZERO;
        }
    }

    private void validate() {
        if (this.classOnly ^ this.callbacks == null) {
            if (this.classOnly) {
                throw new IllegalStateException("createClass does not accept callbacks");
            }
            throw new IllegalStateException("Callbacks are required");
        }
        if (this.classOnly && this.callbackTypes == null) {
            throw new IllegalStateException("Callback types are required");
        }
        if (this.validateCallbackTypes) {
            this.callbackTypes = null;
        }
        if (this.callbacks != null && this.callbackTypes != null) {
            if (this.callbacks.length != this.callbackTypes.length) {
                throw new IllegalStateException("Lengths of callback and callback types array must be the same");
            }
            Type[] check = CallbackInfo.determineTypes(this.callbacks);
            for (int i = 0; i < check.length; ++i) {
                if (check[i].equals(this.callbackTypes[i])) continue;
                throw new IllegalStateException("Callback " + check[i] + " is not assignable to " + this.callbackTypes[i]);
            }
        } else if (this.callbacks != null) {
            this.callbackTypes = CallbackInfo.determineTypes(this.callbacks);
        }
        if (this.interfaces != null) {
            for (int i = 0; i < this.interfaces.length; ++i) {
                if (this.interfaces[i] == null) {
                    throw new IllegalStateException("Interfaces cannot be null");
                }
                if (this.interfaces[i].isInterface()) continue;
                throw new IllegalStateException(this.interfaces[i] + " is not an interface");
            }
        }
    }

    private Object createHelper() {
        Object key;
        this.preValidate();
        this.currentKey = key = KEY_FACTORY.newInstance(this.superclass != null ? this.superclass.getName() : null, ReflectUtils.getNames(this.interfaces), this.filter == ALL_ZERO ? null : new WeakCacheKey<CallbackFilter>(this.filter), this.callbackTypes, this.useFactory, this.interceptDuringConstruction, this.serialVersionUID);
        Object result = super.create(key);
        return result;
    }

    @Override
    protected Class generate(AbstractClassGenerator.ClassLoaderData data) {
        this.validate();
        if (this.superclass != null) {
            this.setNamePrefix(this.superclass.getName());
        } else if (this.interfaces != null) {
            this.setNamePrefix(this.interfaces[ReflectUtils.findPackageProtected(this.interfaces)].getName());
        }
        return super.generate(data);
    }

    @Override
    protected ClassLoader getDefaultClassLoader() {
        if (this.superclass != null) {
            return this.superclass.getClassLoader();
        }
        if (this.interfaces != null) {
            return this.interfaces[0].getClassLoader();
        }
        return null;
    }

    @Override
    protected ProtectionDomain getProtectionDomain() {
        if (this.superclass != null) {
            return ReflectUtils.getProtectionDomain(this.superclass);
        }
        if (this.interfaces != null) {
            return ReflectUtils.getProtectionDomain(this.interfaces[0]);
        }
        return null;
    }

    private Signature rename(Signature sig, int index) {
        return new Signature("CGLIB$" + sig.getName() + "$" + index, sig.getDescriptor());
    }

    public static void getMethods(Class superclass, Class[] interfaces, List methods) {
        Enhancer.getMethods(superclass, interfaces, methods, null, null);
    }

    private static void getMethods(Class superclass, Class[] interfaces, List methods, List interfaceMethods, Set forcePublic) {
        List target;
        ReflectUtils.addAllMethods(superclass, methods);
        List list = target = interfaceMethods != null ? interfaceMethods : methods;
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i] == Factory.class) continue;
                ReflectUtils.addAllMethods(interfaces[i], target);
            }
        }
        if (interfaceMethods != null) {
            if (forcePublic != null) {
                forcePublic.addAll(MethodWrapper.createSet(interfaceMethods));
            }
            methods.addAll(interfaceMethods);
        }
        CollectionUtils.filter(methods, new RejectModifierPredicate(8));
        CollectionUtils.filter(methods, new VisibilityPredicate(superclass, true));
        CollectionUtils.filter(methods, new DuplicatesPredicate());
        CollectionUtils.filter(methods, new RejectModifierPredicate(16));
    }

    @Override
    public void generateClass(ClassVisitor v) throws Exception {
        Class sc;
        Class clazz = sc = this.superclass == null ? Object.class : this.superclass;
        if (TypeUtils.isFinal(sc.getModifiers())) {
            throw new IllegalArgumentException("Cannot subclass final class " + sc.getName());
        }
        ArrayList constructors = new ArrayList(Arrays.asList(sc.getDeclaredConstructors()));
        this.filterConstructors(sc, constructors);
        ArrayList actualMethods = new ArrayList();
        ArrayList interfaceMethods = new ArrayList();
        final HashSet forcePublic = new HashSet();
        Enhancer.getMethods(sc, this.interfaces, actualMethods, interfaceMethods, forcePublic);
        List methods = CollectionUtils.transform(actualMethods, new Transformer(){

            @Override
            public Object transform(Object value) {
                Method method = (Method)value;
                int modifiers = 0x10 | method.getModifiers() & 0xFFFFFBFF & 0xFFFFFEFF & 0xFFFFFFDF;
                if (forcePublic.contains(MethodWrapper.create(method))) {
                    modifiers = modifiers & 0xFFFFFFFB | 1;
                }
                return ReflectUtils.getMethodInfo(method, modifiers);
            }
        });
        ClassEmitter e = new ClassEmitter(v);
        if (this.currentData == null) {
            e.begin_class(52, 1, this.getClassName(), Type.getType(sc), this.useFactory ? TypeUtils.add(TypeUtils.getTypes(this.interfaces), FACTORY) : TypeUtils.getTypes(this.interfaces), "<generated>");
        } else {
            e.begin_class(52, 1, this.getClassName(), null, new Type[]{FACTORY}, "<generated>");
        }
        List constructorInfo = CollectionUtils.transform(constructors, MethodInfoTransformer.getInstance());
        e.declare_field(2, BOUND_FIELD, Type.BOOLEAN_TYPE, null);
        e.declare_field(9, FACTORY_DATA_FIELD, OBJECT_TYPE, null);
        if (!this.interceptDuringConstruction) {
            e.declare_field(2, CONSTRUCTED_FIELD, Type.BOOLEAN_TYPE, null);
        }
        e.declare_field(26, THREAD_CALLBACKS_FIELD, THREAD_LOCAL, null);
        e.declare_field(26, STATIC_CALLBACKS_FIELD, CALLBACK_ARRAY, null);
        if (this.serialVersionUID != null) {
            e.declare_field(26, "serialVersionUID", Type.LONG_TYPE, this.serialVersionUID);
        }
        for (int i = 0; i < this.callbackTypes.length; ++i) {
            e.declare_field(2, Enhancer.getCallbackField(i), this.callbackTypes[i], null);
        }
        e.declare_field(10, CALLBACK_FILTER_FIELD, OBJECT_TYPE, null);
        if (this.currentData == null) {
            this.emitMethods(e, methods, actualMethods);
            this.emitConstructors(e, constructorInfo);
        } else {
            this.emitDefaultConstructor(e);
        }
        this.emitSetThreadCallbacks(e);
        this.emitSetStaticCallbacks(e);
        this.emitBindCallbacks(e);
        if (this.useFactory || this.currentData != null) {
            int[] keys = this.getCallbackKeys();
            this.emitNewInstanceCallbacks(e);
            this.emitNewInstanceCallback(e);
            this.emitNewInstanceMultiarg(e, constructorInfo);
            this.emitGetCallback(e, keys);
            this.emitSetCallback(e, keys);
            this.emitGetCallbacks(e);
            this.emitSetCallbacks(e);
        }
        e.end_class();
    }

    protected void filterConstructors(Class sc, List constructors) {
        CollectionUtils.filter(constructors, new VisibilityPredicate(sc, true));
        if (constructors.size() == 0) {
            throw new IllegalArgumentException("No visible constructors in " + sc);
        }
    }

    @Override
    protected Object firstInstance(Class type) throws Exception {
        if (this.classOnly) {
            return type;
        }
        return this.createUsingReflection(type);
    }

    @Override
    protected Object nextInstance(Object instance) {
        EnhancerFactoryData data = (EnhancerFactoryData)instance;
        if (this.classOnly) {
            return data.generatedClass;
        }
        Class[] argumentTypes = this.argumentTypes;
        Object[] arguments = this.arguments;
        if (argumentTypes == null) {
            argumentTypes = Constants.EMPTY_CLASS_ARRAY;
            arguments = null;
        }
        return data.newInstance(argumentTypes, arguments, this.callbacks);
    }

    protected Object wrapCachedClass(Class klass) {
        Class[] argumentTypes = this.argumentTypes;
        if (argumentTypes == null) {
            argumentTypes = Constants.EMPTY_CLASS_ARRAY;
        }
        EnhancerFactoryData factoryData = new EnhancerFactoryData(klass, argumentTypes, this.classOnly);
        Field factoryDataField = null;
        try {
            factoryDataField = klass.getField(FACTORY_DATA_FIELD);
            factoryDataField.set(null, factoryData);
            Field callbackFilterField = klass.getDeclaredField(CALLBACK_FILTER_FIELD);
            callbackFilterField.setAccessible(true);
            callbackFilterField.set(null, this.filter);
        } catch (NoSuchFieldException e) {
            throw new CodeGenerationException(e);
        } catch (IllegalAccessException e) {
            throw new CodeGenerationException(e);
        }
        return new WeakReference<EnhancerFactoryData>(factoryData);
    }

    protected Object unwrapCachedValue(Object cached) {
        if (this.currentKey instanceof EnhancerKey) {
            EnhancerFactoryData data = (EnhancerFactoryData)((WeakReference)cached).get();
            return data;
        }
        return super.unwrapCachedValue(cached);
    }

    public static void registerCallbacks(Class generatedClass, Callback[] callbacks) {
        Enhancer.setThreadCallbacks(generatedClass, callbacks);
    }

    public static void registerStaticCallbacks(Class generatedClass, Callback[] callbacks) {
        Enhancer.setCallbacksHelper(generatedClass, callbacks, SET_STATIC_CALLBACKS_NAME);
    }

    public static boolean isEnhanced(Class type) {
        try {
            Enhancer.getCallbacksSetter(type, SET_THREAD_CALLBACKS_NAME);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private static void setThreadCallbacks(Class type, Callback[] callbacks) {
        Enhancer.setCallbacksHelper(type, callbacks, SET_THREAD_CALLBACKS_NAME);
    }

    private static void setCallbacksHelper(Class type, Callback[] callbacks, String methodName) {
        try {
            Method setter = Enhancer.getCallbacksSetter(type, methodName);
            setter.invoke(null, new Object[]{callbacks});
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(type + " is not an enhanced class");
        } catch (IllegalAccessException e) {
            throw new CodeGenerationException(e);
        } catch (InvocationTargetException e) {
            throw new CodeGenerationException(e);
        }
    }

    private static Method getCallbacksSetter(Class type, String methodName) throws NoSuchMethodException {
        return type.getDeclaredMethod(methodName, Callback[].class);
    }

    private Object createUsingReflection(Class type) {
        Enhancer.setThreadCallbacks(type, this.callbacks);
        try {
            if (this.argumentTypes != null) {
                Object object = ReflectUtils.newInstance(type, this.argumentTypes, this.arguments);
                return object;
            }
            Object object = ReflectUtils.newInstance(type);
            return object;
        } finally {
            Enhancer.setThreadCallbacks(type, null);
        }
    }

    public static Object create(Class type, Callback callback) {
        Enhancer e = new Enhancer();
        e.setSuperclass(type);
        e.setCallback(callback);
        return e.create();
    }

    public static Object create(Class superclass, Class[] interfaces, Callback callback) {
        Enhancer e = new Enhancer();
        e.setSuperclass(superclass);
        e.setInterfaces(interfaces);
        e.setCallback(callback);
        return e.create();
    }

    public static Object create(Class superclass, Class[] interfaces, CallbackFilter filter, Callback[] callbacks) {
        Enhancer e = new Enhancer();
        e.setSuperclass(superclass);
        e.setInterfaces(interfaces);
        e.setCallbackFilter(filter);
        e.setCallbacks(callbacks);
        return e.create();
    }

    private void emitDefaultConstructor(ClassEmitter ce) {
        Constructor declaredConstructor;
        try {
            declaredConstructor = Object.class.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Object should have default constructor ", e);
        }
        MethodInfo constructor = (MethodInfo)MethodInfoTransformer.getInstance().transform(declaredConstructor);
        CodeEmitter e = EmitUtils.begin_method(ce, constructor, 1);
        e.load_this();
        e.dup();
        Signature sig = constructor.getSignature();
        e.super_invoke_constructor(sig);
        e.return_value();
        e.end_method();
    }

    private void emitConstructors(ClassEmitter ce, List constructors) {
        boolean seenNull = false;
        for (MethodInfo constructor : constructors) {
            if (this.currentData != null && !"()V".equals(constructor.getSignature().getDescriptor())) continue;
            CodeEmitter e = EmitUtils.begin_method(ce, constructor, 1);
            e.load_this();
            e.dup();
            e.load_args();
            Signature sig = constructor.getSignature();
            seenNull = seenNull || sig.getDescriptor().equals("()V");
            e.super_invoke_constructor(sig);
            if (this.currentData == null) {
                e.invoke_static_this(BIND_CALLBACKS);
                if (!this.interceptDuringConstruction) {
                    e.load_this();
                    e.push(1);
                    e.putfield(CONSTRUCTED_FIELD);
                }
            }
            e.return_value();
            e.end_method();
        }
        if (!this.classOnly && !seenNull && this.arguments == null) {
            throw new IllegalArgumentException("Superclass has no null constructors but no arguments were given");
        }
    }

    private int[] getCallbackKeys() {
        int[] keys = new int[this.callbackTypes.length];
        for (int i = 0; i < this.callbackTypes.length; ++i) {
            keys[i] = i;
        }
        return keys;
    }

    private void emitGetCallback(ClassEmitter ce, int[] keys) {
        final CodeEmitter e = ce.begin_method(1, GET_CALLBACK, null);
        e.load_this();
        e.invoke_static_this(BIND_CALLBACKS);
        e.load_this();
        e.load_arg(0);
        e.process_switch(keys, new ProcessSwitchCallback(){

            @Override
            public void processCase(int key, Label end) {
                e.getfield(Enhancer.getCallbackField(key));
                e.goTo(end);
            }

            @Override
            public void processDefault() {
                e.pop();
                e.aconst_null();
            }
        });
        e.return_value();
        e.end_method();
    }

    private void emitSetCallback(ClassEmitter ce, int[] keys) {
        final CodeEmitter e = ce.begin_method(1, SET_CALLBACK, null);
        e.load_arg(0);
        e.process_switch(keys, new ProcessSwitchCallback(){

            @Override
            public void processCase(int key, Label end) {
                e.load_this();
                e.load_arg(1);
                e.checkcast(Enhancer.this.callbackTypes[key]);
                e.putfield(Enhancer.getCallbackField(key));
                e.goTo(end);
            }

            @Override
            public void processDefault() {
            }
        });
        e.return_value();
        e.end_method();
    }

    private void emitSetCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(1, SET_CALLBACKS, null);
        e.load_this();
        e.load_arg(0);
        for (int i = 0; i < this.callbackTypes.length; ++i) {
            e.dup2();
            e.aaload(i);
            e.checkcast(this.callbackTypes[i]);
            e.putfield(Enhancer.getCallbackField(i));
        }
        e.return_value();
        e.end_method();
    }

    private void emitGetCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(1, GET_CALLBACKS, null);
        e.load_this();
        e.invoke_static_this(BIND_CALLBACKS);
        e.load_this();
        e.push(this.callbackTypes.length);
        e.newarray(CALLBACK);
        for (int i = 0; i < this.callbackTypes.length; ++i) {
            e.dup();
            e.push(i);
            e.load_this();
            e.getfield(Enhancer.getCallbackField(i));
            e.aastore();
        }
        e.return_value();
        e.end_method();
    }

    private void emitNewInstanceCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(1, NEW_INSTANCE, null);
        Type thisType = this.getThisType(e);
        e.load_arg(0);
        e.invoke_static(thisType, SET_THREAD_CALLBACKS, false);
        this.emitCommonNewInstance(e);
    }

    private Type getThisType(CodeEmitter e) {
        if (this.currentData == null) {
            return e.getClassEmitter().getClassType();
        }
        return Type.getType(this.currentData.generatedClass);
    }

    private void emitCommonNewInstance(CodeEmitter e) {
        Type thisType = this.getThisType(e);
        e.new_instance(thisType);
        e.dup();
        e.invoke_constructor(thisType);
        e.aconst_null();
        e.invoke_static(thisType, SET_THREAD_CALLBACKS, false);
        e.return_value();
        e.end_method();
    }

    private void emitNewInstanceCallback(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(1, SINGLE_NEW_INSTANCE, null);
        switch (this.callbackTypes.length) {
            case 0: {
                break;
            }
            case 1: {
                e.push(1);
                e.newarray(CALLBACK);
                e.dup();
                e.push(0);
                e.load_arg(0);
                e.aastore();
                e.invoke_static(this.getThisType(e), SET_THREAD_CALLBACKS, false);
                break;
            }
            default: {
                e.throw_exception(ILLEGAL_STATE_EXCEPTION, "More than one callback object required");
            }
        }
        this.emitCommonNewInstance(e);
    }

    private void emitNewInstanceMultiarg(ClassEmitter ce, List constructors) {
        final CodeEmitter e = ce.begin_method(1, MULTIARG_NEW_INSTANCE, null);
        final Type thisType = this.getThisType(e);
        e.load_arg(2);
        e.invoke_static(thisType, SET_THREAD_CALLBACKS, false);
        e.new_instance(thisType);
        e.dup();
        e.load_arg(0);
        EmitUtils.constructor_switch(e, constructors, new ObjectSwitchCallback(){

            @Override
            public void processCase(Object key, Label end) {
                MethodInfo constructor = (MethodInfo)key;
                Type[] types = constructor.getSignature().getArgumentTypes();
                for (int i = 0; i < types.length; ++i) {
                    e.load_arg(1);
                    e.push(i);
                    e.aaload();
                    e.unbox(types[i]);
                }
                e.invoke_constructor(thisType, constructor.getSignature());
                e.goTo(end);
            }

            @Override
            public void processDefault() {
                e.throw_exception(ILLEGAL_ARGUMENT_EXCEPTION, "Constructor not found");
            }
        });
        e.aconst_null();
        e.invoke_static(thisType, SET_THREAD_CALLBACKS, false);
        e.return_value();
        e.end_method();
    }

    private void emitMethods(ClassEmitter ce, List methods, List actualMethods) {
        Iterator it2;
        CallbackGenerator[] generators = CallbackInfo.getGenerators(this.callbackTypes);
        HashMap<CallbackGenerator, ArrayList<MethodInfo>> groups = new HashMap<CallbackGenerator, ArrayList<MethodInfo>>();
        final HashMap<MethodInfo, Integer> indexes = new HashMap<MethodInfo, Integer>();
        final HashMap<MethodInfo, Integer> originalModifiers = new HashMap<MethodInfo, Integer>();
        final Map positions = CollectionUtils.getIndexMap(methods);
        HashMap declToBridge = new HashMap();
        Iterator it1 = methods.iterator();
        Iterator iterator = it2 = actualMethods != null ? actualMethods.iterator() : null;
        while (it1.hasNext()) {
            MethodInfo method = (MethodInfo)it1.next();
            Method actualMethod = it2 != null ? (Method)it2.next() : null;
            int index = this.filter.accept(actualMethod);
            if (index >= this.callbackTypes.length) {
                throw new IllegalArgumentException("Callback filter returned an index that is too large: " + index);
            }
            originalModifiers.put(method, actualMethod != null ? actualMethod.getModifiers() : method.getModifiers());
            indexes.put(method, index);
            ArrayList<MethodInfo> group = (ArrayList<MethodInfo>)groups.get(generators[index]);
            if (group == null) {
                group = new ArrayList<MethodInfo>(methods.size());
                groups.put(generators[index], group);
            }
            group.add(method);
            if (!TypeUtils.isBridge(actualMethod.getModifiers())) continue;
            HashSet<Signature> bridges = (HashSet<Signature>)declToBridge.get(actualMethod.getDeclaringClass());
            if (bridges == null) {
                bridges = new HashSet<Signature>();
                declToBridge.put(actualMethod.getDeclaringClass(), bridges);
            }
            bridges.add(method.getSignature());
        }
        final Map bridgeToTarget = new BridgeMethodResolver(declToBridge, this.getClassLoader()).resolveAll();
        HashSet<CallbackGenerator> seenGen = new HashSet<CallbackGenerator>();
        CodeEmitter se = ce.getStaticHook();
        se.new_instance(THREAD_LOCAL);
        se.dup();
        se.invoke_constructor(THREAD_LOCAL, CSTRUCT_NULL);
        se.putfield(THREAD_CALLBACKS_FIELD);
        Object[] state = new Object[1];
        CallbackGenerator.Context context = new CallbackGenerator.Context(){

            @Override
            public ClassLoader getClassLoader() {
                return Enhancer.this.getClassLoader();
            }

            @Override
            public int getOriginalModifiers(MethodInfo method) {
                return (Integer)originalModifiers.get(method);
            }

            @Override
            public int getIndex(MethodInfo method) {
                return (Integer)indexes.get(method);
            }

            @Override
            public void emitCallback(CodeEmitter e, int index) {
                Enhancer.this.emitCurrentCallback(e, index);
            }

            @Override
            public Signature getImplSignature(MethodInfo method) {
                return Enhancer.this.rename(method.getSignature(), (Integer)positions.get(method));
            }

            @Override
            public void emitLoadArgsAndInvoke(CodeEmitter e, MethodInfo method) {
                Signature bridgeTarget = (Signature)bridgeToTarget.get(method.getSignature());
                if (bridgeTarget != null) {
                    for (int i = 0; i < bridgeTarget.getArgumentTypes().length; ++i) {
                        e.load_arg(i);
                        Type target = bridgeTarget.getArgumentTypes()[i];
                        if (target.equals(method.getSignature().getArgumentTypes()[i])) continue;
                        e.checkcast(target);
                    }
                    e.invoke_virtual_this(bridgeTarget);
                    Type retType = method.getSignature().getReturnType();
                    if (!retType.equals(bridgeTarget.getReturnType())) {
                        e.checkcast(retType);
                    }
                } else {
                    e.load_args();
                    e.super_invoke(method.getSignature());
                }
            }

            @Override
            public CodeEmitter beginMethod(ClassEmitter ce, MethodInfo method) {
                CodeEmitter e = EmitUtils.begin_method(ce, method);
                if (!Enhancer.this.interceptDuringConstruction && !TypeUtils.isAbstract(method.getModifiers())) {
                    Label constructed = e.make_label();
                    e.load_this();
                    e.getfield(Enhancer.CONSTRUCTED_FIELD);
                    e.if_jump(154, constructed);
                    e.load_this();
                    e.load_args();
                    e.super_invoke();
                    e.return_value();
                    e.mark(constructed);
                }
                return e;
            }
        };
        for (int i = 0; i < this.callbackTypes.length; ++i) {
            CallbackGenerator gen = generators[i];
            if (seenGen.contains(gen)) continue;
            seenGen.add(gen);
            List fmethods = (List)groups.get(gen);
            if (fmethods == null) continue;
            try {
                gen.generate(ce, context, fmethods);
                gen.generateStatic(se, context, fmethods);
                continue;
            } catch (RuntimeException x) {
                throw x;
            } catch (Exception x) {
                throw new CodeGenerationException(x);
            }
        }
        se.return_value();
        se.end_method();
    }

    private void emitSetThreadCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(9, SET_THREAD_CALLBACKS, null);
        e.getfield(THREAD_CALLBACKS_FIELD);
        e.load_arg(0);
        e.invoke_virtual(THREAD_LOCAL, THREAD_LOCAL_SET);
        e.return_value();
        e.end_method();
    }

    private void emitSetStaticCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(9, SET_STATIC_CALLBACKS, null);
        e.load_arg(0);
        e.putfield(STATIC_CALLBACKS_FIELD);
        e.return_value();
        e.end_method();
    }

    private void emitCurrentCallback(CodeEmitter e, int index) {
        e.load_this();
        e.getfield(Enhancer.getCallbackField(index));
        e.dup();
        Label end = e.make_label();
        e.ifnonnull(end);
        e.pop();
        e.load_this();
        e.invoke_static_this(BIND_CALLBACKS);
        e.load_this();
        e.getfield(Enhancer.getCallbackField(index));
        e.mark(end);
    }

    private void emitBindCallbacks(ClassEmitter ce) {
        CodeEmitter e = ce.begin_method(26, BIND_CALLBACKS, null);
        Local me = e.make_local();
        e.load_arg(0);
        e.checkcast_this();
        e.store_local(me);
        Label end = e.make_label();
        e.load_local(me);
        e.getfield(BOUND_FIELD);
        e.if_jump(154, end);
        e.load_local(me);
        e.push(1);
        e.putfield(BOUND_FIELD);
        e.getfield(THREAD_CALLBACKS_FIELD);
        e.invoke_virtual(THREAD_LOCAL, THREAD_LOCAL_GET);
        e.dup();
        Label found_callback = e.make_label();
        e.ifnonnull(found_callback);
        e.pop();
        e.getfield(STATIC_CALLBACKS_FIELD);
        e.dup();
        e.ifnonnull(found_callback);
        e.pop();
        e.goTo(end);
        e.mark(found_callback);
        e.checkcast(CALLBACK_ARRAY);
        e.load_local(me);
        e.swap();
        for (int i = this.callbackTypes.length - 1; i >= 0; --i) {
            if (i != 0) {
                e.dup2();
            }
            e.aaload(i);
            e.checkcast(this.callbackTypes[i]);
            e.putfield(Enhancer.getCallbackField(i));
        }
        e.mark(end);
        e.return_value();
        e.end_method();
    }

    private static String getCallbackField(int index) {
        return "CGLIB$CALLBACK_" + index;
    }

    static class EnhancerFactoryData {
        public final Class generatedClass;
        private final Method setThreadCallbacks;
        private final Class[] primaryConstructorArgTypes;
        private final Constructor primaryConstructor;

        public EnhancerFactoryData(Class generatedClass, Class[] primaryConstructorArgTypes, boolean classOnly) {
            this.generatedClass = generatedClass;
            try {
                this.setThreadCallbacks = Enhancer.getCallbacksSetter(generatedClass, Enhancer.SET_THREAD_CALLBACKS_NAME);
                if (classOnly) {
                    this.primaryConstructorArgTypes = null;
                    this.primaryConstructor = null;
                } else {
                    this.primaryConstructorArgTypes = primaryConstructorArgTypes;
                    this.primaryConstructor = ReflectUtils.getConstructor(generatedClass, primaryConstructorArgTypes);
                }
            } catch (NoSuchMethodException e) {
                throw new CodeGenerationException(e);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public Object newInstance(Class[] argumentTypes, Object[] arguments, Callback[] callbacks) {
            this.setThreadCallbacks(callbacks);
            try {
                if (this.primaryConstructorArgTypes == argumentTypes || Arrays.equals(this.primaryConstructorArgTypes, argumentTypes)) {
                    Object object = ReflectUtils.newInstance(this.primaryConstructor, arguments);
                    return object;
                }
                Object object = ReflectUtils.newInstance(this.generatedClass, argumentTypes, arguments);
                return object;
            } finally {
                this.setThreadCallbacks(null);
            }
        }

        private void setThreadCallbacks(Callback[] callbacks) {
            try {
                this.setThreadCallbacks.invoke(this.generatedClass, new Object[]{callbacks});
            } catch (IllegalAccessException e) {
                throw new CodeGenerationException(e);
            } catch (InvocationTargetException e) {
                throw new CodeGenerationException(e.getTargetException());
            }
        }
    }

    public static interface EnhancerKey {
        public Object newInstance(String var1, String[] var2, WeakCacheKey<CallbackFilter> var3, Type[] var4, boolean var5, boolean var6, Long var7);
    }
}

