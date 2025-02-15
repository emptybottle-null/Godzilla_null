/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeCapture;
import com.google.common.reflect.TypeVisitor;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.qual.Nullable;

final class Types {
    private static final Function<Type, String> TYPE_NAME = new Function<Type, String>(){

        @Override
        public String apply(Type from) {
            return JavaVersion.CURRENT.typeName(from);
        }
    };
    private static final Joiner COMMA_JOINER = Joiner.on(", ").useForNull("null");

    static Type newArrayType(Type componentType) {
        if (componentType instanceof WildcardType) {
            WildcardType wildcard = (WildcardType)componentType;
            Type[] lowerBounds = wildcard.getLowerBounds();
            Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
            if (lowerBounds.length == 1) {
                return Types.supertypeOf(Types.newArrayType(lowerBounds[0]));
            }
            Type[] upperBounds = wildcard.getUpperBounds();
            Preconditions.checkArgument(upperBounds.length == 1, "Wildcard should have only one upper bound.");
            return Types.subtypeOf(Types.newArrayType(upperBounds[0]));
        }
        return JavaVersion.CURRENT.newArrayType(componentType);
    }

    static ParameterizedType newParameterizedTypeWithOwner(@Nullable Type ownerType, Class<?> rawType, Type ... arguments) {
        if (ownerType == null) {
            return Types.newParameterizedType(rawType, arguments);
        }
        Preconditions.checkNotNull(arguments);
        Preconditions.checkArgument(rawType.getEnclosingClass() != null, "Owner type for unenclosed %s", rawType);
        return new ParameterizedTypeImpl(ownerType, rawType, arguments);
    }

    static ParameterizedType newParameterizedType(Class<?> rawType, Type ... arguments) {
        return new ParameterizedTypeImpl(ClassOwnership.JVM_BEHAVIOR.getOwnerType(rawType), rawType, arguments);
    }

    static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(D declaration, String name, Type ... bounds) {
        Type[] typeArray;
        if (bounds.length == 0) {
            Type[] typeArray2 = new Type[1];
            typeArray = typeArray2;
            typeArray2[0] = Object.class;
        } else {
            typeArray = bounds;
        }
        return Types.newTypeVariableImpl(declaration, name, typeArray);
    }

    @VisibleForTesting
    static WildcardType subtypeOf(Type upperBound) {
        return new WildcardTypeImpl(new Type[0], new Type[]{upperBound});
    }

    @VisibleForTesting
    static WildcardType supertypeOf(Type lowerBound) {
        return new WildcardTypeImpl(new Type[]{lowerBound}, new Type[]{Object.class});
    }

    static String toString(Type type) {
        return type instanceof Class ? ((Class)type).getName() : type.toString();
    }

    static @Nullable Type getComponentType(Type type) {
        Preconditions.checkNotNull(type);
        final AtomicReference result = new AtomicReference();
        new TypeVisitor(){

            @Override
            void visitTypeVariable(TypeVariable<?> t) {
                result.set(Types.subtypeOfComponentType(t.getBounds()));
            }

            @Override
            void visitWildcardType(WildcardType t) {
                result.set(Types.subtypeOfComponentType(t.getUpperBounds()));
            }

            @Override
            void visitGenericArrayType(GenericArrayType t) {
                result.set(t.getGenericComponentType());
            }

            @Override
            void visitClass(Class<?> t) {
                result.set(t.getComponentType());
            }
        }.visit(type);
        return (Type)result.get();
    }

    private static @Nullable Type subtypeOfComponentType(Type[] bounds) {
        for (Type bound : bounds) {
            Class componentClass;
            Type componentType = Types.getComponentType(bound);
            if (componentType == null) continue;
            if (componentType instanceof Class && (componentClass = (Class)componentType).isPrimitive()) {
                return componentClass;
            }
            return Types.subtypeOf(componentType);
        }
        return null;
    }

