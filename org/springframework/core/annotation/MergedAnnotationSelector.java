/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import org.springframework.core.annotation.MergedAnnotation;

@FunctionalInterface
public interface MergedAnnotationSelector<A extends Annotation> {
    default public boolean isBestCandidate(MergedAnnotation<A> annotation) {
        return false;
    }

    public MergedAnnotation<A> select(MergedAnnotation<A> var1, MergedAnnotation<A> var2);
}

