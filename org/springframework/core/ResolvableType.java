/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.StringJoiner;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableTypeProvider;
import org.springframework.core.SerializableTypeWrapper;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ResolvableType
implements Serializable {
    public static final ResolvableType NONE = new ResolvableType(EmptyType.INSTANCE, null, null, 0);
    private static final ResolvableType[] EMPTY_TYPES_ARRAY = new ResolvableType[0];
    private static final ConcurrentReferenceHashMap<ResolvableType, ResolvableType> cache = new ConcurrentReferenceHashMap(256);
    private final Type type;
    @Nullable
    private final SerializableTypeWrapper.TypeProvider typeProvider;
    @Nullable
    private final VariableResolver variableResolver;
    @Nullable
    private final ResolvableType componentType;
    @Nullable
    private final Integer hash;
    @Nullable
    private Class<?> resolved;
    @Nullable
    private volatile ResolvableType superType;
    @Nullable
    private volatile ResolvableType[] interfaces;
    @Nullable
    private volatile ResolvableType[] generics;

    private ResolvableType(Type type, @Nullable SerializableTypeWrapper.TypeProvider typeProvider, @Nullable VariableResolver variableResolver) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = null;
        this.hash = this.calculateHashCode();
        this.resolved = null;
    }

    private ResolvableType(Type type, @Nullable SerializableTypeWrapper.TypeProvider typeProvider, @Nullable VariableResolver variableResolver, @Nullable Integer hash) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = null;
        this.hash = hash;
        this.resolved = this.resolveClass();
    }

    private ResolvableType(Type type, @Nullable SerializableTypeWrapper.TypeProvider typeProvider, @Nullable VariableResolver variableResolver, @Nullable ResolvableType componentType) {
        this.type = type;
        this.typeProvider = typeProvider;
        this.variableResolver = variableResolver;
        this.componentType = componentType;
        this.hash = null;
        this.resolved = this.resolveClass();
    }

    private ResolvableType(@Nullable Class<?> clazz) {
        this.type = this.resolved = clazz != null ? clazz : Object.class;
        this.typeProvider = null;
        this.variableResolver = null;
        this.componentType = null;
        this.hash = null;
    }

    public Type getType() {
        return SerializableTypeWrapper.unwrap(this.type);
    }

    @Nullable
    public Class<?> getRawClass() {
        if (this.type == this.resolved) {
            return this.resolved;
        }
        Type rawType = this.type;
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType)rawType).getRawType();
        }
        return rawType instanceof Class ? (Class)rawType : null;
    }

    public Object getSource() {
        Object source = this.typeProvider != null ? this.typeProvider.getSource() : null;
        return source != null ? source : this.type;
    }

    public Class<?> toClass() {
        return this.resolve(Object.class);
    }

    public boolean isInstance(@Nullable Object obj) {
        return obj != null && this.isAssignableFrom(obj.getClass());
    }

    public boolean isAssignableFrom(Class<?> other) {
        return this.isAssignableFrom(ResolvableType.forClass(other), null);
    }

    public boolean isAssignableFrom(ResolvableType other) {
        return this.isAssignableFrom(other, null);
    }

    private boolean isAssignableFrom(ResolvableType other, @Nullable Map<Type, Type> matchedBefore) {
        Assert.notNull((Object)other, "ResolvableType must not be null");
        if (this == NONE || other == NONE) {
            return false;
        }
        if (this.isArray()) {
            return other.isArray() && this.getComponentType().isAssignableFrom(other.getComponentType());
        }
        if (matchedBefore != null && matchedBefore.get(this.type) == other.type) {
            return true;
        }
        WildcardBounds ourBounds = WildcardBounds.get(this);
        WildcardBounds typeBounds = WildcardBounds.get(other);
        if (typeBounds != null) {
            return ourBounds != null && ourBounds.isSameKind(typeBounds) && ourBounds.isAssignableFrom(typeBounds.getBounds());
        }
        if (ourBounds != null) {
            return ourBounds.isAssignableFrom(other);
        }
        boolean exactMatch = matchedBefore != null;
        boolean checkGenerics = true;
        Class<?> ourResolved = null;
        if (this.type instanceof TypeVariable) {
            ResolvableType resolved;
            TypeVariable variable = (TypeVariable)this.type;
            if (this.variableResolver != null && (resolved = this.variableResolver.resolveVariable(variable)) != null) {
                ourResolved = resolved.resolve();
            }
            if (ourResolved == null && other.variableResolver != null && (resolved = other.variableResolver.resolveVariable(variable)) != null) {
                ourResolved = resolved.resolve();
                checkGenerics = false;
            }
            if (ourResolved == null) {
                exactMatch = false;
            }
        }
        if (ourResolved == null) {
            ourResolved = this.resolve(Object.class);
        }
        Class<?> otherResolved = other.toClass();
        if (exactMatch ? !ourResolved.equals(otherResolved) : !ClassUtils.isAssignable(ourResolved, otherResolved)) {
            return false;
        }
        if (checkGenerics) {
            ResolvableType[] typeGenerics;
            ResolvableType[] ourGenerics = this.getGenerics();
            if (ourGenerics.length != (typeGenerics = other.as(ourResolved).getGenerics()).length) {
                return false;
            }
            if (matchedBefore == null) {
                matchedBefore = new IdentityHashMap<Type, Type>(1);
            }
            matchedBefore.put(this.type, other.type);
            for (int i = 0; i < ourGenerics.length; ++i) {
                if (ourGenerics[i].isAssignableFrom(typeGenerics[i], matchedBefore)) continue;
                return false;
            }
        }
        return true;
    }

    public boolean isArray() {
        if (this == NONE) {
            return false;
        }
        return this.type instanceof Class && ((Class)this.type).isArray() || this.type instanceof GenericArrayType || this.resolveType().isArray();
    }

    public ResolvableType getComponentType() {
        if (this == NONE) {
            return NONE;
        }
        if (this.componentType != null) {
            return this.componentType;
        }
        if (this.type instanceof Class) {
            Class<?> componentType = ((Class)this.type).getComponentType();
            return ResolvableType.forType(componentType, this.variableResolver);
        }
        if (this.type instanceof GenericArrayType) {
            return ResolvableType.forType(((GenericArrayType)this.type).getGenericComponentType(), this.variableResolver);
        }
        return this.resolveType().getComponentType();
    }

    public ResolvableType asCollection() {
        return this.as(Collection.class);
    }

    public ResolvableType asMap() {
        return this.as(Map.class);
    }

    public ResolvableType as(Class<?> type) {
        if (this == NONE) {
            return NONE;
        }
        Class<?> resolved = this.resolve();
        if (resolved == null || resolved == type) {
            return this;
        }
        for (ResolvableType interfaceType : this.getInterfaces()) {
            ResolvableType interfaceAsType = interfaceType.as(type);
            if (interfaceAsType == NONE) continue;
            return interfaceAsType;
        }
        return this.getSuperType().as(type);
    }

    public ResolvableType getSuperType() {
        Class<?> resolved = this.resolve();
        if (resolved == null) {
            return NONE;
        }
        try {
            Type superclass = resolved.getGenericSuperclass();
            if (superclass == null) {
                return NONE;
            }
            ResolvableType superType = this.superType;
            if (superType == null) {
                this.superType = superType = ResolvableType.forType(superclass, this);
            }
            return superType;
        } catch (TypeNotPresentException ex) {
            return NONE;
        }
    }

    public ResolvableType[] getInterfaces() {
        Class<?> resolved = this.resolve();
        if (resolved == null) {
            return EMPTY_TYPES_ARRAY;
        }
        ResolvableType[] interfaces = this.interfaces;
        if (interfaces == null) {
            Type[] genericIfcs = resolved.getGenericInterfaces();
            interfaces = new ResolvableType[genericIfcs.length];
            for (int i = 0; i < genericIfcs.length; ++i) {
                interfaces[i] = ResolvableType.forType(genericIfcs[i], this);
            }
            this.interfaces = interfaces;
        }
        return interfaces;
    }

    public boolean hasGenerics() {
        return this.getGenerics().length > 0;
    }

    boolean isEntirelyUnresolvable() {
        ResolvableType[] generics;
        if (this == NONE) {
            return false;
        }
        for (ResolvableType generic : generics = this.getGenerics()) {
            if (generic.isUnresolvableTypeVariable() || generic.isWildcardWithoutBounds()) continue;
            return false;
        }
        return true;
    }

    public boolean hasUnresolvableGenerics() {
        ResolvableType[] generics;
        if (this == NONE) {
            return false;
        }
        for (ResolvableType generic : generics = this.getGenerics()) {
            if (!generic.isUnresolvableTypeVariable() && !generic.isWildcardWithoutBounds()) continue;
            return true;
        }
        Class<?> resolved = this.resolve();
        if (resolved != null) {
            try {
                for (Type genericInterface : resolved.getGenericInterfaces()) {
                    if (!(genericInterface instanceof Class) || !ResolvableType.forClass((Class)genericInterface).hasGenerics()) continue;
                    return true;
                }
            } catch (TypeNotPresentException typeNotPresentException) {
                // empty catch block
            }
            return this.getSuperType().hasUnresolvableGenerics();
        }
        return false;
    }

    private boolean isUnresolvableTypeVariable() {
        if (this.type instanceof TypeVariable) {
            if (this.variableResolver == null) {
                return true;
            }
            TypeVariable variable = (TypeVariable)this.type;
            ResolvableType resolved = this.variableResolver.resolveVariable(variable);
            if (resolved == null || resolved.isUnresolvableTypeVariable()) {
                return true;
            }
        }
        return false;
    }

    private boolean isWildcardWithoutBounds() {
        Type[] upperBounds;
        WildcardType wt;
        return this.type instanceof WildcardType && (wt = (WildcardType)this.type).getLowerBounds().length == 0 && ((upperBounds = wt.getUpperBounds()).length == 0 || upperBounds.length == 1 && Object.class == upperBounds[0]);
    }

    public ResolvableType getNested(int nestingLevel) {
        return this.getNested(nestingLevel, null);
    }

    public ResolvableType getNested(int nestingLevel, @Nullable Map<Integer, Integer> typeIndexesPerLevel) {
        ResolvableType result = this;
        for (int i = 2; i <= nestingLevel; ++i) {
            if (result.isArray()) {
                result = result.getComponentType();
                continue;
            }
            while (result != NONE && !result.hasGenerics()) {
                result = result.getSuperType();
            }
            Integer index = typeIndexesPerLevel != null ? typeIndexesPerLevel.get(i) : null;
            index = index == null ? result.getGenerics().length - 1 : index;
            result = result.getGeneric(index);
        }
        return result;
    }

    public ResolvableType getGeneric(@Nullable int ... indexes) {
        ResolvableType[] generics = this.getGenerics();
        if (indexes == null || indexes.length == 0) {
            return generics.length == 0 ? NONE : generics[0];
        }
        ResolvableType generic = this;
        for (int index : indexes) {
            generics = generic.getGenerics();
            if (index < 0 || index >= generics.length) {
                return NONE;
            }
            generic = generics[index];
        }
        return generic;
    }

    public ResolvableType[] getGenerics() {
        if (this == NONE) {
            return EMPTY_TYPES_ARRAY;
        }
        ResolvableType[] generics = this.generics;
        if (generics == null) {
            if (this.type instanceof Class) {
                TypeVariable<Class<T>>[] typeParams = ((Class)this.type).getTypeParameters();
                generics = new ResolvableType[typeParams.length];
                for (int i = 0; i < generics.length; ++i) {
                    generics[i] = ResolvableType.forType(typeParams[i], this);
                }
            } else if (this.type instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType)this.type).getActualTypeArguments();
                generics = new ResolvableType[actualTypeArguments.length];
                for (int i = 0; i < actualTypeArguments.length; ++i) {
                    generics[i] = ResolvableType.forType(actualTypeArguments[i], this.variableResolver);
                }
            } else {
                generics = this.resolveType().getGenerics();
            }
            this.generics = generics;
        }
        return generics;
    }

    public Class<?>[] resolveGenerics() {
        ResolvableType[] generics = this.getGenerics();
        Class[] resolvedGenerics = new Class[generics.length];
        for (int i = 0; i < generics.length; ++i) {
            resolvedGenerics[i] = generics[i].resolve();
        }
        return resolvedGenerics;
    }

    public Class<?>[] resolveGenerics(Class<?> fallback) {
        ResolvableType[] generics = this.getGenerics();
        Class[] resolvedGenerics = new Class[generics.length];
        for (int i = 0; i < generics.length; ++i) {
            resolvedGenerics[i] = generics[i].resolve(fallback);
        }
        return resolvedGenerics;
    }

    @Nullable
    public Class<?> resolveGeneric(int ... indexes) {
        return this.getGeneric(indexes).resolve();
    }

    @Nullable
    public Class<?> resolve() {
        return this.resolved;
    }

    public Class<?> resolve(Class<?> fallback) {
        return this.resolved != null ? this.resolved : fallback;
    }

    @Nullable
    private Class<?> resolveClass() {
        if (this.type == EmptyType.INSTANCE) {
            return null;
        }
        if (this.type instanceof Class) {
            return (Class)this.type;
        }
        if (this.type instanceof GenericArrayType) {
            Class<?> resolvedComponent = this.getComponentType().resolve();
            return resolvedComponent != null ? Array.newInstance(resolvedComponent, 0).getClass() : null;
        }
        return this.resolveType().resolve();
    }

    ResolvableType resolveType() {
        if (this.type instanceof ParameterizedType) {
            return ResolvableType.forType(((ParameterizedType)this.type).getRawType(), this.variableResolver);
        }
        if (this.type instanceof WildcardType) {
            Type resolved = this.resolveBounds(((WildcardType)this.type).getUpperBounds());
            if (resolved == null) {
                resolved = this.resolveBounds(((WildcardType)this.type).getLowerBounds());
            }
            return ResolvableType.forType(resolved, this.variableResolver);
        }
        if (this.type instanceof TypeVariable) {
            ResolvableType resolved;
            TypeVariable variable = (TypeVariable)this.type;
            if (this.variableResolver != null && (resolved = this.variableResolver.resolveVariable(variable)) != null) {
                return resolved;
            }
            return ResolvableType.forType(this.resolveBounds(variable.getBounds()), this.variableResolver);
        }
        return NONE;
    }

    @Nullable
    private Type resolveBounds(Type[] bounds) {
        if (bounds.length == 0 || bounds[0] == Object.class) {
            return null;
        }
        return bounds[0];
    }

    @Nullable
    private ResolvableType resolveVariable(TypeVariable<?> variable) {
        ResolvableType resolved;
        if (this.type instanceof TypeVariable) {
            return this.resolveType().resolveVariable(variable);
        }
        if (this.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)this.type;
            Class<?> resolved2 = this.resolve();
            if (resolved2 == null) {
                return null;
            }
            TypeVariable<Class<?>>[] variables = resolved2.getTypeParameters();
            for (int i = 0; i < variables.length; ++i) {
                if (!ObjectUtils.nullSafeEquals(variables[i].getName(), variable.getName())) continue;
                Type actualType = parameterizedType.getActualTypeArguments()[i];
                return ResolvableType.forType(actualType, this.variableResolver);
            }
            Type ownerType = parameterizedType.getOwnerType();
            if (ownerType != null) {
                return ResolvableType.forType(ownerType, this.variableResolver).resolveVariable(variable);
            }
        }
        if (this.type instanceof WildcardType && (resolved = this.resolveType().resolveVariable(variable)) != null) {
            return resolved;
        }
        if (this.variableResolver != null) {
            return this.variableResolver.resolveVariable(variable);
        }
        return null;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResolvableType)) {
            return false;
        }
        ResolvableType otherType = (ResolvableType)other;
        if (!ObjectUtils.nullSafeEquals(this.type, otherType.type)) {
            return false;
        }
        if (!(this.typeProvider == otherType.typeProvider || this.typeProvider != null && otherType.typeProvider != null && ObjectUtils.nullSafeEquals(this.typeProvider.getType(), otherType.typeProvider.getType()))) {
            return false;
        }
        if (!(this.variableResolver == otherType.variableResolver || this.variableResolver != null && otherType.variableResolver != null && ObjectUtils.nullSafeEquals(this.variableResolver.getSource(), otherType.variableResolver.getSource()))) {
            return false;
        }
        return ObjectUtils.nullSafeEquals(this.componentType, otherType.componentType);
    }

    public int hashCode() {
        return this.hash != null ? this.hash.intValue() : this.calculateHashCode();
    }

    private int calculateHashCode() {
        int hashCode = ObjectUtils.nullSafeHashCode(this.type);
        if (this.typeProvider != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.typeProvider.getType());
        }
        if (this.variableResolver != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.variableResolver.getSource());
        }
        if (this.componentType != null) {
            hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode(this.componentType);
        }
        return hashCode;
    }

    @Nullable
    VariableResolver asVariableResolver() {
        if (this == NONE) {
            return null;
        }
        return new DefaultVariableResolver(this);
    }

    private Object readResolve() {
        return this.type == EmptyType.INSTANCE ? NONE : this;
    }

    public String toString() {
        if (this.isArray()) {
            return this.getComponentType() + "[]";
        }
        if (this.resolved == null) {
            return "?";
        }
        if (this.type instanceof TypeVariable) {
            TypeVariable variable = (TypeVariable)this.type;
            if (this.variableResolver == null || this.variableResolver.resolveVariable(variable) == null) {
                return "?";
            }
        }
        if (this.hasGenerics()) {
            return this.resolved.getName() + '<' + StringUtils.arrayToDelimitedString(this.getGenerics(), ", ") + '>';
        }
        return this.resolved.getName();
    }

    public static ResolvableType forClass(@Nullable Class<?> clazz) {
        return new ResolvableType(clazz);
    }

    public static ResolvableType forRawClass(final @Nullable Class<?> clazz) {
        return new ResolvableType(clazz){

            @Override
            public ResolvableType[] getGenerics() {
                return EMPTY_TYPES_ARRAY;
            }

            @Override
            public boolean isAssignableFrom(Class<?> other) {
                return clazz == null || ClassUtils.isAssignable(clazz, other);
            }

            @Override
            public boolean isAssignableFrom(ResolvableType other) {
                Class<?> otherClass = other.resolve();
                return otherClass != null && (clazz == null || ClassUtils.isAssignable(clazz, otherClass));
            }
        };
    }

    public static ResolvableType forClass(Class<?> baseType, Class<?> implementationClass) {
        Assert.notNull(baseType, "Base type must not be null");
        ResolvableType asType = ResolvableType.forType(implementationClass).as(baseType);
        return asType == NONE ? ResolvableType.forType(baseType) : asType;
    }

    public static ResolvableType forClassWithGenerics(Class<?> clazz, Class<?> ... generics) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(generics, "Generics array must not be null");
        ResolvableType[] resolvableGenerics = new ResolvableType[generics.length];
        for (int i = 0; i < generics.length; ++i) {
            resolvableGenerics[i] = ResolvableType.forClass(generics[i]);
        }
        return ResolvableType.forClassWithGenerics(clazz, resolvableGenerics);
    }

    public static ResolvableType forClassWithGenerics(Class<?> clazz, ResolvableType ... generics) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)generics, "Generics array must not be null");
        TypeVariable<Class<?>>[] variables = clazz.getTypeParameters();
        Assert.isTrue(variables.length == generics.length, "Mismatched number of generics specified");
        Type[] arguments = new Type[generics.length];
        for (int i = 0; i < generics.length; ++i) {
            ResolvableType generic = generics[i];
            Type argument = generic != null ? generic.getType() : null;
            arguments[i] = argument != null && !(argument instanceof TypeVariable) ? argument : variables[i];
        }
        SyntheticParameterizedType syntheticType = new SyntheticParameterizedType(clazz, arguments);
        return ResolvableType.forType((Type)syntheticType, new TypeVariablesVariableResolver(variables, generics));
    }

    public static ResolvableType forInstance(Object instance) {
        ResolvableType type;
        Assert.notNull(instance, "Instance must not be null");
        if (instance instanceof ResolvableTypeProvider && (type = ((ResolvableTypeProvider)instance).getResolvableType()) != null) {
            return type;
        }
        return ResolvableType.forClass(instance.getClass());
    }

    public static ResolvableType forField(Field field) {
        Assert.notNull((Object)field, "Field must not be null");
        return ResolvableType.forType(null, new SerializableTypeWrapper.FieldTypeProvider(field), null);
    }

    public static ResolvableType forField(Field field, Class<?> implementationClass) {
        Assert.notNull((Object)field, "Field must not be null");
        ResolvableType owner = ResolvableType.forType(implementationClass).as(field.getDeclaringClass());
        return ResolvableType.forType(null, new SerializableTypeWrapper.FieldTypeProvider(field), owner.asVariableResolver());
    }

    public static ResolvableType forField(Field field, @Nullable ResolvableType implementationType) {
        Assert.notNull((Object)field, "Field must not be null");
        ResolvableType owner = implementationType != null ? implementationType : NONE;
        owner = owner.as(field.getDeclaringClass());
        return ResolvableType.forType(null, new SerializableTypeWrapper.FieldTypeProvider(field), owner.asVariableResolver());
    }

    public static ResolvableType forField(Field field, int nestingLevel) {
        Assert.notNull((Object)field, "Field must not be null");
        return ResolvableType.forType(null, new SerializableTypeWrapper.FieldTypeProvider(field), null).getNested(nestingLevel);
    }

    public static ResolvableType forField(Field field, int nestingLevel, @Nullable Class<?> implementationClass) {
        Assert.notNull((Object)field, "Field must not be null");
        ResolvableType owner = ResolvableType.forType(implementationClass).as(field.getDeclaringClass());
        return ResolvableType.forType(null, new SerializableTypeWrapper.FieldTypeProvider(field), owner.asVariableResolver()).getNested(nestingLevel);
    }

    public static ResolvableType forConstructorParameter(Constructor<?> constructor, int parameterIndex) {
        Assert.notNull(constructor, "Constructor must not be null");
        return ResolvableType.forMethodParameter(new MethodParameter(constructor, parameterIndex));
    }

    public static ResolvableType forConstructorParameter(Constructor<?> constructor, int parameterIndex, Class<?> implementationClass) {
        Assert.notNull(constructor, "Constructor must not be null");
        MethodParameter methodParameter = new MethodParameter(constructor, parameterIndex, implementationClass);
        return ResolvableType.forMethodParameter(methodParameter);
    }

    public static ResolvableType forMethodReturnType(Method method) {
        Assert.notNull((Object)method, "Method must not be null");
        return ResolvableType.forMethodParameter(new MethodParameter(method, -1));
    }

    public static ResolvableType forMethodReturnType(Method method, Class<?> implementationClass) {
        Assert.notNull((Object)method, "Method must not be null");
        MethodParameter methodParameter = new MethodParameter((Executable)method, -1, implementationClass);
        return ResolvableType.forMethodParameter(methodParameter);
    }

    public static ResolvableType forMethodParameter(Method method, int parameterIndex) {
        Assert.notNull((Object)method, "Method must not be null");
        return ResolvableType.forMethodParameter(new MethodParameter(method, parameterIndex));
    }

    public static ResolvableType forMethodParameter(Method method, int parameterIndex, Class<?> implementationClass) {
        Assert.notNull((Object)method, "Method must not be null");
        MethodParameter methodParameter = new MethodParameter((Executable)method, parameterIndex, implementationClass);
        return ResolvableType.forMethodParameter(methodParameter);
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter) {
        return ResolvableType.forMethodParameter(methodParameter, (Type)null);
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter, @Nullable ResolvableType implementationType) {
        Assert.notNull((Object)methodParameter, "MethodParameter must not be null");
        implementationType = implementationType != null ? implementationType : ResolvableType.forType(methodParameter.getContainingClass());
        ResolvableType owner = implementationType.as(methodParameter.getDeclaringClass());
        return ResolvableType.forType(null, new SerializableTypeWrapper.MethodParameterTypeProvider(methodParameter), owner.asVariableResolver()).getNested(methodParameter.getNestingLevel(), methodParameter.typeIndexesPerLevel);
    }

    public static ResolvableType forMethodParameter(MethodParameter methodParameter, @Nullable Type targetType) {
        Assert.notNull((Object)methodParameter, "MethodParameter must not be null");
        return ResolvableType.forMethodParameter(methodParameter, targetType, methodParameter.getNestingLevel());
    }

    static ResolvableType forMethodParameter(MethodParameter methodParameter, @Nullable Type targetType, int nestingLevel) {
        ResolvableType owner = ResolvableType.forType(methodParameter.getContainingClass()).as(methodParameter.getDeclaringClass());
        return ResolvableType.forType(targetType, new SerializableTypeWrapper.MethodParameterTypeProvider(methodParameter), owner.asVariableResolver()).getNested(nestingLevel, methodParameter.typeIndexesPerLevel);
    }

    public static ResolvableType forArrayComponent(ResolvableType componentType) {
        Assert.notNull((Object)componentType, "Component type must not be null");
        Class<?> arrayClass = Array.newInstance(componentType.resolve(), 0).getClass();
        return new ResolvableType(arrayClass, null, null, componentType);
    }

    public static ResolvableType forType(@Nullable Type type) {
        return ResolvableType.forType(type, null, null);
    }

    public static ResolvableType forType(@Nullable Type type, @Nullable ResolvableType owner) {
        VariableResolver variableResolver = null;
        if (owner != null) {
            variableResolver = owner.asVariableResolver();
        }
        return ResolvableType.forType(type, variableResolver);
    }

    public static ResolvableType forType(ParameterizedTypeReference<?> typeReference) {
        return ResolvableType.forType(typeReference.getType(), null, null);
    }

    static ResolvableType forType(@Nullable Type type, @Nullable VariableResolver variableResolver) {
        return ResolvableType.forType(type, null, variableResolver);
    }

    static ResolvableType forType(@Nullable Type type, @Nullable SerializableTypeWrapper.TypeProvider typeProvider, @Nullable VariableResolver variableResolver) {
        if (type == null && typeProvider != null) {
            type = SerializableTypeWrapper.forTypeProvider(typeProvider);
        }
        if (type == null) {
            return NONE;
        }
        if (type instanceof Class) {
            return new ResolvableType(type, typeProvider, variableResolver, (ResolvableType)null);
        }
        cache.purgeUnreferencedEntries();
        ResolvableType resultType = new ResolvableType(type, typeProvider, variableResolver);
        ResolvableType cachedType = cache.get(resultType);
        if (cachedType == null) {
            cachedType = new ResolvableType(type, typeProvider, variableResolver, resultType.hash);
            cache.put(cachedType, cachedType);
        }
        resultType.resolved = cachedType.resolved;
        return resultType;
    }

    public static void clearCache() {
        cache.clear();
        SerializableTypeWrapper.cache.clear();
    }

    static class EmptyType
    implements Type,
    Serializable {
        static final Type INSTANCE = new EmptyType();

        EmptyType() {
        }

        Object readResolve() {
            return INSTANCE;
        }
    }

    private static class WildcardBounds {
        private final Kind kind;
        private final ResolvableType[] bounds;

        public WildcardBounds(Kind kind, ResolvableType[] bounds) {
            this.kind = kind;
            this.bounds = bounds;
        }

        public boolean isSameKind(WildcardBounds bounds) {
            return this.kind == bounds.kind;
        }

        public boolean isAssignableFrom(ResolvableType ... types) {
            for (ResolvableType bound : this.bounds) {
                for (ResolvableType type : types) {
                    if (this.isAssignable(bound, type)) continue;
                    return false;
                }
            }
            return true;
        }

        private boolean isAssignable(ResolvableType source, ResolvableType from) {
            return this.kind == Kind.UPPER ? source.isAssignableFrom(from) : from.isAssignableFrom(source);
        }

        public ResolvableType[] getBounds() {
            return this.bounds;
        }

        @Nullable
        public static WildcardBounds get(ResolvableType type) {
            ResolvableType resolveToWildcard = type;
            while (!(resolveToWildcard.getType() instanceof WildcardType)) {
                if (resolveToWildcard == NONE) {
                    return null;
                }
                resolveToWildcard = resolveToWildcard.resolveType();
            }
            WildcardType wildcardType = (WildcardType)resolveToWildcard.type;
            Kind boundsType = wildcardType.getLowerBounds().length > 0 ? Kind.LOWER : Kind.UPPER;
            Type[] bounds = boundsType == Kind.UPPER ? wildcardType.getUpperBounds() : wildcardType.getLowerBounds();
            ResolvableType[] resolvableBounds = new ResolvableType[bounds.length];
            for (int i = 0; i < bounds.length; ++i) {
                resolvableBounds[i] = ResolvableType.forType(bounds[i], type.variableResolver);
            }
            return new WildcardBounds(boundsType, resolvableBounds);
        }

        static enum Kind {
            UPPER,
            LOWER;

        }
    }

    private static final class SyntheticParameterizedType
    implements ParameterizedType,
    Serializable {
        private final Type rawType;
        private final Type[] typeArguments;

        public SyntheticParameterizedType(Type rawType, Type[] typeArguments) {
            this.rawType = rawType;
            this.typeArguments = typeArguments;
        }

        @Override
        public String getTypeName() {
            String typeName = this.rawType.getTypeName();
            if (this.typeArguments.length > 0) {
                StringJoiner stringJoiner = new StringJoiner(", ", "<", ">");
                for (Type argument : this.typeArguments) {
                    stringJoiner.add(argument.getTypeName());
                }
                return typeName + stringJoiner;
            }
            return typeName;
        }

        @Override
        @Nullable
        public Type getOwnerType() {
            return null;
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return this.typeArguments;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType otherType = (ParameterizedType)other;
            return otherType.getOwnerType() == null && this.rawType.equals(otherType.getRawType()) && Arrays.equals(this.typeArguments, otherType.getActualTypeArguments());
        }

        public int hashCode() {
            return this.rawType.hashCode() * 31 + Arrays.hashCode(this.typeArguments);
        }

        public String toString() {
            return this.getTypeName();
        }
    }

    private static class TypeVariablesVariableResolver
    implements VariableResolver {
        private final TypeVariable<?>[] variables;
        private final ResolvableType[] generics;

        public TypeVariablesVariableResolver(TypeVariable<?>[] variables, ResolvableType[] generics) {
            this.variables = variables;
            this.generics = generics;
        }

        @Override
        @Nullable
        public ResolvableType resolveVariable(TypeVariable<?> variable) {
            TypeVariable<?> variableToCompare = SerializableTypeWrapper.unwrap(variable);
            for (int i = 0; i < this.variables.length; ++i) {
                TypeVariable<?> resolvedVariable = SerializableTypeWrapper.unwrap(this.variables[i]);
                if (!ObjectUtils.nullSafeEquals(resolvedVariable, variableToCompare)) continue;
                return this.generics[i];
            }
            return null;
        }

        @Override
        public Object getSource() {
            return this.generics;
        }
    }

    private static class DefaultVariableResolver
    implements VariableResolver {
        private final ResolvableType source;

        DefaultVariableResolver(ResolvableType resolvableType) {
            this.source = resolvableType;
        }

        @Override
        @Nullable
        public ResolvableType resolveVariable(TypeVariable<?> variable) {
            return this.source.resolveVariable(variable);
        }

        @Override
        public Object getSource() {
            return this.source;
        }
    }

    static interface VariableResolver
    extends Serializable {
        public Object getSource();

        @Nullable
        public ResolvableType resolveVariable(TypeVariable<?> var1);
    }
}

