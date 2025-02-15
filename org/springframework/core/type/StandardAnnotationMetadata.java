/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardClassMetadata;
import org.springframework.core.type.StandardMethodMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

public class StandardAnnotationMetadata
extends StandardClassMetadata
implements AnnotationMetadata {
    private final MergedAnnotations mergedAnnotations;
    private final boolean nestedAnnotationsAsMap;
    @Nullable
    private Set<String> annotationTypes;

    @Deprecated
    public StandardAnnotationMetadata(Class<?> introspectedClass) {
        this(introspectedClass, false);
    }

    @Deprecated
    public StandardAnnotationMetadata(Class<?> introspectedClass, boolean nestedAnnotationsAsMap) {
        super(introspectedClass);
        this.mergedAnnotations = MergedAnnotations.from(introspectedClass, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, RepeatableContainers.none());
        this.nestedAnnotationsAsMap = nestedAnnotationsAsMap;
    }

    @Override
    public MergedAnnotations getAnnotations() {
        return this.mergedAnnotations;
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
    @Nullable
    public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        if (this.nestedAnnotationsAsMap) {
            return AnnotationMetadata.super.getAnnotationAttributes(annotationName, classValuesAsString);
        }
        return AnnotatedElementUtils.getMergedAnnotationAttributes(this.getIntrospectedClass(), annotationName, classValuesAsString, false);
    }

    @Override
    @Nullable
    public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        if (this.nestedAnnotationsAsMap) {
            return AnnotationMetadata.super.getAllAnnotationAttributes(annotationName, classValuesAsString);
        }
        return AnnotatedElementUtils.getAllAnnotationAttributes(this.getIntrospectedClass(), annotationName, classValuesAsString, false);
    }

    @Override
    public boolean hasAnnotatedMethods(String annotationName) {
        if (AnnotationUtils.isCandidateClass(this.getIntrospectedClass(), annotationName)) {
            try {
                Method[] methods;
                for (Method method : methods = ReflectionUtils.getDeclaredMethods(this.getIntrospectedClass())) {
                    if (!StandardAnnotationMetadata.isAnnotatedMethod(method, annotationName)) continue;
                    return true;
                }
            } catch (Throwable ex) {
                throw new IllegalStateException("Failed to introspect annotated methods on " + this.getIntrospectedClass(), ex);
            }
        }
        return false;
    }

    @Override
    public Set<MethodMetadata> getAnnotatedMethods(String annotationName) {
        Set<MethodMetadata> annotatedMethods = null;
        if (AnnotationUtils.isCandidateClass(this.getIntrospectedClass(), annotationName)) {
            try {
                Method[] methods;
                for (Method method : methods = ReflectionUtils.getDeclaredMethods(this.getIntrospectedClass())) {
                    if (!StandardAnnotationMetadata.isAnnotatedMethod(method, annotationName)) continue;
                    if (annotatedMethods == null) {
                        annotatedMethods = new LinkedHashSet<MethodMetadata>(4);
                    }
                    annotatedMethods.add(new StandardMethodMetadata(method, this.nestedAnnotationsAsMap));
                }
            } catch (Throwable ex) {
                throw new IllegalStateException("Failed to introspect annotated methods on " + this.getIntrospectedClass(), ex);
            }
        }
        return annotatedMethods != null ? annotatedMethods : Collections.emptySet();
    }

    private static boolean isAnnotatedMethod(Method method, String annotationName) {
        return !method.isBridge() && method.getAnnotations().length > 0 && AnnotatedElementUtils.isAnnotated((AnnotatedElement)method, annotationName);
    }

    static AnnotationMetadata from(Class<?> introspectedClass) {
        return new StandardAnnotationMetadata(introspectedClass, true);
    }
}

