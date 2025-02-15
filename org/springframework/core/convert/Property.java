/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public final class Property {
    private static Map<Property, Annotation[]> annotationCache = new ConcurrentReferenceHashMap<Property, Annotation[]>();
    private final Class<?> objectType;
    @Nullable
    private final Method readMethod;
    @Nullable
    private final Method writeMethod;
    private final String name;
    private final MethodParameter methodParameter;
    @Nullable
    private Annotation[] annotations;

    public Property(Class<?> objectType, @Nullable Method readMethod, @Nullable Method writeMethod) {
        this(objectType, readMethod, writeMethod, null);
    }

    public Property(Class<?> objectType, @Nullable Method readMethod, @Nullable Method writeMethod, @Nullable String name) {
        this.objectType = objectType;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
        this.methodParameter = this.resolveMethodParameter();
        this.name = name != null ? name : this.resolveName();
    }

    public Class<?> getObjectType() {
        return this.objectType;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.methodParameter.getParameterType();
    }

    @Nullable
    public Method getReadMethod() {
        return this.readMethod;
    }

    @Nullable
    public Method getWriteMethod() {
        return this.writeMethod;
    }

    MethodParameter getMethodParameter() {
        return this.methodParameter;
    }

    Annotation[] getAnnotations() {
        if (this.annotations == null) {
            this.annotations = this.resolveAnnotations();
        }
        return this.annotations;
    }

    private String resolveName() {
        if (this.readMethod != null) {
            int index = this.readMethod.getName().indexOf("get");
            index = index != -1 ? (index += 3) : ((index = this.readMethod.getName().indexOf("is")) != -1 ? (index += 2) : 0);
            return StringUtils.uncapitalize(this.readMethod.getName().substring(index));
        }
        if (this.writeMethod != null) {
            int index = this.writeMethod.getName().indexOf("set");
            if (index == -1) {
                throw new IllegalArgumentException("Not a setter method");
            }
            return StringUtils.uncapitalize(this.writeMethod.getName().substring(index += 3));
        }
        throw new IllegalStateException("Property is neither readable nor writeable");
    }

    private MethodParameter resolveMethodParameter() {
        MethodParameter read = this.resolveReadMethodParameter();
        MethodParameter write = this.resolveWriteMethodParameter();
        if (write == null) {
            if (read == null) {
                throw new IllegalStateException("Property is neither readable nor writeable");
            }
            return read;
        }
        if (read != null) {
            Class<?> readType = read.getParameterType();
            Class<?> writeType = write.getParameterType();
            if (!writeType.equals(readType) && writeType.isAssignableFrom(readType)) {
                return read;
            }
        }
        return write;
    }

    @Nullable
    private MethodParameter resolveReadMethodParameter() {
        if (this.getReadMethod() == null) {
            return null;
        }
        return new MethodParameter(this.getReadMethod(), -1).withContainingClass(this.getObjectType());
    }

    @Nullable
    private MethodParameter resolveWriteMethodParameter() {
        if (this.getWriteMethod() == null) {
            return null;
        }
        return new MethodParameter(this.getWriteMethod(), 0).withContainingClass(this.getObjectType());
    }

    private Annotation[] resolveAnnotations() {
        Annotation[] annotations = annotationCache.get(this);
        if (annotations == null) {
            LinkedHashMap<Class<? extends Annotation>, Annotation> annotationMap = new LinkedHashMap<Class<? extends Annotation>, Annotation>();
            this.addAnnotationsToMap(annotationMap, this.getReadMethod());
            this.addAnnotationsToMap(annotationMap, this.getWriteMethod());
            this.addAnnotationsToMap(annotationMap, this.getField());
            annotations = annotationMap.values().toArray(new Annotation[0]);
            annotationCache.put(this, annotations);
        }
        return annotations;
    }

    private void addAnnotationsToMap(Map<Class<? extends Annotation>, Annotation> annotationMap, @Nullable AnnotatedElement object) {
        if (object != null) {
            for (Annotation annotation : object.getAnnotations()) {
                annotationMap.put(annotation.annotationType(), annotation);
            }
        }
    }

    @Nullable
    private Field getField() {
        String name = this.getName();
        if (!StringUtils.hasLength(name)) {
            return null;
        }
        Field field = null;
        Class<?> declaringClass = this.declaringClass();
        if (declaringClass != null && (field = ReflectionUtils.findField(declaringClass, name)) == null && (field = ReflectionUtils.findField(declaringClass, StringUtils.uncapitalize(name))) == null) {
            field = ReflectionUtils.findField(declaringClass, StringUtils.capitalize(name));
        }
        return field;
    }

    @Nullable
    private Class<?> declaringClass() {
        if (this.getReadMethod() != null) {
            return this.getReadMethod().getDeclaringClass();
        }
        if (this.getWriteMethod() != null) {
            return this.getWriteMethod().getDeclaringClass();
        }
        return null;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Property)) {
            return false;
        }
        Property otherProperty = (Property)other;
        return ObjectUtils.nullSafeEquals(this.objectType, otherProperty.objectType) && ObjectUtils.nullSafeEquals(this.name, otherProperty.name) && ObjectUtils.nullSafeEquals(this.readMethod, otherProperty.readMethod) && ObjectUtils.nullSafeEquals(this.writeMethod, otherProperty.writeMethod);
    }

    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.objectType) * 31 + ObjectUtils.nullSafeHashCode(this.name);
    }
}

