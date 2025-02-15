/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.converter;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@FunctionalInterface
public interface Converter<S, T> {
    @Nullable
    public T convert(S var1);

    default public <U> Converter<S, U> andThen(Converter<? super T, ? extends U> after) {
        Assert.notNull(after, "After Converter must not be null");
        return s -> {
            T initialResult = this.convert(s);
            return initialResult != null ? after.convert((T)initialResult) : null;
        };
    }
}

