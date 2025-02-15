/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import org.springframework.core.MethodParameter;
import org.springframework.core.NativeDetector;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

final class SerializableTypeWrapper {
    private static final Class<?>[] SUPPORTED_SERIALIZABLE_TYPES = new Class[]{GenericArrayType.class, ParameterizedType.class, TypeVariable.class, WildcardType.class};
    static final ConcurrentReferenceHashMap<Type, Type> cache = new ConcurrentReferenceHashMap(256);

    private SerializableTypeWrapper() {
    }

    @Nullable
    public static Type forField(Field field) {
        return SerializableTypeWrapper.forTypeProvider(new FieldTypeProvider(field));
    }

    @Nullable
    public static Type forMethodParameter(MethodParameter methodParameter) {
        return SerializableTypeWrapper.forTypeProvider(new MethodParameterTypeProvider(methodParameter));
    }

    public static <T extends Type> T unwrap(T type) {
        Type unwrapped = null;
        if (type instanceof SerializableTypeProxy) {
            unwrapped = ((SerializableTypeProxy)((Object)type)).getTypeProvider().getType();
        }
        return (T)(unwrapped != null ? unwrapped : (Type)type);
    }

    @Nullable
    static Type forTypeProvider(TypeProvider provider) {
        Type providedType = provider.getType();
        if (providedType == null || providedType instanceof Serializable) {
            return providedType;
        }
        if (NativeDetector.inNativeImage() || !Serializable.class.isAssignableFrom(Class.class)) {
            return providedType;
        }
        Type cached = cache.get(providedType);
        if (cached != null) {
            return cached;
        }
        for (Class<?> type : SUPPORTED_SERIALIZABLE_TYPES) {
            if (!type.isInstance(providedType)) continue;
            ClassLoader classLoader = provider.getClass().getClassLoader();
            Class[] interfaces = new Class[]{type, SerializableTypeProxy.class, Serializable.class};
            TypeProxyInvocationHandler handler = new TypeProxyInvocationHandler(provider);
            cached = (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
            cache.put(providedType, cached);
            return cached;
        }
        throw new IllegalArgumentException("Unsupported Type class: " + providedType.getClass().getName());
    }

    static class MethodInvokeTypeProvider
    implements TypeProvider {
        private final TypeProvider provider;
        private final String methodName;
        private final Class<?> declaringClass;
        private final int index;
        private transient Method method;
        @Nullable
        private volatile transient Object result;

        public MethodInvokeTypeProvider(TypeProvider provider, Method method, int index) {
            this.provider = provider;
            this.methodName = method.getName();
            this.declaringClass = method.getDeclaringClass();
            this.index = index;
            this.method = method;
        }

        @Override
        @Nullable
        public Type getType() {
            Object result = this.result;
            if (result == null) {
                this.result = result = ReflectionUtils.invokeMethod(this.method, this.provider.getType());
            }
            return result instanceof Type[] ? ((Type[])result)[this.index] : (Type)result;
        }

        @Override
        @Nullable
        public Object getSource() {
            return null;
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            Method method = ReflectionUtils.findMethod(this.declaringClass, this.methodName);
            if (method == null) {
                throw new IllegalStateException("Cannot find method on deserialization: " + this.methodName);
            }
            if (method.getReturnType() != Type.class && method.getReturnType() != Type[].class) {
                throw new IllegalStateException("Invalid return type on deserialized method - needs to be Type or Type[]: " + method);
            }
            this.method = method;
        }
    }

    static class MethodParameterTypeProvider
    implements TypeProvider {
        @Nullable
        private final String methodName;
        private final Class<?>[] parameterTypes;
        private final Class<?> declaringClass;
        private final int parameterIndex;
        private transient MethodParameter methodParameter;

        public MethodParameterTypeProvider(MethodParameter methodParameter) {
            this.methodName = methodParameter.getMethod() != null ? methodParameter.getMethod().getName() : null;
            this.parameterTypes = methodParameter.getExecutable().getParameterTypes();
            this.declaringClass = methodParameter.getDeclaringClass();
            this.parameterIndex = methodParameter.getParameterIndex();
            this.methodParameter = methodParameter;
        }

        @Override
        public Type getType() {
            return this.methodParameter.getGenericParameterType();
        }

        @Override
        public Object getSource() {
            return this.methodParameter;
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            try {
                this.methodParameter = this.methodName != null ? new MethodParameter(this.declaringClass.getDeclaredMethod(this.methodName, this.parameterTypes), this.parameterIndex) : new MethodParameter(this.declaringClass.getDeclaredConstructor(this.parameterTypes), this.parameterIndex);
            } catch (Throwable ex) {
                throw new IllegalStateException("Could not find original class structure", ex);
            }
        }
    }

    static class FieldTypeProvider
    implements TypeProvider {
        private final String fieldName;
        private final Class<?> declaringClass;
        private transient Field field;

        public FieldTypeProvider(Field field) {
            this.fieldName = field.getName();
            this.declaringClass = field.getDeclaringClass();
            this.field = field;
        }

        @Override
        public Type getType() {
            return this.field.getGenericType();
        }

        @Override
        public Object getSource() {
            return this.field;
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            try {
                this.field = this.declaringClass.getDeclaredField(this.fieldName);
            } catch (Throwable ex) {
                throw new IllegalStateException("Could not find original class structure", ex);
            }
        }
    }

    private static class TypeProxyInvocationHandler
    implements InvocationHandler,
    Serializable {
        private final TypeProvider provider;

        public TypeProxyInvocationHandler(TypeProvider provider) {
            this.provider = provider;
        }

        @Override
        @Nullable
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            switch (method.getName()) {
                case "equals": {
                    Object other = args[0];
                    if (other instanceof Type) {
                        other = SerializableTypeWrapper.unwrap((Type)other);
                    }
                    return ObjectUtils.nullSafeEquals(this.provider.getType(), other);
                }
                case "hashCode": {
                    return ObjectUtils.nullSafeHashCode(this.provider.getType());
                }
                case "getTypeProvider": {
                    return this.provider;
                }
            }
            if (Type.class == method.getReturnType() && ObjectUtils.isEmpty(args)) {
                return SerializableTypeWrapper.forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, -1));
            }
            if (Type[].class == method.getReturnType() && ObjectUtils.isEmpty(args)) {
                Type[] result = new Type[((Type[])method.invoke(this.provider.getType(), new Object[0])).length];
                for (int i = 0; i < result.length; ++i) {
                    result[i] = SerializableTypeWrapper.forTypeProvider(new MethodInvokeTypeProvider(this.provider, method, i));
                }
                return result;
            }
            try {
                return method.invoke(this.provider.getType(), args);
            } catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
    }

    static interface TypeProvider
    extends Serializable {
        @Nullable
        public Type getType();

        @Nullable
        default public Object getSource() {
            return null;
        }
    }

    static interface SerializableTypeProxy {
        public TypeProvider getTypeProvider();
    }
}

