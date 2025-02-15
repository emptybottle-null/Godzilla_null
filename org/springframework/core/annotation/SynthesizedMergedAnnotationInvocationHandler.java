/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.core.annotation.AttributeMethods;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

final class SynthesizedMergedAnnotationInvocationHandler<A extends Annotation>
implements InvocationHandler {
    private final MergedAnnotation<?> annotation;
    private final Class<A> type;
    private final AttributeMethods attributes;
    private final Map<String, Object> valueCache = new ConcurrentHashMap<String, Object>(8);
    @Nullable
    private volatile Integer hashCode;
    @Nullable
    private volatile String string;

    private SynthesizedMergedAnnotationInvocationHandler(MergedAnnotation<A> annotation, Class<A> type) {
        Assert.notNull(annotation, "MergedAnnotation must not be null");
        Assert.notNull(type, "Type must not be null");
        Assert.isTrue(type.isAnnotation(), "Type must be an annotation");
        this.annotation = annotation;
        this.type = type;
        this.attributes = AttributeMethods.forAnnotationType(type);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (ReflectionUtils.isEqualsMethod(method)) {
            return this.annotationEquals(args[0]);
        }
        if (ReflectionUtils.isHashCodeMethod(method)) {
            return this.annotationHashCode();
        }
        if (ReflectionUtils.isToStringMethod(method)) {
            return this.annotationToString();
        }
        if (this.isAnnotationTypeMethod(method)) {
            return this.type;
        }
        if (this.attributes.indexOf(method.getName()) != -1) {
            return this.getAttributeValue(method);
        }
        throw new AnnotationConfigurationException(String.format("Method [%s] is unsupported for synthesized annotation type [%s]", method, this.type));
    }

    private boolean isAnnotationTypeMethod(Method method) {
        return method.getName().equals("annotationType") && method.getParameterCount() == 0;
    }

    private boolean annotationEquals(Object other) {
        if (this == other) {
            return true;
        }
        if (!this.type.isInstance(other)) {
            return false;
        }
        for (int i = 0; i < this.attributes.size(); ++i) {
            Object otherValue;
            Method attribute = this.attributes.get(i);
            Object thisValue = this.getAttributeValue(attribute);
            if (ObjectUtils.nullSafeEquals(thisValue, otherValue = ReflectionUtils.invokeMethod(attribute, other))) continue;
            return false;
        }
        return true;
    }

    private int annotationHashCode() {
        Integer hashCode = this.hashCode;
        if (hashCode == null) {
            this.hashCode = hashCode = this.computeHashCode();
        }
        return hashCode;
    }

    private Integer computeHashCode() {
        int hashCode = 0;
        for (int i = 0; i < this.attributes.size(); ++i) {
            Method attribute = this.attributes.get(i);
            Object value = this.getAttributeValue(attribute);
            hashCode += 127 * attribute.getName().hashCode() ^ this.getValueHashCode(value);
        }
        return hashCode;
    }

    private int getValueHashCode(Object value) {
        if (value instanceof boolean[]) {
            return Arrays.hashCode((boolean[])value);
        }
        if (value instanceof byte[]) {
            return Arrays.hashCode((byte[])value);
        }
        if (value instanceof char[]) {
            return Arrays.hashCode((char[])value);
        }
        if (value instanceof double[]) {
            return Arrays.hashCode((double[])value);
        }
        if (value instanceof float[]) {
            return Arrays.hashCode((float[])value);
        }
        if (value instanceof int[]) {
            return Arrays.hashCode((int[])value);
        }
        if (value instanceof long[]) {
            return Arrays.hashCode((long[])value);
        }
        if (value instanceof short[]) {
            return Arrays.hashCode((short[])value);
        }
        if (value instanceof Object[]) {
            return Arrays.hashCode((Object[])value);
        }
        return value.hashCode();
    }

    private String annotationToString() {
        String string = this.string;
        if (string == null) {
            StringBuilder builder = new StringBuilder("@").append(this.type.getName()).append('(');
            for (int i = 0; i < this.attributes.size(); ++i) {
                Method attribute = this.attributes.get(i);
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(attribute.getName());
                builder.append('=');
                builder.append(this.toString(this.getAttributeValue(attribute)));
            }
            builder.append(')');
            this.string = string = builder.toString();
        }
        return string;
    }

    private String toString(Object value) {
        if (value instanceof Class) {
            return ((Class)value).getName();
        }
        if (value.getClass().isArray()) {
            StringBuilder builder = new StringBuilder("[");
            for (int i = 0; i < Array.getLength(value); ++i) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(this.toString(Array.get(value, i)));
            }
            builder.append(']');
            return builder.toString();
        }
        return String.valueOf(value);
    }

    private Object getAttributeValue(Method method) {
        Object value = this.valueCache.computeIfAbsent(method.getName(), attributeName -> {
            Class<?> type = ClassUtils.resolvePrimitiveIfNecessary(method.getReturnType());
            return this.annotation.getValue((String)attributeName, type).orElseThrow(() -> new NoSuchElementException("No value found for attribute named '" + attributeName + "' in merged annotation " + this.annotation.getType().getName()));
        });
        if (value.getClass().isArray() && Array.getLength(value) > 0) {
            value = this.cloneArray(value);
        }
        return value;
    }

    private Object cloneArray(Object array) {
        if (array instanceof boolean[]) {
            return ((boolean[])array).clone();
        }
        if (array instanceof byte[]) {
            return ((byte[])array).clone();
        }
        if (array instanceof char[]) {
            return ((char[])array).clone();
        }
        if (array instanceof double[]) {
            return ((double[])array).clone();
        }
        if (array instanceof float[]) {
            return ((float[])array).clone();
        }
        if (array instanceof int[]) {
            return ((int[])array).clone();
        }
        if (array instanceof long[]) {
            return ((long[])array).clone();
        }
        if (array instanceof short[]) {
            return ((short[])array).clone();
        }
        return ((Object[])array).clone();
    }

    static <A extends Annotation> A createProxy(MergedAnnotation<A> annotation, Class<A> type) {
        Class[] classArray;
        ClassLoader classLoader = type.getClassLoader();
        SynthesizedMergedAnnotationInvocationHandler<A> handler = new SynthesizedMergedAnnotationInvocationHandler<A>(annotation, type);
        if (SynthesizedMergedAnnotationInvocationHandler.isVisible(classLoader, SynthesizedAnnotation.class)) {
            Class[] classArray2 = new Class[2];
            classArray2[0] = type;
            classArray = classArray2;
            classArray2[1] = SynthesizedAnnotation.class;
        } else {
            Class[] classArray3 = new Class[1];
            classArray = classArray3;
            classArray3[0] = type;
        }
        Class[] interfaces = classArray;
        return (A)((Annotation)Proxy.newProxyInstance(classLoader, interfaces, handler));
    }

    private static boolean isVisible(ClassLoader classLoader, Class<?> interfaceClass) {
        if (classLoader == interfaceClass.getClassLoader()) {
            return true;
        }
        try {
            return Class.forName(interfaceClass.getName(), false, classLoader) == interfaceClass;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}

