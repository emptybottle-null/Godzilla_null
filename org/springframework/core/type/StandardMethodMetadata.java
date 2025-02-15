/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.type.MethodMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

public class StandardMethodMetadata
implements MethodMetadata {
    private final Method introspectedMethod;
    private final boolean nestedAnnotationsAsMap;
    private final MergedAnnotations mergedAnnotations;

    @Deprecated
    public StandardMethodMetadata(Method introspectedMethod) {
        this(introspectedMethod, false);
    }

    @Deprecated
    public StandardMethodMetadata(Method introspectedMethod, boolean nestedAnnotationsAsMap) {
        Assert.notNull((Object)introspectedMethod, "Method must not be null");
        this.introspectedMethod = introspectedMethod;
        this.nestedAnnotationsAsMap = nestedAnnotationsAsMap;
        this.mergedAnnotations = MergedAnnotations.from(introspectedMethod, MergedAnnotations.SearchStrategy.DIRECT, RepeatableContainers.none());
    }

    @Override
    public MergedAnnotations getAnnotations() {
        return this.mergedAnnotations;
    }

    public final Method getIntrospectedMethod() {
        return this.introspectedMethod;
    }

    @Override
    public String getMethodName() {
        return this.introspectedMethod.getName();
    }

    @Override
    public String getDeclaringClassName() {
        return this.introspectedMethod.getDeclaringClass().getName();
    }

    @Override
    public String getReturnTypeName() {
        return this.introspectedMethod.getReturnType().getName();
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(this.introspectedMethod.getModifiers());
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.introspectedMethod.getModifiers());
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(this.introspectedMethod.getModifiers());
    }

    @Override
    public boolean isOverridable() {
        return !this.isStatic() && !this.isFinal() && !this.isPrivate();
    }

    private boolean isPrivate() {
        return Modifier.isPrivate(this.introspectedMethod.getModifiers());
    }

    @Override
    @Nullable
    public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        if (this.nestedAnnotationsAsMap) {
            return MethodMetadata.super.getAnnotationAttributes(annotationName, classValuesAsString);
        }
        return AnnotatedElementUtils.getMergedAnnotationAttributes(this.introspectedMethod, annotationName, classValuesAsString, false);
    }

    @Override
    @Nullable
    public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        if (this.nestedAnnotationsAsMap) {
            return MethodMetadata.super.getAllAnnotationAttributes(annotationName, classValuesAsString);
        }
        return AnnotatedElementUtils.getAllAnnotationAttributes(this.introspectedMethod, annotationName, classValuesAsString, false);
    }

    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof StandardMethodMetadata && this.introspectedMethod.equals(((StandardMethodMetadata)obj).introspectedMethod);
    }

    public int hashCode() {
        return this.introspectedMethod.hashCode();
    }

    public String toString() {
        return this.introspectedMethod.toString();
    }
}

