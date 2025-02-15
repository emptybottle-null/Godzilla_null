/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

final class CollectionToArrayConverter
implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public CollectionToArrayConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Collection.class, Object[].class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getElementTypeDescriptor(), targetType.getElementTypeDescriptor(), this.conversionService);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        Collection sourceCollection = (Collection)source;
        TypeDescriptor targetElementType = targetType.getElementTypeDescriptor();
        Assert.state(targetElementType != null, "No target element type");
        Object array = Array.newInstance(targetElementType.getType(), sourceCollection.size());
        int i = 0;
        for (Object sourceElement : sourceCollection) {
            Object targetElement = this.conversionService.convert(sourceElement, sourceType.elementTypeDescriptor(sourceElement), targetElementType);
            Array.set(array, i++, targetElement);
        }
        return array;
    }
}

