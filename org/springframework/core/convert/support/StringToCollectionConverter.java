/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

final class StringToCollectionConverter
implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public StringToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(String.class, Collection.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.getElementTypeDescriptor() == null || this.conversionService.canConvert(sourceType, targetType.getElementTypeDescriptor());
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        String string = (String)source;
        String[] fields = StringUtils.commaDelimitedListToStringArray(string);
        TypeDescriptor elementDesc = targetType.getElementTypeDescriptor();
        Collection<Object> target = CollectionFactory.createCollection(targetType.getType(), elementDesc != null ? elementDesc.getType() : null, fields.length);
        if (elementDesc == null) {
            for (String field : fields) {
                target.add(field.trim());
            }
        } else {
            for (String field : fields) {
                Object targetElement = this.conversionService.convert(field.trim(), sourceType, elementDesc);
                target.add(targetElement);
            }
        }
        return target;
    }
}

