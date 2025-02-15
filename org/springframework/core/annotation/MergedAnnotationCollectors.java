/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class MergedAnnotationCollectors {
    private static final Collector.Characteristics[] NO_CHARACTERISTICS = new Collector.Characteristics[0];
    private static final Collector.Characteristics[] IDENTITY_FINISH_CHARACTERISTICS = new Collector.Characteristics[]{Collector.Characteristics.IDENTITY_FINISH};

    private MergedAnnotationCollectors() {
    }

    public static <A extends Annotation> Collector<MergedAnnotation<A>, ?, Set<A>> toAnnotationSet() {
        return Collector.of(LinkedHashSet::new, (set, annotation) -> set.add(annotation.synthesize()), MergedAnnotationCollectors::combiner, new Collector.Characteristics[0]);
    }

    public static <A extends Annotation> Collector<MergedAnnotation<A>, ?, Annotation[]> toAnnotationArray() {
        return MergedAnnotationCollectors.toAnnotationArray(Annotation[]::new);
    }

    public static <R extends Annotation, A extends R> Collector<MergedAnnotation<A>, ?, R[]> toAnnotationArray(IntFunction<R[]> generator) {
        return Collector.of(ArrayList::new, (list, annotation) -> list.add(annotation.synthesize()), MergedAnnotationCollectors::combiner, list -> (Annotation[])list.toArray((Object[])generator.apply(list.size())), new Collector.Characteristics[0]);
    }

    public static <A extends Annotation> Collector<MergedAnnotation<A>, ?, MultiValueMap<String, Object>> toMultiValueMap(MergedAnnotation.Adapt ... adaptations) {
        return MergedAnnotationCollectors.toMultiValueMap(Function.identity(), adaptations);
    }

    public static <A extends Annotation> Collector<MergedAnnotation<A>, ?, MultiValueMap<String, Object>> toMultiValueMap(Function<MultiValueMap<String, Object>, MultiValueMap<String, Object>> finisher, MergedAnnotation.Adapt ... adaptations) {
        Collector.Characteristics[] characteristics = MergedAnnotationCollectors.isSameInstance(finisher, Function.identity()) ? IDENTITY_FINISH_CHARACTERISTICS : NO_CHARACTERISTICS;
        return Collector.of(LinkedMultiValueMap::new, (map, annotation) -> annotation.asMap(adaptations).forEach(map::add), MergedAnnotationCollectors::combiner, finisher, characteristics);
    }

    private static boolean isSameInstance(Object instance, Object candidate) {
        return instance == candidate;
    }

    private static <E, C extends Collection<E>> C combiner(C collection, C additions) {
        collection.addAll(additions);
        return collection;
    }

    private static <K, V> MultiValueMap<K, V> combiner(MultiValueMap<K, V> map, MultiValueMap<K, V> additions) {
        map.addAll(additions);
        return map;
    }
}

