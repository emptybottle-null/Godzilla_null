/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.MethodExecutor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.support.ReflectionHelper;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class ReflectiveMethodExecutor
implements MethodExecutor {
    private final Method originalMethod;
    private final Method methodToInvoke;
    @Nullable
    private final Integer varargsPosition;
    private boolean computedPublicDeclaringClass = false;
    @Nullable
    private Class<?> publicDeclaringClass;
    private boolean argumentConversionOccurred = false;

    public ReflectiveMethodExecutor(Method method) {
        this.originalMethod = method;
        this.methodToInvoke = ClassUtils.getInterfaceMethodIfPossible(method);
        this.varargsPosition = method.isVarArgs() ? Integer.valueOf(method.getParameterCount() - 1) : null;
    }

    public final Method getMethod() {
        return this.originalMethod;
    }

    @Nullable
    public Class<?> getPublicDeclaringClass() {
        if (!this.computedPublicDeclaringClass) {
            this.publicDeclaringClass = this.discoverPublicDeclaringClass(this.originalMethod, this.originalMethod.getDeclaringClass());
            this.computedPublicDeclaringClass = true;
        }
        return this.publicDeclaringClass;
    }

    @Nullable
    private Class<?> discoverPublicDeclaringClass(Method method, Class<?> clazz) {
        if (Modifier.isPublic(clazz.getModifiers())) {
            try {
                clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
                return clazz;
            } catch (NoSuchMethodException noSuchMethodException) {
                // empty catch block
            }
        }
        if (clazz.getSuperclass() != null) {
            return this.discoverPublicDeclaringClass(method, clazz.getSuperclass());
        }
        return null;
    }

    public boolean didArgumentConversionOccur() {
        return this.argumentConversionOccurred;
    }

    @Override
    public TypedValue execute(EvaluationContext context, Object target, Object ... arguments) throws AccessException {
        try {
            this.argumentConversionOccurred = ReflectionHelper.convertArguments(context.getTypeConverter(), arguments, this.originalMethod, this.varargsPosition);
            if (this.originalMethod.isVarArgs()) {
                arguments = ReflectionHelper.setupArgumentsForVarargsInvocation(this.originalMethod.getParameterTypes(), arguments);
            }
            ReflectionUtils.makeAccessible(this.methodToInvoke);
            Object value = this.methodToInvoke.invoke(target, arguments);
            return new TypedValue(value, new TypeDescriptor(new MethodParameter(this.originalMethod, -1)).narrow(value));
        } catch (Exception ex) {
            throw new AccessException("Problem invoking method: " + this.methodToInvoke, ex);
        }
    }
}

