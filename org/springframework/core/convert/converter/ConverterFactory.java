/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.converter;

import org.springframework.core.convert.converter.Converter;

public interface ConverterFactory<S, R> {
    public <T extends R> Converter<S, T> getConverter(Class<T> var1);
}

