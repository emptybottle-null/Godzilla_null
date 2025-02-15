/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public interface TypeConverter {
    public boolean canConvert(@Nullable TypeDescriptor var1, TypeDescriptor var2);

    @Nullable
    public Object convertValue(@Nullable Object var1, @Nullable TypeDescriptor var2, TypeDescriptor var3);
}

