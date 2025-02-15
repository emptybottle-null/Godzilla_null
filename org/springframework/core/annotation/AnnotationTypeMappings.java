/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.AnnotationTypeMapping;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.AnnotationsScanner;
import org.springframework.core.annotation.IntrospectionFailureLogger;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;

final class AnnotationTypeMappings {
    private static final IntrospectionFailureLogger failureLogger = IntrospectionFailureLogger.DEBUG;
    private static final Map<AnnotationFilter, Cache> standardRepeatablesCache = new ConcurrentReferenceHashMap<AnnotationFilter, Cache>();
    private static final Map<AnnotationFilter, Cache> noRepeatablesCache = new ConcurrentReferenceHashMap<AnnotationFilter, Cache>();
    private final RepeatableContainers repeatableContainers;
    private final AnnotationFilter filter;
    private final List<AnnotationTypeMapping> mappings;

    private AnnotationTypeMappings(RepeatableContainers repeatableContainers, AnnotationFilter filter, Class<? extends Annotation> annotationType) {
        this.repeatableContainers = repeatableContainers;
        this.filter = filter;
        this.mappings = new ArrayList<AnnotationTypeMapping>();
        this.addAllMappings(annotationType);
        this.mappings.forEach(AnnotationTypeMapping::afterAllMappingsSet);
    }

    private void addAllMappings(Class<? extends Annotation> annotationType) {
        ArrayDeque<AnnotationTypeMapping> queue = new ArrayDeque<AnnotationTypeMapping>();
        this.addIfPossible(queue, null, annotationType, null);
        while (!queue.isEmpty()) {
            AnnotationTypeMapping mapping = (AnnotationTypeMapping)queue.removeFirst();
            this.mappings.add(mapping);
            this.addMetaAnnotationsToQueue(queue, mapping);
        }
    }

    private void addMetaAnnotationsToQueue(Deque<AnnotationTypeMapping> queue, AnnotationTypeMapping source) {
        Annotation[] metaAnnotations;
        for (Annotation metaAnnotation : metaAnnotations = AnnotationsScanner.getDeclaredAnnotations(source.getAnnotationType(), false)) {
            if (!this.isMappable(source, metaAnnotation)) continue;
            Annotation[] repeatedAnnotations = this.repeatableContainers.findRepeatedAnnotations(metaAnnotation);
            if (repeatedAnnotations != null) {
                for (Annotation repeatedAnnotation : repeatedAnnotations) {
                    if (!this.isMappable(source, repeatedAnnotation)) continue;
                    this.addIfPossible(queue, source, repeatedAnnotation);
                }
                continue;
            }
            this.addIfPossible(queue, source, metaAnnotation);
        }
    }

    private void addIfPossible(Deque<AnnotationTypeMapping> queue, AnnotationTypeMapping source, Annotation ann) {
        this.addIfPossible(queue, source, ann.annotationType(), ann);
    }

    private void addIfPossible(Deque<AnnotationTypeMapping> queue, @Nullable AnnotationTypeMapping source, Class<? extends Annotation> annotationType, @Nullable Annotation ann) {
        block2: {
            try {
                queue.addLast(new AnnotationTypeMapping(source, annotationType, ann));
            } catch (Exception ex) {
                AnnotationUtils.rethrowAnnotationConfigurationException(ex);
                if (!failureLogger.isEnabled()) break block2;
                failureLogger.log("Failed to introspect meta-annotation " + annotationType.getName(), source != null ? source.getAnnotationType() : null, ex);
            }
        }
    }

    private boolean isMappable(AnnotationTypeMapping source, @Nullable Annotation metaAnnotation) {
        return metaAnnotation != null && !this.filter.matches(metaAnnotation) && !AnnotationFilter.PLAIN.matches(source.getAnnotationType()) && !this.isAlreadyMapped(source, metaAnnotation);
    }

    private boolean isAlreadyMapped(AnnotationTypeMapping source, Annotation metaAnnotation) {
        Class<? extends Annotation> annotationType = metaAnnotation.annotationType();
        for (AnnotationTypeMapping mapping = source; mapping != null; mapping = mapping.getSource()) {
            if (mapping.getAnnotationType() != annotationType) continue;
            return true;
        }
        return false;
    }

    int size() {
        return this.mappings.size();
    }

    AnnotationTypeMapping get(int index) {
        return this.mappings.get(index);
    }

    static AnnotationTypeMappings forAnnotationType(Class<? extends Annotation> annotationType) {
        return AnnotationTypeMappings.forAnnotationType(annotationType, AnnotationFilter.PLAIN);
    }

    static AnnotationTypeMappings forAnnotationType(Class<? extends Annotation> annotationType, AnnotationFilter annotationFilter) {
        return AnnotationTypeMappings.forAnnotationType(annotationType, RepeatableContainers.standardRepeatables(), annotationFilter);
    }

    static AnnotationTypeMappings forAnnotationType(Class<? extends Annotation> annotationType, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        if (repeatableContainers == RepeatableContainers.standardRepeatables()) {
            return standardRepeatablesCache.computeIfAbsent(annotationFilter, key -> new Cache(repeatableContainers, (AnnotationFilter)key)).get(annotationType);
        }
        if (repeatableContainers == RepeatableContainers.none()) {
            return noRepeatablesCache.computeIfAbsent(annotationFilter, key -> new Cache(repeatableContainers, (AnnotationFilter)key)).get(annotationType);
        }
        return new AnnotationTypeMappings(repeatableContainers, annotationFilter, annotationType);
    }

    static void clearCache() {
        standardRepeatablesCache.clear();
        noRepeatablesCache.clear();
    }

    private static class Cache {
        private final RepeatableContainers repeatableContainers;
        private final AnnotationFilter filter;
        private final Map<Class<? extends Annotation>, AnnotationTypeMappings> mappings;

        Cache(RepeatableContainers repeatableContainers, AnnotationFilter filter) {
            this.repeatableContainers = repeatableContainers;
            this.filter = filter;
            this.mappings = new ConcurrentReferenceHashMap<Class<? extends Annotation>, AnnotationTypeMappings>();
        }

        AnnotationTypeMappings get(Class<? extends Annotation> annotationType) {
            return this.mappings.computeIfAbsent(annotationType, this::createMappings);
        }

        AnnotationTypeMappings createMappings(Class<? extends Annotation> annotationType) {
            return new AnnotationTypeMappings(this.repeatableContainers, this.filter, annotationType);
        }
    }
}

