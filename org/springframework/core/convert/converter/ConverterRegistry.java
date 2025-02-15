/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;

public interface ConverterRegistry {
    public void addConverter(Converter<?, ?> var1);

    public <S, T> void addConverter(Class<S> var1, Class<T> var2, Converter<? super S, ? extends T> var3);

    public void addConverter(GenericConverter var1);

    public void addConverterFactory(ConverterFactory<?, ?> var1);

    public void removeConvertible(Class<?> var1, Class<?> var2);
}

