/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationSelector;
import org.springframework.core.annotation.MergedAnnotationsCollection;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.annotation.TypeMappedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public interface MergedAnnotations
extends Iterable<MergedAnnotation<Annotation>> {
    public <A extends Annotation> boolean isPresent(Class<A> var1);

    public boolean isPresent(String var1);

    public <A extends Annotation> boolean isDirectlyPresent(Class<A> var1);

    public boolean isDirectlyPresent(String var1);

    public <A extends Annotation> MergedAnnotation<A> get(Class<A> var1);

    public <A extends Annotation> MergedAnnotation<A> get(Class<A> var1, @Nullable Predicate<? super MergedAnnotation<A>> var2);

    public <A extends Annotation> MergedAnnotation<A> get(Class<A> var1, @Nullable Predicate<? super MergedAnnotation<A>> var2, @Nullable MergedAnnotationSelector<A> var3);

    public <A extends Annotation> MergedAnnotation<A> get(String var1);

    public <A extends Annotation> MergedAnnotation<A> get(String var1, @Nullable Predicate<? super MergedAnnotation<A>> var2);

    public <A extends Annotation> MergedAnnotation<A> get(String var1, @Nullable Predicate<? super MergedAnnotation<A>> var2, @Nullable MergedAnnotationSelector<A> var3);

    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(Class<A> var1);

    public <A extends Annotation> Stream<MergedAnnotation<A>> stream(String var1);

    public Stream<MergedAnnotation<Annotation>> stream();

    public static MergedAnnotations from(AnnotatedElement element) {
        return MergedAnnotations.from(element, SearchStrategy.DIRECT);
    }

    public static MergedAnnotations from(AnnotatedElement element, SearchStrategy searchStrategy) {
        return MergedAnnotations.from(element, searchStrategy, RepeatableContainers.standardRepeatables());
    }

    public static MergedAnnotations from(AnnotatedElement element, SearchStrategy searchStrategy, RepeatableContainers repeatableContainers) {
        return MergedAnnotations.from(element, searchStrategy, repeatableContainers, AnnotationFilter.PLAIN);
    }

    public static MergedAnnotations from(AnnotatedElement element, SearchStrategy searchStrategy, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        Assert.notNull((Object)repeatableContainers, "RepeatableContainers must not be null");
        Assert.notNull((Object)annotationFilter, "AnnotationFilter must not be null");
        return TypeMappedAnnotations.from(element, searchStrategy, repeatableContainers, annotationFilter);
    }

    public static MergedAnnotations from(Annotation ... annotations) {
        return MergedAnnotations.from((Object)annotations, annotations);
    }

    public static MergedAnnotations from(Object source, Annotation ... annotations) {
        return MergedAnnotations.from(source, annotations, RepeatableContainers.standardRepeatables());
    }

    public static MergedAnnotations from(Object source, Annotation[] annotations, RepeatableContainers repeatableContainers) {
        return MergedAnnotations.from(source, annotations, repeatableContainers, AnnotationFilter.PLAIN);
    }

    public static MergedAnnotations from(Object source, Annotation[] annotations, RepeatableContainers repeatableContainers, AnnotationFilter annotationFilter) {
        Assert.notNull((Object)repeatableContainers, "RepeatableContainers must not be null");
        Assert.notNull((Object)annotationFilter, "AnnotationFilter must not be null");
        return TypeMappedAnnotations.from(source, annotations, repeatableContainers, annotationFilter);
    }

    public static MergedAnnotations of(Collection<MergedAnnotation<?>> annotations) {
        return MergedAnnotationsCollection.of(annotations);
    }

    public static enum SearchStrategy {
        DIRECT,
        INHERITED_ANNOTATIONS,
        SUPERCLASS,
        TYPE_HIERARCHY,
        TYPE_HIERARCHY_AND_ENCLOSING_CLASSES;

    }
}

