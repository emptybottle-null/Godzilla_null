/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.AnnotationTypeMapping;
import org.springframework.core.annotation.AnnotationTypeMappings;
import org.springframework.core.annotation.AnnotationsProcessor;
import org.springframework.core.annotation.AnnotationsScanner;
import org.springframework.core.annotation.IntrospectionFailureLogger;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationSelector;
import org.springframework.core.annotation.MergedAnnotationSelectors;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.annotation.TypeMappedAnnotation;
import org.springframework.lang.Nullable;

final class TypeMappedAnnotations
implements MergedAnnotations {
    static final MergedAnnotations NONE = new TypeMappedAnnotations(null, new Annotation[0], RepeatableContainers.none(), AnnotationFilter.ALL);
    @Nullable
    private final Object source;
    @Nullable
    private final AnnotatedElement element;
    @Nullable
    private final MergedAnnotations.SearchStrategy searchStrategy;
    @Nullable
    private final Annotation[] annotations;
    private final RepeatableContainers repeatableContainers;
    private final AnnotationFilter annotationFilter;
    @Nullable
    private volatile List<Aggregate> aggregates;

    private TypeMappedAnnotations(AnnotatedElement element, MergedAnnotations.SearchStrategy searchStrategy, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        this.source = element;
        this.element = element;
        this.searchStrategy = searchStrategy;
        this.annotations = null;
        this.repeatableContainers = repeatableContainers;
        this.annotationFilter = annotationFilter;
    }

    private TypeMappedAnnotations(@Nullable Object source, Annotation[] annotations, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        this.source = source;
        this.element = null;
        this.searchStrategy = null;
        this.annotations = annotations;
        this.repeatableContainers = repeatableContainers;
        this.annotationFilter = annotationFilter;
    }

    @Override
    public <A extends Annotation> boolean isPresent(Class<A> annotationType) {
        if (this.annotationFilter.matches(annotationType)) {
            return false;
        }
        return Boolean.TRUE.equals(this.scan(annotationType, IsPresent.get(this.repeatableContainers, this.annotationFilter, false)));
    }

    @Override
    public boolean isPresent(String annotationType) {
        if (this.annotationFilter.matches(annotationType)) {
            return false;
        }
        return Boolean.TRUE.equals(this.scan(annotationType, IsPresent.get(this.repeatableContainers, this.annotationFilter, false)));
    }

    @Override
    public <A extends Annotation> boolean isDirectlyPresent(Class<A> annotationType) {
        if (this.annotationFilter.matches(annotationType)) {
            return false;
        }
        return Boolean.TRUE.equals(this.scan(annotationType, IsPresent.get(this.repeatableContainers, this.annotationFilter, true)));
    }

    @Override
    public boolean isDirectlyPresent(String annotationType) {
        if (this.annotationFilter.matches(annotationType)) {
            return false;
        }
        return Boolean.TRUE.equals(this.scan(annotationType, IsPresent.get(this.repeatableContainers, this.annotationFilter, true)));
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
        if (this.annotationFilter.matches(annotationType)) {
            return MergedAnnotation.missing();
        }
        MergedAnnotation result = (MergedAnnotation)this.scan(annotationType, new MergedAnnotationFinder<A>(annotationType, predicate, selector));
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
        if (this.annotationFilter.matches(annotationType)) {
            return MergedAnnotation.missing();
        }
        MergedAnnotation result = (MergedAnnotation)this.scan(annotationType, new MergedAnnotationFinder<A>(annotationType, predicate, selector));
        return result != null ? result : MergedAnnotation.missing();
    }

    @Override
    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(Class<A> annotationType) {
        if (this.annotationFilter == AnnotationFilter.ALL) {
            return Stream.empty();
        }
        return StreamSupport.stream(this.spliterator(annotationType), false);
    }

    @Override
    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(String annotationType) {
        if (this.annotationFilter == AnnotationFilter.ALL) {
            return Stream.empty();
        }
        return StreamSupport.stream(this.spliterator(annotationType), false);
    }

    @Override
    public Stream<MergedAnnotation<Annotation>> stream() {
        if (this.annotationFilter == AnnotationFilter.ALL) {
            return Stream.empty();
        }
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public Iterator<MergedAnnotation<Annotation>> iterator() {
        if (this.annotationFilter == AnnotationFilter.ALL) {
            return Collections.emptyIterator();
        }
        return Spliterators.iterator(this.spliterator());
    }

    @Override
    public Spliterator<MergedAnnotation<Annotation>> spliterator() {
        if (this.annotationFilter == AnnotationFilter.ALL) {
            return Spliterators.emptySpliterator();
        }
        return this.spliterator(null);
    }

    private <A extends Annotation> Spliterator<MergedAnnotation<A>> spliterator(@Nullable Object annotationType) {
        return new AggregatesSpliterator(annotationType, this.getAggregates());
    }

    private List<Aggregate> getAggregates() {
        List<Aggregate> aggregates = this.aggregates;
        if (aggregates == null) {
            aggregates = this.scan(this, new AggregatesCollector());
            if (aggregates == null || aggregates.isEmpty()) {
                aggregates = Collections.emptyList();
            }
            this.aggregates = aggregates;
        }
        return aggregates;
    }

    @Nullable
    private <C, R> R scan(C criteria, AnnotationsProcessor<C, R> processor) {
        if (this.annotations != null) {
            R result = processor.doWithAnnotations(criteria, 0, this.source, this.annotations);
            return processor.finish(result);
        }
        if (this.element != null && this.searchStrategy != null) {
            return AnnotationsScanner.scan(criteria, this.element, this.searchStrategy, processor);
        }
        return null;
    }

    static MergedAnnotations from(AnnotatedElement element, MergedAnnotations.SearchStrategy searchStrategy, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        if (AnnotationsScanner.isKnownEmpty(element, searchStrategy)) {
            return NONE;
        }
        return new TypeMappedAnnotations(element, searchStrategy, repeatableContainers, annotationFilter);
    }

    static MergedAnnotations from(@Nullable Object source, Annotation[] annotations, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        if (annotations.length == 0) {
            return NONE;
        }
        return new TypeMappedAnnotations(source, annotations, repeatableContainers, annotationFilter);
    }

    private static boolean isMappingForType(AnnotationTypeMapping mapping, AnnotationFilter annotationFilter, @Nullable Object requiredType) {
        Class<? extends Annotation> actualType = mapping.getAnnotationType();
        return !annotationFilter.matches(actualType) && (requiredType == null || actualType == requiredType || actualType.getName().equals(requiredType));
    }

    private class AggregatesSpliterator<A extends Annotation>
    implements Spliterator<MergedAnnotation<A>> {
        @Nullable
        private final Object requiredType;
        private final List<Aggregate> aggregates;
        private int aggregateCursor;
        @Nullable
        private int[] mappingCursors;

        AggregatesSpliterator(Object requiredType, List<Aggregate> aggregates) {
            this.requiredType = requiredType;
            this.aggregates = aggregates;
            this.aggregateCursor = 0;
        }

        @Override
        public boolean tryAdvance(Consumer<? super MergedAnnotation<A>> action) {
            while (this.aggregateCursor < this.aggregates.size()) {
                Aggregate aggregate = this.aggregates.get(this.aggregateCursor);
                if (this.tryAdvance(aggregate, action)) {
                    return true;
                }
                ++this.aggregateCursor;
                this.mappingCursors = null;
            }
            return false;
        }

        private boolean tryAdvance(Aggregate aggregate, Consumer<? super MergedAnnotation<A>> action) {
            if (this.mappingCursors == null) {
                this.mappingCursors = new int[aggregate.size()];
            }
            int lowestDistance = Integer.MAX_VALUE;
            int annotationResult = -1;
            for (int annotationIndex = 0; annotationIndex < aggregate.size(); ++annotationIndex) {
                AnnotationTypeMapping mapping = this.getNextSuitableMapping(aggregate, annotationIndex);
                if (mapping != null && mapping.getDistance() < lowestDistance) {
                    annotationResult = annotationIndex;
                    lowestDistance = mapping.getDistance();
                }
                if (lowestDistance == 0) break;
            }
            if (annotationResult != -1) {
                MergedAnnotation mergedAnnotation = aggregate.createMergedAnnotationIfPossible(annotationResult, this.mappingCursors[annotationResult], this.requiredType != null ? IntrospectionFailureLogger.INFO : IntrospectionFailureLogger.DEBUG);
                int n = annotationResult;
                this.mappingCursors[n] = this.mappingCursors[n] + 1;
                if (mergedAnnotation == null) {
                    return this.tryAdvance(aggregate, action);
                }
                action.accept(mergedAnnotation);
                return true;
            }
            return false;
        }

        @Nullable
        private AnnotationTypeMapping getNextSuitableMapping(Aggregate aggregate, int annotationIndex) {
            int[] cursors = this.mappingCursors;
            if (cursors != null) {
                AnnotationTypeMapping mapping;
                do {
                    if ((mapping = aggregate.getMapping(annotationIndex, cursors[annotationIndex])) != null && TypeMappedAnnotations.isMappingForType(mapping, TypeMappedAnnotations.this.annotationFilter, this.requiredType)) {
                        return mapping;
                    }
                    int n = annotationIndex;
                    cursors[n] = cursors[n] + 1;
                } while (mapping != null);
            }
            return null;
        }

        @Override
        @Nullable
        public Spliterator<MergedAnnotation<A>> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            int size = 0;
            for (int aggregateIndex = this.aggregateCursor; aggregateIndex < this.aggregates.size(); ++aggregateIndex) {
                Aggregate aggregate = this.aggregates.get(aggregateIndex);
                for (int annotationIndex = 0; annotationIndex < aggregate.size(); ++annotationIndex) {
                    AnnotationTypeMappings mappings = aggregate.getMappings(annotationIndex);
                    int numberOfMappings = mappings.size();
                    if (aggregateIndex == this.aggregateCursor && this.mappingCursors != null) {
                        numberOfMappings -= Math.min(this.mappingCursors[annotationIndex], mappings.size());
                    }
                    size += numberOfMappings;
                }
            }
            return size;
        }

        @Override
        public int characteristics() {
            return 1280;
        }
    }

    private static class Aggregate {
        private final int aggregateIndex;
        @Nullable
        private final Object source;
        private final List<Annotation> annotations;
        private final AnnotationTypeMappings[] mappings;

        Aggregate(int aggregateIndex, @Nullable Object source, List<Annotation> annotations) {
            this.aggregateIndex = aggregateIndex;
            this.source = source;
            this.annotations = annotations;
            this.mappings = new AnnotationTypeMappings[annotations.size()];
            for (int i = 0; i < annotations.size(); ++i) {
                this.mappings[i] = AnnotationTypeMappings.forAnnotationType(annotations.get(i).annotationType());
            }
        }

        int size() {
            return this.annotations.size();
        }

        @Nullable
        AnnotationTypeMapping getMapping(int annotationIndex, int mappingIndex) {
            AnnotationTypeMappings mappings = this.getMappings(annotationIndex);
            return mappingIndex < mappings.size() ? mappings.get(mappingIndex) : null;
        }

        AnnotationTypeMappings getMappings(int annotationIndex) {
            return this.mappings[annotationIndex];
        }

        @Nullable
        <A extends Annotation> MergedAnnotation<A> createMergedAnnotationIfPossible(int annotationIndex, int mappingIndex, IntrospectionFailureLogger logger) {
            return TypeMappedAnnotation.createIfPossible(this.mappings[annotationIndex].get(mappingIndex), this.source, this.annotations.get(annotationIndex), this.aggregateIndex, logger);
        }
    }

    private class AggregatesCollector
    implements AnnotationsProcessor<Object, List<Aggregate>> {
        private final List<Aggregate> aggregates = new ArrayList<Aggregate>();

        private AggregatesCollector() {
        }

        @Override
        @Nullable
        public List<Aggregate> doWithAnnotations(Object criteria, int aggregateIndex, @Nullable Object source, Annotation[] annotations) {
            this.aggregates.add(this.createAggregate(aggregateIndex, source, annotations));
            return null;
        }

        private Aggregate createAggregate(int aggregateIndex, @Nullable Object source, Annotation[] annotations) {
            List<Annotation> aggregateAnnotations = this.getAggregateAnnotations(annotations);
            return new Aggregate(aggregateIndex, source, aggregateAnnotations);
        }

        private List<Annotation> getAggregateAnnotations(Annotation[] annotations) {
            ArrayList<Annotation> result = new ArrayList<Annotation>(annotations.length);
            this.addAggregateAnnotations(result, annotations);
            return result;
        }

        private void addAggregateAnnotations(List<Annotation> aggregateAnnotations, Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                if (annotation == null || TypeMappedAnnotations.this.annotationFilter.matches(annotation)) continue;
                Annotation[] repeatedAnnotations = TypeMappedAnnotations.this.repeatableContainers.findRepeatedAnnotations(annotation);
                if (repeatedAnnotations != null) {
                    this.addAggregateAnnotations(aggregateAnnotations, repeatedAnnotations);
                    continue;
                }
                aggregateAnnotations.add(annotation);
            }
        }

        @Override
        public List<Aggregate> finish(@Nullable List<Aggregate> processResult) {
            return this.aggregates;
        }
    }

    private class MergedAnnotationFinder<A extends Annotation>
    implements AnnotationsProcessor<Object, MergedAnnotation<A>> {
        private final Object requiredType;
        @Nullable
        private final Predicate<? super MergedAnnotation<A>> predicate;
        private final MergedAnnotationSelector<A> selector;
        @Nullable
        private MergedAnnotation<A> result;

        MergedAnnotationFinder(@Nullable Object requiredType, @Nullable Predicate<? super MergedAnnotation<A>> predicate, MergedAnnotationSelector<A> selector) {
            this.requiredType = requiredType;
            this.predicate = predicate;
            this.selector = selector != null ? selector : MergedAnnotationSelectors.nearest();
        }

        @Override
        @Nullable
        public MergedAnnotation<A> doWithAggregate(Object context, int aggregateIndex) {
            return this.result;
        }

        @Override
        @Nullable
        public MergedAnnotation<A> doWithAnnotations(Object type, int aggregateIndex, @Nullable Object source, Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                MergedAnnotation<A> result;
                if (annotation == null || TypeMappedAnnotations.this.annotationFilter.matches(annotation) || (result = this.process(type, aggregateIndex, source, annotation)) == null) continue;
                return result;
            }
            return null;
        }

        @Nullable
        private MergedAnnotation<A> process(Object type, int aggregateIndex, @Nullable Object source, Annotation annotation) {
            Annotation[] repeatedAnnotations = TypeMappedAnnotations.this.repeatableContainers.findRepeatedAnnotations(annotation);
            if (repeatedAnnotations != null) {
                return this.doWithAnnotations(type, aggregateIndex, source, repeatedAnnotations);
            }
            AnnotationTypeMappings mappings = AnnotationTypeMappings.forAnnotationType(annotation.annotationType(), TypeMappedAnnotations.this.repeatableContainers, TypeMappedAnnotations.this.annotationFilter);
            for (int i = 0; i < mappings.size(); ++i) {
                TypeMappedAnnotation candidate;
                AnnotationTypeMapping mapping = mappings.get(i);
                if (!TypeMappedAnnotations.isMappingForType(mapping, TypeMappedAnnotations.this.annotationFilter, this.requiredType) || (candidate = TypeMappedAnnotation.createIfPossible(mapping, source, annotation, aggregateIndex, IntrospectionFailureLogger.INFO)) == null || this.predicate != null && !this.predicate.test(candidate)) continue;
                if (this.selector.isBestCandidate(candidate)) {
                    return candidate;
                }
                this.updateLastResult(candidate);
            }
            return null;
        }

        private void updateLastResult(MergedAnnotation<A> candidate) {
            MergedAnnotation<A> lastResult = this.result;
            this.result = lastResult != null ? this.selector.select(lastResult, candidate) : candidate;
        }

        @Override
        @Nullable
        public MergedAnnotation<A> finish(@Nullable MergedAnnotation<A> result) {
            return result != null ? result : this.result;
        }
    }

    private static final class IsPresent
    implements AnnotationsProcessor<Object, Boolean> {
        private static final IsPresent[] SHARED = new IsPresent[4];
        private final RepeatableContainers repeatableContainers;
        private final AnnotationFilter annotationFilter;
        private final boolean directOnly;

        private IsPresent(RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter, boolean directOnly) {
            this.repeatableContainers = repeatableContainers;
            this.annotationFilter = annotationFilter;
            this.directOnly = directOnly;
        }

        @Override
        @Nullable
        public Boolean doWithAnnotations(Object requiredType, int aggregateIndex, @Nullable Object source, Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                Boolean result;
                Class<? extends Annotation> type;
                if (annotation == null || (type = annotation.annotationType()) == null || this.annotationFilter.matches(type)) continue;
                if (type == requiredType || type.getName().equals(requiredType)) {
                    return Boolean.TRUE;
                }
                Annotation[] repeatedAnnotations = this.repeatableContainers.findRepeatedAnnotations(annotation);
                if (repeatedAnnotations != null && (result = this.doWithAnnotations(requiredType, aggregateIndex, source, repeatedAnnotations)) != null) {
                    return result;
                }
                if (this.directOnly) continue;
                AnnotationTypeMappings mappings = AnnotationTypeMappings.forAnnotationType(type);
                for (int i = 0; i < mappings.size(); ++i) {
                    AnnotationTypeMapping mapping = mappings.get(i);
                    if (!TypeMappedAnnotations.isMappingForType(mapping, this.annotationFilter, requiredType)) continue;
                    return Boolean.TRUE;
                }
            }
            return null;
        }

        static IsPresent get(RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter, boolean directOnly) {
            if (annotationFilter == AnnotationFilter.PLAIN) {
                if (repeatableContainers == RepeatableContainers.none()) {
                    return SHARED[directOnly ? 0 : 1];
                }
                if (repeatableContainers == RepeatableContainers.standardRepeatables()) {
                    return SHARED[directOnly ? 2 : 3];
                }
            }
            return new IsPresent(repeatableContainers, annotationFilter, directOnly);
        }

        static {
            IsPresent.SHARED[0] = new IsPresent(RepeatableContainers.none(), AnnotationFilter.PLAIN, true);
            IsPresent.SHARED[1] = new IsPresent(RepeatableContainers.none(), AnnotationFilter.PLAIN, false);
            IsPresent.SHARED[2] = new IsPresent(RepeatableContainers.standardRepeatables(), AnnotationFilter.PLAIN, true);
            IsPresent.SHARED[3] = new IsPresent(RepeatableContainers.standardRepeatables(), AnnotationFilter.PLAIN, false);
        }
    }
}

