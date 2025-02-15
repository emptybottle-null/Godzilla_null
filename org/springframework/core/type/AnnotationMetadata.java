/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

public interface AnnotationMetadata
extends ClassMetadata,
AnnotatedTypeMetadata {
    default public Set<String> getAnnotationTypes() {
        return this.getAnnotations().stream().filter(MergedAnnotation::isDirectlyPresent).map(annotation -> annotation.getType().getName()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public Set<String> getMetaAnnotationTypes(String annotationName) {
        MergedAnnotation annotation = this.getAnnotations().get(annotationName, MergedAnnotation::isDirectlyPresent);
        if (!annotation.isPresent()) {
            return Collections.emptySet();
        }
        return MergedAnnotations.from(annotation.getType(), MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS).stream().map(mergedAnnotation -> mergedAnnotation.getType().getName()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public boolean hasAnnotation(String annotationName) {
        return this.getAnnotations().isDirectlyPresent(annotationName);
    }

    default public boolean hasMetaAnnotation(String metaAnnotationName) {
        return this.getAnnotations().get(metaAnnotationName, MergedAnnotation::isMetaPresent).isPresent();
    }

    default public boolean hasAnnotatedMethods(String annotationName) {
        return !this.getAnnotatedMethods(annotationName).isEmpty();
    }

    public Set<MethodMetadata> getAnnotatedMethods(String var1);

    public static AnnotationMetadata introspect(Class<?> type) {
        return StandardAnnotationMetadata.from(type);
    }
}

