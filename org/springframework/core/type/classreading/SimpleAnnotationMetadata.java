/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.lang.Nullable;

final class SimpleAnnotationMetadata
implements AnnotationMetadata {
    private final String className;
    private final int access;
    @Nullable
    private final String enclosingClassName;
    @Nullable
    private final String superClassName;
    private final boolean independentInnerClass;
    private final String[] interfaceNames;
    private final String[] memberClassNames;
    private final MethodMetadata[] annotatedMethods;
    private final MergedAnnotations annotations;
    @Nullable
    private Set<String> annotationTypes;

    SimpleAnnotationMetadata(String className, int access, @Nullable String enclosingClassName, @Nullable String superClassName, boolean independentInnerClass, String[] interfaceNames, String[] memberClassNames, MethodMetadata[] annotatedMethods, MergedAnnotations annotations) {
        this.className = className;
        this.access = access;
        this.enclosingClassName = enclosingClassName;
        this.superClassName = superClassName;
        this.independentInnerClass = independentInnerClass;
        this.interfaceNames = interfaceNames;
        this.memberClassNames = memberClassNames;
        this.annotatedMethods = annotatedMethods;
        this.annotations = annotations;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isInterface() {
        return (this.access & 0x200) != 0;
    }

    @Override
    public boolean isAnnotation() {
        return (this.access & 0x2000) != 0;
    }

    @Override
    public boolean isAbstract() {
        return (this.access & 0x400) != 0;
    }

    @Override
    public boolean isFinal() {
        return (this.access & 0x10) != 0;
    }

    @Override
    public boolean isIndependent() {
        return this.enclosingClassName == null || this.independentInnerClass;
    }

    @Override
    @Nullable
    public String getEnclosingClassName() {
        return this.enclosingClassName;
    }

    @Override
    @Nullable
    public String getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return (String[])this.interfaceNames.clone();
    }

    @Override
    public String[] getMemberClassNames() {
        return (String[])this.memberClassNames.clone();
    }

    @Override
    public Set<String> getAnnotationTypes() {
        Set<String> annotationTypes = this.annotationTypes;
        if (annotationTypes == null) {
            this.annotationTypes = annotationTypes = Collections.unmodifiableSet(AnnotationMetadata.super.getAnnotationTypes());
        }
        return annotationTypes;
    }

    @Override
    public Set<MethodMetadata> getAnnotatedMethods(String annotationName) {
        Set<MethodMetadata> annotatedMethods = null;
        for (MethodMetadata annotatedMethod : this.annotatedMethods) {
            if (!annotatedMethod.isAnnotated(annotationName)) continue;
            if (annotatedMethods == null) {
                annotatedMethods = new LinkedHashSet<MethodMetadata>(4);
            }
            annotatedMethods.add(annotatedMethod);
        }
        return annotatedMethods != null ? annotatedMethods : Collections.emptySet();
    }

    @Override
    public MergedAnnotations getAnnotations() {
        return this.annotations;
    }

    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof SimpleAnnotationMetadata && this.className.equals(((SimpleAnnotationMetadata)obj).className);
    }

    public int hashCode() {
        return this.className.hashCode();
    }

    public String toString() {
        return this.className;
    }
}

