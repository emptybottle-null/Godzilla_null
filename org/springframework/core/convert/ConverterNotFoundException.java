/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert;

import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public class ConverterNotFoundException
extends ConversionException {
    @Nullable
    private final TypeDescriptor sourceType;
    private final TypeDescriptor targetType;

    public ConverterNotFoundException(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        super("No converter found capable of converting from type [" + sourceType + "] to type [" + targetType + "]");
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    @Nullable
    public TypeDescriptor getSourceType() {
        return this.sourceType;
    }

    public TypeDescriptor getTargetType() {
        return this.targetType;
    }
}

