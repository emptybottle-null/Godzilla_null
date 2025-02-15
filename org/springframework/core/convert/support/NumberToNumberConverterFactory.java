/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

final class NumberToNumberConverterFactory
implements ConverterFactory<Number, Number>,
ConditionalConverter {
    NumberToNumberConverterFactory() {
    }

    @Override
    public <T extends Number> Converter<Number, T> getConverter(Class<T> targetType) {
        return new NumberToNumber<T>(targetType);
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return !sourceType.equals(targetType);
    }

    private static final class NumberToNumber<T extends Number>
    implements Converter<Number, T> {
        private final Class<T> targetType;

        NumberToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(Number source) {
            return NumberUtils.convertNumberToTargetClass(source, this.targetType);
        }
    }
}

