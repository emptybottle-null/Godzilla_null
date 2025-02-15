/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;

public final class GenericTypeResolver {
    private static final Map<Class<?>, Map<TypeVariable, Type>> typeVariableCache = new ConcurrentReferenceHashMap();

    private GenericTypeResolver() {
    }

    @Deprecated
    public static Class<?> resolveParameterType(MethodParameter methodParameter, Class<?> implementationClass) {
        Assert.notNull((Object)methodParameter, "MethodParameter must not be null");
        Assert.notNull(implementationClass, "Class must not be null");
        methodParameter.setContainingClass(implementationClass);
        return methodParameter.getParameterType();
    }

    public static Class<?> resolveReturnType(Method method, Class<?> clazz) {
        Assert.notNull((Object)method, "Method must not be null");
        Assert.notNull(clazz, "Class must not be null");
        return ResolvableType.forMethodReturnType(method, clazz).resolve(method.getReturnType());
    }

    @Nullable
    public static Class<?> resolveReturnTypeArgument(Method method, Class<?> genericIfc) {
        Assert.notNull((Object)method, "Method must not be null");
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(method).as(genericIfc);
        if (!resolvableType.hasGenerics() || resolvableType.getType() instanceof WildcardType) {
            return null;
        }
        return GenericTypeResolver.getSingleGeneric(resolvableType);
    }

    @Nullable
    public static Class<?> resolveTypeArgument(Class<?> clazz, Class<?> genericIfc) {
        ResolvableType resolvableType = ResolvableType.forClass(clazz).as(genericIfc);
        if (!resolvableType.hasGenerics()) {
            return null;
        }
        return GenericTypeResolver.getSingleGeneric(resolvableType);
    }

    @Nullable
    private static Class<?> getSingleGeneric(ResolvableType resolvableType) {
        Assert.isTrue(resolvableType.getGenerics().length == 1, () -> "Expected 1 type argument on generic interface [" + resolvableType + "] but found " + resolvableType.getGenerics().length);
        return resolvableType.getGeneric(new int[0]).resolve();
    }

    @Nullable
    public static Class<?>[] resolveTypeArguments(Class<?> clazz, Class<?> genericIfc) {
        ResolvableType type = ResolvableType.forClass(clazz).as(genericIfc);
        if (!type.hasGenerics() || type.isEntirelyUnresolvable()) {
            return null;
        }
        return type.resolveGenerics(Object.class);
    }

    public static Type resolveType(Type genericType, @Nullable Class<?> contextClass) {
        if (contextClass != null) {
            ResolvableType resolvedType;
            if (genericType instanceof TypeVariable) {
                Class<?> resolved;
                ResolvableType resolvedTypeVariable = GenericTypeResolver.resolveVariable((TypeVariable)genericType, ResolvableType.forClass(contextClass));
                if (resolvedTypeVariable != ResolvableType.NONE && (resolved = resolvedTypeVariable.resolve()) != null) {
                    return resolved;
                }
            } else if (genericType instanceof ParameterizedType && (resolvedType = ResolvableType.forType(genericType)).hasUnresolvableGenerics()) {
                ParameterizedType parameterizedType = (ParameterizedType)genericType;
                Class[] generics = new Class[parameterizedType.getActualTypeArguments().length];
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                ResolvableType contextType = ResolvableType.forClass(contextClass);
                for (int i = 0; i < typeArguments.length; ++i) {
                    Type typeArgument = typeArguments[i];
                    if (typeArgument instanceof TypeVariable) {
                        ResolvableType resolvedTypeArgument = GenericTypeResolver.resolveVariable((TypeVariable)typeArgument, contextType);
                        if (resolvedTypeArgument != ResolvableType.NONE) {
                            generics[i] = resolvedTypeArgument.resolve();
                            continue;
                        }
                        generics[i] = ResolvableType.forType(typeArgument).resolve();
                        continue;
                    }
                    generics[i] = ResolvableType.forType(typeArgument).resolve();
                }
                Class<?> rawClass = resolvedType.getRawClass();
                if (rawClass != null) {
                    return ResolvableType.forClassWithGenerics(rawClass, generics).getType();
                }
            }
        }
        return genericType;
    }

    private static ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType contextType) {
        ResolvableType resolvedType;
        if (contextType.hasGenerics() && (resolvedType = ResolvableType.forType(typeVariable, contextType)).resolve() != null) {
            return resolvedType;
        }
        ResolvableType superType = contextType.getSuperType();
        if (superType != ResolvableType.NONE && (resolvedType = GenericTypeResolver.resolveVariable(typeVariable, superType)).resolve() != null) {
            return resolvedType;
        }
        for (ResolvableType ifc : contextType.getInterfaces()) {
            resolvedType = GenericTypeResolver.resolveVariable(typeVariable, ifc);
            if (resolvedType.resolve() == null) continue;
            return resolvedType;
        }
        return ResolvableType.NONE;
    }

    public static Class<?> resolveType(Type genericType, Map<TypeVariable, Type> map) {
        return ResolvableType.forType(genericType, new TypeVariableMapVariableResolver(map)).toClass();
    }

    public static Map<TypeVariable, Type> getTypeVariableMap(Class<?> clazz) {
        Map<TypeVariable, Type> typeVariableMap = typeVariableCache.get(clazz);
        if (typeVariableMap == null) {
            typeVariableMap = new HashMap<TypeVariable, Type>();
            GenericTypeResolver.buildTypeVariableMap(ResolvableType.forClass(clazz), typeVariableMap);
            typeVariableCache.put(clazz, Collections.unmodifiableMap(typeVariableMap));
        }
        return typeVariableMap;
    }

    private static void buildTypeVariableMap(ResolvableType type, Map<TypeVariable, Type> typeVariableMap) {
        if (type != ResolvableType.NONE) {
            Class<?> resolved = type.resolve();
            if (resolved != null && type.getType() instanceof ParameterizedType) {
                TypeVariable<Class<?>>[] variables = resolved.getTypeParameters();
                for (int i = 0; i < variables.length; ++i) {
                    ResolvableType generic = type.getGeneric(i);
                    while (generic.getType() instanceof TypeVariable) {
                        generic = generic.resolveType();
                    }
                    if (generic == ResolvableType.NONE) continue;
                    typeVariableMap.put(variables[i], generic.getType());
                }
            }
            GenericTypeResolver.buildTypeVariableMap(type.getSuperType(), typeVariableMap);
            for (ResolvableType interfaceType : type.getInterfaces()) {
                GenericTypeResolver.buildTypeVariableMap(interfaceType, typeVariableMap);
            }
            if (resolved != null && resolved.isMemberClass()) {
                GenericTypeResolver.buildTypeVariableMap(ResolvableType.forClass(resolved.getEnclosingClass()), typeVariableMap);
            }
        }
    }

    private static class TypeVariableMapVariableResolver
    implements ResolvableType.VariableResolver {
        private final Map<TypeVariable, Type> typeVariableMap;

        public TypeVariableMapVariableResolver(Map<TypeVariable, Type> typeVariableMap) {
            this.typeVariableMap = typeVariableMap;
        }

        @Override
        @Nullable
        public ResolvableType resolveVariable(TypeVariable<?> variable) {
            Type type = this.typeVariableMap.get(variable);
            return type != null ? ResolvableType.forType(type) : null;
        }

        @Override
        public Object getSource() {
            return this.typeVariableMap;
        }
    }
}

