/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Element;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class Invokable<T, R>
extends Element
implements GenericDeclaration {
    <M extends AccessibleObject> Invokable(M member) {
        super(member);
    }

    public static Invokable<?, Object> from(Method method) {
        return new MethodInvokable(method);
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new ConstructorInvokable(constructor);
    }

    public abstract boolean isOverridable();

    public abstract boolean isVarArgs();

    @CanIgnoreReturnValue
    public final R invoke(@Nullable T receiver, Object ... args) throws InvocationTargetException, IllegalAccessException {
        return (R)this.invokeInternal(receiver, Preconditions.checkNotNull(args));
    }

    public final TypeToken<? extends R> getReturnType() {
        return TypeToken.of(this.getGenericReturnType());
    }

    public final ImmutableList<Parameter> getParameters() {
        Type[] parameterTypes = this.getGenericParameterTypes();
        Annotation[][] annotations = this.getParameterAnnotations();
        AnnotatedType[] annotatedTypes = this.getAnnotatedParameterTypes();
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < parameterTypes.length; ++i) {
            builder.add(new Parameter(this, i, TypeToken.of(parameterTypes[i]), annotations[i], annotatedTypes[i]));
        }
        return builder.build();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : this.getGenericExceptionTypes()) {
            TypeToken<?> exceptionType = TypeToken.of(type);
            builder.add(exceptionType);
        }
        return builder.build();
    }

    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> returnType) {
        return this.returning(TypeToken.of(returnType));
    }

    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> returnType) {
        if (!returnType.isSupertypeOf(this.getReturnType())) {
            throw new IllegalArgumentException("Invokable is known to return " + this.getReturnType() + ", not " + returnType);
        }
        Invokable specialized = this;
        return specialized;
    }

    public final Class<? super T> getDeclaringClass() {
        return super.getDeclaringClass();
    }

    public TypeToken<T> getOwnerType() {
        return TypeToken.of(this.getDeclaringClass());
    }

    abstract Object invokeInternal(@Nullable Object var1, Object[] var2) throws InvocationTargetException, IllegalAccessException;

    abstract Type[] getGenericParameterTypes();

    abstract AnnotatedType[] getAnnotatedParameterTypes();

    abstract Type[] getGenericExceptionTypes();

    abstract Annotation[][] getParameterAnnotations();

    abstract Type getGenericReturnType();

    public abstract AnnotatedType getAnnotatedReturnType();

    static class ConstructorInvokable<T>
    extends Invokable<T, T> {
        final Constructor<?> constructor;

        ConstructorInvokable(Constructor<?> constructor) {
            super(constructor);
            this.constructor = constructor;
        }

        @Override
        final Object invokeInternal(@Nullable Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
            try {
                return this.constructor.newInstance(args);
            } catch (InstantiationException e) {
                throw new RuntimeException(this.constructor + " failed.", e);
            }
        }

        @Override
        Type getGenericReturnType() {
            Class declaringClass = this.getDeclaringClass();
            Type[] typeParams = declaringClass.getTypeParameters();
            if (typeParams.length > 0) {
                return Types.newParameterizedType(declaringClass, typeParams);
            }
            return declaringClass;
        }

        @Override
        Type[] getGenericParameterTypes() {
            Class<?>[] rawParamTypes;
            Type[] types = this.constructor.getGenericParameterTypes();
            if (types.length > 0 && this.mayNeedHiddenThis() && types.length == (rawParamTypes = this.constructor.getParameterTypes()).length && rawParamTypes[0] == this.getDeclaringClass().getEnclosingClass()) {
                return Arrays.copyOfRange(types, 1, types.length);
            }
            return types;
        }

        @Override
        AnnotatedType[] getAnnotatedParameterTypes() {
            return this.constructor.getAnnotatedParameterTypes();
        }

        @Override
        public AnnotatedType getAnnotatedReturnType() {
            return this.constructor.getAnnotatedReturnType();
        }

        @Override
        Type[] getGenericExceptionTypes() {
            return this.constructor.getGenericExceptionTypes();
        }

        @Override
        final Annotation[][] getParameterAnnotations() {
            return this.constructor.getParameterAnnotations();
        }

        @Override
        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable<Class<T>>[] declaredByClass = this.getDeclaringClass().getTypeParameters();
            TypeVariable<Constructor<?>>[] declaredByConstructor = this.constructor.getTypeParameters();
            TypeVariable[] result = new TypeVariable[declaredByClass.length + declaredByConstructor.length];
            System.arraycopy(declaredByClass, 0, result, 0, declaredByClass.length);
            System.arraycopy(declaredByConstructor, 0, result, declaredByClass.length, declaredByConstructor.length);
            return result;
        }

        @Override
        public final boolean isOverridable() {
            return false;
        }

        @Override
        public final boolean isVarArgs() {
            return this.constructor.isVarArgs();
        }

        private boolean mayNeedHiddenThis() {
            Class<?> declaringClass = this.constructor.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                return !Modifier.isStatic(enclosingMethod.getModifiers());
            }
            return declaringClass.getEnclosingClass() != null && !Modifier.isStatic(declaringClass.getModifiers());
        }
    }

    static class MethodInvokable<T>
    extends Invokable<T, Object> {
        final Method method;

        MethodInvokable(Method method) {
            super(method);
            this.method = method;
        }

        @Override
        final Object invokeInternal(@Nullable Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
            return this.method.invoke(receiver, args);
        }

        @Override
        Type getGenericReturnType() {
            return this.method.getGenericReturnType();
        }

        @Override
        Type[] getGenericParameterTypes() {
            return this.method.getGenericParameterTypes();
        }

        @Override
        AnnotatedType[] getAnnotatedParameterTypes() {
            return this.method.getAnnotatedParameterTypes();
        }

        @Override
        public AnnotatedType getAnnotatedReturnType() {
            return this.method.getAnnotatedReturnType();
        }

        @Override
        Type[] getGenericExceptionTypes() {
            return this.method.getGenericExceptionTypes();
        }

        @Override
        final Annotation[][] getParameterAnnotations() {
            return this.method.getParameterAnnotations();
        }

        @Override
        public final TypeVariable<?>[] getTypeParameters() {
            return this.method.getTypeParameters();
        }

        @Override
        public final boolean isOverridable() {
            return !this.isFinal() && !this.isPrivate() && !this.isStatic() && !Modifier.isFinal(this.getDeclaringClass().getModifiers());
        }

        @Override
        public final boolean isVarArgs() {
            return this.method.isVarArgs();
        }
    }
}

