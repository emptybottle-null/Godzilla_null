/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.AnnotationsScanner;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotationSelectors;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

public abstract class AnnotatedElementUtils {
    public static AnnotatedElement forAnnotations(Annotation ... annotations) {
        return new AnnotatedElementForAnnotations(annotations);
    }

    public static Set<String> getMetaAnnotationTypes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        return AnnotatedElementUtils.getMetaAnnotationTypes(element, element.getAnnotation(annotationType));
    }

    public static Set<String> getMetaAnnotationTypes(AnnotatedElement element, String annotationName) {
        for (Annotation annotation : element.getAnnotations()) {
            if (!annotation.annotationType().getName().equals(annotationName)) continue;
            return AnnotatedElementUtils.getMetaAnnotationTypes(element, annotation);
        }
        return Collections.emptySet();
    }

    private static Set<String> getMetaAnnotationTypes(AnnotatedElement element, @Nullable Annotation annotation) {
        if (annotation == null) {
            return Collections.emptySet();
        }
        return AnnotatedElementUtils.getAnnotations(annotation.annotationType()).stream().map(mergedAnnotation -> mergedAnnotation.getType().getName()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static boolean hasMetaAnnotationTypes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        return AnnotatedElementUtils.getAnnotations(element).stream(annotationType).anyMatch(MergedAnnotation::isMetaPresent);
    }

    public static boolean hasMetaAnnotationTypes(AnnotatedElement element, String annotationName) {
        return AnnotatedElementUtils.getAnnotations(element).stream(annotationName).anyMatch(MergedAnnotation::isMetaPresent);
    }

    public static boolean isAnnotated(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(element)) {
            return element.isAnnotationPresent(annotationType);
        }
        return AnnotatedElementUtils.getAnnotations(element).isPresent(annotationType);
    }

    public static boolean isAnnotated(AnnotatedElement element, String annotationName) {
        return AnnotatedElementUtils.getAnnotations(element).isPresent(annotationName);
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        MergedAnnotation<? extends Annotation> mergedAnnotation = AnnotatedElementUtils.getAnnotations(element).get(annotationType, null, MergedAnnotationSelectors.firstDirectlyDeclared());
        return AnnotatedElementUtils.getAnnotationAttributes(mergedAnnotation, false, false);
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, String annotationName) {
        return AnnotatedElementUtils.getMergedAnnotationAttributes(element, annotationName, false, false);
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element, String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        MergedAnnotation mergedAnnotation = AnnotatedElementUtils.getAnnotations(element).get(annotationName, null, MergedAnnotationSelectors.firstDirectlyDeclared());
        return AnnotatedElementUtils.getAnnotationAttributes(mergedAnnotation, classValuesAsString, nestedAnnotationsAsMap);
    }

    @Nullable
    public static <A extends Annotation> A getMergedAnnotation(AnnotatedElement element, Class<A> annotationType) {
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(element)) {
            return element.getDeclaredAnnotation(annotationType);
        }
        return (A)((Annotation)AnnotatedElementUtils.getAnnotations(element).get(annotationType, null, MergedAnnotationSelectors.firstDirectlyDeclared()).synthesize(MergedAnnotation::isPresent).orElse(null));
    }

    public static <A extends Annotation> Set<A> getAllMergedAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return AnnotatedElementUtils.getAnnotations(element).stream(annotationType).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    public static Set<Annotation> getAllMergedAnnotations(AnnotatedElement element, Set<Class<? extends Annotation>> annotationTypes) {
        return AnnotatedElementUtils.getAnnotations(element).stream().filter(MergedAnnotationPredicates.typeIn(annotationTypes)).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    public static <A extends Annotation> Set<A> getMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return AnnotatedElementUtils.getMergedRepeatableAnnotations(element, annotationType, null);
    }

    public static <A extends Annotation> Set<A> getMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType, @Nullable Class<? extends Annotation> containerType) {
        return AnnotatedElementUtils.getRepeatableAnnotations(element, containerType, annotationType).stream(annotationType).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    @Nullable
    public static MultiValueMap<String, Object> getAllAnnotationAttributes(AnnotatedElement element, String annotationName) {
        return AnnotatedElementUtils.getAllAnnotationAttributes(element, annotationName, false, false);
    }

    @Nullable
    public static MultiValueMap<String, Object> getAllAnnotationAttributes(AnnotatedElement element, String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        MergedAnnotation.Adapt[] adaptations = MergedAnnotation.Adapt.values(classValuesAsString, nestedAnnotationsAsMap);
        return AnnotatedElementUtils.getAnnotations(element).stream(annotationName).filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes)).map(MergedAnnotation::withNonMergedAttributes).collect(MergedAnnotationCollectors.toMultiValueMap(AnnotatedElementUtils::nullIfEmpty, adaptations));
    }

    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(element)) {
            return element.isAnnotationPresent(annotationType);
        }
        return AnnotatedElementUtils.findAnnotations(element).isPresent(annotationType);
    }

    @Nullable
    public static AnnotationAttributes findMergedAnnotationAttributes(AnnotatedElement element, Class<? extends Annotation> annotationType, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        MergedAnnotation<? extends Annotation> mergedAnnotation = AnnotatedElementUtils.findAnnotations(element).get(annotationType, null, MergedAnnotationSelectors.firstDirectlyDeclared());
        return AnnotatedElementUtils.getAnnotationAttributes(mergedAnnotation, classValuesAsString, nestedAnnotationsAsMap);
    }

    @Nullable
    public static AnnotationAttributes findMergedAnnotationAttributes(AnnotatedElement element, String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        MergedAnnotation mergedAnnotation = AnnotatedElementUtils.findAnnotations(element).get(annotationName, null, MergedAnnotationSelectors.firstDirectlyDeclared());
        return AnnotatedElementUtils.getAnnotationAttributes(mergedAnnotation, classValuesAsString, nestedAnnotationsAsMap);
    }

    @Nullable
    public static <A extends Annotation> A findMergedAnnotation(AnnotatedElement element, Class<A> annotationType) {
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(element)) {
            return element.getDeclaredAnnotation(annotationType);
        }
        return (A)((Annotation)AnnotatedElementUtils.findAnnotations(element).get(annotationType, null, MergedAnnotationSelectors.firstDirectlyDeclared()).synthesize(MergedAnnotation::isPresent).orElse(null));
    }

    public static <A extends Annotation> Set<A> findAllMergedAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return AnnotatedElementUtils.findAnnotations(element).stream(annotationType).sorted(AnnotatedElementUtils.highAggregateIndexesFirst()).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    public static Set<Annotation> findAllMergedAnnotations(AnnotatedElement element, Set<Class<? extends Annotation>> annotationTypes) {
        return AnnotatedElementUtils.findAnnotations(element).stream().filter(MergedAnnotationPredicates.typeIn(annotationTypes)).sorted(AnnotatedElementUtils.highAggregateIndexesFirst()).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    public static <A extends Annotation> Set<A> findMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType) {
        return AnnotatedElementUtils.findMergedRepeatableAnnotations(element, annotationType, null);
    }

    public static <A extends Annotation> Set<A> findMergedRepeatableAnnotations(AnnotatedElement element, Class<A> annotationType, @Nullable Class<? extends Annotation> containerType) {
        return AnnotatedElementUtils.findRepeatableAnnotations(element, containerType, annotationType).stream(annotationType).sorted(AnnotatedElementUtils.highAggregateIndexesFirst()).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    private static MergedAnnotations getAnnotations(AnnotatedElement element) {
        return MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, RepeatableContainers.none());
    }

    private static MergedAnnotations getRepeatableAnnotations(AnnotatedElement element, @Nullable Class<? extends Annotation> containerType, Class<? extends Annotation> annotationType) {
        RepeatableContainers repeatableContainers = RepeatableContainers.of(annotationType, containerType);
        return MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, repeatableContainers);
    }

    private static MergedAnnotations findAnnotations(AnnotatedElement element) {
        return MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY, RepeatableContainers.none());
    }

    private static MergedAnnotations findRepeatableAnnotations(AnnotatedElement element, @Nullable Class<? extends Annotation> containerType, Class<? extends Annotation> annotationType) {
        RepeatableContainers repeatableContainers = RepeatableContainers.of(annotationType, containerType);
        return MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY, repeatableContainers);
    }

    @Nullable
    private static MultiValueMap<String, Object> nullIfEmpty(MultiValueMap<String, Object> map) {
        return map.isEmpty() ? null : map;
    }

    private static <A extends Annotation> Comparator<MergedAnnotation<A>> highAggregateIndexesFirst() {
        return Comparator.comparingInt(MergedAnnotation::getAggregateIndex).reversed();
    }

    @Nullable
    private static AnnotationAttributes getAnnotationAttributes(MergedAnnotation<?> annotation, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        if (!annotation.isPresent()) {
            return null;
        }
        return annotation.asAnnotationAttributes(MergedAnnotation.Adapt.values(classValuesAsString, nestedAnnotationsAsMap));
    }

    private static class AnnotatedElementForAnnotations
    implements AnnotatedElement {
        private final Annotation[] annotations;

        AnnotatedElementForAnnotations(Annotation ... annotations) {
            this.annotations = annotations;
        }

        @Override
        @Nullable
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            for (Annotation annotation : this.annotations) {
                if (annotation.annotationType() != annotationClass) continue;
                return (T)annotation;
            }
            return null;
        }

        @Override
        public Annotation[] getAnnotations() {
            return (Annotation[])this.annotations.clone();
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return (Annotation[])this.annotations.clone();
        }
    }
}

