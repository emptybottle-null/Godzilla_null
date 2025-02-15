/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeVisitor;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class TypeResolver {
    private final TypeTable typeTable;

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable) {
        this.typeTable = typeTable;
    }

    static TypeResolver covariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(contextType));
    }

    static TypeResolver invariantly(Type contextType) {
        Type invariantContext = WildcardCapturer.INSTANCE.capture(contextType);
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(invariantContext));
    }

    public TypeResolver where(Type formal, Type actual) {
        HashMap<TypeVariableKey, Type> mappings = Maps.newHashMap();
        TypeResolver.populateTypeMappings(mappings, Preconditions.checkNotNull(formal), Preconditions.checkNotNull(actual));
        return this.where(mappings);
    }

    TypeResolver where(Map<TypeVariableKey, ? extends Type> mappings) {
        return new TypeResolver(this.typeTable.where(mappings));
    }

    private static void populateTypeMappings(final Map<TypeVariableKey, Type> mappings, Type from, final Type to) {
        if (from.equals(to)) {
            return;
        }
        new TypeVisitor(){

            @Override
            void visitTypeVariable(TypeVariable<?> typeVariable) {
                mappings.put(new TypeVariableKey(typeVariable), to);
            }

            @Override
            void visitWildcardType(WildcardType fromWildcardType) {
                int i;
                if (!(to instanceof WildcardType)) {
                    return;
                }
                WildcardType toWildcardType = (WildcardType)to;
                Type[] fromUpperBounds = fromWildcardType.getUpperBounds();
                Type[] toUpperBounds = toWildcardType.getUpperBounds();
                Type[] fromLowerBounds = fromWildcardType.getLowerBounds();
                Type[] toLowerBounds = toWildcardType.getLowerBounds();
                Preconditions.checkArgument(fromUpperBounds.length == toUpperBounds.length && fromLowerBounds.length == toLowerBounds.length, "Incompatible type: %s vs. %s", (Object)fromWildcardType, (Object)to);
                for (i = 0; i < fromUpperBounds.length; ++i) {
                    TypeResolver.populateTypeMappings(mappings, fromUpperBounds[i], toUpperBounds[i]);
                }
                for (i = 0; i < fromLowerBounds.length; ++i) {
                    TypeResolver.populateTypeMappings(mappings, fromLowerBounds[i], toLowerBounds[i]);
                }
            }

            @Override
            void visitParameterizedType(ParameterizedType fromParameterizedType) {
                if (to instanceof WildcardType) {
                    return;
                }
                ParameterizedType toParameterizedType = (ParameterizedType)TypeResolver.expectArgument(ParameterizedType.class, to);
                if (fromParameterizedType.getOwnerType() != null && toParameterizedType.getOwnerType() != null) {
                    TypeResolver.populateTypeMappings(mappings, fromParameterizedType.getOwnerType(), toParameterizedType.getOwnerType());
                }
                Preconditions.checkArgument(fromParameterizedType.getRawType().equals(toParameterizedType.getRawType()), "Inconsistent raw type: %s vs. %s", (Object)fromParameterizedType, (Object)to);
                Type[] fromArgs = fromParameterizedType.getActualTypeArguments();
                Type[] toArgs = toParameterizedType.getActualTypeArguments();
                Preconditions.checkArgument(fromArgs.length == toArgs.length, "%s not compatible with %s", (Object)fromParameterizedType, (Object)toParameterizedType);
                for (int i = 0; i < fromArgs.length; ++i) {
                    TypeResolver.populateTypeMappings(mappings, fromArgs[i], toArgs[i]);
                }
            }

            @Override
            void visitGenericArrayType(GenericArrayType fromArrayType) {
                if (to instanceof WildcardType) {
                    return;
                }
                Type componentType = Types.getComponentType(to);
                Preconditions.checkArgument(componentType != null, "%s is not an array type.", (Object)to);
                TypeResolver.populateTypeMappings(mappings, fromArrayType.getGenericComponentType(), componentType);
            }

            @Override
            void visitClass(Class<?> fromClass) {
                if (to instanceof WildcardType) {
                    return;
                }
                throw new IllegalArgumentException("No type mapping from " + fromClass + " to " + to);
            }
        }.visit(from);
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.typeTable.resolve((TypeVariable)type);
        }
        if (type instanceof ParameterizedType) {
            return this.resolveParameterizedType((ParameterizedType)type);
        }
        if (type instanceof GenericArrayType) {
            return this.resolveGenericArrayType((GenericArrayType)type);
        }
        if (type instanceof WildcardType) {
            return this.resolveWildcardType((WildcardType)type);
        }
        return type;
    }

    Type[] resolveTypesInPlace(Type[] types) {
        for (int i = 0; i < types.length; ++i) {
            types[i] = this.resolveType(types[i]);
        }
        return types;
    }

    private Type[] resolveTypes(Type[] types) {
        Type[] result = new Type[types.length];
        for (int i = 0; i < types.length; ++i) {
            result[i] = this.resolveType(types[i]);
        }
        return result;
    }

    private WildcardType resolveWildcardType(WildcardType type) {
        Type[] lowerBounds = type.getLowerBounds();
        Type[] upperBounds = type.getUpperBounds();
        return new Types.WildcardTypeImpl(this.resolveTypes(lowerBounds), this.resolveTypes(upperBounds));
    }

    private Type resolveGenericArrayType(GenericArrayType type) {
        Type componentType = type.getGenericComponentType();
        Type resolvedComponentType = this.resolveType(componentType);
        return Types.newArrayType(resolvedComponentType);
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType type) {
        Type owner = type.getOwnerType();
        Type resolvedOwner = owner == null ? null : this.resolveType(owner);
        Type resolvedRawType = this.resolveType(type.getRawType());
        Type[] args = type.getActualTypeArguments();
        Type[] resolvedArgs = this.resolveTypes(args);
        return Types.newParameterizedTypeWithOwner(resolvedOwner, (Class)resolvedRawType, resolvedArgs);
    }

    private static <T> T expectArgument(Class<T> type, Object arg) {
        try {
            return type.cast(arg);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(arg + " is not a " + type.getSimpleName());
        }
    }

    static final class TypeVariableKey {
        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> var) {
            this.var = Preconditions.checkNotNull(var);
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                TypeVariableKey that = (TypeVariableKey)obj;
                return this.equalsTypeVariable(that.var);
            }
            return false;
        }

        public String toString() {
            return this.var.toString();
        }

        static TypeVariableKey forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable)t);
            }
            return null;
        }

        boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return this.equalsTypeVariable((TypeVariable)type);
            }
            return false;
        }

        private boolean equalsTypeVariable(TypeVariable<?> that) {
            return this.var.getGenericDeclaration().equals(that.getGenericDeclaration()) && this.var.getName().equals(that.getName());
        }
    }

    private static class WildcardCapturer {
        static final WildcardCapturer INSTANCE = new WildcardCapturer();
        private final AtomicInteger id;

        private WildcardCapturer() {
            this(new AtomicInteger());
        }

        private WildcardCapturer(AtomicInteger id) {
            this.id = id;
        }

        final Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if (type instanceof Class) {
                return type;
            }
            if (type instanceof TypeVariable) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                GenericArrayType arrayType = (GenericArrayType)type;
                return Types.newArrayType(this.notForTypeVariable().capture(arrayType.getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType)type;
                Class rawType = (Class)parameterizedType.getRawType();
                TypeVariable<Class<T>>[] typeVars = rawType.getTypeParameters();
                Type[] typeArgs = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < typeArgs.length; ++i) {
                    typeArgs[i] = this.forTypeVariable(typeVars[i]).capture(typeArgs[i]);
                }
                return Types.newParameterizedTypeWithOwner(this.notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), rawType, typeArgs);
            }
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType)type;
                Type[] lowerBounds = wildcardType.getLowerBounds();
                if (lowerBounds.length == 0) {
                    return this.captureAsTypeVariable(wildcardType.getUpperBounds());
                }
                return type;
            }
            throw new AssertionError((Object)"must have been one of the known types");
        }

        TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
            String name = "capture#" + this.id.incrementAndGet() + "-of ? extends " + Joiner.on('&').join(upperBounds);
            return Types.newArtificialTypeVariable(WildcardCapturer.class, name, upperBounds);
        }

        private WildcardCapturer forTypeVariable(final TypeVariable<?> typeParam) {
            return new WildcardCapturer(this.id){

                @Override
                TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
                    LinkedHashSet<Type> combined = new LinkedHashSet<Type>(Arrays.asList(upperBounds));
                    combined.addAll(Arrays.asList(typeParam.getBounds()));
                    if (combined.size() > 1) {
                        combined.remove(Object.class);
                    }
                    return super.captureAsTypeVariable(combined.toArray(new Type[0]));
                }
            };
        }

        private WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.id);
        }

        private Type captureNullable(@Nullable Type type) {
            if (type == null) {
                return null;
            }
            return this.capture(type);
        }
    }

    private static final class TypeMappingIntrospector
    extends TypeVisitor {
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            Preconditions.checkNotNull(contextType);
            TypeMappingIntrospector introspector = new TypeMappingIntrospector();
            introspector.visit(contextType);
            return ImmutableMap.copyOf(introspector.mappings);
        }

        @Override
        void visitClass(Class<?> clazz) {
            this.visit(clazz.getGenericSuperclass());
            this.visit(clazz.getGenericInterfaces());
        }

        @Override
        void visitParameterizedType(ParameterizedType parameterizedType) {
            Type[] typeArgs;
            Class rawClass = (Class)parameterizedType.getRawType();
            TypeVariable<Class<T>>[] vars = rawClass.getTypeParameters();
            Preconditions.checkState(vars.length == (typeArgs = parameterizedType.getActualTypeArguments()).length);
            for (int i = 0; i < vars.length; ++i) {
                this.map(new TypeVariableKey(vars[i]), typeArgs[i]);
            }
            this.visit(rawClass);
            this.visit(parameterizedType.getOwnerType());
        }

        @Override
        void visitTypeVariable(TypeVariable<?> t) {
            this.visit(t.getBounds());
        }

        @Override
        void visitWildcardType(WildcardType t) {
            this.visit(t.getUpperBounds());
        }

        private void map(TypeVariableKey var, Type arg) {
            if (this.mappings.containsKey(var)) {
                return;
            }
            Type t = arg;
            while (t != null) {
                if (var.equalsType(t)) {
                    Type x = arg;
                    while (x != null) {
                        x = this.mappings.remove(TypeVariableKey.forLookup(x));
                    }
                    return;
                }
                t = this.mappings.get(TypeVariableKey.forLookup(t));
            }
            this.mappings.put(var, arg);
        }
    }

    private static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        TypeTable() {
            this.map = ImmutableMap.of();
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> map) {
            this.map = map;
        }

        final TypeTable where(Map<TypeVariableKey, ? extends Type> mappings) {
            ImmutableMap.Builder<TypeVariableKey, Type> builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Map.Entry<TypeVariableKey, ? extends Type> mapping : mappings.entrySet()) {
                Type type;
                TypeVariableKey variable = mapping.getKey();
                Preconditions.checkArgument(!variable.equalsType(type = mapping.getValue()), "Type variable %s bound to itself", (Object)variable);
                builder.put(variable, type);
            }
            return new TypeTable(builder.build());
        }

        final Type resolve(final TypeVariable<?> var) {
            final TypeTable unguarded = this;
            TypeTable guarded = new TypeTable(){

                @Override
                public Type resolveInternal(TypeVariable<?> intermediateVar, TypeTable forDependent) {
                    if (intermediateVar.getGenericDeclaration().equals(var.getGenericDeclaration())) {
                        return intermediateVar;
                    }
                    return unguarded.resolveInternal(intermediateVar, forDependent);
                }
            };
            return this.resolveInternal(var, guarded);
        }

        Type resolveInternal(TypeVariable<?> var, TypeTable forDependants) {
            Type type = this.map.get(new TypeVariableKey(var));
            if (type == null) {
                Object[] bounds = var.getBounds();
                if (bounds.length == 0) {
                    return var;
                }
                Object[] resolvedBounds = new TypeResolver(forDependants).resolveTypes((Type[])bounds);
                if (Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY && Arrays.equals(bounds, resolvedBounds)) {
                    return var;
                }
                return Types.newArtificialTypeVariable(var.getGenericDeclaration(), var.getName(), (Type[])resolvedBounds);
            }
            return new TypeResolver(forDependants).resolveType(type);
        }
    }
}

