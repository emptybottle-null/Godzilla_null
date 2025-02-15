/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.introspector;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.introspector.GenericProperty;
import org.yaml.snakeyaml.util.ArrayUtils;

public class MethodProperty
extends GenericProperty {
    private final PropertyDescriptor property;
    private final boolean readable;
    private final boolean writable;

    private static Type discoverGenericType(PropertyDescriptor property) {
        Type[] paramTypes;
        Method readMethod = property.getReadMethod();
        if (readMethod != null) {
            return readMethod.getGenericReturnType();
        }
        Method writeMethod = property.getWriteMethod();
        if (writeMethod != null && (paramTypes = writeMethod.getGenericParameterTypes()).length > 0) {
            return paramTypes[0];
        }
        return null;
    }

    public MethodProperty(PropertyDescriptor property) {
        super(property.getName(), property.getPropertyType(), MethodProperty.discoverGenericType(property));
        this.property = property;
        this.readable = property.getReadMethod() != null;
        this.writable = property.getWriteMethod() != null;
    }

    @Override
    public void set(Object object, Object value) throws Exception {
        if (!this.writable) {
            throw new YAMLException("No writable property '" + this.getName() + "' on class: " + object.getClass().getName());
        }
        this.property.getWriteMethod().invoke(object, value);
    }

    @Override
    public Object get(Object object) {
        try {
            this.property.getReadMethod().setAccessible(true);
            return this.property.getReadMethod().invoke(object, new Object[0]);
        } catch (Exception e) {
            throw new YAMLException("Unable to find getter for property '" + this.property.getName() + "' on object " + object + ":" + e);
        }
    }

    @Override
    public List<Annotation> getAnnotations() {
        List<Annotation> annotations = this.isReadable() && this.isWritable() ? ArrayUtils.toUnmodifiableCompositeList(this.property.getReadMethod().getAnnotations(), this.property.getWriteMethod().getAnnotations()) : (this.isReadable() ? ArrayUtils.toUnmodifiableList(this.property.getReadMethod().getAnnotations()) : ArrayUtils.toUnmodifiableList(this.property.getWriteMethod().getAnnotations()));
        return annotations;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        A annotation = null;
        if (this.isReadable()) {
            annotation = this.property.getReadMethod().getAnnotation(annotationType);
        }
        if (annotation == null && this.isWritable()) {
            annotation = this.property.getWriteMethod().getAnnotation(annotationType);
        }
        return annotation;
    }

    @Override
    public boolean isWritable() {
        return this.writable;
    }

    @Override
    public boolean isReadable() {
        return this.readable;
    }
}

