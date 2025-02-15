/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

public final class ConversionServiceFactory {
    private ConversionServiceFactory() {
    }

    public static void registerConverters(@Nullable Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter)converter);
                    continue;
                }
                if (converter instanceof Converter) {
                    registry.addConverter((Converter)converter);
                    continue;
                }
                if (converter instanceof ConverterFactory) {
                    registry.addConverterFactory((ConverterFactory)converter);
                    continue;
                }
                throw new IllegalArgumentException("Each converter object must implement one of the Converter, ConverterFactory, or GenericConverter interfaces");
            }
        }
    }
}

