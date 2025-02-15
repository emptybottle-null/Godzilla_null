/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.convert.Property;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class TypeDescriptor
implements Serializable {
    private static final Annotation[] EMPTY_ANNOTATION_ARRAY = new Annotation[0];
    private static final Map<Class<?>, TypeDescriptor> commonTypesCache = new HashMap(32);
    private static final Class<?>[] CACHED_COMMON_TYPES;
    private final Class<?> type;
    private final ResolvableType resolvableType;
    private final AnnotatedElementAdapter annotatedElement;

    public TypeDescriptor(MethodParameter methodParameter) {
        this.resolvableType = ResolvableType.forMethodParameter(methodParameter);
        this.type = this.resolvableType.resolve(methodParameter.getNestedParameterType());
        this.annotatedElement = new AnnotatedElementAdapter(methodParameter.getParameterIndex() == -1 ? methodParameter.getMethodAnnotations() : methodParameter.getParameterAnnotations());
    }

    public TypeDescriptor(Field field) {
        this.resolvableType = ResolvableType.forField(field);
        this.type = this.resolvableType.resolve(field.getType());
        this.annotatedElement = new AnnotatedElementAdapter(field.getAnnotations());
    }

    public TypeDescriptor(Property property) {
        Assert.notNull((Object)property, "Property must not be null");
        this.resolvableType = ResolvableType.forMethodParameter(property.getMethodParameter());
        this.type = this.resolvableType.resolve(property.getType());
        this.annotatedElement = new AnnotatedElementAdapter(property.getAnnotations());
    }

    public TypeDescriptor(ResolvableType resolvableType, @Nullable Class<?> type, @Nullable Annotation[] annotations) {
        this.resolvableType = resolvableType;
        this.type = type != null ? type : resolvableType.toClass();
        this.annotatedElement = new AnnotatedElementAdapter(annotations);
    }

    public Class<?> getObjectType() {
        return ClassUtils.resolvePrimitiveIfNecessary(this.getType());
    }

    public Class<?> getType() {
        return this.type;
    }

    public ResolvableType getResolvableType() {
        return this.resolvableType;
    }

    public Object getSource() {
        return this.resolvableType.getSource();
    }

    public TypeDescriptor narrow(@Nullable Object value) {
        if (value == null) {
            return this;
        }
        ResolvableType narrowed = ResolvableType.forType(value.getClass(), this.getResolvableType());
        return new TypeDescriptor(narrowed, value.getClass(), this.getAnnotations());
    }

    @Nullable
    public TypeDescriptor upcast(@Nullable Class<?> superType) {
        if (superType == null) {
            return null;
        }
        Assert.isAssignable(superType, this.getType());
        return new TypeDescriptor(this.getResolvableType().as(superType), superType, this.getAnnotations());
    }

    public String getName() {
        return ClassUtils.getQualifiedName(this.getType());
    }

    public boolean isPrimitive() {
        return this.getType().isPrimitive();
    }

    public Annotation[] getAnnotations() {
        return this.annotatedElement.getAnnotations();
    }

    public boolean hasAnnotation(Class<? extends Annotation> annotationType) {
        if (this.annotatedElement.isEmpty()) {
            return false;
        }
        return AnnotatedElementUtils.isAnnotated((AnnotatedElement)this.annotatedElement, annotationType);
    }

    @Nullable
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        if (this.annotatedElement.isEmpty()) {
            return null;
        }
        return AnnotatedElementUtils.getMergedAnnotation(this.annotatedElement, annotationType);
    }

    public boolean isAssignableTo(TypeDescriptor typeDescriptor) {
        boolean typesAssignable = typeDescriptor.getObjectType().isAssignableFrom(this.getObjectType());
        if (!typesAssignable) {
            return false;
        }
        if (this.isArray() && typeDescriptor.isArray()) {
            return this.isNestedAssignable(this.getElementTypeDescriptor(), typeDescriptor.getElementTypeDescriptor());
        }
        if (this.isCollection() && typeDescriptor.isCollection()) {
            return this.isNestedAssignable(this.getElementTypeDescriptor(), typeDescriptor.getElementTypeDescriptor());
        }
        if (this.isMap() && typeDescriptor.isMap()) {
            return this.isNestedAssignable(this.getMapKeyTypeDescriptor(), typeDescriptor.getMapKeyTypeDescriptor()) && this.isNestedAssignable(this.getMapValueTypeDescriptor(), typeDescriptor.getMapValueTypeDescriptor());
        }
        return true;
    }

    private boolean isNestedAssignable(@Nullable TypeDescriptor nestedTypeDescriptor, @Nullable TypeDescriptor otherNestedTypeDescriptor) {
        return nestedTypeDescriptor == null || otherNestedTypeDescriptor == null || nestedTypeDescriptor.isAssignableTo(otherNestedTypeDescriptor);
    }

    public boolean isCollection() {
        return Collection.class.isAssignableFrom(this.getType());
    }

    public boolean isArray() {
        return this.getType().isArray();
    }

    @Nullable
    public TypeDescriptor getElementTypeDescriptor() {
        if (this.getResolvableType().isArray()) {
            return new TypeDescriptor(this.getResolvableType().getComponentType(), null, this.getAnnotations());
        }
        if (Stream.class.isAssignableFrom(this.getType())) {
            return TypeDescriptor.getRelatedIfResolvable(this, this.getResolvableType().as(Stream.class).getGeneric(0));
        }
        return TypeDescriptor.getRelatedIfResolvable(this, this.getResolvableType().asCollection().getGeneric(0));
    }

    @Nullable
    public TypeDescriptor elementTypeDescriptor(Object element) {
        return this.narrow(element, this.getElementTypeDescriptor());
    }

    public boolean isMap() {
        return Map.class.isAssignableFrom(this.getType());
    }

    @Nullable
    public TypeDescriptor getMapKeyTypeDescriptor() {
        Assert.state(this.isMap(), "Not a [java.util.Map]");
        return TypeDescriptor.getRelatedIfResolvable(this, this.getResolvableType().asMap().getGeneric(0));
    }

    @Nullable
    public TypeDescriptor getMapKeyTypeDescriptor(Object mapKey) {
        return this.narrow(mapKey, this.getMapKeyTypeDescriptor());
    }

    @Nullable
    public TypeDescriptor getMapValueTypeDescriptor() {
        Assert.state(this.isMap(), "Not a [java.util.Map]");
        return TypeDescriptor.getRelatedIfResolvable(this, this.getResolvableType().asMap().getGeneric(1));
    }

    @Nullable
    public TypeDescriptor getMapValueTypeDescriptor(Object mapValue) {
        return this.narrow(mapValue, this.getMapValueTypeDescriptor());
    }

    @Nullable
    private TypeDescriptor narrow(@Nullable Object value, @Nullable TypeDescriptor typeDescriptor) {
        if (typeDescriptor != null) {
            return typeDescriptor.narrow(value);
        }
        if (value != null) {
            return this.narrow(value);
        }
        return null;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeDescriptor)) {
            return false;
        }
        TypeDescriptor otherDesc = (TypeDescriptor)other;
        if (this.getType() != otherDesc.getType()) {
            return false;
        }
        if (!this.annotationsMatch(otherDesc)) {
            return false;
        }
        if (this.isCollection() || this.isArray()) {
            return ObjectUtils.nullSafeEquals(this.getElementTypeDescriptor(), otherDesc.getElementTypeDescriptor());
        }
        if (this.isMap()) {
            return ObjectUtils.nullSafeEquals(this.getMapKeyTypeDescriptor(), otherDesc.getMapKeyTypeDescriptor()) && ObjectUtils.nullSafeEquals(this.getMapValueTypeDescriptor(), otherDesc.getMapValueTypeDescriptor());
        }
        return true;
    }

    private boolean annotationsMatch(TypeDescriptor otherDesc) {
        Annotation[] otherAnns;
        Annotation[] anns = this.getAnnotations();
        if (anns == (otherAnns = otherDesc.getAnnotations())) {
            return true;
        }
        if (anns.length != otherAnns.length) {
            return false;
        }
        if (anns.length > 0) {
            for (int i = 0; i < anns.length; ++i) {
                if (this.annotationEquals(anns[i], otherAnns[i])) continue;
                return false;
            }
        }
        return true;
    }

    private boolean annotationEquals(Annotation ann, Annotation otherAnn) {
        return ann == otherAnn || ann.getClass() == otherAnn.getClass() && ann.equals(otherAnn);
    }

    public int hashCode() {
        return this.getType().hashCode();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Annotation ann : this.getAnnotations()) {
            builder.append('@').append(ann.annotationType().getName()).append(' ');
        }
        builder.append(this.getResolvableType());
        return builder.toString();
    }

    @Nullable
    public static TypeDescriptor forObject(@Nullable Object source) {
        return source != null ? TypeDescriptor.valueOf(source.getClass()) : null;
    }

    public static TypeDescriptor valueOf(@Nullable Class<?> type) {
        TypeDescriptor desc;
        if (type == null) {
            type = Object.class;
        }
        return (desc = commonTypesCache.get(type)) != null ? desc : new TypeDescriptor(ResolvableType.forClass(type), null, null);
    }

    public static TypeDescriptor collection(Class<?> collectionType, @Nullable TypeDescriptor elementTypeDescriptor) {
        Assert.notNull(collectionType, "Collection type must not be null");
        if (!Collection.class.isAssignableFrom(collectionType)) {
            throw new IllegalArgumentException("Collection type must be a [java.util.Collection]");
        }
        ResolvableType element = elementTypeDescriptor != null ? elementTypeDescriptor.resolvableType : null;
        return new TypeDescriptor(ResolvableType.forClassWithGenerics(collectionType, element), null, null);
    }

    public static TypeDescriptor map(Class<?> mapType, @Nullable TypeDescriptor keyTypeDescriptor, @Nullable TypeDescriptor valueTypeDescriptor) {
        Assert.notNull(mapType, "Map type must not be null");
        if (!Map.class.isAssignableFrom(mapType)) {
            throw new IllegalArgumentException("Map type must be a [java.util.Map]");
        }
        ResolvableType key = keyTypeDescriptor != null ? keyTypeDescriptor.resolvableType : null;
        ResolvableType value = valueTypeDescriptor != null ? valueTypeDescriptor.resolvableType : null;
        return new TypeDescriptor(ResolvableType.forClassWithGenerics(mapType, key, value), null, null);
    }

    @Nullable
    public static TypeDescriptor array(@Nullable TypeDescriptor elementTypeDescriptor) {
        if (elementTypeDescriptor == null) {
            return null;
        }
        return new TypeDescriptor(ResolvableType.forArrayComponent(elementTypeDescriptor.resolvableType), null, elementTypeDescriptor.getAnnotations());
    }

    @Nullable
    public static TypeDescriptor nested(MethodParameter methodParameter, int nestingLevel) {
        if (methodParameter.getNestingLevel() != 1) {
            throw new IllegalArgumentException("MethodParameter nesting level must be 1: use the nestingLevel parameter to specify the desired nestingLevel for nested type traversal");
        }
        return TypeDescriptor.nested(new TypeDescriptor(methodParameter), nestingLevel);
    }

    @Nullable
    public static TypeDescriptor nested(Field field, int nestingLevel) {
        return TypeDescriptor.nested(new TypeDescriptor(field), nestingLevel);
    }

    @Nullable
    public static TypeDescriptor nested(Property property, int nestingLevel) {
        return TypeDescriptor.nested(new TypeDescriptor(property), nestingLevel);
    }

    @Nullable
    private static TypeDescriptor nested(TypeDescriptor typeDescriptor, int nestingLevel) {
        ResolvableType nested = typeDescriptor.resolvableType;
        for (int i = 0; i < nestingLevel; ++i) {
            if (Object.class == nested.getType()) continue;
            nested = nested.getNested(2);
        }
        if (nested == ResolvableType.NONE) {
            return null;
        }
        return TypeDescriptor.getRelatedIfResolvable(typeDescriptor, nested);
    }

    @Nullable
    private static TypeDescriptor getRelatedIfResolvable(TypeDescriptor source, ResolvableType type) {
        if (type.resolve() == null) {
            return null;
        }
        return new TypeDescriptor(type, null, source.getAnnotations());
    }

    static {
        for (Class<?> preCachedClass : CACHED_COMMON_TYPES = new Class[]{Boolean.TYPE, Boolean.class, Byte.TYPE, Byte.class, Character.TYPE, Character.class, Double.TYPE, Double.class, Float.TYPE, Float.class, Integer.TYPE, Integer.class, Long.TYPE, Long.class, Short.TYPE, Short.class, String.class, Object.class}) {
            commonTypesCache.put(preCachedClass, TypeDescriptor.valueOf(preCachedClass));
        }
    }

    private class AnnotatedElementAdapter
    implements AnnotatedElement,
    Serializable {
        @Nullable
        private final Annotation[] annotations;

        public AnnotatedElementAdapter(Annotation[] annotations) {
            this.annotations = annotations;
        }

        @Override
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            for (Annotation annotation : this.getAnnotations()) {
                if (annotation.annotationType() != annotationClass) continue;
                return true;
            }
            return false;
        }

        @Override
        @Nullable
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            for (Annotation annotation : this.getAnnotations()) {
                if (annotation.annotationType() != annotationClass) continue;
                return (T)annotation;
            }
            return null;
        }

        @Override
        public Annotation[] getAnnotations() {
            return this.annotations != null ? (Annotation[])this.annotations.clone() : EMPTY_ANNOTATION_ARRAY;
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return this.getAnnotations();
        }

        public boolean isEmpty() {
            return ObjectUtils.isEmpty(this.annotations);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || other instanceof AnnotatedElementAdapter && Arrays.equals(this.annotations, ((AnnotatedElementAdapter)other).annotations);
        }

        public int hashCode() {
            return Arrays.hashCode(this.annotations);
        }

        public String toString() {
            return TypeDescriptor.this.toString();
        }
    }
}

