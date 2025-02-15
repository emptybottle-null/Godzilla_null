/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class Parameter
implements AnnotatedElement {
    private final Invokable<?, ?> declaration;
    private final int position;
    private final TypeToken<?> type;
    private final ImmutableList<Annotation> annotations;
    private final AnnotatedType annotatedType;

    Parameter(Invokable<?, ?> declaration, int position, TypeToken<?> type, Annotation[] annotations, AnnotatedType annotatedType) {
        this.declaration = declaration;
        this.position = position;
        this.type = type;
        this.annotations = ImmutableList.copyOf(annotations);
        this.annotatedType = annotatedType;
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return this.getAnnotation((Class)annotationType) != null;
    }

    public <A extends Annotation> @Nullable A getAnnotation(Class<A> annotationType) {
        Preconditions.checkNotNull(annotationType);
        for (Annotation annotation : this.annotations) {
            if (!annotationType.isInstance(annotation)) continue;
            return (A)((Annotation)annotationType.cast(annotation));
        }
        return null;
    }

    @Override
    public Annotation[] getAnnotations() {
        return this.getDeclaredAnnotations();
    }

    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
        return this.getDeclaredAnnotationsByType(annotationType);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return this.annotations.toArray(new Annotation[this.annotations.size()]);
    }

    public <A extends Annotation> @Nullable A getDeclaredAnnotation(Class<A> annotationType) {
        Preconditions.checkNotNull(annotationType);
        return (A)((Annotation)FluentIterable.from(this.annotations).filter(annotationType).first().orNull());
    }

    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationType) {
        return (Annotation[])FluentIterable.from(this.annotations).filter(annotationType).toArray(annotationType);
    }

    public AnnotatedType getAnnotatedType() {
        return this.annotatedType;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Parameter) {
            Parameter that = (Parameter)obj;
            return this.position == that.position && this.declaration.equals(that.declaration);
        }
        return false;
    }

    public int hashCode() {
        return this.position;
    }

    public String toString() {
        return this.type + " arg" + this.position;
    }
}

