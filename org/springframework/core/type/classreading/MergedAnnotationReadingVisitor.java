/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

class MergedAnnotationReadingVisitor<A extends Annotation>
extends AnnotationVisitor {
    @Nullable
    private final ClassLoader classLoader;
    @Nullable
    private final Object source;
    private final Class<A> annotationType;
    private final Consumer<MergedAnnotation<A>> consumer;
    private final Map<String, Object> attributes = new LinkedHashMap<String, Object>(4);

    public MergedAnnotationReadingVisitor(@Nullable ClassLoader classLoader, @Nullable Object source, Class<A> annotationType, Consumer<MergedAnnotation<A>> consumer) {
        super(0x10A0000);
        this.classLoader = classLoader;
        this.source = source;
        this.annotationType = annotationType;
        this.consumer = consumer;
    }

    @Override
    public void visit(String name, Object value) {
        if (value instanceof Type) {
            value = ((Type)value).getClassName();
        }
        this.attributes.put(name, value);
    }

    @Override
    public void visitEnum(String name, String descriptor, String value) {
        this.visitEnum(descriptor, value, (E enumValue) -> this.attributes.put(name, enumValue));
    }

    @Override
    @Nullable
    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        return this.visitAnnotation(descriptor, (MergedAnnotation<T> annotation) -> this.attributes.put(name, annotation));
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        return new ArrayVisitor(value -> this.attributes.put(name, value));
    }

    @Override
    public void visitEnd() {
        MergedAnnotation<A> annotation = MergedAnnotation.of(this.classLoader, this.source, this.annotationType, this.attributes);
        this.consumer.accept(annotation);
    }

    public <E extends Enum<E>> void visitEnum(String descriptor, String value, Consumer<E> consumer) {
        String className = Type.getType(descriptor).getClassName();
        Class<?> type = ClassUtils.resolveClassName(className, this.classLoader);
        consumer.accept(Enum.valueOf(type, value));
    }

    @Nullable
    private <T extends Annotation> AnnotationVisitor visitAnnotation(String descriptor, Consumer<MergedAnnotation<T>> consumer) {
        String className = Type.getType(descriptor).getClassName();
        if (AnnotationFilter.PLAIN.matches(className)) {
            return null;
        }
        Class<?> type = ClassUtils.resolveClassName(className, this.classLoader);
        return new MergedAnnotationReadingVisitor(this.classLoader, this.source, type, consumer);
    }

    @Nullable
    static <A extends Annotation> AnnotationVisitor get(@Nullable ClassLoader classLoader, @Nullable Object source, String descriptor, boolean visible, Consumer<MergedAnnotation<A>> consumer) {
        if (!visible) {
            return null;
        }
        String typeName = Type.getType(descriptor).getClassName();
        if (AnnotationFilter.PLAIN.matches(typeName)) {
            return null;
        }
        try {
            Class<?> annotationType = ClassUtils.forName(typeName, classLoader);
            return new MergedAnnotationReadingVisitor(classLoader, source, annotationType, consumer);
        } catch (ClassNotFoundException | LinkageError ex) {
            return null;
        }
    }

    private class ArrayVisitor
    extends AnnotationVisitor {
        private final List<Object> elements;
        private final Consumer<Object[]> consumer;

        ArrayVisitor(Consumer<Object[]> consumer) {
            super(0x10A0000);
            this.elements = new ArrayList<Object>();
            this.consumer = consumer;
        }

        @Override
        public void visit(String name, Object value) {
            if (value instanceof Type) {
                value = ((Type)value).getClassName();
            }
            this.elements.add(value);
        }

        @Override
        public void visitEnum(String name, String descriptor, String value) {
            MergedAnnotationReadingVisitor.this.visitEnum(descriptor, value, this.elements::add);
        }

        @Override
        @Nullable
        public AnnotationVisitor visitAnnotation(String name, String descriptor) {
            return MergedAnnotationReadingVisitor.this.visitAnnotation(descriptor, this.elements::add);
        }

        @Override
        public void visitEnd() {
            Class<?> componentType = this.getComponentType();
            Object[] array = (Object[])Array.newInstance(componentType, this.elements.size());
            this.consumer.accept(this.elements.toArray(array));
        }

        private Class<?> getComponentType() {
            if (this.elements.isEmpty()) {
                return Object.class;
            }
            Object firstElement = this.elements.get(0);
            if (firstElement instanceof Enum) {
                return ((Enum)firstElement).getDeclaringClass();
            }
            return firstElement.getClass();
        }
    }
}

