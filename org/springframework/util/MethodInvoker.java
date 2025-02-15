/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class MethodInvoker {
    private static final Object[] EMPTY_ARGUMENTS = new Object[0];
    @Nullable
    protected Class<?> targetClass;
    @Nullable
    private Object targetObject;
    @Nullable
    private String targetMethod;
    @Nullable
    private String staticMethod;
    @Nullable
    private Object[] arguments;
    @Nullable
    private Method methodObject;

    public void setTargetClass(@Nullable Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Nullable
    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public void setTargetObject(@Nullable Object targetObject) {
        this.targetObject = targetObject;
        if (targetObject != null) {
            this.targetClass = targetObject.getClass();
        }
    }

    @Nullable
    public Object getTargetObject() {
        return this.targetObject;
    }

    public void setTargetMethod(@Nullable String targetMethod) {
        this.targetMethod = targetMethod;
    }

    @Nullable
    public String getTargetMethod() {
        return this.targetMethod;
    }

    public void setStaticMethod(String staticMethod) {
        this.staticMethod = staticMethod;
    }

    public void setArguments(Object ... arguments) {
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return this.arguments != null ? this.arguments : EMPTY_ARGUMENTS;
    }

    public void prepare() throws ClassNotFoundException, NoSuchMethodException {
        block5: {
            if (this.staticMethod != null) {
                int lastDotIndex = this.staticMethod.lastIndexOf(46);
                if (lastDotIndex == -1 || lastDotIndex == this.staticMethod.length()) {
                    throw new IllegalArgumentException("staticMethod must be a fully qualified class plus method name: e.g. 'example.MyExampleClass.myExampleMethod'");
                }
                String className = this.staticMethod.substring(0, lastDotIndex);
                String methodName = this.staticMethod.substring(lastDotIndex + 1);
                this.targetClass = this.resolveClassName(className);
                this.targetMethod = methodName;
            }
            Class<?> targetClass = this.getTargetClass();
            String targetMethod = this.getTargetMethod();
            Assert.notNull(targetClass, "Either 'targetClass' or 'targetObject' is required");
            Assert.notNull((Object)targetMethod, "Property 'targetMethod' is required");
            Object[] arguments = this.getArguments();
            Class[] argTypes = new Class[arguments.length];
            for (int i = 0; i < arguments.length; ++i) {
                argTypes[i] = arguments[i] != null ? arguments[i].getClass() : Object.class;
            }
            try {
                this.methodObject = targetClass.getMethod(targetMethod, argTypes);
            } catch (NoSuchMethodException ex) {
                this.methodObject = this.findMatchingMethod();
                if (this.methodObject != null) break block5;
                throw ex;
            }
        }
    }

    protected Class<?> resolveClassName(String className) throws ClassNotFoundException {
        return ClassUtils.forName(className, ClassUtils.getDefaultClassLoader());
    }

    @Nullable
    protected Method findMatchingMethod() {
        String targetMethod = this.getTargetMethod();
        Object[] arguments = this.getArguments();
        int argCount = arguments.length;
        Class<?> targetClass = this.getTargetClass();
        Assert.state(targetClass != null, "No target class set");
        Method[] candidates = ReflectionUtils.getAllDeclaredMethods(targetClass);
        int minTypeDiffWeight = Integer.MAX_VALUE;
        Method matchingMethod = null;
        for (Method candidate : candidates) {
            Class<?>[] paramTypes;
            int typeDiffWeight;
            if (!candidate.getName().equals(targetMethod) || candidate.getParameterCount() != argCount || (typeDiffWeight = MethodInvoker.getTypeDifferenceWeight(paramTypes = candidate.getParameterTypes(), arguments)) >= minTypeDiffWeight) continue;
            minTypeDiffWeight = typeDiffWeight;
            matchingMethod = candidate;
        }
        return matchingMethod;
    }

    public Method getPreparedMethod() throws IllegalStateException {
        if (this.methodObject == null) {
            throw new IllegalStateException("prepare() must be called prior to invoke() on MethodInvoker");
        }
        return this.methodObject;
    }

    public boolean isPrepared() {
        return this.methodObject != null;
    }

    @Nullable
    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        Object targetObject = this.getTargetObject();
        Method preparedMethod = this.getPreparedMethod();
        if (targetObject == null && !Modifier.isStatic(preparedMethod.getModifiers())) {
            throw new IllegalArgumentException("Target method must not be non-static without a target");
        }
        ReflectionUtils.makeAccessible(preparedMethod);
        return preparedMethod.invoke(targetObject, this.getArguments());
    }

    public static int getTypeDifferenceWeight(Class<?>[] paramTypes, Object[] args) {
        int result = 0;
        for (int i = 0; i < paramTypes.length; ++i) {
            if (!ClassUtils.isAssignableValue(paramTypes[i], args[i])) {
                return Integer.MAX_VALUE;
            }
            if (args[i] == null) continue;
            Class<?> paramType = paramTypes[i];
            Class<?> superClass = args[i].getClass().getSuperclass();
            while (superClass != null) {
                if (paramType.equals(superClass)) {
                    result += 2;
                    superClass = null;
                    continue;
                }
                if (ClassUtils.isAssignable(paramType, superClass)) {
                    result += 2;
                    superClass = superClass.getSuperclass();
                    continue;
                }
                superClass = null;
            }
            if (!paramType.isInterface()) continue;
            ++result;
        }
        return result;
    }
}

