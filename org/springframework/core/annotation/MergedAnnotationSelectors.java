/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationSelector;

public abstract class MergedAnnotationSelectors {
    private static final MergedAnnotationSelector<?> NEAREST = new Nearest();
    private static final MergedAnnotationSelector<?> FIRST_DIRECTLY_DECLARED = new FirstDirectlyDeclared();

    private MergedAnnotationSelectors() {
    }

    public static <A extends Annotation> MergedAnnotationSelector<A> nearest() {
        return NEAREST;
    }

    public static <A extends Annotation> MergedAnnotationSelector<A> firstDirectlyDeclared() {
        return FIRST_DIRECTLY_DECLARED;
    }

    private static class FirstDirectlyDeclared
    implements MergedAnnotationSelector<Annotation> {
        private FirstDirectlyDeclared() {
        }

        @Override
        public boolean isBestCandidate(MergedAnnotation<Annotation> annotation) {
            return annotation.getDistance() == 0;
        }

        @Override
        public MergedAnnotation<Annotation> select(MergedAnnotation<Annotation> existing, MergedAnnotation<Annotation> candidate) {
            if (existing.getDistance() > 0 && candidate.getDistance() == 0) {
                return candidate;
            }
            return existing;
        }
    }

    private static class Nearest
    implements MergedAnnotationSelector<Annotation> {
        private Nearest() {
        }

        @Override
        public boolean isBestCandidate(MergedAnnotation<Annotation> annotation) {
            return annotation.getDistance() == 0;
        }

        @Override
        public MergedAnnotation<Annotation> select(MergedAnnotation<Annotation> existing, MergedAnnotation<Annotation> candidate) {
            if (candidate.getDistance() < existing.getDistance()) {
                return candidate;
            }
            return existing;
        }
    }
}

