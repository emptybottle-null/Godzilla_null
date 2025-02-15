/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.StringJoiner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;

final class CollectionToStringConverter
implements ConditionalGenericConverter {
    private static final String DELIMITER = ",";
    private final ConversionService conversionService;

    public CollectionToStringConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Collection.class, String.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getElementTypeDescriptor(), targetType, this.conversionService);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        Collection sourceCollection = (Collection)source;
        if (sourceCollection.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(DELIMITER);
        for (Object sourceElement : sourceCollection) {
            Object targetElement = this.conversionService.convert(sourceElement, sourceType.elementTypeDescriptor(sourceElement), targetType);
            sj.add(String.valueOf(targetElement));
        }
        return sj.toString();
    }
}

