/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

public final class BridgeMethodResolver {
    private static final Map<Method, Method> cache = new ConcurrentReferenceHashMap<Method, Method>();

    private BridgeMethodResolver() {
    }

    public static Method findBridgedMethod(Method bridgeMethod) {
        if (!bridgeMethod.isBridge()) {
            return bridgeMethod;
        }
        Method bridgedMethod = cache.get(bridgeMethod);
        if (bridgedMethod == null) {
            ArrayList<Method> candidateMethods = new ArrayList<Method>();
            ReflectionUtils.MethodFilter filter = candidateMethod -> BridgeMethodResolver.isBridgedCandidateFor(candidateMethod, bridgeMethod);
            ReflectionUtils.doWithMethods(bridgeMethod.getDeclaringClass(), candidateMethods::add, filter);
            if (!candidateMethods.isEmpty()) {
                Method method = bridgedMethod = candidateMethods.size() == 1 ? (Method)candidateMethods.get(0) : BridgeMethodResolver.searchCandidates(candidateMethods, bridgeMethod);
            }
            if (bridgedMethod == null) {
                bridgedMethod = bridgeMethod;
            }
            cache.put(bridgeMethod, bridgedMethod);
        }
        return bridgedMethod;
    }

    private static boolean isBridgedCandidateFor(Method candidateMethod, Method bridgeMethod) {
        return !candidateMethod.isBridge() && !candidateMethod.equals(bridgeMethod) && candidateMethod.getName().equals(bridgeMethod.getName()) && candidateMethod.getParameterCount() == bridgeMethod.getParameterCount();
    }

    @Nullable
    private static Method searchCandidates(List<Method> candidateMethods, Method bridgeMethod) {
        if (candidateMethods.isEmpty()) {
            return null;
        }
        Method previousMethod = null;
        boolean sameSig = true;
        for (Method candidateMethod : candidateMethods) {
            if (BridgeMethodResolver.isBridgeMethodFor(bridgeMethod, candidateMethod, bridgeMethod.getDeclaringClass())) {
                return candidateMethod;
            }
            if (previousMethod != null) {
                sameSig = sameSig && Arrays.equals(candidateMethod.getGenericParameterTypes(), previousMethod.getGenericParameterTypes());
            }
            previousMethod = candidateMethod;
        }
        return sameSig ? candidateMethods.get(0) : null;
    }

    static boolean isBridgeMethodFor(Method bridgeMethod, Method candidateMethod, Class<?> declaringClass) {
        if (BridgeMethodResolver.isResolvedTypeMatch(candidateMethod, bridgeMethod, declaringClass)) {
            return true;
        }
        Method method = BridgeMethodResolver.findGenericDeclaration(bridgeMethod);
        return method != null && BridgeMethodResolver.isResolvedTypeMatch(method, candidateMethod, declaringClass);
    }

    private static boolean isResolvedTypeMatch(Method genericMethod, Method candidateMethod, Class<?> declaringClass) {
        Type[] genericParameters = genericMethod.getGenericParameterTypes();
        if (genericParameters.length != candidateMethod.getParameterCount()) {
            return false;
        }
        Class<?>[] candidateParameters = candidateMethod.getParameterTypes();
        for (int i = 0; i < candidateParameters.length; ++i) {
            ResolvableType genericParameter = ResolvableType.forMethodParameter(genericMethod, i, declaringClass);
            Class<?> candidateParameter = candidateParameters[i];
            if (candidateParameter.isArray() && !candidateParameter.getComponentType().equals(genericParameter.getComponentType().toClass())) {
                return false;
            }
            if (ClassUtils.resolvePrimitiveIfNecessary(candidateParameter).equals(ClassUtils.resolvePrimitiveIfNecessary(genericParameter.toClass()))) continue;
            return false;
        }
        return true;
    }

    @Nullable
    private static Method findGenericDeclaration(Method bridgeMethod) {
        for (Class<?> superclass = bridgeMethod.getDeclaringClass().getSuperclass(); superclass != null && Object.class != superclass; superclass = superclass.getSuperclass()) {
            Method method = BridgeMethodResolver.searchForMatch(superclass, bridgeMethod);
            if (method == null || method.isBridge()) continue;
            return method;
        }
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(bridgeMethod.getDeclaringClass());
        return BridgeMethodResolver.searchInterfaces(interfaces, bridgeMethod);
    }

    @Nullable
    private static Method searchInterfaces(Class<?>[] interfaces, Method bridgeMethod) {
        for (Class<?> ifc : interfaces) {
            Method method = BridgeMethodResolver.searchForMatch(ifc, bridgeMethod);
            if (method != null && !method.isBridge()) {
                return method;
            }
            method = BridgeMethodResolver.searchInterfaces(ifc.getInterfaces(), bridgeMethod);
            if (method == null) continue;
            return method;
        }
        return null;
    }

    @Nullable
    private static Method searchForMatch(Class<?> type, Method bridgeMethod) {
        try {
            return type.getDeclaredMethod(bridgeMethod.getName(), bridgeMethod.getParameterTypes());
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    public static boolean isVisibilityBridgeMethodPair(Method bridgeMethod, Method bridgedMethod) {
        if (bridgeMethod == bridgedMethod) {
            return true;
        }
        return bridgeMethod.getReturnType().equals(bridgedMethod.getReturnType()) && bridgeMethod.getParameterCount() == bridgedMethod.getParameterCount() && Arrays.equals(bridgeMethod.getParameterTypes(), bridgedMethod.getParameterTypes());
    }
}

