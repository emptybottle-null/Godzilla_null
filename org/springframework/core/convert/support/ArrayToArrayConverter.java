/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.CollectionToArrayConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

final class ArrayToArrayConverter
implements ConditionalGenericConverter {
    private final CollectionToArrayConverter helperConverter;
    private final ConversionService conversionService;

    public ArrayToArrayConverter(ConversionService conversionService) {
        this.helperConverter = new CollectionToArrayConverter(conversionService);
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object[].class, Object[].class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return this.helperConverter.matches(sourceType, targetType);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        TypeDescriptor targetElement;
        if (this.conversionService instanceof GenericConversionService && (targetElement = targetType.getElementTypeDescriptor()) != null && ((GenericConversionService)this.conversionService).canBypassConvert(sourceType.getElementTypeDescriptor(), targetElement)) {
            return source;
        }
        List<Object> sourceList = Arrays.asList(ObjectUtils.toObjectArray(source));
        return this.helperConverter.convert(sourceList, sourceType, targetType);
    }
}

