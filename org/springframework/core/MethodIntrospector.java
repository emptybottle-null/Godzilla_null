/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public final class MethodIntrospector {
    private MethodIntrospector() {
    }

    public static <T> Map<Method, T> selectMethods(Class<?> targetType, MetadataLookup<T> metadataLookup) {
        LinkedHashMap methodMap = new LinkedHashMap();
        LinkedHashSet handlerTypes = new LinkedHashSet();
        Class specificHandlerType = null;
        if (!Proxy.isProxyClass(targetType)) {
            specificHandlerType = ClassUtils.getUserClass(targetType);
            handlerTypes.add(specificHandlerType);
        }
        handlerTypes.addAll(ClassUtils.getAllInterfacesForClassAsSet(targetType));
        for (Class clazz : handlerTypes) {
            Class targetClass = specificHandlerType != null ? specificHandlerType : clazz;
            ReflectionUtils.doWithMethods(clazz, method -> {
                Method bridgedMethod;
                Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
                Object result = metadataLookup.inspect(specificMethod);
                if (result != null && ((bridgedMethod = BridgeMethodResolver.findBridgedMethod(specificMethod)) == specificMethod || metadataLookup.inspect(bridgedMethod) == null)) {
                    methodMap.put(specificMethod, result);
                }
            }, ReflectionUtils.USER_DECLARED_METHODS);
        }
        return methodMap;
    }

    public static Set<Method> selectMethods(Class<?> targetType, ReflectionUtils.MethodFilter methodFilter) {
        return MethodIntrospector.selectMethods(targetType, (Method method) -> methodFilter.matches(method) ? Boolean.TRUE : null).keySet();
    }

    public static Method selectInvocableMethod(Method method, Class<?> targetType) {
        if (method.getDeclaringClass().isAssignableFrom(targetType)) {
            return method;
        }
        try {
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> ifc : targetType.getInterfaces()) {
                try {
                    return ifc.getMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException noSuchMethodException) {
                }
            }
            return targetType.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException(String.format("Need to invoke method '%s' declared on target class '%s', but not found in any interface(s) of the exposed proxy type. Either pull the method up to an interface or switch to CGLIB proxies by enforcing proxy-target-class mode in your configuration.", method.getName(), method.getDeclaringClass().getSimpleName()));
        }
    }

    @FunctionalInterface
    public static interface MetadataLookup<T> {
        @Nullable
        public T inspect(Method var1);
    }
}

