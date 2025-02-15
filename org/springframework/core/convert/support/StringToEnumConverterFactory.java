/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;

final class StringToEnumConverterFactory
implements ConverterFactory<String, Enum> {
    StringToEnumConverterFactory() {
    }

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(ConversionUtils.getEnumType(targetType));
    }

    private static class StringToEnum<T extends Enum>
    implements Converter<String, T> {
        private final Class<T> enumType;

        StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return Enum.valueOf(this.enumType, source.trim());
        }
    }
}

