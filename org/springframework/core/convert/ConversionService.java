/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public interface ConversionService {
    public boolean canConvert(@Nullable Class<?> var1, Class<?> var2);

    public boolean canConvert(@Nullable TypeDescriptor var1, TypeDescriptor var2);

    @Nullable
    public <T> T convert(@Nullable Object var1, Class<T> var2);

    @Nullable
    public Object convert(@Nullable Object var1, @Nullable TypeDescriptor var2, TypeDescriptor var3);
}

