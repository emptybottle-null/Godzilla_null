/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import java.util.Map;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotationSelectors;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

public interface AnnotatedTypeMetadata {
    public MergedAnnotations getAnnotations();

    default public boolean isAnnotated(String annotationName) {
        return this.getAnnotations().isPresent(annotationName);
    }

    @Nullable
    default public Map<String, Object> getAnnotationAttributes(String annotationName) {
        return this.getAnnotationAttributes(annotationName, false);
    }

    @Nullable
    default public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        MergedAnnotation annotation = this.getAnnotations().get(annotationName, null, MergedAnnotationSelectors.firstDirectlyDeclared());
        if (!annotation.isPresent()) {
            return null;
        }
        return annotation.asAnnotationAttributes(MergedAnnotation.Adapt.values(classValuesAsString, true));
    }

    @Nullable
    default public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName) {
        return this.getAllAnnotationAttributes(annotationName, false);
    }

    @Nullable
    default public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        MergedAnnotation.Adapt[] adaptations = MergedAnnotation.Adapt.values(classValuesAsString, true);
        return this.getAnnotations().stream(annotationName).filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes)).map(MergedAnnotation::withNonMergedAttributes).collect(MergedAnnotationCollectors.toMultiValueMap(map -> map.isEmpty() ? null : map, adaptations));
    }
}

