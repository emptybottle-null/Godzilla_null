/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.core.annotation.AnnotationTypeMapping;
import org.springframework.core.annotation.AnnotationTypeMappings;
import org.springframework.core.annotation.IntrospectionFailureLogger;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationSelector;
import org.springframework.core.annotation.MergedAnnotationSelectors;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.TypeMappedAnnotation;
import org.springframework.core.annotation.TypeMappedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

final class MergedAnnotationsCollection
implements MergedAnnotations {
    private final MergedAnnotation<?>[] annotations;
    private final AnnotationTypeMappings[] mappings;

    private MergedAnnotationsCollection(Collection<MergedAnnotation<?>> annotations) {
        Assert.notNull(annotations, "Annotations must not be null");
        this.annotations = annotations.toArray(new MergedAnnotation[0]);
        this.mappings = new AnnotationTypeMappings[this.annotations.length];
        for (int i = 0; i < this.annotations.length; ++i) {
            MergedAnnotation<?> annotation = this.annotations[i];
            Assert.notNull(annotation, "Annotation must not be null");
            Assert.isTrue(annotation.isDirectlyPresent(), "Annotation must be directly present");
            Assert.isTrue(annotation.getAggregateIndex() == 0, "Annotation must have aggregate index of zero");
            this.mappings[i] = AnnotationTypeMappings.forAnnotationType(annotation.getType());
        }
    }

    @Override
    public Iterator<MergedAnnotation<Annotation>> iterator() {
        return Spliterators.iterator(this.spliterator());
    }

    @Override
    public Spliterator<MergedAnnotation<Annotation>> spliterator() {
        return this.spliterator(null);
    }

    private <A extends Annotation> Spliterator<MergedAnnotation<A>> spliterator(@Nullable Object annotationType) {
        return new AnnotationsSpliterator(annotationType);
    }

    @Override
    public <A extends Annotation> boolean isPresent(Class<A> annotationType) {
        return this.isPresent(annotationType, false);
    }

    @Override
    public boolean isPresent(String annotationType) {
        return this.isPresent(annotationType, false);
    }

    @Override
    public <A extends Annotation> boolean isDirectlyPresent(Class<A> annotationType) {
        return this.isPresent(annotationType, true);
    }

    @Override
    public boolean isDirectlyPresent(String annotationType) {
        return this.isPresent(annotationType, true);
    }

    private boolean isPresent(Object requiredType, boolean directOnly) {
        for (MergedAnnotation<?> annotation : this.annotations) {
            Class<?> type = annotation.getType();
            if (type != requiredType && !type.getName().equals(requiredType)) continue;
            return true;
        }
        if (!directOnly) {
            for (AnnotationTypeMappings mappings : this.mappings) {
                for (int i = 1; i < mappings.size(); ++i) {
                    AnnotationTypeMapping mapping = mappings.get(i);
                    if (!MergedAnnotationsCollection.isMappingForType(mapping, requiredType)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(Class<A> annotationType) {
        return this.get(annotationType, null, null);
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(Class<A> annotationType, @Nullable Predicate<? super MergedAnnotation<A>> predicate) {
        return this.get(annotationType, predicate, null);
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(Class<A> annotationType, @Nullable Predicate<? super MergedAnnotation<A>> predicate, @Nullable MergedAnnotationSelector<A> selector) {
        MergedAnnotation<A> result = this.find(annotationType, predicate, selector);
        return result != null ? result : MergedAnnotation.missing();
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(String annotationType) {
        return this.get(annotationType, null, null);
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(String annotationType, @Nullable Predicate<? super MergedAnnotation<A>> predicate) {
        return this.get(annotationType, predicate, null);
    }

    @Override
    public <A extends Annotation> MergedAnnotation<A> get(String annotationType, @Nullable Predicate<? super MergedAnnotation<A>> predicate, @Nullable MergedAnnotationSelector<A> selector) {
        MergedAnnotation<A> result = this.find(annotationType, predicate, selector);
        return result != null ? result : MergedAnnotation.missing();
    }

    @Nullable
    private <A extends Annotation> MergedAnnotation<A> find(Object requiredType, @Nullable Predicate<? super MergedAnnotation<A>> predicate, @Nullable MergedAnnotationSelector<A> selector) {
        if (selector == null) {
            selector = MergedAnnotationSelectors.nearest();
        }
        MergedAnnotation<?> result = null;
        for (int i = 0; i < this.annotations.length; ++i) {
            MergedAnnotation<?> root = this.annotations[i];
            AnnotationTypeMappings mappings = this.mappings[i];
            for (int mappingIndex = 0; mappingIndex < mappings.size(); ++mappingIndex) {
                MergedAnnotation<?> candidate;
                AnnotationTypeMapping mapping = mappings.get(mappingIndex);
                if (!MergedAnnotationsCollection.isMappingForType(mapping, requiredType)) continue;
                MergedAnnotation<Object> mergedAnnotation = candidate = mappingIndex == 0 ? root : TypeMappedAnnotation.createIfPossible(mapping, root, IntrospectionFailureLogger.INFO);
                if (candidate == null || predicate != null && !predicate.test(candidate)) continue;
                if (selector.isBestCandidate(candidate)) {
                    return candidate;
                }
                result = result != null ? selector.select(result, candidate) : candidate;
            }
        }
        return result;
    }

    @Override
    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(Class<A> annotationType) {
        return StreamSupport.stream(this.spliterator(annotationType), false);
    }

    @Override
    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(String annotationType) {
        return StreamSupport.stream(this.spliterator(annotationType), false);
    }

    @Override
    public Stream<MergedAnnotation<Annotation>> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    private static boolean isMappingForType(AnnotationTypeMapping mapping, @Nullable Object requiredType) {
        if (requiredType == null) {
            return true;
        }
        Class<? extends Annotation> actualType = mapping.getAnnotationType();
        return actualType == requiredType || actualType.getName().equals(requiredType);
    }

    static MergedAnnotations of(Collection<MergedAnnotation<?>> annotations) {
        Assert.notNull(annotations, "Annotations must not be null");
        if (annotations.isEmpty()) {
            return TypeMappedAnnotations.NONE;
        }
        return new MergedAnnotationsCollection(annotations);
    }

    private class AnnotationsSpliterator<A extends Annotation>
    implements Spliterator<MergedAnnotation<A>> {
        @Nullable
        private Object requiredType;
        private final int[] mappingCursors;

        public AnnotationsSpliterator(Object requiredType) {
            this.mappingCursors = new int[MergedAnnotationsCollection.this.annotations.length];
            this.requiredType = requiredType;
        }

        @Override
        public boolean tryAdvance(Consumer<? super MergedAnnotation<A>> action) {
            int lowestDistance = Integer.MAX_VALUE;
            int annotationResult = -1;
            for (int annotationIndex = 0; annotationIndex < MergedAnnotationsCollection.this.annotations.length; ++annotationIndex) {
                AnnotationTypeMapping mapping = this.getNextSuitableMapping(annotationIndex);
                if (mapping != null && mapping.getDistance() < lowestDistance) {
                    annotationResult = annotationIndex;
                    lowestDistance = mapping.getDistance();
                }
                if (lowestDistance == 0) break;
            }
            if (annotationResult != -1) {
                MergedAnnotation<A> mergedAnnotation = this.createMergedAnnotationIfPossible(annotationResult, this.mappingCursors[annotationResult]);
                int n = annotationResult;
                this.mappingCursors[n] = this.mappingCursors[n] + 1;
                if (mergedAnnotation == null) {
                    return this.tryAdvance((Consumer<? super MergedAnnotation<A>>)action);
                }
                action.accept(mergedAnnotation);
                return true;
            }
            return false;
        }

        @Nullable
        private AnnotationTypeMapping getNextSuitableMapping(int annotationIndex) {
            AnnotationTypeMapping mapping;
            do {
                if ((mapping = this.getMapping(annotationIndex, this.mappingCursors[annotationIndex])) != null && MergedAnnotationsCollection.isMappingForType(mapping, this.requiredType)) {
                    return mapping;
                }
                int n = annotationIndex;
                this.mappingCursors[n] = this.mappingCursors[n] + 1;
            } while (mapping != null);
            return null;
        }

        @Nullable
        private AnnotationTypeMapping getMapping(int annotationIndex, int mappingIndex) {
            AnnotationTypeMappings mappings = MergedAnnotationsCollection.this.mappings[annotationIndex];
            return mappingIndex < mappings.size() ? mappings.get(mappingIndex) : null;
        }

        @Nullable
        private MergedAnnotation<A> createMergedAnnotationIfPossible(int annotationIndex, int mappingIndex) {
            MergedAnnotation root = MergedAnnotationsCollection.this.annotations[annotationIndex];
            if (mappingIndex == 0) {
                return root;
            }
            IntrospectionFailureLogger logger = this.requiredType != null ? IntrospectionFailureLogger.INFO : IntrospectionFailureLogger.DEBUG;
            return TypeMappedAnnotation.createIfPossible(MergedAnnotationsCollection.this.mappings[annotationIndex].get(mappingIndex), root, logger);
        }

        @Override
        @Nullable
        public Spliterator<MergedAnnotation<A>> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            int size = 0;
            for (int i = 0; i < MergedAnnotationsCollection.this.annotations.length; ++i) {
                AnnotationTypeMappings mappings = MergedAnnotationsCollection.this.mappings[i];
                int numberOfMappings = mappings.size();
                size += (numberOfMappings -= Math.min(this.mappingCursors[i], mappings.size()));
            }
            return size;
        }

        @Override
        public int characteristics() {
            return 1280;
        }
    }
}

