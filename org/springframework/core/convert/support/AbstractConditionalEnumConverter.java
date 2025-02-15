/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.util.ClassUtils;

abstract class AbstractConditionalEnumConverter
implements ConditionalConverter {
    private final ConversionService conversionService;

    protected AbstractConditionalEnumConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        for (Class<?> interfaceType : ClassUtils.getAllInterfacesForClassAsSet(sourceType.getType())) {
            if (!this.conversionService.canConvert(TypeDescriptor.valueOf(interfaceType), targetType)) continue;
            return false;
        }
        return true;
    }
}

