/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConversionUtils;

final class IntegerToEnumConverterFactory
implements ConverterFactory<Integer, Enum> {
    IntegerToEnumConverterFactory() {
    }

    @Override
    public <T extends Enum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum(ConversionUtils.getEnumType(targetType));
    }

    private static class IntegerToEnum<T extends Enum>
    implements Converter<Integer, T> {
        private final Class<T> enumType;

        public IntegerToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer source) {
            return (T)((Enum[])this.enumType.getEnumConstants())[source];
        }
    }
}