    private static <D extends GenericDeclaration> TypeVariable<D> newTypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
        TypeVariableImpl<D> typeVariableImpl = new TypeVariableImpl<D>(genericDeclaration, name, bounds);
        TypeVariable typeVariable = Reflection.newProxy(TypeVariable.class, new TypeVariableInvocationHandler(typeVariableImpl));
        return typeVariable;
    }

    private static Type[] toArray(Collection<Type> types) {
        return types.toArray(new Type[types.size()]);
    }

    private static Iterable<Type> filterUpperBounds(Iterable<Type> bounds) {
        return Iterables.filter(bounds, Predicates.not(Predicates.equalTo(Object.class)));
    }

    private static void disallowPrimitiveType(Type[] types, String usedAs) {
        for (Type type : types) {
            if (!(type instanceof Class)) continue;
            Class cls = (Class)type;
            Preconditions.checkArgument(!cls.isPrimitive(), "Primitive type '%s' used as %s", (Object)cls, (Object)usedAs);
        }
    }

    static Class<?> getArrayClass(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }

    private Types() {
    }

    static final class NativeTypeVariableEquals<X> {
        static final boolean NATIVE_TYPE_VARIABLE_ONLY = !NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(NativeTypeVariableEquals.class, "X", new Type[0]));

        NativeTypeVariableEquals() {
        }
    }

    static enum JavaVersion {
        JAVA6{

            @Override
            GenericArrayType newArrayType(Type componentType) {
                return new GenericArrayTypeImpl(componentType);
            }

            @Override
            Type usedInGenericType(Type type) {
                Class cls;
                Preconditions.checkNotNull(type);
                if (type instanceof Class && (cls = (Class)type).isArray()) {
                    return new GenericArrayTypeImpl(cls.getComponentType());
                }
                return type;
            }
        }
        ,
        JAVA7{

            @Override
            Type newArrayType(Type componentType) {
                if (componentType instanceof Class) {
                    return Types.getArrayClass((Class)componentType);
                }
                return new GenericArrayTypeImpl(componentType);
            }

            @Override
            Type usedInGenericType(Type type) {
                return Preconditions.checkNotNull(type);
            }
        }
        ,
        JAVA8{

            @Override
            Type newArrayType(Type componentType) {
                return JAVA7.newArrayType(componentType);
            }

            @Override
            Type usedInGenericType(Type type) {
                return JAVA7.usedInGenericType(type);
            }

            @Override
            String typeName(Type type) {
                try {
                    Method getTypeName = Type.class.getMethod("getTypeName", new Class[0]);
                    return (String)getTypeName.invoke(type, new Object[0]);
                } catch (NoSuchMethodException e) {
                    throw new AssertionError((Object)"Type.getTypeName should be available in Java 8");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ,
        JAVA9{

            @Override
            Type newArrayType(Type componentType) {
                return JAVA8.newArrayType(componentType);
            }

            @Override
            Type usedInGenericType(Type type) {
                return JAVA8.usedInGenericType(type);
            }

            @Override
            String typeName(Type type) {
                return JAVA8.typeName(type);
            }

            @Override
            boolean jdkTypeDuplicatesOwnerName() {
                return false;
            }
        };

        static final JavaVersion CURRENT;

        abstract Type newArrayType(Type var1);

        abstract Type usedInGenericType(Type var1);

        final ImmutableList<Type> usedInGenericType(Type[] types) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Type type : types) {
                builder.add(this.usedInGenericType(type));
            }
            return builder.build();
        }

        String typeName(Type type) {
            return Types.toString(type);
        }

        boolean jdkTypeDuplicatesOwnerName() {
            return true;
        }

        static {
            CURRENT = AnnotatedElement.class.isAssignableFrom(TypeVariable.class) ? (new TypeCapture<Map.Entry<String, int[][]>>(){}.capture().toString().contains("java.util.Map.java.util.Map") ? JAVA8 : JAVA9) : (new TypeCapture<int[]>(){}.capture() instanceof Class ? JAVA7 : JAVA6);
        }
    }

    static final class WildcardTypeImpl
    implements WildcardType,
    Serializable {
        private final ImmutableList<Type> lowerBounds;
        private final ImmutableList<Type> upperBounds;
        private static final long serialVersionUID = 0L;

        WildcardTypeImpl(Type[] lowerBounds, Type[] upperBounds) {
            Types.disallowPrimitiveType(lowerBounds, "lower bound for wildcard");
            Types.disallowPrimitiveType(upperBounds, "upper bound for wildcard");
            this.lowerBounds = JavaVersion.CURRENT.usedInGenericType(lowerBounds);
            this.upperBounds = JavaVersion.CURRENT.usedInGenericType(upperBounds);
        }

        @Override
        public Type[] getLowerBounds() {
            return Types.toArray(this.lowerBounds);
        }

        @Override
        public Type[] getUpperBounds() {
            return Types.toArray(this.upperBounds);
        }

        public boolean equals(Object obj) {
            if (obj instanceof WildcardType) {
                WildcardType that = (WildcardType)obj;
                return this.lowerBounds.equals(Arrays.asList(that.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(that.getUpperBounds()));
            }
            return false;
        }

        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("?");
            for (Type lowerBound : this.lowerBounds) {
                builder.append(" super ").append(JavaVersion.CURRENT.typeName(lowerBound));
            }
            for (Type upperBound : Types.filterUpperBounds(this.upperBounds)) {
                builder.append(" extends ").append(JavaVersion.CURRENT.typeName(upperBound));
            }
            return builder.toString();
        }
    }

    private static final class TypeVariableImpl<D extends GenericDeclaration> {
        private final D genericDeclaration;
        private final String name;
        private final ImmutableList<Type> bounds;

        TypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
            Types.disallowPrimitiveType(bounds, "bound for type variable");
            this.genericDeclaration = (GenericDeclaration)Preconditions.checkNotNull(genericDeclaration);
            this.name = Preconditions.checkNotNull(name);
            this.bounds = ImmutableList.copyOf(bounds);
        }

        public Type[] getBounds() {
            return Types.toArray(this.bounds);
        }

        public D getGenericDeclaration() {
            return this.genericDeclaration;
        }

        public String getName() {
            return this.name;
        }

        public String getTypeName() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }

        public int hashCode() {
            return this.genericDeclaration.hashCode() ^ this.name.hashCode();
        }

        public boolean equals(Object obj) {
            if (NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
                if (obj != null && Proxy.isProxyClass(obj.getClass()) && Proxy.getInvocationHandler(obj) instanceof TypeVariableInvocationHandler) {
                    TypeVariableInvocationHandler typeVariableInvocationHandler = (TypeVariableInvocationHandler)Proxy.getInvocationHandler(obj);
                    TypeVariableImpl that = typeVariableInvocationHandler.typeVariableImpl;
                    return this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()) && this.bounds.equals(that.bounds);
                }
                return false;
            }
            if (obj instanceof TypeVariable) {
                TypeVariable that = (TypeVariable)obj;
                return this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration());
            }
            return false;
        }
    }

    private static final class TypeVariableInvocationHandler
    implements InvocationHandler {
        private static final ImmutableMap<String, Method> typeVariableMethods;
        private final TypeVariableImpl<?> typeVariableImpl;

        TypeVariableInvocationHandler(TypeVariableImpl<?> typeVariableImpl) {
            this.typeVariableImpl = typeVariableImpl;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Method typeVariableMethod = typeVariableMethods.get(methodName);
            if (typeVariableMethod == null) {
                throw new UnsupportedOperationException(methodName);
            }
            try {
                return typeVariableMethod.invoke(this.typeVariableImpl, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }

        static {
            ImmutableMap.Builder<String, Method> builder = ImmutableMap.builder();
            for (Method method : TypeVariableImpl.class.getMethods()) {
                if (!method.getDeclaringClass().equals(TypeVariableImpl.class)) continue;
                try {
                    method.setAccessible(true);
                } catch (AccessControlException accessControlException) {
                    // empty catch block
                }
                builder.put(method.getName(), method);
            }
            typeVariableMethods = builder.build();
        }
    }

    private static final class ParameterizedTypeImpl
    implements ParameterizedType,
    Serializable {
        private final @Nullable Type ownerType;
        private final ImmutableList<Type> argumentsList;
        private final Class<?> rawType;
        private static final long serialVersionUID = 0L;

        ParameterizedTypeImpl(@Nullable Type ownerType, Class<?> rawType, Type[] typeArguments) {
            Preconditions.checkNotNull(rawType);
            Preconditions.checkArgument(typeArguments.length == rawType.getTypeParameters().length);
            Types.disallowPrimitiveType(typeArguments, "type parameter");
            this.ownerType = ownerType;
            this.rawType = rawType;
            this.argumentsList = JavaVersion.CURRENT.usedInGenericType(typeArguments);
        }

        @Override
        public Type[] getActualTypeArguments() {
            return Types.toArray(this.argumentsList);
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public Type getOwnerType() {
            return this.ownerType;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (this.ownerType != null && JavaVersion.CURRENT.jdkTypeDuplicatesOwnerName()) {
                builder.append(JavaVersion.CURRENT.typeName(this.ownerType)).append('.');
            }
            return builder.append(this.rawType.getName()).append('<').append(COMMA_JOINER.join(Iterables.transform(this.argumentsList, TYPE_NAME))).append('>').toString();
        }

        public int hashCode() {
            return (this.ownerType == null ? 0 : this.ownerType.hashCode()) ^ this.argumentsList.hashCode() ^ this.rawType.hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType that = (ParameterizedType)other;
            return this.getRawType().equals(that.getRawType()) && Objects.equal(this.getOwnerType(), that.getOwnerType()) && Arrays.equals(this.getActualTypeArguments(), that.getActualTypeArguments());
        }
    }

    private static final class GenericArrayTypeImpl
    implements GenericArrayType,
    Serializable {
        private final Type componentType;
        private static final long serialVersionUID = 0L;

        GenericArrayTypeImpl(Type componentType) {
            this.componentType = JavaVersion.CURRENT.usedInGenericType(componentType);
        }

        @Override
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public String toString() {
            return Types.toString(this.componentType) + "[]";
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                GenericArrayType that = (GenericArrayType)obj;
                return Objects.equal(this.getGenericComponentType(), that.getGenericComponentType());
            }
            return false;
        }
    }

    private static enum ClassOwnership {
        OWNED_BY_ENCLOSING_CLASS{

            @Override
            @Nullable Class<?> getOwnerType(Class<?> rawType) {
                return rawType.getEnclosingClass();
            }
        }
        ,
        LOCAL_CLASS_HAS_NO_OWNER{

            @Override
            @Nullable Class<?> getOwnerType(Class<?> rawType) {
                if (rawType.isLocalClass()) {
                    return null;
                }
                return rawType.getEnclosingClass();
            }
        };

        static final ClassOwnership JVM_BEHAVIOR;

        abstract @Nullable Class<?> getOwnerType(Class<?> var1);

        private static ClassOwnership detectJvmBehavior() {
            class LocalClass<T> {
                LocalClass() {
                }
            }
            Class<?> subclass = new LocalClass<String>(){
                {
                }
            }.getClass();
            ParameterizedType parameterizedType = (ParameterizedType)subclass.getGenericSuperclass();
            for (ClassOwnership behavior : ClassOwnership.values()) {
                if (behavior.getOwnerType(LocalClass.class) != parameterizedType.getOwnerType()) continue;
                return behavior;
            }
            throw new AssertionError();
        }

        static {
            JVM_BEHAVIOR = ClassOwnership.detectJvmBehavior();
        }
    }
}

