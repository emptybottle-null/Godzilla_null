/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import org.springframework.lang.Nullable;

@FunctionalInterface
interface AnnotationsProcessor<C, R> {
    @Nullable
    default public R doWithAggregate(C context, int aggregateIndex) {
        return null;
    }

    @Nullable
    public R doWithAnnotations(C var1, int var2, @Nullable Object var3, Annotation[] var4);

    @Nullable
    default public R finish(@Nullable R result) {
        return result;
    }
}

