/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.converter;

import java.util.Comparator;
import java.util.Map;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.comparator.Comparators;

public class ConvertingComparator<S, T>
implements Comparator<S> {
    private final Comparator<T> comparator;
    private final Converter<S, T> converter;

    public ConvertingComparator(Converter<S, T> converter) {
        this(Comparators.comparable(), converter);
    }

    public ConvertingComparator(Comparator<T> comparator, Converter<S, T> converter) {
        Assert.notNull(comparator, "Comparator must not be null");
        Assert.notNull(converter, "Converter must not be null");
        this.comparator = comparator;
        this.converter = converter;
    }

    public ConvertingComparator(Comparator<T> comparator, ConversionService conversionService, Class<? extends T> targetType) {
        this(comparator, new ConversionServiceConverter(conversionService, targetType));
    }

    @Override
    public int compare(S o1, S o2) {
        T c1 = this.converter.convert(o1);
        T c2 = this.converter.convert(o2);
        return this.comparator.compare(c1, c2);
    }

    public static <K, V> ConvertingComparator<Map.Entry<K, V>, K> mapEntryKeys(Comparator<K> comparator) {
        return new ConvertingComparator<Map.Entry, Object>(comparator, Map.Entry::getKey);
    }

    public static <K, V> ConvertingComparator<Map.Entry<K, V>, V> mapEntryValues(Comparator<V> comparator) {
        return new ConvertingComparator<Map.Entry, Object>(comparator, Map.Entry::getValue);
    }

    private static class ConversionServiceConverter<S, T>
    implements Converter<S, T> {
        private final ConversionService conversionService;
        private final Class<? extends T> targetType;

        public ConversionServiceConverter(ConversionService conversionService, Class<? extends T> targetType) {
            Assert.notNull((Object)conversionService, "ConversionService must not be null");
            Assert.notNull(targetType, "TargetType must not be null");
            this.conversionService = conversionService;
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(S source) {
            return this.conversionService.convert(source, this.targetType);
        }
    }
}

