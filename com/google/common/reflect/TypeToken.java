/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeCapture;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.TypeVisitor;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class TypeToken<T>
extends TypeCapture<T>
implements Serializable {
    private final Type runtimeType;
    private transient @MonotonicNonNull TypeResolver invariantTypeResolver;
    private transient @MonotonicNonNull TypeResolver covariantTypeResolver;
    private static final long serialVersionUID = 3637540370352322684L;

    protected TypeToken() {
        this.runtimeType = this.capture();
        Preconditions.checkState(!(this.runtimeType instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", (Object)this.runtimeType);
    }

    protected TypeToken(Class<?> declaringClass) {
        Type captured = super.capture();
        this.runtimeType = captured instanceof Class ? captured : TypeResolver.covariantly(declaringClass).resolveType(captured);
    }

    private TypeToken(Type type) {
        this.runtimeType = Preconditions.checkNotNull(type);
    }

    public static <T> TypeToken<T> of(Class<T> type) {
        return new SimpleTypeToken((Type)type);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final Class<? super T> getRawType() {
        Class rawType;
        Class result = rawType = (Class)this.getRawTypes().iterator().next();
        return result;
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParam, TypeToken<X> typeArg) {
        TypeResolver resolver = new TypeResolver().where(ImmutableMap.of(new TypeResolver.TypeVariableKey(typeParam.typeVariable), typeArg.runtimeType));
        return new SimpleTypeToken(resolver.resolveType(this.runtimeType));
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParam, Class<X> typeArg) {
        return this.where(typeParam, TypeToken.of(typeArg));
    }

    public final TypeToken<?> resolveType(Type type) {
        Preconditions.checkNotNull(type);
        return TypeToken.of(this.getInvariantTypeResolver().resolveType(type));
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> supertype = TypeToken.of(this.getCovariantTypeResolver().resolveType(type));
        supertype.covariantTypeResolver = this.covariantTypeResolver;
        supertype.invariantTypeResolver = this.invariantTypeResolver;
        return supertype;
    }

    final @Nullable TypeToken<? super T> getGenericSuperclass() {
        if (this.runtimeType instanceof TypeVariable) {
            return this.boundAsSuperclass(((TypeVariable)this.runtimeType).getBounds()[0]);
        }
        if (this.runtimeType instanceof WildcardType) {
            return this.boundAsSuperclass(((WildcardType)this.runtimeType).getUpperBounds()[0]);
        }
        Type superclass = this.getRawType().getGenericSuperclass();
        if (superclass == null) {
            return null;
        }
        TypeToken<?> superToken = this.resolveSupertype(superclass);
        return superToken;
    }

    private @Nullable TypeToken<? super T> boundAsSuperclass(Type bound) {
        TypeToken<?> token = TypeToken.of(bound);
        if (token.getRawType().isInterface()) {
            return null;
        }
        TypeToken<?> superclass = token;
        return superclass;
    }

    final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        if (this.runtimeType instanceof TypeVariable) {
            return this.boundsAsInterfaces(((TypeVariable)this.runtimeType).getBounds());
        }
        if (this.runtimeType instanceof WildcardType) {
            return this.boundsAsInterfaces(((WildcardType)this.runtimeType).getUpperBounds());
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type interfaceType : this.getRawType().getGenericInterfaces()) {
            TypeToken<?> resolvedInterface = this.resolveSupertype(interfaceType);
            builder.add(resolvedInterface);
        }
        return builder.build();
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] bounds) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type bound : bounds) {
            TypeToken<?> boundType = TypeToken.of(bound);
            if (!boundType.getRawType().isInterface()) continue;
            builder.add(boundType);
        }
        return builder.build();
    }

    public final TypeSet getTypes() {
        return new TypeSet();
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> superclass) {
        Preconditions.checkArgument(this.someRawTypeIsSubclassOf(superclass), "%s is not a super class of %s", superclass, (Object)this);
        if (this.runtimeType instanceof TypeVariable) {
            return this.getSupertypeFromUpperBounds(superclass, ((TypeVariable)this.runtimeType).getBounds());
        }
        if (this.runtimeType instanceof WildcardType) {
            return this.getSupertypeFromUpperBounds(superclass, ((WildcardType)this.runtimeType).getUpperBounds());
        }
        if (superclass.isArray()) {
            return this.getArraySupertype(superclass);
        }
        TypeToken<?> supertype = this.resolveSupertype(TypeToken.toGenericType(superclass).runtimeType);
        return supertype;
    }

    public final TypeToken<? extends T> getSubtype(Class<?> subclass) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", (Object)this);
        if (this.runtimeType instanceof WildcardType) {
            return this.getSubtypeFromLowerBounds(subclass, ((WildcardType)this.runtimeType).getLowerBounds());
        }
        if (this.isArray()) {
            return this.getArraySubtype(subclass);
        }
        Preconditions.checkArgument(this.getRawType().isAssignableFrom(subclass), "%s isn't a subclass of %s", subclass, (Object)this);
        Type resolvedTypeArgs = this.resolveTypeArgsForSubclass(subclass);
        TypeToken<?> subtype = TypeToken.of(resolvedTypeArgs);
        Preconditions.checkArgument(subtype.isSubtypeOf(this), "%s does not appear to be a subtype of %s", subtype, (Object)this);
        return subtype;
    }

    public final boolean isSupertypeOf(TypeToken<?> type) {
        return type.isSubtypeOf(this.getType());
    }

    public final boolean isSupertypeOf(Type type) {
        return TypeToken.of(type).isSubtypeOf(this.getType());
    }

    public final boolean isSubtypeOf(TypeToken<?> type) {
        return this.isSubtypeOf(type.getType());
    }

    public final boolean isSubtypeOf(Type supertype) {
        Preconditions.checkNotNull(supertype);
        if (supertype instanceof WildcardType) {
            return TypeToken.any(((WildcardType)supertype).getLowerBounds()).isSupertypeOf(this.runtimeType);
        }
        if (this.runtimeType instanceof WildcardType) {
            return TypeToken.any(((WildcardType)this.runtimeType).getUpperBounds()).isSubtypeOf(supertype);
        }
        if (this.runtimeType instanceof TypeVariable) {
            return this.runtimeType.equals(supertype) || TypeToken.any(((TypeVariable)this.runtimeType).getBounds()).isSubtypeOf(supertype);
        }
        if (this.runtimeType instanceof GenericArrayType) {
            return super.isSupertypeOfArray((GenericArrayType)this.runtimeType);
        }
        if (supertype instanceof Class) {
            return this.someRawTypeIsSubclassOf((Class)supertype);
        }
        if (supertype instanceof ParameterizedType) {
            return this.isSubtypeOfParameterizedType((ParameterizedType)supertype);
        }
        if (supertype instanceof GenericArrayType) {
            return this.isSubtypeOfArrayType((GenericArrayType)supertype);
        }
        return false;
    }

    public final boolean isArray() {
        return this.getComponentType() != null;
    }

    public final boolean isPrimitive() {
        return this.runtimeType instanceof Class && ((Class)this.runtimeType).isPrimitive();
    }

    public final TypeToken<T> wrap() {
        if (this.isPrimitive()) {
            Class type = (Class)this.runtimeType;
            return TypeToken.of(Primitives.wrap(type));
        }
        return this;
    }

    private boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        if (this.isWrapper()) {
            Class type = (Class)this.runtimeType;
            return TypeToken.of(Primitives.unwrap(type));
        }
        return this;
    }

    public final @Nullable TypeToken<?> getComponentType() {
        Type componentType = Types.getComponentType(this.runtimeType);
        if (componentType == null) {
            return null;
        }
        return TypeToken.of(componentType);
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(this.someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", (Object)method, (Object)this);
        return new Invokable.MethodInvokable<T>(method){

            @Override
            Type getGenericReturnType() {
                return TypeToken.this.getCovariantTypeResolver().resolveType(super.getGenericReturnType());
            }

            @Override
            Type[] getGenericParameterTypes() {
                return TypeToken.this.getInvariantTypeResolver().resolveTypesInPlace(super.getGenericParameterTypes());
            }

            @Override
            Type[] getGenericExceptionTypes() {
                return TypeToken.this.getCovariantTypeResolver().resolveTypesInPlace(super.getGenericExceptionTypes());
            }

            @Override
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override
            public String toString() {
                return this.getOwnerType() + "." + super.toString();
            }
        };
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        Preconditions.checkArgument(constructor.getDeclaringClass() == this.getRawType(), "%s not declared by %s", constructor, this.getRawType());
        return new Invokable.ConstructorInvokable<T>(constructor){

            @Override
            Type getGenericReturnType() {
                return TypeToken.this.getCovariantTypeResolver().resolveType(super.getGenericReturnType());
            }

            @Override
            Type[] getGenericParameterTypes() {
                return TypeToken.this.getInvariantTypeResolver().resolveTypesInPlace(super.getGenericParameterTypes());
            }

            @Override
            Type[] getGenericExceptionTypes() {
                return TypeToken.this.getCovariantTypeResolver().resolveTypesInPlace(super.getGenericExceptionTypes());
            }

            @Override
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override
            public String toString() {
                return this.getOwnerType() + "(" + Joiner.on(", ").join(this.getGenericParameterTypes()) + ")";
            }
        };
    }

    public boolean equals(@Nullable Object o) {
        if (o instanceof TypeToken) {
            TypeToken that = (TypeToken)o;
            return this.runtimeType.equals(that.runtimeType);
        }
        return false;
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public String toString() {
        return Types.toString(this.runtimeType);
    }

    protected Object writeReplace() {
        return TypeToken.of(new TypeResolver().resolveType(this.runtimeType));
    }

    @CanIgnoreReturnValue
    final TypeToken<T> rejectTypeVariables() {
        new TypeVisitor(){

            @Override
            void visitTypeVariable(TypeVariable<?> type) {
                throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
            }

            @Override
            void visitWildcardType(WildcardType type) {
                this.visit(type.getLowerBounds());
                this.visit(type.getUpperBounds());
            }

            @Override
            void visitParameterizedType(ParameterizedType type) {
                this.visit(type.getActualTypeArguments());
                this.visit(type.getOwnerType());
            }

            @Override
            void visitGenericArrayType(GenericArrayType type) {
                this.visit(type.getGenericComponentType());
            }
        }.visit(this.runtimeType);
        return this;
    }

    private boolean someRawTypeIsSubclassOf(Class<?> superclass) {
        for (Class clazz : this.getRawTypes()) {
            if (!superclass.isAssignableFrom(clazz)) continue;
            return true;
        }
        return false;
    }

    private boolean isSubtypeOfParameterizedType(ParameterizedType supertype) {
        Class<?> matchedClass = TypeToken.of(supertype).getRawType();
        if (!this.someRawTypeIsSubclassOf(matchedClass)) {
            return false;
        }
        TypeVariable<Class<?>>[] typeVars = matchedClass.getTypeParameters();
        Type[] supertypeArgs = supertype.getActualTypeArguments();
        for (int i = 0; i < typeVars.length; ++i) {
            Type subtypeParam = this.getCovariantTypeResolver().resolveType(typeVars[i]);
            if (super.is(supertypeArgs[i], typeVars[i])) continue;
            return false;
        }
        return Modifier.isStatic(((Class)supertype.getRawType()).getModifiers()) || supertype.getOwnerType() == null || this.isOwnedBySubtypeOf(supertype.getOwnerType());
    }

    private boolean isSubtypeOfArrayType(GenericArrayType supertype) {
        if (this.runtimeType instanceof Class) {
            Class fromClass = (Class)this.runtimeType;
            if (!fromClass.isArray()) {
                return false;
            }
            return TypeToken.of(fromClass.getComponentType()).isSubtypeOf(supertype.getGenericComponentType());
        }
        if (this.runtimeType instanceof GenericArrayType) {
            GenericArrayType fromArrayType = (GenericArrayType)this.runtimeType;
            return TypeToken.of(fromArrayType.getGenericComponentType()).isSubtypeOf(supertype.getGenericComponentType());
        }
        return false;
    }

    private boolean isSupertypeOfArray(GenericArrayType subtype) {
        if (this.runtimeType instanceof Class) {
            Class thisClass = (Class)this.runtimeType;
            if (!thisClass.isArray()) {
                return thisClass.isAssignableFrom(Object[].class);
            }
            return TypeToken.of(subtype.getGenericComponentType()).isSubtypeOf(thisClass.getComponentType());
        }
        if (this.runtimeType instanceof GenericArrayType) {
            return TypeToken.of(subtype.getGenericComponentType()).isSubtypeOf(((GenericArrayType)this.runtimeType).getGenericComponentType());
        }
        return false;
    }

    private boolean is(Type formalType, TypeVariable<?> declaration) {
        if (this.runtimeType.equals(formalType)) {
            return true;
        }
        if (formalType instanceof WildcardType) {
            WildcardType your = TypeToken.canonicalizeWildcardType(declaration, (WildcardType)formalType);
            return TypeToken.every(your.getUpperBounds()).isSupertypeOf(this.runtimeType) && TypeToken.every(your.getLowerBounds()).isSubtypeOf(this.runtimeType);
        }
        return TypeToken.canonicalizeWildcardsInType(this.runtimeType).equals(TypeToken.canonicalizeWildcardsInType(formalType));
    }

    private static Type canonicalizeTypeArg(TypeVariable<?> declaration, Type typeArg) {
        return typeArg instanceof WildcardType ? TypeToken.canonicalizeWildcardType(declaration, (WildcardType)typeArg) : TypeToken.canonicalizeWildcardsInType(typeArg);
    }

    private static Type canonicalizeWildcardsInType(Type type) {
        if (type instanceof ParameterizedType) {
            return TypeToken.canonicalizeWildcardsInParameterizedType((ParameterizedType)type);
        }
        if (type instanceof GenericArrayType) {
            return Types.newArrayType(TypeToken.canonicalizeWildcardsInType(((GenericArrayType)type).getGenericComponentType()));
        }
        return type;
    }

    private static WildcardType canonicalizeWildcardType(TypeVariable<?> declaration, WildcardType type) {
        Type[] declared = declaration.getBounds();
        ArrayList<Type> upperBounds = new ArrayList<Type>();
        for (Type bound : type.getUpperBounds()) {
            if (TypeToken.any(declared).isSubtypeOf(bound)) continue;
            upperBounds.add(TypeToken.canonicalizeWildcardsInType(bound));
        }
        return new Types.WildcardTypeImpl(type.getLowerBounds(), upperBounds.toArray(new Type[0]));
    }

    private static ParameterizedType canonicalizeWildcardsInParameterizedType(ParameterizedType type) {
        Class rawType = (Class)type.getRawType();
        TypeVariable<Class<T>>[] typeVars = rawType.getTypeParameters();
        Type[] typeArgs = type.getActualTypeArguments();
        for (int i = 0; i < typeArgs.length; ++i) {
            typeArgs[i] = TypeToken.canonicalizeTypeArg(typeVars[i], typeArgs[i]);
        }
        return Types.newParameterizedTypeWithOwner(type.getOwnerType(), rawType, typeArgs);
    }

    private static Bounds every(Type[] bounds) {
        return new Bounds(bounds, false);
    }

    private static Bounds any(Type[] bounds) {
        return new Bounds(bounds, true);
    }

    private ImmutableSet<Class<? super T>> getRawTypes() {
        final ImmutableSet.Builder builder = ImmutableSet.builder();
        new TypeVisitor(){

            @Override
            void visitTypeVariable(TypeVariable<?> t) {
                this.visit(t.getBounds());
            }

            @Override
            void visitWildcardType(WildcardType t) {
                this.visit(t.getUpperBounds());
            }

            @Override
            void visitParameterizedType(ParameterizedType t) {
                builder.add((Class)t.getRawType());
            }

            @Override
            void visitClass(Class<?> t) {
                builder.add(t);
            }

            @Override
            void visitGenericArrayType(GenericArrayType t) {
                builder.add(Types.getArrayClass(TypeToken.of(t.getGenericComponentType()).getRawType()));
            }
        }.visit(this.runtimeType);
        ImmutableCollection result = builder.build();
        return result;
    }

    private boolean isOwnedBySubtypeOf(Type supertype) {
        for (TypeToken type : this.getTypes()) {
            Type ownerType = type.getOwnerTypeIfPresent();
            if (ownerType == null || !TypeToken.of(ownerType).isSubtypeOf(supertype)) continue;
            return true;
        }
        return false;
    }

    private @Nullable Type getOwnerTypeIfPresent() {
        if (this.runtimeType instanceof ParameterizedType) {
            return ((ParameterizedType)this.runtimeType).getOwnerType();
        }
        if (this.runtimeType instanceof Class) {
            return ((Class)this.runtimeType).getEnclosingClass();
        }
        return null;
    }

    @VisibleForTesting
    static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        Type ownerType;
        if (cls.isArray()) {
            Type arrayOfGenericType = Types.newArrayType(TypeToken.toGenericType(cls.getComponentType()).runtimeType);
            TypeToken<?> result = TypeToken.of(arrayOfGenericType);
            return result;
        }
        Type[] typeParams = cls.getTypeParameters();
        Type type = ownerType = cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers()) ? TypeToken.toGenericType(cls.getEnclosingClass()).runtimeType : null;
        if (typeParams.length > 0 || ownerType != null && ownerType != cls.getEnclosingClass()) {
            TypeToken<?> type2 = TypeToken.of(Types.newParameterizedTypeWithOwner(ownerType, cls, typeParams));
            return type2;
        }
        return TypeToken.of(cls);
    }

    private TypeResolver getCovariantTypeResolver() {
        TypeResolver resolver = this.covariantTypeResolver;
        if (resolver == null) {
            resolver = this.covariantTypeResolver = TypeResolver.covariantly(this.runtimeType);
        }
        return resolver;
    }

    private TypeResolver getInvariantTypeResolver() {
        TypeResolver resolver = this.invariantTypeResolver;
        if (resolver == null) {
            resolver = this.invariantTypeResolver = TypeResolver.invariantly(this.runtimeType);
        }
        return resolver;
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> supertype, Type[] upperBounds) {
        for (Type upperBound : upperBounds) {
            TypeToken<?> bound = TypeToken.of(upperBound);
            if (!bound.isSubtypeOf(supertype)) continue;
            TypeToken<? super T> result = bound.getSupertype(supertype);
            return result;
        }
        throw new IllegalArgumentException(supertype + " isn't a super type of " + this);
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> subclass, Type[] lowerBounds) {
        int n = 0;
        Type[] typeArray = lowerBounds;
        int n2 = typeArray.length;
        if (n < n2) {
            Type lowerBound = typeArray[n];
            TypeToken<?> bound = TypeToken.of(lowerBound);
            return bound.getSubtype(subclass);
        }
        throw new IllegalArgumentException(subclass + " isn't a subclass of " + this);
    }

    private TypeToken<? super T> getArraySupertype(Class<? super T> supertype) {
        TypeToken<?> componentType = Preconditions.checkNotNull(this.getComponentType(), "%s isn't a super type of %s", supertype, (Object)this);
        TypeToken<?> componentSupertype = componentType.getSupertype(supertype.getComponentType());
        TypeToken<?> result = TypeToken.of(TypeToken.newArrayClassOrGenericArrayType(componentSupertype.runtimeType));
        return result;
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> subclass) {
        TypeToken<?> componentSubtype = this.getComponentType().getSubtype(subclass.getComponentType());
        TypeToken<?> result = TypeToken.of(TypeToken.newArrayClassOrGenericArrayType(componentSubtype.runtimeType));
        return result;
    }

    private Type resolveTypeArgsForSubclass(Class<?> subclass) {
        if (this.runtimeType instanceof Class && (subclass.getTypeParameters().length == 0 || this.getRawType().getTypeParameters().length != 0)) {
            return subclass;
        }
        TypeToken<?> genericSubtype = TypeToken.toGenericType(subclass);
        Type supertypeWithArgsFromSubtype = genericSubtype.getSupertype(this.getRawType()).runtimeType;
        return new TypeResolver().where(supertypeWithArgsFromSubtype, this.runtimeType).resolveType(genericSubtype.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type componentType) {
        return Types.JavaVersion.JAVA7.newArrayType(componentType);
    }

    private static abstract class TypeCollector<K> {
        static final TypeCollector<TypeToken<?>> FOR_GENERIC_TYPE = new TypeCollector<TypeToken<?>>(){

            @Override
            Class<?> getRawType(TypeToken<?> type) {
                return type.getRawType();
            }

            @Override
            Iterable<? extends TypeToken<?>> getInterfaces(TypeToken<?> type) {
                return type.getGenericInterfaces();
            }

            @Override
            @Nullable TypeToken<?> getSuperclass(TypeToken<?> type) {
                return type.getGenericSuperclass();
            }
        };
        static final TypeCollector<Class<?>> FOR_RAW_TYPE = new TypeCollector<Class<?>>(){

            @Override
            Class<?> getRawType(Class<?> type) {
                return type;
            }

            @Override
            Iterable<? extends Class<?>> getInterfaces(Class<?> type) {
                return Arrays.asList(type.getInterfaces());
            }

            @Override
            @Nullable Class<?> getSuperclass(Class<?> type) {
                return type.getSuperclass();
            }
        };

        private TypeCollector() {
        }

        final TypeCollector<K> classesOnly() {
            return new ForwardingTypeCollector<K>(this){

                @Override
                Iterable<? extends K> getInterfaces(K type) {
                    return ImmutableSet.of();
                }

                @Override
                ImmutableList<K> collectTypes(Iterable<? extends K> types) {
                    ImmutableList.Builder builder = ImmutableList.builder();
                    for (Object type : types) {
                        if (this.getRawType(type).isInterface()) continue;
                        builder.add(type);
                    }
                    return super.collectTypes(builder.build());
                }
            };
        }

        final ImmutableList<K> collectTypes(K type) {
            return this.collectTypes((Iterable<? extends K>)ImmutableList.of(type));
        }

        ImmutableList<K> collectTypes(Iterable<? extends K> types) {
            HashMap map = Maps.newHashMap();
            for (K type : types) {
                this.collectTypes(type, map);
            }
            return TypeCollector.sortKeysByValue(map, Ordering.natural().reverse());
        }

        @CanIgnoreReturnValue
        private int collectTypes(K type, Map<? super K, Integer> map) {
            Integer existing = map.get(type);
            if (existing != null) {
                return existing;
            }
            int aboveMe = this.getRawType(type).isInterface() ? 1 : 0;
            for (K interfaceType : this.getInterfaces(type)) {
                aboveMe = Math.max(aboveMe, this.collectTypes(interfaceType, map));
            }
            K superclass = this.getSuperclass(type);
            if (superclass != null) {
                aboveMe = Math.max(aboveMe, this.collectTypes(superclass, map));
            }
            map.put(type, aboveMe + 1);
            return aboveMe + 1;
        }

        private static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> valueComparator) {
            Ordering keyOrdering = new Ordering<K>(){

                @Override
                public int compare(K left, K right) {
                    return valueComparator.compare(map.get(left), map.get(right));
                }
            };
            return keyOrdering.immutableSortedCopy(map.keySet());
        }

        abstract Class<?> getRawType(K var1);

        abstract Iterable<? extends K> getInterfaces(K var1);

        abstract @Nullable K getSuperclass(K var1);

        private static class ForwardingTypeCollector<K>
        extends TypeCollector<K> {
            private final TypeCollector<K> delegate;

            ForwardingTypeCollector(TypeCollector<K> delegate) {
                this.delegate = delegate;
            }

            @Override
            Class<?> getRawType(K type) {
                return this.delegate.getRawType(type);
            }

            @Override
            Iterable<? extends K> getInterfaces(K type) {
                return this.delegate.getInterfaces(type);
            }

            @Override
            K getSuperclass(K type) {
                return this.delegate.getSuperclass(type);
            }
        }
    }

    private static final class SimpleTypeToken<T>
    extends TypeToken<T> {
        private static final long serialVersionUID = 0L;

        SimpleTypeToken(Type type) {
            super(type);
        }
    }

    private static class Bounds {
        private final Type[] bounds;
        private final boolean target;

        Bounds(Type[] bounds, boolean target) {
            this.bounds = bounds;
            this.target = target;
        }

        boolean isSubtypeOf(Type supertype) {
            for (Type bound : this.bounds) {
                if (TypeToken.of(bound).isSubtypeOf(supertype) != this.target) continue;
                return this.target;
            }
            return !this.target;
        }

        boolean isSupertypeOf(Type subtype) {
            TypeToken<?> type = TypeToken.of(subtype);
            for (Type bound : this.bounds) {
                if (type.isSubtypeOf(bound) != this.target) continue;
                return this.target;
            }
            return !this.target;
        }
    }

    private static enum TypeFilter implements Predicate<TypeToken<?>>
    {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD{

            @Override
            public boolean apply(TypeToken<?> type) {
                return !(((TypeToken)type).runtimeType instanceof TypeVariable) && !(((TypeToken)type).runtimeType instanceof WildcardType);
            }
        }
        ,
        INTERFACE_ONLY{

            @Override
            public boolean apply(TypeToken<?> type) {
                return type.getRawType().isInterface();
            }
        };

    }

    private final class ClassSet
    extends TypeSet {
        private transient @MonotonicNonNull ImmutableSet<TypeToken<? super T>> classes;
        private static final long serialVersionUID = 0L;

        private ClassSet() {
        }

        @Override
        protected Set<TypeToken<? super T>> delegate() {
            ImmutableSet result = this.classes;
            if (result == null) {
                ImmutableList<TypeToken> collectedTypes = TypeCollector.FOR_GENERIC_TYPE.classesOnly().collectTypes(TypeToken.this);
                this.classes = FluentIterable.from(collectedTypes).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
                return this.classes;
            }
            return result;
        }

        @Override
        public TypeSet classes() {
            return this;
        }

        @Override
        public Set<Class<? super T>> rawTypes() {
            ImmutableList<Class<?>> collectedTypes = TypeCollector.FOR_RAW_TYPE.classesOnly().collectTypes(TypeToken.this.getRawTypes());
            return ImmutableSet.copyOf(collectedTypes);
        }

        @Override
        public TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }
    }

    private final class InterfaceSet
    extends TypeSet {
        private final transient TypeSet allTypes;
        private transient @MonotonicNonNull ImmutableSet<TypeToken<? super T>> interfaces;
        private static final long serialVersionUID = 0L;

        InterfaceSet(TypeSet allTypes) {
            this.allTypes = allTypes;
        }

        @Override
        protected Set<TypeToken<? super T>> delegate() {
            ImmutableSet result = this.interfaces;
            if (result == null) {
                this.interfaces = FluentIterable.from(this.allTypes).filter(TypeFilter.INTERFACE_ONLY).toSet();
                return this.interfaces;
            }
            return result;
        }

        @Override
        public TypeSet interfaces() {
            return this;
        }

        @Override
        public Set<Class<? super T>> rawTypes() {
            ImmutableList<Class<?>> collectedTypes = TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes());
            return FluentIterable.from(collectedTypes).filter(new Predicate<Class<?>>(){

                @Override
                public boolean apply(Class<?> type) {
                    return type.isInterface();
                }
            }).toSet();
        }

        @Override
        public TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }
    }

    public class TypeSet
    extends ForwardingSet<TypeToken<? super T>>
    implements Serializable {
        private transient @MonotonicNonNull ImmutableSet<TypeToken<? super T>> types;
        private static final long serialVersionUID = 0L;

        TypeSet() {
        }

        public TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public TypeSet classes() {
            return new ClassSet();
        }

        @Override
        protected Set<TypeToken<? super T>> delegate() {
            ImmutableSet filteredTypes = this.types;
            if (filteredTypes == null) {
                ImmutableList<TypeToken> collectedTypes = TypeCollector.FOR_GENERIC_TYPE.collectTypes(TypeToken.this);
                this.types = FluentIterable.from(collectedTypes).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
                return this.types;
            }
            return filteredTypes;
        }

        public Set<Class<? super T>> rawTypes() {
            ImmutableList<Class<?>> collectedTypes = TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes());
            return ImmutableSet.copyOf(collectedTypes);
        }
    }
}

